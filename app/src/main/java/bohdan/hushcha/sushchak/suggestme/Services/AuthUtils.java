package bohdan.hushcha.sushchak.suggestme.Services;
import android.content.Context;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bohdan.hushcha.sushchak.suggestme.R;

public class AuthUtils {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
                    Pattern.CASE_INSENSITIVE);

    private FirebaseAuth mAuth;
    private Context context;

    public AuthUtils(Context context) {

        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> SignUp(@NonNull String email, @NonNull String password, @NonNull String rePassword)
            throws RegisterException {

        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        Matcher passwordMatcher = VALID_PASSWORD_REGEX.matcher(password);

        if(!emailMatcher.find() || !passwordMatcher.find() || !password.equals(rePassword)){

            String emailError = GetEmailError(email);
            String passwordError = GetPasswordError(password);
            String repeatPassword = null;

            if(!password.equals(rePassword))
                repeatPassword = context.getString(R.string.alert_repassword_notequals);

            throw new RegisterException(emailError, passwordError, repeatPassword);
        }

        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> SignIn(@NonNull String email, @NonNull String password)
            throws RegisterException {

        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if(!emailMatcher.find() || email.length() == 0 || password.length() == 0){

            String emailError = GetEmailError(email);
            String passwordError = null;

            if(password.length() == 0)
                passwordError = context.getString(R.string.alert_empty_password);

            throw new RegisterException(emailError, passwordError, null);
        }

        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public String GetEmailError(String email){
        String emailError;
        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if(email.length() == 0)
            emailError = context.getString(R.string.alert_empty_email);
        else if(!emailMatcher.find())
            emailError = context.getString(R.string.alert_email_notvalid);
        else emailError = null;

        return  emailError;
    }

    public String GetPasswordError(String password){
        String passwordError;

        Matcher passwordMatcher = VALID_PASSWORD_REGEX.matcher(password);
        if(password.length() == 0)
            passwordError = context.getString(R.string.alert_empty_password);
        else if(password.length() < 8)
            passwordError = context.getString(R.string.alert_password_toshort);
        else if(!passwordMatcher.find())
            passwordError = context.getString(R.string.alert_password_notvalid);
        else passwordError = null;

        return  passwordError;
    }

    public class RegisterException extends Exception{
        private String EmailError;
        private String PasswordError;
        private String RepeatError;

        public RegisterException(String emailError, String passwordError, String repeatError) {

            EmailError = emailError;
            PasswordError = passwordError;
            RepeatError = repeatError;
        }

        public String getEmailError() {
            return EmailError;
        }

        public String getPasswordError() {
            return PasswordError;
        }

        public String getRepeatError() {
            return RepeatError;
        }
    }
}



