import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Zagadka {

    Kostka stanPoczatkowy;
    Kostka stanKoncowy;
    Stack<Kostka> biezacyFrontStos;

    public Zagadka() throws FileNotFoundException {
        stanPoczatkowy = new Kostka();
        stanPoczatkowy.wstaw(1,0);
      }

    private Kostka wybierzWezelDoAnalizyDFS(){
      //zdejmujemy pierwszy element ze stosu
        return biezacyFrontStos.pop();
    }


    //Testowanie bieżącego węzła
    protected Boolean jestRozwiazaniem(Kostka k){
        boolean jestrozwiazanie = false;
        for(int i = 0; i < 8; i++ ) {
            if (k.wez(i) == 0) {
                return false;
            }
        }
        //1+2+5+6=1+2+3+4=3+4+8+7=5+6+7+8
        int sciana1 = k.wez(0)+k.wez(1)+k.wez(2)+k.wez(3);
        int sciana2 = k.wez(0)+k.wez(1)+k.wez(4)+k.wez(5);
        int sciana3 = k.wez(0)+k.wez(4)+k.wez(2)+k.wez(6);
        int sciana4 = k.wez(1)+k.wez(5)+k.wez(3)+k.wez(7);
        int sciana5 = k.wez(2)+k.wez(3)+k.wez(6)+k.wez(7);
        int sciana6 = k.wez(4)+k.wez(5)+k.wez(6)+k.wez(7);

        jestrozwiazanie = (sciana1 == sciana2) && (sciana3 == sciana4) && (sciana5 == sciana6) && (sciana1 == sciana6) && (sciana4 == sciana5);

        // 18 == 18 && 18 == 18 itp.
        return jestrozwiazanie;
    }

    //Rozwinięcie bieżącego węzła/wierzchołka
    private void rozwinPoziomDFS(Kostka stanDoRozwiniecia){

        // UWAGA: Jeśli w danym problemie istnieje możliwość powrotu do poprzednio rozważanego stanu (cyklu w grafie), to przed wrzuceniem
        // na stos trzeba się upewnić, czy dany węzeł nie był już odwiedzony - w tym celu trzeba przechowywać i sprawdzać dodatkowo listę odwiedzonych węzłów
        // w rozważanej zagadce logicznej z kostką taka sytuacja nie występuje, więc w tym przykładowym kodzie tego nie ma

        int poziom = 0;

        //Tutaj ograniczam przeszukiwanie w głąb
        while ((poziom < 8) && ((stanDoRozwiniecia.wez(poziom)) > 0))  {
            poziom++;
        }
        if (poziom == 8) {
           System.out.println("Brak wezłów podrzędnych - ostatni poziom");
           return;
           //UWAGA: Ze względu na charakterystykę zagadki, łatwo (na sztywno) mogę określić ograniczenie głębokości przeszukiwania.
           // Dla niektórych problemów lepiej to określać parametrycznie
        }

        System.out.println("Rozwijam poziom: " + poziom);
        for (int i = 1; i < 9; i++ ) {
            Kostka stanTemp = new Kostka(stanDoRozwiniecia);

            //jeśli dana cyfra jest już w rozważanym stanie, to jej nie wrzucamy na stos
            if (!stanTemp.jestWKostce(i)) {
                stanTemp.wstaw(i, poziom);
                System.out.println("Wrzucam na stos: ");
                stanTemp.wypiszNaEkran();
                biezacyFrontStos.push(stanTemp);
            }
        };
    }
    //Przeszukiwanie w głąb (Depth First Search)
    public Boolean przeszukajDFS(){

        //zainicjuj bieżący front, tak aby zawierał tylko punkt startowy
        biezacyFrontStos = new Stack<>();
        biezacyFrontStos.push(stanPoczatkowy);
        boolean sukces = false;

        //dopóki coś jest na stosie i nie znaleziono jeszcze rozwiązania
        while ((!biezacyFrontStos.isEmpty()) && (!sukces)) {

            //Strategia przeszukiwania - tu się decyduje, który obiekt ma być ściągnięty ze stosu w bieżacym froncie
            Kostka tempKostka = wybierzWezelDoAnalizyDFS();
            System.out.println("Biorę ze stosu: ");
            tempKostka.wypiszNaEkran();

            //Sprawdzenie, czy węzeł nie jest rozwiązaniem - jeśli tak, nie trzeba go dalej rozwijać
            if (jestRozwiazaniem(tempKostka)) {
                stanKoncowy = tempKostka;
                sukces = true;
            } else {
                System.out.println("Węzeł nie jest rozwiązaniem, rozwijam dalej");
                rozwinPoziomDFS(tempKostka);
            }
        }
        return sukces;
    }
    // UWAGA: DFS można zoptymalizować tak, aby sprawdzał czy węzeł nie jest rozwiązaniem już zanim się go doda do kolejki
    // dla BFS nie można tego robić, bo istnieje ryzyko, że algorytm przestanie być algorytmem wszerz (znalezione zostanie
    // wczesniej rozwiązanie na głębokości X+1 niż na głębokości X

    public void pokazWynik(){
        stanKoncowy.wypiszNaEkran();
    }
}
