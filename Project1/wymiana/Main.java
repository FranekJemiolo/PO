package wymiana;

public class Main {

	public static void main(String[] args) {
		 int[] t = { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4 };
         Odwołania o2 = new Odwołania(t, "FIFO", 2);
         try{
        	 //W tym bloku proszę wywoływać metody wykonaj().
        	 o2.wykonaj();
        	 o2.wykonaj();
        	 o2.wykonaj();
         }
         catch(Exception e){
        	 System.out.println("Podane złe dane");
         }
	}

}
