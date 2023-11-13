import java.util.Arrays;
import java.util.Stack;

public class Rozwiazanie {

    private static final int LICZBA_PRZEDMIOTOW = 4;
    private static final int MAX_WAGA = 20;

    private Plecak stanPoczatkowy;
    private Plecak stanKoncowy;
    private Stack<Plecak> biezacyFrontStos;
    private Plecak najlepszeRozwiazanie;

    // Definicja wag i wartości przedmiotów
    private static final int[] wagi = {19, 18, 20, 20};
    private static final int[] wartosci = {15, 50, 40, 1};

    private int maxWeight = MAX_WAGA; // Maximum allowed weight

    public Rozwiazanie() {
        stanPoczatkowy = new Plecak(new int[LICZBA_PRZEDMIOTOW]);
        stanPoczatkowy.wstaw(1, 0);
        najlepszeRozwiazanie = new Plecak(new int[LICZBA_PRZEDMIOTOW]);
    }

    private Plecak wybierzWezelDoAnalizyDFS() {
        if (!biezacyFrontStos.isEmpty()) {
            return biezacyFrontStos.pop();
        }
        return null;
    }

    private void zapiszNajlepszeRozwiazanie(Plecak rozwiazanie) {
        if (ocenRozwiazanie(rozwiazanie) > ocenRozwiazanie(najlepszeRozwiazanie)) {
            najlepszeRozwiazanie = new Plecak(rozwiazanie);
        }
    }

    private void rozwinPoziomDFS(Plecak stanDoRozwiniecia) {
        int poziom = 0;
        int tempWeight = 0; // Variable to keep track of the total weight

        while ((poziom < LICZBA_PRZEDMIOTOW) && ((stanDoRozwiniecia.wez(poziom)) > 0)) {
            tempWeight += wagi[stanDoRozwiniecia.wez(poziom) - 1];
            poziom++;
        }

        if (tempWeight > maxWeight) {
            System.out.println("Przekroczono maksymalną wagę - wycofuję węzeł");
            return;
        }

        if (poziom == LICZBA_PRZEDMIOTOW) {
            System.out.println("Brak wezłów podrzędnych - ostatni poziom");
            zapiszNajlepszeRozwiazanie(stanDoRozwiniecia);
            return;
        }

        System.out.println("Rozwijam poziom: " + poziom);
        for (int i = 0; i < LICZBA_PRZEDMIOTOW; i++) {
            Plecak stanTemp = new Plecak(stanDoRozwiniecia);

            if (!stanTemp.jestWPlecaku(i + 1)) {
                // Check if adding the item exceeds the maximum weight
                if (tempWeight + wagi[i] <= maxWeight) {
                    stanTemp.wstaw(i + 1, poziom);
                    System.out.println("Wrzucam na stos: ");
                    stanTemp.wypiszNaEkran();
                    biezacyFrontStos.push(stanTemp);
                }
            }
        }
    }

    public boolean przeszukajDFS() {
        biezacyFrontStos = new Stack<>();
        biezacyFrontStos.push(stanPoczatkowy);
        boolean sukces = false;

        while (!biezacyFrontStos.isEmpty()) {
            Plecak tempPlecak = wybierzWezelDoAnalizyDFS();
            System.out.println("Biorę ze stosu: ");
            tempPlecak.wypiszNaEkran();
            rozwinPoziomDFS(tempPlecak);

            // Jeśli znaleziono najlepsze rozwiązanie, ustaw sukces na true
            if (ocenRozwiazanie(tempPlecak) > ocenRozwiazanie(najlepszeRozwiazanie)) {
                sukces = true;
            }
        }
        return sukces;
    }

    private int ocenRozwiazanie(Plecak rozwiazanie) {
        int sumaWartosci = 0;
        for (int i = 0; i < LICZBA_PRZEDMIOTOW; i++) {
            sumaWartosci += rozwiazanie.wez(i) * wartosci[i];
        }
        return sumaWartosci;
    }

    public void pokazWynik() {
        najlepszeRozwiazanie.wypiszNaEkran();
    }
}