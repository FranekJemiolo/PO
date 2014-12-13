package pl.edu.mimuw.wikiontology;
import java.util.regex.*;
//W tej klasie sprawdzamy czy artykuł opisuje encję, patrząc na przypisania artykułu do kategorii.
public class KlasyfikatorEncjiPoKategoriach extends KlasyfikatorEncji {
	@Override
	public boolean stwierdzCzyArtykułJestEncja(Artykuł artykuł, String tekst) {
		boolean wynik = false;
		//Dowolny rok births
		Pattern pattern = Pattern.compile("\\d* births");
		for (String s : artykuł.getKategorie()){
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()){
				wynik = true;
			}
		}
		//Dowolny rok deaths
		pattern = Pattern.compile("\\d* deaths");
		for (String s : artykuł.getKategorie()){
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()){
				wynik = true;
			}
		}
		//Dowolny prefiks philosophers
		pattern = Pattern.compile(".* philosophers");
		for (String s : artykuł.getKategorie()){
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()){
				wynik = true;
			}
		}
		//Dowolny prefiks writers
		pattern = Pattern.compile(".* writers");
		for (String s : artykuł.getKategorie()){
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()){
				wynik = true;
			}
		}
		//Dowolny prefiks mathematicians
		pattern = Pattern.compile(".* mathematicians");
		for (String s : artykuł.getKategorie()){
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()){
				wynik = true;
			}
		}
		return wynik;
	}
}
