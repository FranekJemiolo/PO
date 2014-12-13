package wymiana;

import java.util.LinkedList;

public class Kolejka {
	private LinkedList<Strona> kolejka;
	public Kolejka(){
		kolejka = new LinkedList<Strona>();
	}
	public void wstaw(Strona s){
		kolejka.addLast(s);
	}
	public void wstawNaPoczatek(Strona s){
		kolejka.addFirst(s);
	}
	public Strona usunPoczatek(){
		return kolejka.removeFirst();
	}
	public Strona usunKoniec(){
		return kolejka.removeLast();
	}
	public void usun(Strona s){
		kolejka.remove(s);
	}
	public void ustaw(Strona s){
		kolejka.set(kolejka.indexOf(s), s);
	}
	public Strona poczatek(){
		return kolejka.peekFirst();
	}
	public Strona koniec(){
		return kolejka.peekLast();
	}
}
