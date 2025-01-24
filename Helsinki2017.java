import java.io.*;
import java.util.*;

public class Helsinki2017 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pontszam feldolgozo = new Pontszam();

        try {
            List<String> rovidProgramAdatok = fajlBeolvas("rovidprogram.csv");
            List<String> dontoAdatok = fajlBeolvas("donto.csv");

            feldolgozo.beolvasRovidProgram(rovidProgramAdatok);
            feldolgozo.beolvasDonto(dontoAdatok);
            System.out.println("5. feladat\nAdja meg egy versenyző nevét! ");
            String nev = scanner.nextLine();
            Versenyzo keresett = feldolgozo.keresVersenyzo(nev);
            if (keresett != null) {
                System.out.println("6. feladat\n A versenyző összpontszáma: " + String.format("%.2f", keresett.getVegsoPont()));
            } else {
                System.out.println("A keresett név nem található a versenyzők listáján!");
            }

            System.out.println("Ország statisztika:");
            Map<String, Long> statisztika = feldolgozo.orszagStatisztika();
            System.out.println("7. feladat\n");
            statisztika.forEach((orszag, darab) -> System.out.println(orszag + ": " + darab + " versenyző"));

            List<Versenyzo> vegeredmeny = feldolgozo.vegeredmeny();
            fajlKiir("vegeredmeny.csv", vegeredmeny);

            System.out.println("A végeredmény mentve a vegeredmeny.csv fájlba.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<String> fajlBeolvas(String fajlNev) throws IOException {
        List<String> sorok = new ArrayList<>();
        try (BufferedReader olvaso = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            boolean elsoSor = true;
            while ((sor = olvaso.readLine()) != null) {
                if (elsoSor) {
                    elsoSor = false;
                    continue;
                }
                sorok.add(sor);
            }
        }
        return sorok;
    }
    

    private static void fajlKiir(String fajlNev, List<Versenyzo> eredmenyek) throws IOException {
        try (BufferedWriter iro = new BufferedWriter(new FileWriter(fajlNev))) {
            int helyezes = 1;
            for (Versenyzo versenyzo : eredmenyek) {
                iro.write(helyezes++ + ";" + versenyzo.toString());
                iro.newLine();
            }
        }
    }
}
