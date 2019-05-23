

public class Node {
	
	private int key;
	private String value;
	private int[] documents;
	private Node next;
	private int collision;
	private int total;
	
	public Node(int key,String value){  //Kelime Node
		
		this.key = key;
		this.value = value;
		collision = 0;
		total = 0;
		
		documents = new int[670];
		
	}
	

	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int[] getDocuments() {
		return documents;
	}

	public void setDocuments(int[] documents) {
		this.documents = documents;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getCollision() {
		return collision;
	}

	public void setCollision(int collision) {
		this.collision = collision;
	}

	
	
	
	
}
