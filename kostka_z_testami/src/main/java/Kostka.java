import java.util.Arrays;

public class Kostka {

    private int[] wierzcholki;

    public Kostka() {
        wierzcholki = new int[8];
    }

    public Kostka(Kostka kostka) {
        wierzcholki = new int[8];
        for (int i=0; i<8; i++)
            wierzcholki[i]= kostka.wez(i);
    }

    public void wstaw(int liczba, int pozycja) {
        wierzcholki[pozycja] = liczba;
    }

    public int wez(int pozycja) {
        return wierzcholki[pozycja];
    }

    public boolean jestWKostce(int liczba) {
        for (int j : wierzcholki)
            if (j == liczba)
                return true;

        return false;
    }

    public void wypiszNaEkran() {
        System.out.println("Zawartość tablicy:" + Arrays.toString(wierzcholki));
    }
}
