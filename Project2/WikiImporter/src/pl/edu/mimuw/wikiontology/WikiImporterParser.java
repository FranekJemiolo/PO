package pl.edu.mimuw.wikiontology;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class WikiImporterParser extends DefaultHandler {

	private Artykuł aktualnyArtykuł;
	private boolean dodaj;
	private GrafEncji grafEncji = new GrafEncji();
	private StringBuilder sb = new StringBuilder();
	private ArrayList<Artykuł> listaArtykułów = new ArrayList<Artykuł>();
	private KlasyfikatorEncji klasyfikatorEncji = new KlasyfikatorEncjiPoPersondata();
	private KlasyfikatorEncji klasyfikatorEncji1 = new KlasyfikatorEncjiPoKategoriach();
	private ArrayList<String> listaStringów = new ArrayList<String>();
	
	public WikiImporterParser(String nazwaPliku) throws IOException, SAXException,ParserConfigurationException  {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse(nazwaPliku, this);
		//Usuwamy znaleziony artykuły i przepisujemy je na liste stringów.
		this.przepiszNaStringi();
		//W tym momencie dodajemy również sąsiadów bo mamy wszystkie artykuły o 
		//osobach na liście stringów.
		saxParser.parse(nazwaPliku, this);
		//Wypisujemy ile załadowaliśmy encji.
		this.readList();
		//Ładujemy encje do grafu.
		this.załadujDoGrafu();
		this.wczytujZWejscia();
	}
	
	public void characters(char[] buffer, int start, int length) {
		sb.append(buffer, start, length);
    }
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		sb = new StringBuilder();
		if (qName.equalsIgnoreCase("page")) {
			aktualnyArtykuł = new Artykuł();
			dodaj = false;	
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ((qName.equalsIgnoreCase("page"))&&(dodaj)) {
			listaArtykułów.add(aktualnyArtykuł);
		}
		else if (qName.equalsIgnoreCase("title")) {
			aktualnyArtykuł.setNazwa(sb.toString());
		}
		else if (qName.equalsIgnoreCase("text")) {
			aktualnyArtykuł.dodajKategorie(sb.toString());
			aktualnyArtykuł.dodajSąsiada(listaStringów,sb.toString());
			//Sprawdzamy czy artykuł dotyczy osoby.
			if (klasyfikatorEncji.stwierdzCzyArtykułJestEncja(aktualnyArtykuł, sb.toString())){
				dodaj = true;
			}
			else if (klasyfikatorEncji1.stwierdzCzyArtykułJestEncja(aktualnyArtykuł, sb.toString())){
				dodaj = true;
			}
		}
	}
	
	private void załadujDoGrafu(){
		//Ładujemy wszystkie artykuły jako encje do grafu encji.
		for (Artykuł a : listaArtykułów){
			grafEncji.dodajEncje(grafEncji.zamienArtykułNaEncje(a));
		}
		grafEncji.stworzPowiazania(listaArtykułów);
	}

	private void readList() {
		//Wypisujemy ile zaimportowaliśmy encji.
		System.out.println("Zaimportowano encji " + listaArtykułów.size()  + ".");
	}

	private void przepiszNaStringi(){
		for (Artykuł a : listaArtykułów){
			listaStringów.add(a.getNazwa());
		}
		listaArtykułów.removeAll(listaArtykułów);
	}

	private void wczytujZWejscia(){
		Scanner sc = new Scanner(System.in);
		String wejscie = sc.nextLine();
		int b,c;
		String imie1,imie2;
		String modyfikator;
		while(!wejscie.isEmpty()){
			grafEncji.ustawNieodwiedzone();
			PrzeszukujGrafWszerz przeszukuj = new PrzeszukujGrafWszerz(grafEncji);
			b = 0;
			c = 0;
			for (int i = 0; i < wejscie.length(); i++){
				if (wejscie.charAt(i) == ' '){
					if (b == 0){
						b = i;
					}
					else{
						c = i;
					}
				};
			}
			wejscie = wejscie.replace('_',' ');
			modyfikator = wejscie.substring(0,b).toLowerCase();
			imie1 = wejscie.substring(b+1, c-b+modyfikator.length()).toLowerCase();
			imie2 = wejscie.substring(c+1, wejscie.length()-b+modyfikator.length()).toLowerCase();
			if (!modyfikator.equals("all")){
				GrafEncjiPrzefiltrowany grafik = new GrafEncjiPrzefiltrowany();
				for (Artykuł a : listaArtykułów){
					Encja e = grafik.zamienArtykułNaEncje(a,modyfikator);
					if (e != null){
						grafik.dodajEncje(e);
					}
				}
				grafik.stworzPowiazania(listaArtykułów);
				przeszukuj = new PrzeszukujGrafWszerz(grafik);
			}
			if ((przeszukuj.czyJestEncjaWGrafie(imie1))&&(przeszukuj.czyJestEncjaWGrafie(imie2))){
				Encja e1 = przeszukuj.zwrocEncję(imie1);
				Encja e2 = przeszukuj.zwrocEncję(imie2);
				LinkedList<Encja> listaWynikowa = przeszukuj.zwróćŚcieżkę(e1, e2);
				if ((listaWynikowa == null)|| (listaWynikowa.isEmpty())){
					System.out.println("Nie istnieje taka ścieżka");
				}
				else{
					for (Encja e : listaWynikowa){
						System.out.println(e.getImie());
					}
				}
			}
			else{
				System.out.println("Nie istnieje taka ścieżka");
			}
			wejscie = sc.nextLine();
		}
		sc.close();
	}
}
