package bohdan.hushcha.sushchak.suggestme.services;


import org.junit.Test;

import static org.junit.Assert.*;

public class AuthServiceTest {

    @Test
    public void TestGetEmailError() {
        String text = "bohdan@gmail.com";
        String expected = null;
        String actual = AuthService.GetEmailError(text);
        assertEquals(expected, actual);

        text = "bohdangmail.com";
        expected = "Email not valid";
        actual = AuthService.GetEmailError(text);
        assertEquals(expected, actual);

        text = "";
        expected = "Email is empty";
        actual = AuthService.GetEmailError(text);
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetPasswordError(){
        String text = "123qweQ@2";
        String expected = null;
        String actual = AuthService.GetPasswordError(text);
        assertEquals(expected, actual);

        text = "123456";
        expected = "Password to short min 8 symbols";
        actual = AuthService.GetPasswordError(text);
        assertEquals(expected, actual);

        text = "";
        expected = "Password is empty";
        actual = AuthService.GetPasswordError(text);
        assertEquals(expected, actual);

        text = "123456789";
        expected = "Password not valid";
        actual = AuthService.GetPasswordError(text);
        assertEquals(expected, actual);
    }

}