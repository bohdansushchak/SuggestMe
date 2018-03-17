package bohdan.hushcha.sushchak.suggestme.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import bohdan.hushcha.sushchak.suggestme.MainActivity;
import bohdan.hushcha.sushchak.suggestme.R;
import bohdan.hushcha.sushchak.suggestme.Services.AuthUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etEmail) TextInputEditText etUserEmail;
    @BindView(R.id.etPassword) TextInputEditText etPassword;
    @BindView(R.id.etRePassword) TextInputEditText etRepeatPassword;

    private AuthUtils authUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(RegisterActivity.this);

        authUtils = new AuthUtils(RegisterActivity.this);
    }

    @OnClick(R.id.btnRegister)
    public void clickRegister(View view) {
        String email = etUserEmail.getText().toString();
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
            Toast.makeText(RegisterActivity.this, regEx.getEmailError() + regEx.getPasswordError() + regEx.getRepeatError(), Toast.LENGTH_LONG).show();
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
