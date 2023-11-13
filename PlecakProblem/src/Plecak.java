import java.util.Arrays;

public class Plecak {

    private int[] przedmioty;

    public Plecak(int[] przedmioty) {
        this.przedmioty = przedmioty;
    }

    public Plecak(Plecak plecak) {
        this.przedmioty = Arrays.copyOf(plecak.przedmioty, plecak.przedmioty.length);
    }

    public void wstaw(int liczba, int pozycja) {
        przedmioty[pozycja] = liczba;
    }

    public int wez(int pozycja) {
        return przedmioty[pozycja];
    }

    public boolean jestWPlecaku(int liczba) {
        for (int i = 0; i < przedmioty.length; i++) {
            if (przedmioty[i] == liczba) {
                return true;
            }
        }
        return false;
    }

    public void wypiszNaEkran() {
        System.out.println("Zawartość tablicy:" + Arrays.toString(przedmioty));
    }
}