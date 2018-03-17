package bohdan.hushcha.sushchak.suggestme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import bohdan.hushcha.sushchak.suggestme.activities.LoginActivity;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.signOut();
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(MainActivity.this, mAuth.getCurrentUser().getEmail() + "Has already logined ", Toast.LENGTH_LONG).show();
        } else {
            MainActivity.this.finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
