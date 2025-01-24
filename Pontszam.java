import java.util.*;
import java.util.stream.Collectors;

public class Pontszam {
    private List<Versenyzo> rovidProgram = new ArrayList<>();
    private List<Versenyzo> dontoProgram = new ArrayList<>();

    public void beolvasRovidProgram(List<String> adatok) {
        for (String sor : adatok) {
            String[] elemek = sor.split(";");
            String nev = elemek[0];
            String orszagKod = elemek[1];
            double technikaiPont = Double.parseDouble(elemek[2]);
            double komponensPont = Double.parseDouble(elemek[3]);
            double hibapont = Double.parseDouble(elemek[4]);
            rovidProgram.add(new Versenyzo(nev, orszagKod, technikaiPont, komponensPont, hibapont));
        }
    }

    public void beolvasDonto(List<String> adatok) {
        for (String sor : adatok) {
            String[] elemek = sor.split(";");
            String nev = elemek[0];
            String orszagKod = elemek[1];
            double dontoPont = Double.parseDouble(elemek[2]);
            for (Versenyzo versenyzo : rovidProgram) {
                if (versenyzo.getNev().equals(nev)) {
                    versenyzo.setVegsoPont(dontoPont + versenyzo.getTechnikaiPont() + versenyzo.getKomponensPont() - versenyzo.getHibapont());
                    dontoProgram.add(versenyzo);
                    break;
                }
            }
        }
    }

    public Versenyzo keresVersenyzo(String nev) {
        return rovidProgram.stream()
                .filter(v -> v.getNev().equalsIgnoreCase(nev))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Long> orszagStatisztika() {
        return dontoProgram.stream()
                .collect(Collectors.groupingBy(Versenyzo::getOrszagKod, Collectors.counting()));
    }

    public List<Versenyzo> vegeredmeny() {
        return dontoProgram.stream()
                .sorted(Comparator.comparingDouble(Versenyzo::getVegsoPont).reversed())
                .collect(Collectors.toList());
    }
}
