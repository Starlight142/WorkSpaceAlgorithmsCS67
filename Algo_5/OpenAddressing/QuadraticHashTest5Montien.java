
public class QuadraticHashTest5Montien {
	private String[] keys;
	private String[] values;
	private int currentSize, maxSize;
	public QuadraticHashTest5Montien(int capacity) {	 // Set value size to default(0)
		currentSize = 0;					// current size value is 0
		maxSize = capacity;					// set maxSize = capacity for use in keys and values
		keys = new String[maxSize];
		values = new String[maxSize];
	}
	public int getSize() {					// use for get current size and return to show in terminal
		return currentSize;
	}
	public boolean isEmpty() {				// use for check size is empty or not 
		return getSize() == 0;
	}
	public void makeEmpty() {				// use for clear hash table by set current size to 0
		currentSize = 0;					// and set new to maxsize 
		keys = new String[maxSize];
		values = new String[maxSize];
	}
	public boolean isFull() {				// use for check if size is full by current size == max size 
		return currentSize == maxSize;
	}
	private int hash(String key) {			
		return key.hashCode() % maxSize;
	}
	public String get(String key) {			// use for request hash and val by enter key you using 
		int i = hash(key), h = 1;			
		while(keys[i] != null) {			// use "while" for check if keys is null 
			if(keys[i].equals(key))			// if have keys it will return values and show hash inside 
				return values[i];
			i = (i + h*h++)%maxSize;
			System.out.println("i = " + i);	
		}
		return null;
	}
	public boolean contains(String key) {	//for check if key is null or not 
		return get(key) != null;
	}
	public void insert(String key, String val) { 	// use for insert hash table
		int tmp = hash(key);						// set tmp = hash(key)
		int i = tmp, h = 1;							// set i = tmp and h = 1
		do { 
			if(keys[i] == null) { 
				keys[i] = key;
				values[i] = val;
				currentSize++;
				return;
			}
			if(keys[i].equals(key)) {
				values[i] = val;
				return;
			}
			i = (i + h*h++)%maxSize;
		}while(i != tmp);
	}
	public void remove(String key) {		// use for remove hash table and values
		if(!contains(key))
			return;
		int i = hash(key), h = 1;
		while(!key.equals(keys[i])) {
			i = (i + h*h++)%maxSize;
		}
		keys[i] = values[i] = null;
		for(i = (i + h*h++)%maxSize; keys[i] != null; i = (i + h*h++)%maxSize) {
			String tmpKey = keys[i], tmpVal = values[i];
			keys[i] = values[i] = null;
			currentSize--;
			insert(tmpKey, tmpVal);
		}
		currentSize--;
	}
	public void printHash() {				// this pubilc use to show what's keys and values in hash table 
		System.out.println("\nHash Table: "); 	
		for(int i = 0; i < maxSize; i++) {			// by "for" use to check if i = 0 or < maxsize 
			if(keys[i] != null)						// if keys is null force to show nothing 
				System.out.println(keys[i] + " " + values[i]); // if keys isn't null showing keys and values
		}
		System.out.println();
	}
}













