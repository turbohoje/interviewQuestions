import org.junit.*;
import static org.junit.Assert.*;

public class ViperTest {
    @Test
    public void reverseTestEmpty(){
        assertEquals("", Viper.reverse(""));
    }

    @Test
    public void reverseTestSingleChar(){
        assertEquals("a", Viper.reverse("a"));
    }

    @Test
    public void reverseTestCase(){
        assertEquals("aA", Viper.reverse("Aa"));
        assertNotEquals("aa", Viper.reverse("Aa"));
    }

    @Test
    public void testReverse1() throws Exception {
        assertEquals("a", "a");
    }

    @Test
    public void testReverseLong() throws Exception {
        String a = "a big long string can go in here";
        assertEquals(new StringBuilder(a).reverse().toString(), Viper.reverse(a));
    }

    @Test
    public void palendromeEmpty() throws Exception {
        assertFalse(Viper.isPalendrome(""));
    }
    @Test
    public void palendromeSingle() throws Exception {
        assertTrue(Viper.isPalendrome("a"));
    }
    @Test
    public void palendromeLong() throws Exception {
        String a = "a big long string can go in here";
        assertEquals(new StringBuilder(a).reverse().toString(), Viper.reverse(a));
    }

    @Test
    public void findPalendromeSimple() throws Exception {
        String palendrome = "racecar";
        assertEquals(palendrome, Viper.largestPalendrome(palendrome));
    }

    @Test
    public void findPalendrome() throws Exception{
        String palendrome = "racecar".trim();
        assertEquals(palendrome, Viper.largestPalendrome("left noise " + palendrome).trim());
        assertEquals(palendrome, Viper.largestPalendrome(palendrome + " right noise").trim());
        assertEquals(palendrome, Viper.largestPalendrome("bothleft " + palendrome + " right noise").trim());

    }

    @Test
    public void findPalendromeSpaceTest() throws Exception{
        String palendrome = "GoHangASalamiImALasagnaHog".toLowerCase().trim();
        assertEquals(palendrome, Viper.largestPalendrome(palendrome).trim());
        assertEquals(palendrome, Viper.largestPalendrome(" " + palendrome).trim());
        assertEquals(palendrome, Viper.largestPalendrome(palendrome + " ").trim());
        assertEquals(palendrome, Viper.largestPalendrome(" " + palendrome + " ").trim());
        assertEquals(palendrome, Viper.largestPalendrome(" " + palendrome + "  ").trim());

    }
}