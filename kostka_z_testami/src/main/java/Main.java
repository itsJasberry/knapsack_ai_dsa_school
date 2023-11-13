import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Czesc");

        System.out.println("*************Rozpoczynam przeszukiwanie w głąb*****************");
        Zagadka zagadka1 = new Zagadka();
        if (zagadka1.przeszukajDFS()) {
            System.out.println( "SUKCES: Znaleziono rozwiązanie");
            zagadka1.pokazWynik();
        }

        else System.out.println("PORAŻKA: Nie znaleziono wyniku");

    }
}
