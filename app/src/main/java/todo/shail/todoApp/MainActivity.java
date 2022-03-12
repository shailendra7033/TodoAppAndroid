package todo.shail.todoApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView slogan;
    ImageView splashPic;
    Animation fadeAnim,bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Animation
        fadeAnim = AnimationUtils.loadAnimation(this,R.anim.fadeanim);
        bottomAnim =AnimationUtils.loadAnimation(this,R.anim.bottomanim);

        // Hooks
        slogan=findViewById(R.id.txt_solgan);
        splashPic=findViewById(R.id.img_splashpic);

        //Set Animation with hooks
        slogan.setAnimation(bottomAnim);
        splashPic.setAnimation(fadeAnim);

        // using handler class we use runnable thread

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent =new Intent(MainActivity.this,StartActivity.class);
                    startActivity(intent);
                    finish();
            }
        },2000);

    }
}