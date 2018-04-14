package bohdan.hushcha.sushchak.suggestme.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void TestWithSpace() {
        String text = "chicken, potato";
        String expected = "chicken,potato";
        String formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = "    chicken, potato    ";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = "    chicken,   potato";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);
    }

    @Test
    public void CheckToMutchComma() {
        String text = "  , chicken,   potato, , , ,";
        String expected = "chicken,potato";
        String formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = ",,chicken  ,  potato    ";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = "chicken,,meat,,,";
        expected = "chicken,meat";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);
    }

    @Test
    public void CheckWithSomeNumber() {
        String text = "chicken,8,meat,,,";
        String expected = "chicken,meat";
        String formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = ",89,chicken  ,  potato    ";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = "chicken,   potato, 8888";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);
    }

    @Test
    public void CheckOnlyNumber() {
        String text = "89,8,meat,,,";
        String expected = "meat";
        String formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = ",89";
        expected = "";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);

        text = "chicken,   potato, 8888";
        expected = "chicken,potato";
        formatedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formatedText);
    }

    @Test
    public void IsNUmberTest() {
        String text = "897";
        boolean expected = true;
        boolean actual = StringUtils.isNumeric(text);

        assertEquals(expected, actual);

        text = "78 sdf";
        expected = false;
        actual = StringUtils.isNumeric(text);

        assertEquals(expected, actual);

        text = "koka";
        expected = false;
        actual = StringUtils.isNumeric(text);
        assertEquals(expected, actual);

        text = "null";
        expected = false;
        actual = StringUtils.isNumeric(text);
        assertEquals(expected, actual);

        text = "12.5";
        expected = true;
        actual = StringUtils.isNumeric(text);
        assertEquals(expected, actual);
    }

}