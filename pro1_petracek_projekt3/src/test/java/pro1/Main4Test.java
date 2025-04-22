package pro1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main4Test
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void test01()
    {
        Main4.printShortestEmails("KIKM",5);
        // TODO 4.2: Oprav test
        assertEquals(
                "jan.vanek@uhk.cz\r\npetr.bauer@uhk.cz\r\njan.budina@uhk.cz\r\nfilip.maly@uhk.cz\r\nkarel.maly@uhk.cz\r\n",
                outContent.toString()
        );
    }
}