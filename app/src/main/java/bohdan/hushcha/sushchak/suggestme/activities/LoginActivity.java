package bohdan.hushcha.sushchak.suggestme.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bohdan.hushcha.sushchak.suggestme.MainActivity;
import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.Services.AuthUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    //private AuthLoginUtils authLoginUtils;

    @BindView(R.id.etEmail_Login) TextInputEditText editTextUsername;
    @BindView(R.id.etPassword_Login) TextInputEditText editTextPassword;

    private AuthUtils authUtils;

    private GoogleApiClient mGoogleApiClient;

    private static final int REQUEST_CODE_SING_GOOGLE = 555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);

        authUtils = new AuthUtils(LoginActivity.this);
    }

    private void SignIn() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        try {
            Task<AuthResult> authResult = authUtils.SignIn(username, password);

            authResult.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful() && task.getResult().getUser() != null) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LoginActivity.this.finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (AuthUtils.RegisterException regEx) {
            Toast.makeText(LoginActivity.this, regEx.getEmailError() + regEx.getPasswordError() + regEx.getRepeatError(), Toast.LENGTH_LONG).show();
        } finally {

        }
    }

    @OnClick({R.id.sing_in_button, R.id.btnLogin, R.id.tvRegister_Login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {
                SignIn();
                break;
            }

            case R.id.tvRegister_Login: {
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            }

            case R.id.sing_in_button: {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, REQUEST_CODE_SING_GOOGLE);
                break;
            }
        }
    }

}
