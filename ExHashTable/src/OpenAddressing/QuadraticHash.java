package OpenAddressing;

public class QuadraticHash {
	private String[] keys;
	private String[] values;
	private  int currentSize, maxSize;
	public QuadraticHash(int capacity) {
		currentSize = 0;
		maxSize = capacity;
		keys = new String[maxSize];
		values = new String[maxSize];
	}
	public int getSize() {
		return currentSize;
	}
	public boolean isEmpty() {
		return getSize() == 0;
	}
	public void makeEmpty() {
		currentSize = 0;
		keys = new String[maxSize];
		values = new String[maxSize];
	}
	public boolean isFull() {
		return currentSize == maxSize;
	}
	private int hash(String key) {
		return key.hashCode()%maxSize;
	}
	public String get(String key) {
		int i = hash(key), h = 1;
		while(keys[i] != null) {
			if(keys[i].equals(key))
				return values[i];
			i = (i + h*h++)&maxSize;
			System.out.println("i = " + i);
		}
		return null;
	}
	public boolean contains(String key) {
		return get(key) != null;
	}
	public void insert(String key, String val) {
		int tmp = hash(key);
		int i = tmp, h = 1;
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
	public void remove(String key) {
		if(!contains(key))
			return;
		int i = hash(key), h = 1;
		while(!key.equals(keys[i])) {
			i = (i + h*h++)%maxSize;
		}
		keys[i] = values[i] = null;
		for(i = (i + h*h++)%maxSize; keys[i] != null; i= (i+h*h++)%maxSize) {
			String tmpkey = keys[i], tmpval = values[i];
			keys[i] = values[i] = null;
			currentSize--;
			insert(tmpkey, tmpval);
		}
		currentSize--;
	}
	public void printHash() {
		System.out.println("\nHash Table: ");
		for(int i = 0; i < maxSize; i++) {
			if(keys[i] != null)
				System.out.println(keys[i] + " " + values[i]);
		}
		System.out.println("");
	}
}
