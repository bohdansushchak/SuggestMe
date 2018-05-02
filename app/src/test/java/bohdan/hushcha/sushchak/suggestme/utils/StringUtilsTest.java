package bohdan.hushcha.sushchak.suggestme.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void WhiteSpaceTest() {
        String text = "chicken, potato";
        String expected = "chicken,potato";
        String formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = "    chicken, potato    ";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = "    chicken,   potato";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);
    }

    @Test
    public void ToMutchCommasTest() {
        String text = "  , chicken,   potato, , , ,";
        String expected = "chicken,potato";
        String formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = ",,chicken  ,  potato    ";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = "chicken,,meat,,,";
        expected = "chicken,meat";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);
    }

    @Test
    public void SomeNumberTest() {
        String text = "chicken,8,meat,,,";
        String expected = "chicken,meat";
        String formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = ",89,chicken  ,  potato    ";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = "chicken,   potato, 8888";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);
    }

    @Test
    public void OnlyNumberTest() {
        String text = "89,8,meat,,,";
        String expected = "meat";
        String formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = ",89";
        expected = "";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);

        text = "chicken,   potato, 8888";
        expected = "chicken,potato";
        formattedText = StringUtils.FormatIngredients(text);
        assertEquals(expected, formattedText);
    }

    @Test
    public void IsNumberTest() {
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