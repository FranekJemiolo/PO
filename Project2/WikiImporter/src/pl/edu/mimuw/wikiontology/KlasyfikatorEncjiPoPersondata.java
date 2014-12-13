package pl.edu.mimuw.wikiontology;
import java.util.regex.*;
//W tej klasie klasyfikujemy czy w danym artykule znajduje się szablon Persondata.
public class KlasyfikatorEncjiPoPersondata extends KlasyfikatorEncji {
	@Override
	public boolean stwierdzCzyArtykułJestEncja(Artykuł artykuł, String tekst){
		Pattern pattern = Pattern.compile("\\{\\{Persondata");
		Matcher matcher = pattern.matcher(tekst);
		boolean wynik = matcher.find();
		return wynik;
	}
}
