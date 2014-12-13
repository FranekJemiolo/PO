package pl.edu.mimuw.wikiontology;
//Abstrakcyjna klasa służąca do klasyfikowania czy artykuł dotyczy osoby.
public abstract class KlasyfikatorEncji {
	public abstract boolean stwierdzCzyArtykułJestEncja(Artykuł artykuł, String tekst);
}
