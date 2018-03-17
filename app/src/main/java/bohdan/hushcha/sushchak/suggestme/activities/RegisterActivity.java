package bohdan.hushcha.sushchak.suggestme.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.Services.AuthUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etEmail) TextInputEditText etEmail;
    @BindView(R.id.etPassword) TextInputEditText etPassword;
    @BindView(R.id.etRePassword) TextInputEditText etRepeatPassword;

    @BindView(R.id.emailLayout) TextInputLayout emailLayout;
    @BindView(R.id.passwordLayout) TextInputLayout passwordLayout;
    @BindView(R.id.rePasswordLayout) TextInputLayout rePasswordLayout;

    private AuthUtils authUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(RegisterActivity.this);

        authUtils = new AuthUtils(RegisterActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        InitTextWatcher();
    }

    private void InitTextWatcher(){
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String emailError = authUtils.GetEmailError(etEmail.getText().toString());
                emailLayout.setError(emailError);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String passwordError = authUtils.GetPasswordError(etPassword.getText().toString());
                passwordLayout.setError(passwordError);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        etRepeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = etPassword.getText().toString();
                String rePassword = etRepeatPassword.getText().toString();

                String rePasswordError = !password.equals(rePassword) ?
                        getString(R.string.alert_repassword_notequals) : null;

                rePasswordLayout.setError(rePasswordError);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    @OnClick(R.id.btnRegister)
    public void clickRegister(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String repeatPassword = etRepeatPassword.getText().toString();

        try {

           Task<AuthResult> authResult = authUtils.SignUp(email, password, repeatPassword);

           authResult.addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful() && task.getResult().getUser() != null){
                       startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                       RegisterActivity.this.finish();
                   }
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
               }
           });
        }

        catch (AuthUtils.RegisterException regEx){
            String emailError = regEx.getEmailError();
            String passwordError = regEx.getPasswordError();
            String rePasswordError = regEx.getRepeatError();

            emailLayout.setError(emailError);
            passwordLayout.setError(passwordError);
            rePasswordLayout.setError(rePasswordError);
        }

        finally {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RegisterActivity.this.finish();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

}
