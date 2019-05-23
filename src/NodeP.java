
public class NodeP {
	
	private double piority;
	private String docName;
	private int[] array;
	private int docElements;
	
	public NodeP(double piority,String docName,int[] intArray){
		this.piority = piority;
		this.docName = docName;
		array = intArray;
		docElements = 0;
	}

	

	public double getPiority() {
		return piority;
	}

	public void setPiority(double piority) {
		this.piority = piority;
	}



	public String getDocName() {
		return docName;
	}



	public void setDocName(String docName) {
		this.docName = docName;
	}



	public int[] getArray() {
		return array;
	}



	public void setArray(int[] array) {
		this.array = array;
	}



	public int getDocElements() {
		return docElements;
	}



	public void setDocElements(int docElements) {
		this.docElements = docElements;
	}
	
	

}
