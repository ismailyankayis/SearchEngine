import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reading {



	public String[] StopWords;
	public static HT word;
	int wordCount = 0;
	static int which;
	int dotCount = 0;

	public Reading(int whichs, int size) throws IOException{

		which = whichs;
		if (which == 1 || which == 3 || which == 4) {
			word = new HT(size); // Double
		}
		else if (which == 2) {
			word = new HT(40009); // Chain
		}

		StopWords = new String[319];
		long t = System.currentTimeMillis();


		BufferedReader reader = null;

		reader = new BufferedReader(new FileReader("C:\\StopWords.txt"));


		String sCurrentLine = reader.readLine();
		int count = 0;
		while(sCurrentLine !=null){
			StopWords[count] = sCurrentLine;
			count++;
			sCurrentLine = reader.readLine();
		}


		File klasor = new File("C:\\projectCorpus"); // klasörü tanýmlama
		int dosyaCount = 0;
		String[] strDosyalar = klasor.list();  // dosya isimlerini arraye atama

		for(String dosya : strDosyalar){ //dosyalarýn tek tek ele alýnmasý

			long a = System.currentTimeMillis();

			String str = "C:\\projectCorpus\\" + dosya; // dosyanýn uzantýsýný string olarak alma
			FileReader file = new FileReader(str);



			reader = new BufferedReader(file);

			String satir = reader.readLine();
			while(satir != null){



				satir = satir.replaceAll("[^a-zA-Z0-9]"," "); // removing punctiation
				satir = satir.replaceAll("\\s+"," "); // remowing multi spaces

				String[] kelime = satir.split(" ");

				for (int i = 0; i < kelime.length; i++) {

					boolean isStop = false;
					if (kelime[i].length() > 2) { // 1 ve 2 harflileri direk eliyoruz
						for (int j = 0; j < StopWords.length; j++) {
							if(StopWords[j].equals(kelime[i])){
								isStop = true;
							}
						}
						if(!isStop){

							wordCount++;


							if (which == 1 || which == 3 || which == 4) {
								word.insert1(kelime[i], dosyaCount);
							}
							else if (which == 2) {

								word.insert2(kelime[i], dosyaCount);
							}
							

						}
					}
				}
				satir = reader.readLine();
			}

			if (dotCount % 70 == 0) {

				System.out.print(".");
			}
			//			System.out.println(System.currentTimeMillis()-a);
			dosyaCount++;
			dotCount++;
		}
		long t2 = System.currentTimeMillis();
		System.out.println();
		if (which == 1 || which == 3 || which == 4) {

			System.out.println(HT.rehashingCount + " times rehasing" + " \nUnique word amount: " + HT.Unique + "  \nMS: " + ((t2-t)) + " milisec");
		}
		else {
			System.out.println("Unique word: " + HT.Unique + "  MS: " + ((t2-t)) + " milisec");
		}



		File file2 = new File("C:\\projectCorpus2\\OpenAddressing.txt");
		if(!file2.exists()) 
			file2.createNewFile();

		FileWriter fileWriter = new FileWriter(file2, false);
		BufferedWriter bWriter = new BufferedWriter(fileWriter);





		for (int i = 0; i < word.size(); i++) {
			if(word.getElements()[i] != null){
				bWriter.write(i+".Kelime("+word.getElements()[i].getValue()+" ("+word.getElements()[i].getCollision()+")"+") ");

				for (int j = 0; j < 670; j++) {
					if(word.getElements()[i].getDocuments()[j] != 0)
						bWriter.write("("+strDosyalar[j]+" -- "+word.getElements()[i].getDocuments()[j]+")");
				}
				bWriter.newLine();
			}
		}
		System.out.println("Maximum collision: " + word.getMax() + "  \nTotal collision: "+ word.getMaxCol() 
		+ "  \nWord amount: " + wordCount);


		if (which == 1 || which == 3 || which == 4) {
			for (int i = 0; i < word.getColArray().length; i++) {

				bWriter.write(i + ": " + word.getColArray()[i]);

			}
		}

		for (int i = 0; i < 670; i++) {
			bWriter.write(i + " -- " + HT.docElements(i) + " -- " + strDosyalar[i]);
			bWriter.newLine();
		}






		bWriter.close();
		reader.close();



	}




}
