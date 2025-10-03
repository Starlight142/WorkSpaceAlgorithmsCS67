#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include "DHT.h"

// ---------- CONFIG ----------
#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64
#define OLED_RESET    -1
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RESET);

#define DHTPIN 15        // DHT22 data pin
#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

#define MQ_PIN 34        // ADC pin for MQ sensor (use ADC1 pins for stable readings)
#define FAN_PIN 26       // digital output to control relay/MOSFET (optional)

const int NUM_SAMPLES = 8;    // smoothing samples

// ---------- Thresholds (เริ่มต้น) ----------
// ค่าพวกนี้เป็นค่า ADC ของ ESP32 (0..4095). ปรับหลังคาลิเบรต
int THRESHOLD_GOOD = 700;     // <= -> Good
int THRESHOLD_MODERATE = 1800; // <= -> Moderate, > -> Poor

// ---------- Helpers ----------
int mqSamples[NUM_SAMPLES];
int sampleIndex = 0;

void setup() {
  Serial.begin(115200);
  pinMode(FAN_PIN, OUTPUT);
  digitalWrite(FAN_PIN, LOW);

  dht.begin();

  if(!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) {
    Serial.println("SSD1306 allocation failed");
    for(;;);
  }
  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(SSD1306_WHITE);

  // initialize sample buffer
  for(int i=0;i<NUM_SAMPLES;i++) mqSamples[i] = 0;

  Serial.println("Smart Vent ready");
}

int readMQavg(){
  // read ADC and keep moving average
  int raw = analogRead(MQ_PIN); // 0..4095
  mqSamples[sampleIndex] = raw;
  sampleIndex = (sampleIndex + 1) % NUM_SAMPLES;
  long sum = 0;
  for(int i=0;i<NUM_SAMPLES;i++) sum += mqSamples[i];
  return (int)(sum / NUM_SAMPLES);
}

void showOLED(const char* line1, const char* line2, int mqValue, float temp, float hum) {
  display.clearDisplay();
  display.setTextSize(1);
  display.setCursor(0,0);
  display.printf("Air: %s\n", line1);
  display.printf("Suggest: %s\n", line2);
  display.printf("MQ: %d\n", mqValue);
  display.printf("T: %.1fC  H: %.1f%%\n", temp, hum);
  display.display();
}

void loop() {
  // read sensors
  int mq = readMQavg();
  float hum = dht.readHumidity();
  float temp = dht.readTemperature();

  // check DHT read OK
  if(isnan(hum) || isnan(temp)){
    Serial.println("Failed to read from DHT sensor!");
    hum = 0; temp = 0;
  }

  // determine quality
  const char* status;
  const char* advise;

  if(mq <= THRESHOLD_GOOD) {
    status = "GOOD";
    advise = "No action";
    digitalWrite(FAN_PIN, LOW);
  } else if(mq <= THRESHOLD_MODERATE) {
    status = "MODERATE";
    advise = "Open window / Fan low";
    digitalWrite(FAN_PIN, LOW);
  } else {
    status = "POOR";
    advise = "Open window + Turn fan ON";
    digitalWrite(FAN_PIN, HIGH); // สั่งพัดลม
  }

  // show on OLED
  showOLED(status, advise, mq, temp, hum);

  // debug
  Serial.printf("MQ: %d  Status: %s  T: %.1f  H: %.1f\n", mq, status, temp, hum);

  delay(2000);
}