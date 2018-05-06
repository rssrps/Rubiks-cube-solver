package raman.in.alan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends AppCompatActivity {


    private ImageView image;
    private Button next,prev;
    private TextView text;
    private int pos = 1;
    private Animation in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        image = (ImageView) findViewById(R.id.image);
        next = (Button) findViewById(R.id.nextInstruction);
        prev = (Button) findViewById(R.id.prevInstruction);
        text = (TextView) findViewById(R.id.texthelp);

        in = new AlphaAnimation(0.4f, 1.0f);
        in.setDuration(700);

        display();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos<6)
                 pos++;
                display();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos>1)
                 pos--;
                display();
            }
        });
    }

    private void display()  {

        if(pos==1){
            prev.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.cube_red_blue_yellow);
            text.setText("To input front face, hold the cube such that blue centered face is in front of you and red centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }
        else if(pos==2){
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.cube_top);
            text.setText("To input top face, rotate the cube towards you such that red centered face is in front of you and green centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }
        else if(pos==3){
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.cube_back);
            text.setText("To input back face, rotate the cube towards you such that green centered face is in front of you and orange centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }
        else if(pos==4){
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.cube_bottom);
            text.setText("To input bottom face, rotate the cube towards you such that orange centered face is in front of you and blue centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }
        else if(pos==5){
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.cube_left);
            text.setText("To input left face, hold the cube such that yellow centered face is in front of you and red centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }
        else if(pos==6){
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
            image.setImageResource(R.drawable.cube_right);
            text.setText("To input right face, hold the cube such that white centered face is in front of you and red centered face is at the top.");
            image.startAnimation(in);
            text.startAnimation(in);
        }

    }

    @Override public void onBackPressed() { super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); }

}
