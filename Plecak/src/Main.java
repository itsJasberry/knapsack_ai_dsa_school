// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        System.out.println("Rozwiązanie problemu plecaka:");

        Plecak plecak = new Plecak(20);
        plecak.przeszukajDFS();

        System.out.println("Najlepsza kombinacja przedmiotów:");
        plecak.pokazWynik();
    }
}