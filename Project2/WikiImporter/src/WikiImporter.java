import pl.edu.mimuw.wikiontology.*;
import java.io.*;
import java.util.Scanner;
public class WikiImporter {
	public static void main(String[] args){
		if (args.length != 1){
			System.out.println("Podano złą ilość parametrów startowych. Proszę uruchomić ponownie program");
		}
		else{
			boolean zmienna = true;
			Scanner sc = new Scanner(System.in);
			do{
				WikiImporterParser wip;
				try{
					wip = new WikiImporterParser(args[0]);
					zmienna = false;
				}
				catch (IOException e){
					System.out.println("Podano błędny plik xml, proszę podać jeszcze raz nazwę pliku :");
					args[0] = sc.nextLine();
					zmienna = true;

				}
				catch (Throwable t){
					System.out.println("Nieznany błąd");
					zmienna = false;
				}
			}
			while(zmienna);
			sc.close();
		}
	}
}
