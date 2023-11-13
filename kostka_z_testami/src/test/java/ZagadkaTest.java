import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ZagadkaTest {

    private Zagadka zagadka;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        zagadka = new Zagadka();
    }

    //metoda wywoływana po każdej metodzie testowej
    @AfterEach
    public void tearDown()
    {
        zagadka = null;
    }

    @Test
    void jestRozwiazaniem() {
        //given
        zagadka.stanPoczatkowy.wstaw(1,0);
        zagadka.stanPoczatkowy.wstaw(1,1);
        zagadka.stanPoczatkowy.wstaw(1,2);
        zagadka.stanPoczatkowy.wstaw(1,3);
        zagadka.stanPoczatkowy.wstaw(1,4);
        zagadka.stanPoczatkowy.wstaw(1,5);
        zagadka.stanPoczatkowy.wstaw(1,6);
        zagadka.stanPoczatkowy.wstaw(1,7);
        // when
        zagadka.jestRozwiazaniem(zagadka.stanPoczatkowy);
        // then
        assertTrue(zagadka.jestRozwiazaniem(zagadka.stanPoczatkowy));

        //given
        zagadka.stanPoczatkowy.wstaw(1,0);
        zagadka.stanPoczatkowy.wstaw(1,1);
        zagadka.stanPoczatkowy.wstaw(1,2);
        zagadka.stanPoczatkowy.wstaw(1,3);
        zagadka.stanPoczatkowy.wstaw(4,4);
        zagadka.stanPoczatkowy.wstaw(1,5);
        zagadka.stanPoczatkowy.wstaw(1,6);
        zagadka.stanPoczatkowy.wstaw(1,7);
        // when
        zagadka.jestRozwiazaniem(zagadka.stanPoczatkowy);
        // then
        assertFalse(zagadka.jestRozwiazaniem(zagadka.stanPoczatkowy));

    }
}