package todo.shail.todoApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    Button logoutBtn,saveBtn;
    RecyclerView todoList;
    private todoAdapter todoAdopt;
    EditText title,task;
   private FirebaseAuth LogoutAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ,getRef;


   String title1,task1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        logoutBtn=findViewById(R.id.btn_logout);
        todoList=findViewById(R.id.recyler_view);
        title=findViewById(R.id.txt_title);
        task=findViewById(R.id.txt_summary);
        saveBtn=findViewById(R.id.btn_save);

        todoList.setLayoutManager(new LinearLayoutManager(this));

       ArrayList<String> dataList=new ArrayList<>();
        todoAdopt=new todoAdapter(dataList,TodoActivity.this);
        todoList.setAdapter(todoAdopt);
        FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
        String uid= userProfile.getUid();
        getRef=database.getReference("users").child(uid);
        getRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot datasnapshot:snapshot.getChildren()){
                    dataList.add(datasnapshot.getValue().toString());

                }
                todoAdopt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //firebase instance
LogoutAuth=FirebaseAuth.getInstance();

// logout function
        FirebaseUser user=LogoutAuth.getCurrentUser();
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutAuth.signOut();
                Intent intent=new Intent(TodoActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });// end logout set on listener

// firebase main data saving technique
     saveBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             title1=title.getText().toString();
             task1=task.getText().toString();
             FirebaseData userData=new FirebaseData(task1);
//             FirebaseUser user=LogoutAuth.getCurrentUser();
             FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
             if(userProfile!=null) {
                 String uid= userProfile.getUid();
                 Intent intent = getIntent();
                 String str = intent.getStringExtra("userHandle");
                 myRef = database.getReference("users").child(uid).child(title1);
                 myRef.setValue(userData);
                 myRef = database.getReference("users").child(uid).child("username");
                 myRef.setValue(str);



                 // retireving from database
                getRef=database.getReference("users").child(uid);
                getRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dataList.clear();
                        for (DataSnapshot datasnapshot:snapshot.getChildren()){
                           dataList.add(datasnapshot.getValue().toString());

                        }
                        todoAdopt.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });// end of add value listener
                title.getEditableText().clear();
                task.getEditableText().clear();

             }

         }
     });

    }
}