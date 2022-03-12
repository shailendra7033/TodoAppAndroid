package todo.shail.todoApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    // xml input field
    TextView signUp;
    Button btn_login;
    EditText email, password;
    String Email, Password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //linking to xml hooks
        signUp = findViewById(R.id.txt_sign_up);
        btn_login = findViewById(R.id.btn_login);
        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);

        // on click listener on sign up
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });// completion of signup on click

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // on click listener on login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sign in existing user
                Email = email.getText().toString();
                Password = password.getText().toString();
                mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(StartActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(StartActivity.this, TodoActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(StartActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });// end of login

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(StartActivity.this, TodoActivity.class);
            startActivity(intent);
        }
    }
}