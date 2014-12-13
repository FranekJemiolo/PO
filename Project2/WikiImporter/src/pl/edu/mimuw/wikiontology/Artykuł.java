package pl.edu.mimuw.wikiontology;
import java.util.*;
import java.util.regex.*;
public class Artykuł {
	//W tej klasie wczytujemy Artykuł i sprawdzamy czy jest on o osobie.
	private String nazwa;
	private ArrayList<String> kategorie;
	private ArrayList<String> sąsiedzi;
	private boolean czyOsoba;
	public Artykuł(){
		this.kategorie = new ArrayList<String>();
		this.sąsiedzi = new ArrayList<String>();
		this.czyOsoba = false;
	}
	public Artykuł(String nazwa){
		this.nazwa = nazwa;
		this.kategorie = new ArrayList<String>();
		this.sąsiedzi = new ArrayList<String>();
		this.czyOsoba = false;
	}
	
	public void dodajSasiada(String nazwa){
		this.sąsiedzi.add(nazwa);
	}
	public void setNazwa(String nazwa){
		this.nazwa = nazwa;
	}
	public String getNazwa(){
		return this.nazwa;
	}
	public ArrayList<String> getSąsiedzi(){
		return this.sąsiedzi;
	}
	public boolean getCzyOsoba(){
		return this.czyOsoba;
	}
	public void setCzyOsoba(boolean czyOsoba){
		this.czyOsoba = czyOsoba;
	}
	public void dodajKategorie(String tekst){
		//Dodajemy do artykułu kategorie.
		Pattern pattern = Pattern.compile("\\[\\[Category:([^\\]]*)\\]\\]");
		Matcher matcher = pattern.matcher(tekst);
		while(matcher.find()){
			String s = matcher.group();
			this.kategorie.add(s.substring(11,s.length()-2 ).toLowerCase());
		}
	}
	public void dodajSąsiada(ArrayList<String> listaStringów,String tekst){
		for (String a : listaStringów){
			//Szukamy w tekście imion sąsiadów.
			Pattern pattern = Pattern.compile(a);
			Matcher matcher = pattern.matcher(tekst);
			while(matcher.find()){
				String s = matcher.group();
				if (!this.sąsiedzi.contains(s.toLowerCase())){
					this.sąsiedzi.add(a.toLowerCase());
				}
			}
		}

	}
	public ArrayList<String> getKategorie(){
		return this.kategorie;
	}
}
