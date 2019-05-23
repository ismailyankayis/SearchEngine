import java.io.IOException;
import java.util.Scanner;

public class Management {

	Reading reading;
	Search search;
	static int which;
	Scanner scn = new Scanner(System.in);

	public Management() throws IOException{

		searchEngine();
	}

	public void searchEngine() throws IOException{


		System.out.println();
		System.out.println();
		System.out.println(">> Welcome to BID Searching Browser <<");
		System.out.println();
		System.out.println("   Press 1 for create a Hashtable with use DoubleHashing method");
		System.out.println("   Press 2 for create a Hashtable with use Chaining method");
		System.out.println("   Press 3 for create a Hashtable with use LinearHashing method");
		System.out.println("   Press 4 for create a Hashtable with use QuadraticHashing method");
		System.out.println();
		System.out.print("   Your choice : ");

		String m = scn.nextLine();
		if (isNumber(m)) {
			which = Integer.parseInt(m);
		}
		else {

			System.out.println();
			System.out.println();
			System.out.println("   I think you didn't join only integer, bad boooooy.");
			searchEngine();
		}


		if (which == 1 || which == 3 || which == 4) {

			Choose1();
		}

		else if (which == 2) {
			System.out.print("   Algorithm is working, please wait   ");
			reading = new Reading(which,40009);
		}
		else {
			System.out.println();
			System.out.println();
			System.out.println("I think you missed click. Don't worry, I will give a chance to choose one more time :)");
			searchEngine();
		}
		search = new Search();

		while(true){

			scn = new Scanner(System.in);
			System.out.print("Please Enter Your Search: ");
			String line = null;
			line = scn.nextLine();
			if (which == 1 || which == 3 || which == 4) {

				long time=System.currentTimeMillis();
				search.search(line);
				System.out.println(" Runtime for search : "+ (System.currentTimeMillis() - time ) + " MS");
			}
			else if (which == 2) {

				long time=System.currentTimeMillis();
				search.search(line);
				System.out.println(" Runtime for search : "+ (System.currentTimeMillis() - time ) + " MS ");
			}
		}
	}
	public void Choose1 () throws IOException { /// Size seç hafýz


		scn = new Scanner(System.in);
		String answer;

		System.out.println("   The default size is 10.000, Would you like to change the array size? Y/N");
		System.out.print("   Y/N ? : ");
		answer = scn.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();


		if (answer.equalsIgnoreCase("Y")) {

			Choose2();
		}
		else if (answer.equalsIgnoreCase("N")) {

			System.out.print("   Algorithm is working, please wait   ");
			reading = new Reading(which,10000);
		}
		else {
			System.out.println("   Dude, you always trying to broken our code :( , lets try again");
			Choose1();
		}
	}
	public void Choose2 () throws NumberFormatException, IOException {



		scn = new Scanner(System.in);

		System.out.print("   Please enter what do you want to be array size between 100 and 100.000 : ");
		String size = scn.nextLine(); // isNumber kontrol.

		if (isNumber (size) && isSize(size)) {

			System.out.print("   Algorithm is working, please wait   ");
			reading = new Reading(which,Integer.parseInt(size));
		}
		else {

			Choose2();
		}
	}

	public boolean isNumber(String string) {// Is all off them sayý mý ?

		boolean isno=true;
		for(int i=0; i<string.length();i++)
		{
			
			int x = string.charAt(i);
			if(!(x>= 48 && x <= 57)){
				isno = false;
				break;
			}

		}
		
		return isno;	
	}
	public boolean isSize(String string){
		int x = Integer.parseInt(string);
		if((x >= 100 && x <= 100000)){
			return true;
		}
		else return false;
	}


}
