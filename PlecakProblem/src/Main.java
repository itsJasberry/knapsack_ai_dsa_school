import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Czesc");

        System.out.println("*************Rozpoczynam przeszukiwanie w głąb*****************");
        Rozwiazanie rozwiazanie = new Rozwiazanie();


        if (rozwiazanie.przeszukajDFS()) {
            System.out.println( "SUKCES: Znaleziono rozwiązanie");
            rozwiazanie.pokazWynik();
        } else {
            System.out.println("PORAŻKA: Nie znaleziono wyniku");
        }
    }
}