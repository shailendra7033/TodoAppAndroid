package todo.shail.todoApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText emailRegister,passwordRegister,userName;
    Button btnRegister;
    FirebaseAuth registerAuth;
    String EmailRegister,PasswordRegister,username;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // instance of firebase
        registerAuth=FirebaseAuth.getInstance();
        //hooks
        passwordRegister=findViewById(R.id.password_field_reg);
        emailRegister=findViewById(R.id.email_field_reg);
        btnRegister=findViewById(R.id.btn_register);
        userName=findViewById(R.id.user_name);

        // register button click on listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailRegister=emailRegister.getText().toString();
                PasswordRegister=passwordRegister.getText().toString();
                username=userName.getText().toString();
                registerUser(EmailRegister,PasswordRegister,username);

            }


        });


    }

    public void registerUser(String email, String password,String username){
        registerAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=registerAuth.getCurrentUser();
                    myRef=database.getReference("users").child(username);
                    myRef.setValue("hello");
                    Intent intent =new Intent(RegisterActivity.this,TodoActivity.class);
                    intent.putExtra("userHandle", username);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Unsuccessfull Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}