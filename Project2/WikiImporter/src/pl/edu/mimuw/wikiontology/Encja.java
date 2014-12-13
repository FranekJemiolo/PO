package pl.edu.mimuw.wikiontology;
import java.util.*;
public class Encja{
	//Klasa encja jest to tak naprawdę węzeł w grafie reprezentujący artykuł o osobie.
	private String imie;
	private Encja ojciec;
	private ArrayList<Encja> sąsiedzi;
	private boolean odwiedzony;
	public Encja(String imie){
		this.imie = imie;
		this.ojciec = null;
		this.sąsiedzi = new ArrayList<Encja>();
		this.odwiedzony = false;
	}
	public void dodajSasiada(Encja e){
		this.sąsiedzi.add(e);
	}
	public ArrayList<Encja> zwrocSasiadow(){
		return this.sąsiedzi;
	}
	public String getImie(){
		return this.imie;
	}
	public boolean czyOdwiedzony(){
		return this.odwiedzony;
	}
	public void setOdwiedzony(boolean odwiedzony){
		this.odwiedzony = odwiedzony;
	}
	public void setOjciec(Encja e){
		this.ojciec = e;
	}
	public Encja getOjciec(){
		return this.ojciec;
	}
}
