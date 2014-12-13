package pl.edu.mimuw.wikiontology;

import java.util.ArrayList;
import java.util.Iterator;

public class GrafEncjiPrzefiltrowany extends GrafEncji {
	//Jest to graf, w którym przechowujemy przefiltrowany graf encji.
	public GrafEncjiPrzefiltrowany(){
		this.graf = new ArrayList<Encja>();
	}
	public Encja zamienArtykułNaEncje(Artykuł artykuł, String kategoria){
		//Dodajemy artykuł, ale tylko gdy należy do podanej kategorii.
		for (String s : artykuł.getKategorie()){
			if (s.contains(kategoria.toLowerCase())){
				Encja e = new Encja(artykuł.getNazwa());
				return e;
			}
		}
		return null;

	}
	@Override
	public void stworzPowiazania(ArrayList<Artykuł> listaArtykułów){
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
}
