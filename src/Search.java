import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Search {
	
	

	public Search(){
		
		
	}
	
	
	public void search(String line){
		
		File klasor = new File("C:\\projectCorpus");
		String[] strDosyalar = klasor.list();
		
		String[] kelime = line.split(" ");
		Node[] temp = new Node[kelime.length];
		
		
		for (int i = 0; i < kelime.length; i++) {
			
			if (Reading.which == 1 || Reading.which == 3 || Reading.which == 4) { // Double linear quadratic
				temp[i] = Reading.word.search(kelime[i]);
			}
			else if (Reading.which == 2) { // Chain
				temp[i] = Reading.word.search2(kelime[i]);
			}
			
			if(temp[i] ==  null) {
				Node newnode = new Node(-1,"-1");
				int[] tempArray = new int[strDosyalar.length];
				for (int j = 0; j < tempArray.length; j++) {
					tempArray[j] = -1;
				}
				newnode.setDocuments(tempArray);
				temp[i] = newnode;
			}
		}
		
			
			PiorityQueue pq = new PiorityQueue(10);
			
			for (int i = 0; i < strDosyalar.length; i++) {
				
				double piority = standartDeviation(i,temp);
				String docName = strDosyalar[i];
				int[] tempInt = new int[temp.length];
				for (int j = 0; j < tempInt.length; j++) {
					tempInt[j] = temp[j].getDocuments()[i];
				}
				int element = HT.documentWords[i];
				pq.enqueue(piority, docName, tempInt,element);
				
			}
			pq.display();
		
	}
	
	public double standartDeviation(int doc, Node[] temp){
		
		
		/*
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[i].getDocuments()[doc];
			
			
		}
		double mean = sum / temp.length;
		double sd = 0;
		for (int i = 0; i < temp.length; i++) {
			sd+= Math.pow(temp[i].getDocuments()[doc] - mean, 2);
		}
		
		sd = Math.sqrt(sd/(temp.length));
		
		double rank = (sum*mean)/(sd+1);
		
		*/
		
		
		double sum1 = 0;
		
		for (int i = 0; i < temp.length; i++) {
			
			BigDecimal a=new BigDecimal(temp[i].getDocuments()[doc]);
			BigDecimal division=new BigDecimal(HT.docElements(doc)+1);
			sum1+=a.divide(division, 6, RoundingMode.CEILING).doubleValue();
		}
		
		return sum1;
	}
	
}
