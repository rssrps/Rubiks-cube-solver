package raman.in.alan;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMoves extends AppCompatActivity {

    private Bundle b;
    private int [] array;
    private int movecount=1,curr=0;
    private ImageView img,solved;
    private Animation in,out;
    private SeekBar speed;
    private int delay = 50;
    private ProgressBar progress;
    private TextView percent,textsolved,dj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_moves);

        b = getIntent().getExtras();
        array = b.getIntArray("array");
        movecount = b.getInt("movecount");

        solved = (ImageView) findViewById(R.id.solved);
        solved.setVisibility(View.INVISIBLE);
        textsolved = (TextView) findViewById(R.id.textSolved);
        textsolved.setVisibility(View.INVISIBLE);
        dj = (TextView) findViewById(R.id.adj);


        in = new AlphaAnimation(0.2f, 1.0f);
        in.setDuration(1100);
        out = new AlphaAnimation(1.0f, 0.2f);
        out.setDuration(500);

        percent = (TextView) findViewById(R.id.percent);
        progress = (ProgressBar) findViewById(R.id.progress);

        speed = (SeekBar) findViewById(R.id.speed);
        speed.setProgress(35);

        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.letsbegin);


        if(curr>=movecount){

            solved.setVisibility(View.VISIBLE);
            solved.startAnimation(in);
            textsolved.setVisibility(View.VISIBLE);
            textsolved.startAnimation(in);
            img.setVisibility(View.INVISIBLE);
            speed.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.INVISIBLE);
            percent.setVisibility(View.INVISIBLE);
            dj.setVisibility(View.INVISIBLE);
        }
        else {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    img.setVisibility(View.INVISIBLE);
                    if (curr < movecount) {
                        img.setVisibility(View.VISIBLE);
                        display(array[curr++]);
                        percent.setText(String.valueOf(curr * 100 / movecount) + "%");
                        progress.setProgress(curr * 100 / movecount);
                        loop();
                    } else {
                        solved.setVisibility(View.VISIBLE);
                        solved.startAnimation(in);
                        textsolved.setVisibility(View.VISIBLE);
                        textsolved.startAnimation(in);
                        img.setVisibility(View.INVISIBLE);
                        speed.setVisibility(View.INVISIBLE);
                        progress.setVisibility(View.INVISIBLE);
                        percent.setVisibility(View.INVISIBLE);
                        dj.setVisibility(View.INVISIBLE);
                    }

                    //Do something after 100ms
                }
            }, 2500);

        }






        final String[] value = new String[1];
        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                delay = progress;
                value[0] = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


    }

    private void display(int pos) {

        img.startAnimation(out);

        if(pos==1)
            img.setImageResource(R.drawable.fc);
        if(pos==2)
            img.setImageResource(R.drawable.fa);
        if(pos==3)
            img.setImageResource(R.drawable.tc);
        if(pos==4)
            img.setImageResource(R.drawable.ta);
        if(pos==5)
            img.setImageResource(R.drawable.bac);
        if(pos==6)
            img.setImageResource(R.drawable.baa);
        if(pos==7)
            img.setImageResource(R.drawable.boc);
        if(pos==8)
            img.setImageResource(R.drawable.boa);
        if(pos==9)
            img.setImageResource(R.drawable.lc);
        if(pos==10)
            img.setImageResource(R.drawable.la);
        if(pos==11)
            img.setImageResource(R.drawable.rc);
        if(pos==12)
            img.setImageResource(R.drawable.ra);

        img.startAnimation(in);
    }

    private void loop(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(curr < movecount){
                    img.setVisibility(View.VISIBLE);
                    display(array[curr++]);
                    percent.setText(String.valueOf(curr*100/movecount) + "%");
                    progress.setProgress(curr*100/movecount);
                    loop();
                }

                else {
                    solved.setVisibility(View.VISIBLE);
                    solved.startAnimation(in);
                    textsolved.setVisibility(View.VISIBLE);
                    textsolved.startAnimation(in);
                    img.setVisibility(View.INVISIBLE);
                    speed.setVisibility(View.INVISIBLE);
                    progress.setVisibility(View.INVISIBLE);
                    percent.setVisibility(View.INVISIBLE);
                    dj.setVisibility(View.INVISIBLE);
                    return;
                }
                //Do something after 100ms
            }
        }, 5000 - 40*delay);
    }

}
