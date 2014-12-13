package pl.edu.mimuw.wikiontology;
import java.util.*;
public class GrafEncji {
	//W tej klasie trzymamy graf encji w postacji arraylisty encji.
	protected ArrayList<Encja> graf;
	public GrafEncji(){
		this.graf = new ArrayList<Encja>();
	}
	public Encja zamienArtykułNaEncje(Artykuł artykuł){
		//Zamieniamy artykuł na encję.
		Encja e = new Encja(artykuł.getNazwa());
		return e;
	}
	public void stworzPowiazania(ArrayList<Artykuł> listaArtykułów){
		//Tworzymy powiązania w grafie, czyli tworzymy dla każdego węzła(encji) sąsiadów.
		for (Artykuł a : listaArtykułów){
			Iterator<Encja> it = graf.iterator();
			Encja e = null;
			while(it.hasNext()){
				e = it.next();
				if (e.getImie().equalsIgnoreCase(a.getNazwa())){
					break;
				}
			}
			ArrayList<String> sąsiedzi = a.getSąsiedzi();
			for (String s : sąsiedzi){
				for (Encja e1 : graf){
					if (e1.getImie().equalsIgnoreCase(s)){
						e.dodajSasiada(e1);
					}
				}
			}
		}
	}
	public void dodajEncje(Encja e){
		this.graf.add(e);
	}
	public void usunEncje(Encja e){
		this.graf.remove(e);
	}
	public int ileEncji(){
		return this.graf.size();
	}
	public ArrayList<Encja> dajGraf(){
		return this.graf;
	}
	public void ustawNieodwiedzone(){
		for (Encja e : graf){
			e.setOdwiedzony(false);
			e.setOjciec(null);
		}
	}
}
