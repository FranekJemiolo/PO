package wymiana;

public class Komunikaty {
	public Komunikaty(){
		
	}
	public void wypisz(int a, int b){
		System.out.println("Błąd: "+a+" --> "+b);
	}
	public void wypiszPoczatek(String s, int ilosc){
		System.out.println("Strategia: "+s+", ramki: "+ilosc);
	}
	public void wypiszKoniec(int o,int b){
		System.out.println("Odwołania: "+o+", błędy: "+b);
	}
}
