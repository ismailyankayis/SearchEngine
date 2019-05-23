import java.util.Locale;

public class HT {

	private Node[] elements;
	public static int[] documentWords; // dosyalarýn kaçar kelime içerdiðini yazar.
	private int HTsize;
	private int numberElement;
	private int h2PrimeNumber;
	private int max;
	private int maxCol = 0;
	static int Unique = 0;
	private int[] colArray;
	static int rehashingCount = 0;


	public HT (int size) {

		if (Reading.which == 1 || Reading.which == 3 || Reading.which == 4) {

			HTsize = size; // 319991
			elements = new Node[HTsize]; 
			numberElement = 0;
			prime();
			max = 0;
			colArray = new int[200];
		}
		else if (Reading.which == 2) {

			HTsize = size;
			elements = new Node[HTsize];
			numberElement = 0;
			max = 0;
		}
		documentWords = new int[670];
	}



	public int[] getColArray() {
		return colArray;
	}
	public void setColArray(int[] colArray) {
		this.colArray = colArray;
	}





	public void insert2(String word,int dosya) { //Chaining


		int key = keyCode2(word);

		if(elements[key] != null){

			Node temp = elements[key];
			boolean flag = false;
			int x = 0;
			while(temp.getNext() != null){

				if(temp.getValue().equalsIgnoreCase(word)){
					temp.getDocuments()[dosya]++;
					flag = true;
					break;
				}
				temp = temp.getNext();
				if(x > max) max = x;
				x++;
			}
			if(x>1) maxCol++;

			if(!flag){

				if(temp.getValue().equalsIgnoreCase(word)){
					temp.getDocuments()[dosya]++;
					flag = true;
				}
				else{
					Node newnode = new Node(key,word.toLowerCase(Locale.ENGLISH));
					newnode.getDocuments()[dosya] = 1;
					temp.setNext(newnode);
				}
			}


		}
		else{
			Node newnode = new Node(key,word.toLowerCase(Locale.ENGLISH));
			newnode.getDocuments()[dosya] = 1;
			elements[key] = newnode;
			Unique++;

		}

		documentWords[dosya]++;

	}



	public void insert1(String word,int dosya){ // Dooooouble ! Linear and Quadratic

		int k = keyCode1(word);
		int x = 0;
		boolean isFilled = false;

		do{
			int key = 0;
			if(Reading.which == 1){
				key = h(k,x);
			}
			else if(Reading.which == 3){ // Linear
				key = (k + x) % HTsize;
			}
			else if(Reading.which == 4){ // Quadratic
				key = (k + x + x*x) % HTsize;
			}
			

			if(elements[key] == null){ // Bomboþ bir alan varsa, doldur anam.

				Node newnode = new Node(k,word.toLowerCase(Locale.ENGLISH));
				newnode.getDocuments()[dosya] = 1;
				elements[key] = newnode;
				elements[key].setTotal(0);
				isFilled = true;
				numberElement++;
				Unique++;
				break;
			}
			else if(elements[key].getValue().equals(word.toLowerCase(Locale.ENGLISH))){ // Gelen kelime ve elements[key] eþitse.
				elements[key].setCollision(x);
				elements[key].getDocuments()[dosya]++;
				elements[key].setTotal(elements[key].getTotal() + 1);
				isFilled = true;
				break;
			}
			if(max <= x) max = x;
			x++;
			if (x > 1) {

				maxCol++;
			}
		}while(!isFilled);
		documentWords[dosya]++;

		colArray[x]++;

		if((double)numberElement/HTsize >= 0.35){

			rehashingCount++;
			double a = System.currentTimeMillis();
			reHash();
			double b = System.currentTimeMillis();
			//			System.out.println(rehashingCount + ". Rehashing time: " + ((b-a)/1000) + "sec");
		}

	}


	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public void reHash(){


		max = 0;
		maxCol = 0;
		HTsize = HTsize*2;
		prime();
		Node[] temp = elements;
		elements = new Node[HTsize];
		for (int i = 0; i < colArray.length; i++) {
			colArray[i] = 0;
		}

		for (int i = 0; i < temp.length; i++) {

			if (temp[i] != null) {
				int k = keyCode1(temp[i].getValue());
				int x = 0;
				int key = 0;
				if(Reading.which == 1){
					key = h(k,x);
				}
				else if(Reading.which == 3){
					key = (k + x) % HTsize;
				}
				else if(Reading.which == 4){
					key = (k + x + x*x) % HTsize;
				}

				while (elements[key] != null) {

					if(max <= x) max = x;
					x++;
					if(Reading.which == 1){
						key = h(k,x);
					}
					else if(Reading.which == 3){
						key = (k + x) % HTsize;
					}
					else if(Reading.which == 4){
						key = (k + x + x*x) % HTsize;
					}

					maxCol += x * temp[i].getTotal();
				}

				if (x < 1) {
					colArray[x] += temp[i].getTotal();
				}
				else {
					colArray[x] += (temp[i].getTotal() * x);
				}


				elements[key] = temp[i];
			}
		}

		/*
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			HTsize = HTsize*2;
			Node[] temp = new Node[HTsize];
			prime(); //daha düzenlenecek...

			for (int i = 0; i < elements.length; i++) {
				if(elements[i] != null){

					int k = keyCode(elements[i].getValue());

					int x = 0;
					boolean isFilled = false;
					do{
						int key = h(k,x);

						if(temp[key] == null){
							Node newnode = new Node(key,elements[i].getValue());
							newnode.setCollision(x);
							newnode.setDocuments(elements[i].getDocuments());

							temp[key] = newnode;
							isFilled = true;
						}
						x++;
					}while(!isFilled);

				}
			}
			elements = null;
			elements = temp;
		 */

	}

	public int h2(int k){
		return 1+(k*139%h2PrimeNumber);
	}

	public int h(int k,int i){
		return (k + i+h2(k))%HTsize;
	}


	public int keyCode1(String word){ // double

		word = word.toLowerCase(Locale.ENGLISH);
		int x = 0;
		for (int i = 0; i < word.length(); i++) {

			x+=((int)word.charAt(i)+1049*x)%h2PrimeNumber;
		}

		return x%HTsize;
	}

	public int keyCode2(String word){

		word = word.toLowerCase(Locale.ENGLISH);
		int x = 0;
		for (int i = 0; i < word.length(); i++) {

			x+=((int)word.charAt(i)+1049*x) % 40009;
		}

		return x % HTsize;
	}

	public void prime(){

		for (int i = HTsize+1;true ; i++) {
			boolean flag = false;
			for (int j = 2; j < i/2; j++) {
				if(i%j == 0){
					flag = true;
					break;
				}
			}
			if(!flag){
				h2PrimeNumber = i;
				break;
			}
		}
	}

	public Node search(String word){ // Node döndürem lazým*** // DOUBLE HASHING.

		int k = keyCode1(word);
		boolean isFilled = false;
		int x = 0;
		Node temp = null;
		do{
			int key = 0;
			if(Reading.which == 1){
				key = h(k,x);
			}
			else if(Reading.which == 3){
				key =(k + x) % HTsize;
			}
			else if(Reading.which == 4){
				key = (k + x + x*x) % HTsize;
			}
			
			if(elements[key] == null) break;
			else if(elements[key].getValue().equalsIgnoreCase(word)){
				temp = elements[key];
				isFilled = true;
			}
			x++;
		}while(!isFilled);
		return temp;
	}


	public Node search2(String word){ // CHAINING.

		int key = keyCode2(word);

		Node temp = elements[key];

		while(temp != null){

			if(temp.getValue().equalsIgnoreCase(word)){


				break;
			}

			temp = temp.getNext();
		}

		return temp;
	}



	public int size(){
		return elements.length;
	}

	public Node[] getElements() {
		return elements;
	}

	public void setElements(Node[] elements) {
		this.elements = elements;
	}

	public int getMax() {
		return max;
	}

	public static int docElements(int doc){
		return documentWords[doc];
	}


}
