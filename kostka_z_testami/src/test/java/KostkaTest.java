import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KostkaTest {
    //obiekt który będziemy testować
    private Kostka kostka;

    //konstruktor klasy testowej
    public void KostkaTests() { }

    //metoda wywoływana przed kazdą metodą testową
    @BeforeEach
    public void setUp()
    {
        kostka = new Kostka();
    }

    //metoda wywoływana po każdej metodzie testowej
    @AfterEach
    public void tearDown()
    {
        kostka = null;
    }

    @org.junit.jupiter.api.Test
    void jestWKostce() {
        //given
          kostka.wstaw(3,2);
        // when
         //boolean jest=kostka.jestWKostce(3);
        kostka.jestWKostce(3);
        // then
       assertTrue(kostka.jestWKostce(3));
        //assertTrue(jest);
        assertFalse(kostka.jestWKostce(2));
    }


}