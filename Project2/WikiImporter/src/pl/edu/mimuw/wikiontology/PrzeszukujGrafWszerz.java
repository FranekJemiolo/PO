package pl.edu.mimuw.wikiontology;
import java.util.*;
//W tej klasie przechodzimy bfs-em zadany w konstruktorze graf i zwracamy ścieżkę osób.
public class PrzeszukujGrafWszerz {
	private ArrayList<Encja> przeszukiwanyGraf;
	private LinkedList<Encja> kolejkaPrzeszukan;
	private LinkedList<Encja> listaEncji;
	private Iterator<Encja> it;
	
	public PrzeszukujGrafWszerz(GrafEncji g){ 
		this.przeszukiwanyGraf = g.dajGraf();
		this.kolejkaPrzeszukan = new LinkedList<Encja>();
		this.listaEncji = new LinkedList<Encja>();
	}
	
	public boolean czyJestEncjaWGrafie(String imie){
		//W tej metodzie sprawdzamy czy istnieje encja o podanym imieniu w grafie.
		it = this.przeszukiwanyGraf.iterator();
		boolean zwroc = false;
		Encja patrzona;
		while(it.hasNext()){
			patrzona = it.next();
			if (patrzona.getImie().equalsIgnoreCase(imie)){
				zwroc = true;
				break;
			}
		}
		return zwroc;
	}
	
	public Encja zwrocEncję(String imie){
		//W tej metodzie zwracamy encję o podanym imieniu, uprzednio 
		//sprawdziwszy czy takowa się tam znajduje.
		it = this.przeszukiwanyGraf.iterator();
		Encja patrzona = null;
		while(it.hasNext()){
			patrzona = it.next();
			if (patrzona.getImie().equalsIgnoreCase(imie)){
				break;
			}
		}
		return patrzona;
	}
	
	public LinkedList<Encja> zwróćŚcieżkę(Encja e, Encja e1){
		//Metoda zwracająca scieżkę między dwoma encjami w grafie.
		kolejkaPrzeszukan.removeAll(kolejkaPrzeszukan);
		kolejkaPrzeszukan.add(e);
		listaEncji = new LinkedList<Encja>();
		it = this.przeszukiwanyGraf.iterator();
		LinkedList<Encja> wynik = this.znajdzSciezke(e1);
		return wynik;
	}
	
	private LinkedList<Encja> znajdzSciezke(Encja e){
		//W tej metodzie przeszukujemy graf i znajdujemy scieżkę do podanej
		//encji, którą później zwracamy.
		while (!kolejkaPrzeszukan.isEmpty()){
			Encja usuwana = kolejkaPrzeszukan.removeFirst();
			if (usuwana.getImie().equalsIgnoreCase(e.getImie())){
				int pathLength = 0;
				listaEncji.addLast(usuwana);
				Encja e2 = usuwana;
				while(e2.getOjciec() != null){
					//Przechodzimy ojcami encji w grafie i dodajemy je do ścieżki.
					pathLength++;
					listaEncji.addFirst(e2.getOjciec());
					e2 = e2.getOjciec();
				}
				//Wypisujemy długość ścieżki.
				System.out.println("Path length : "+pathLength);
				return listaEncji;
			}
			else {
				//Wrzucamy nieodwiedzonych synów danego węzła.
				usuwana.setOdwiedzony(true);
				ArrayList<Encja> sąsiedzi = usuwana.zwrocSasiadow();
				for (Encja e1 : sąsiedzi){
					if (!e1.czyOdwiedzony()){
						e1.setOjciec(usuwana);
						e1.setOdwiedzony(true);
						kolejkaPrzeszukan.addLast(e1);
					}
				}
			}
		}
		return listaEncji;
	}
}