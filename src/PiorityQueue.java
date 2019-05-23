
public class PiorityQueue {
	
	private NodeP[] elements;
	private int rear,front;
	private int elementNumber;
	
	public PiorityQueue(int capacity){
		elements =  new NodeP[capacity];
		rear = -1;
		front = 0;
		elementNumber = 0;
	}
	
	public void enqueue(double piority,String docName,int[] intArray,int element){
		
		if(!isEmpty()){
			
			boolean isFilled = false;
			
			for (int i = 0; i < size(); i++) {
				
			if(elements[i].getPiority() < piority && piority != 0){
				for (int j = elements.length-2; j >= i ; j--) {
					elements[j+1] = elements[j];
				}
				
				NodeP newnode = new NodeP(piority,docName,intArray);
				newnode.setDocElements(element);
				elements[i] = newnode;
				isFilled = true;
				if(!isFull()){
					rear = (rear + 1)%elements.length;
					elementNumber++;
				}
				break;
			}
			
			}
			
			if(!isFilled && !isFull()){
				NodeP newnode = new NodeP(piority,docName,intArray);
				newnode.setDocElements(element);
				rear = (rear + 1)%elements.length;
				elements[rear] = newnode;
				elementNumber++;
			}
		}
		else{
			NodeP newnode = new NodeP(piority,docName,intArray);
			newnode.setDocElements(element);
			rear = (rear + 1)%elements.length;
			elements[rear] = newnode;
			elementNumber++;
		}
		
		
	}
	
	public boolean isEmpty(){
		return elementNumber == 0;
	}
	
	public boolean isFull(){
		return elementNumber == elements.length;
	}
	
	public int size(){
		return elementNumber;
	}
	
	public void display(){
		
		int x = 1;
		for (int i = 0; i < elements.length; i++){
			System.out.print(x +". " + elements[i].getDocName()+" piority: "+ elements[i].getPiority() + "  ( ");
			for (int j = 0; j < elements[i].getArray().length; j++) {
				if (elements[i].getArray()[j] == -1) {
					System.out.print("- ");
				}
				else {

					System.out.print(elements[i].getArray()[j] + " ");
				}
			}
			System.out.println(") / " + elements[i].getDocElements());
			x++;
		}
	}

}
