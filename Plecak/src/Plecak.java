import java.util.Stack;

public class Plecak {

    private static final int ILOSC_PRZEDMIOTOW = 5;
    private static int pojemnoscPlecaka;
    private static final int[] WAGI = {10, 3, 12, 8, 7};
    private static final int[] WARTOSCI = {7, 15, 45, 20, 10};

    private Stack<Integer> biezacyFrontStos;
    private Stack<Integer> najlepszaKombinacja;

    public Plecak(int pojemnośćPlecaka) {
        this.pojemnoscPlecaka = pojemnośćPlecaka;
        biezacyFrontStos = new Stack<>();
        najlepszaKombinacja = new Stack<>();
    }
    public static int getPojemnoscPlecaka() {
        return pojemnoscPlecaka;
    }
    private int obliczWartoscKombinacji(Stack<Integer> kombinacja) {
        int sumaWartosci = 0;
        for (int indeks : kombinacja) {
            sumaWartosci += WARTOSCI[indeks];
        }
        return sumaWartosci;
    }

    private int obliczMaseKombinacji(Stack<Integer> kombinacja) {
        int sumaMas = 0;
        for (int indeks : kombinacja) {
            sumaMas += WAGI[indeks];
        }
        return sumaMas;
    }

    private void rozwinPoziomDFS(int indeksPrzedmiotu) {
        if (indeksPrzedmiotu < ILOSC_PRZEDMIOTOW) {
            biezacyFrontStos.push(indeksPrzedmiotu);

            // Rozwinięcie poziomu - dodajemy przedmiot do plecaka
            System.out.println("Wrzucam na stos: ");
            pokazStos(biezacyFrontStos);
            rozwinPoziomDFS(indeksPrzedmiotu + 1);

            // Rozwinięcie poziomu - nie dodajemy przedmiotu do plecaka
            biezacyFrontStos.pop();
            System.out.println("Biorę ze stosu: ");
            pokazStos(biezacyFrontStos);
            rozwinPoziomDFS(indeksPrzedmiotu + 1);
        } else {
            // Osiągnięto liść drzewa - koniec gałęzi
            int masaKombinacji = obliczMaseKombinacji(biezacyFrontStos);
            if (masaKombinacji <= getPojemnoscPlecaka()) {
                // Aktualizacja najlepszej kombinacji, jeśli masa mieści się w plecaku
                int wartoscKombinacji = obliczWartoscKombinacji(biezacyFrontStos);
                int najlepszaWartosc = obliczWartoscKombinacji(najlepszaKombinacja);

                if (wartoscKombinacji > najlepszaWartosc) {
                    najlepszaKombinacja.clear();
                    najlepszaKombinacja.addAll(biezacyFrontStos);
                }
            }
        }
    }

    private void pokazStos(Stack<Integer> stos) {
        for (int indeks : stos) {
            System.out.print(indeks + " ");
        }
        System.out.println();
    }

    public void przeszukajDFS() {
        rozwinPoziomDFS(0);
    }

    public void pokazWynik() {
        pokazStos(najlepszaKombinacja);
    }
}