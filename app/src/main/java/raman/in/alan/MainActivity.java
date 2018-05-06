package raman.in.alan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/*
    Color Specifications
      1. Blue                   (Front)
      2. Red                    (Top)
      3. Green                  (Back)
      4. Orange                 (Bottom)
      5. Yellow                 (Left)
      6. White                  (Right)

    Algorithm Start State
        -> Blue in front
        -> Red at top
 */

/* Moves Numbering is as follows

1.  fc
2.  fa
3.  tc
4.  ta
5.  bac
6.  baa
7.  boc
8.  boa
9.  lc
10. la
11. rc
12. ra

 */

public class MainActivity extends AppCompatActivity {

    private int front[]={6,6,6,6,1,6,6,6,6};        //  Blue centered front array
    private int top[]={6,6,6,6,2,6,6,6,6};          //  Red centered top array
    private int back[]={6,6,6,6,3,6,6,6,6};         //  Green centered back array
    private int bottom[]={6,6,6,6,4,6,6,6,6};       //  Orange centered bottom array
    private int left[]={6,6,6,6,5,6,6,6,6};         //  Yellow centered left array
    private int right[]={6,6,6,6,6,6,6,6,6};        //  White centered right array

    //Input image views
    private ImageView ipBlue,ipRed,ipGreen,ipOrange,ipYellow,ipWhite,question;

    //Display Image views
    private ImageView[] c;
    //Textview to display current face name
    private TextView faceText;

    //index of block which is to be taken as input currently.
    private int pos=0;
    //Pointer to array for which input is being taken currently.
    private int arrnum=1;
    //Pointer to array which is to be displayed currently.
    private int disparr=1;
    //Button for showing next face
    private Button nextBtn;
    //Ready button
    private Button readyBtn;

    //Move array
    private int moveArr[];
    //moveArr pointer
    private int ptr_moveArr=0;

    private int count=0;
    private int i=0;
    private int stepEnd=0;
    private Animation out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipBlue = (ImageView) findViewById(R.id.ip_blue);
        ipRed = (ImageView) findViewById(R.id.ip_red);
        ipGreen = (ImageView) findViewById(R.id.ip_green);
        ipOrange = (ImageView) findViewById(R.id.ip_orange);
        ipYellow = (ImageView) findViewById(R.id.ip_yellow);
        ipWhite = (ImageView) findViewById(R.id.ip_white);        // Declaring input Image views

        faceText = (TextView) findViewById(R.id.faceText);      // Declaring faceText
        showFaceName();

        nextBtn = (Button) findViewById(R.id.nextBtn);          // Declaring next button
        readyBtn = (Button) findViewById(R.id.readyBtn);        // Declaring ready button

        question = (ImageView) findViewById(R.id.question);    // For input help

        moveArr = new int[900];
        out = new AlphaAnimation(0.0f, 1.0f);
        out.setDuration(200);


        c = new ImageView[9];
        c[0] = (ImageView) findViewById(R.id.c0);
        c[1] = (ImageView) findViewById(R.id.c1);
        c[2] = (ImageView) findViewById(R.id.c2);
        c[3] = (ImageView) findViewById(R.id.c3);
        c[4] = (ImageView) findViewById(R.id.c4);
        c[5] = (ImageView) findViewById(R.id.c5);
        c[6] = (ImageView) findViewById(R.id.c6);
        c[7] = (ImageView) findViewById(R.id.c7);
        c[8] = (ImageView) findViewById(R.id.c8);                 // Declaring display Image views

        c[0].setAlpha( (float) 0.8);

        displayFace();          // Display the initial orientation of cube

        // ---------------------- Listeners for taking input -----------------------------------------

        ipBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9) {

                    ipBlue.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 1;
                    if (arrnum == 2)
                        top[pos++] = 1;
                    if (arrnum == 3)
                        back[pos++] = 1;
                    if (arrnum == 4)
                        bottom[pos++] = 1;
                    if (arrnum == 5)
                        left[pos++] = 1;
                    if (arrnum == 6)
                        right[pos++] = 1;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                       c[8].setAlpha((float)1);

                }

                if(pos==4)
                    pos++;
                displayFace();

            }
        });

        ipRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pos!=9) {

                    ipRed.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 2;
                    if (arrnum == 2)
                        top[pos++] = 2;
                    if (arrnum == 3)
                        back[pos++] = 2;
                    if (arrnum == 4)
                        bottom[pos++] = 2;
                    if (arrnum == 5)
                        left[pos++] = 2;
                    if (arrnum == 6)
                        right[pos++] = 2;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                        c[8].setAlpha((float)1);

                }
                if(pos==4)
                    pos++;
                displayFace();

            }
        });

        ipGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pos!=9) {
                    ipGreen.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 3;
                    if (arrnum == 2)
                        top[pos++] = 3;
                    if (arrnum == 3)
                        back[pos++] = 3;
                    if (arrnum == 4)
                        bottom[pos++] = 3;
                    if (arrnum == 5)
                        left[pos++] = 3;
                    if (arrnum == 6)
                        right[pos++] = 3;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                        c[8].setAlpha((float)1);

                }

                if(pos==4)
                    pos++;

                displayFace();

            }
        });


        ipOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(pos!=9) {
                    ipOrange.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 4;
                    if (arrnum == 2)
                        top[pos++] = 4;
                    if (arrnum == 3)
                        back[pos++] = 4;
                    if (arrnum == 4)
                        bottom[pos++] = 4;
                    if (arrnum == 5)
                        left[pos++] = 4;
                    if (arrnum == 6)
                        right[pos++] = 4;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                        c[8].setAlpha((float)1);

                }
                if(pos==4)
                    pos++;

                displayFace();

            }
        });


        ipYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9) {
                    ipYellow.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 5;
                    if (arrnum == 2)
                        top[pos++] = 5;
                    if (arrnum == 3)
                        back[pos++] = 5;
                    if (arrnum == 4)
                        bottom[pos++] = 5;
                    if (arrnum == 5)
                        left[pos++] = 5;
                    if (arrnum == 6)
                        right[pos++] = 5;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                        c[8].setAlpha((float)1);

                }
                if(pos==4)
                    pos++;

                displayFace();

            }
        });

        ipWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9) {

                    ipWhite.startAnimation(out);
                    if (arrnum == 1)
                        front[pos++] = 6;
                    if (arrnum == 2)
                        top[pos++] = 6;
                    if (arrnum == 3)
                        back[pos++] = 6;
                    if (arrnum == 4)
                        bottom[pos++] = 6;
                    if (arrnum == 5)
                        left[pos++] = 6;
                    if (arrnum == 6)
                        right[pos++] = 6;


                    if(pos<=8){

                        if(pos==4) {
                            c[5].setAlpha((float) 0.8);
                            c[3].setAlpha((float) 1);
                        }
                        else {
                            c[pos].setAlpha((float) 0.8);
                            c[pos-1].setAlpha((float) 1);
                        }
                    }
                    else
                        c[8].setAlpha((float)1);

                }
                if(pos==4)
                    pos++;

                displayFace();

            }
        });



        //------------------------------listeners for cube image views------------------------------

        c[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 0;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 1;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 2;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 3;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 5;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 6;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 7;
                c[pos].setAlpha( (float) 0.8);
            }
        });

        c[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pos!=9)
                c[pos].setAlpha((float)1);
                pos = 8;
                c[pos].setAlpha( (float) 0.8);
            }
        });



        //------------------------------------------------------------------------------------------


        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showImage();
            }
        });

        //-------------------------------- Listener for next button -------------------------------

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrnum  =   ((arrnum)%6) + 1;
                disparr =   ((disparr)%6)+ 1;
                pos=0;

                for(int vj=0;vj<=8;vj++)
                    c[vj].setAlpha( (float)1);

                if(arrnum!=1)
                 c[0].setAlpha( (float)0.8);
                showFaceName();
                displayFace();
            }
        });



        //--------------------------------Listener for ready button --------------------------------

        readyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(verifyCube()==1) {

                    i = 0;

                    step1();
                    makeMoves(0, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step2();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step3();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step4();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step5();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step6();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step7();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step8();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step9();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step10();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step11();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step12();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step13();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step14();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step15();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step16();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step17();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step18();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step18();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step19();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step19();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step19();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    step19();
                    makeMoves(stepEnd, ptr_moveArr);
                    stepEnd = ptr_moveArr;

                    Intent i2 = new Intent(MainActivity.this, DisplayMoves.class);
                    i2.putExtra("array", moveArr);
                    i2.putExtra("movecount", ptr_moveArr);
                    startActivity(i2);

                }
                else
                    Toast.makeText(getApplicationContext(),"Please enter a valid cube!",Toast.LENGTH_SHORT).show();


            }
        });

}

    private void makeMoves(int x1, int x2) {


        int j=x1;

        while(j<x2) {

            if (moveArr[j] == 1) {

                fc();
            }
            if (moveArr[j] == 3) {

                tc();
            }
            if (moveArr[j] == 5) {

                bac();
            }
            if (moveArr[j] == 7) {

                boc();
            }
            if (moveArr[j] == 9) {

                lc();
            }
            if (moveArr[j] == 11) {

                rc();
            }
            if (moveArr[j] == 2) {

                fa();
            }
            if (moveArr[j] == 4) {

                ta();
            }
            if (moveArr[j] == 6) {

                baa();
            }
            if (moveArr[j] == 8) {

                boa();
            }
            if (moveArr[j] == 10) {

                la();
            }
            if (moveArr[j] == 12) {

                ra();
            }


            j++;

        }

    }

    //-------------------------------- Function to display face colors -----------------------------
    private void displayFace(){

        // To display front face:
        if(disparr==1){

            for(int i=0;i<=8;i++){

                if(front[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(front[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(front[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(front[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(front[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(front[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }


        // To display top face:
        if(disparr==2){

            for(int i=0;i<=8;i++){

                if(top[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(top[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(top[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(top[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(top[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(top[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }

        // To display back face:
        if(disparr==3){

            for(int i=0;i<=8;i++){

                if(back[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(back[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(back[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(back[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(back[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(back[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }

        // To display bottom face:
        if(disparr==4){

            for(int i=0;i<=8;i++){

                if(bottom[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(bottom[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(bottom[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(bottom[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(bottom[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(bottom[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }

        // To display left face:
        if(disparr==5){

            for(int i=0;i<=8;i++){

                if(left[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(left[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(left[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(left[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(left[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(left[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }

        // To display right face:
        if(disparr==6){

            for(int i=0;i<=8;i++){

                if(right[i]==1)
                    c[i].setImageResource(R.drawable.blue);
                if(right[i]==2)
                    c[i].setImageResource(R.drawable.red);
                if(right[i]==3)
                    c[i].setImageResource(R.drawable.green);
                if(right[i]==4)
                    c[i].setImageResource(R.drawable.orange);
                if(right[i]==5)
                    c[i].setImageResource(R.drawable.yellow);
                if(right[i]==6)
                    c[i].setImageResource(R.drawable.white);
            }

        }


    }

    //function to show the name of current face in display in textview

    private void showFaceName(){

        if(arrnum==1)
            faceText.setText("Front");
        if(arrnum==2)
            faceText.setText("Top");
        if(arrnum==3)
            faceText.setText("Back");
        if(arrnum==4)
            faceText.setText("Bottom");
        if(arrnum==5)
            faceText.setText("Left");
        if(arrnum==6)
            faceText.setText("Right");

    }

    //------------------------ Functions for 12 possible moves ---------------------------------------
    private void fc(){

        int t;

        t=front[1];
        front[1]=front[3];
        front[3]=front[7];
        front[7]=front[5];
        front[5]=t;

        t=front[0];
        front[0]=front[6];
        front[6]=front[8];
        front[8]=front[2];
        front[2]=t;

        t=top[6];
        top[6]=left[8];
        left[8]=bottom[2];
        bottom[2]=right[0];
        right[0]=t;

        t=top[7];
        top[7]=left[5];
        left[5]=bottom[1];
        bottom[1]=right[3];
        right[3]=t;

        t=top[8];
        top[8]=left[2];
        left[2]=bottom[0];
        bottom[0]=right[6];
        right[6]=t;

    }


    // function for front Anti clockwise
    private void fa(){

        int t;

        t=front[1];
        front[1]=front[5];
        front[5]=front[7];
        front[7]=front[3];
        front[3]=t;

        t=front[0];
        front[0]=front[2];
        front[2]=front[8];
        front[8]=front[6];
        front[6]=t;

        t=top[6];
        top[6]=right[0];
        right[0]=bottom[2];
        bottom[2]=left[8];
        left[8]=t;

        t=top[7];
        top[7]=right[3];
        right[3]=bottom[1];
        bottom[1]=left[5];
        left[5]=t;

        t=top[8];
        top[8]=right[6];
        right[6]=bottom[0];
        bottom[0]=left[2];
        left[2]=t;

    }

    //Function for top Clockwise
    private void tc(){

        int t;

        t=top[1];
        top[1]=top[3];
        top[3]=top[7];
        top[7]=top[5];
        top[5]=t;

        t=top[0];
        top[0]=top[6];
        top[6]=top[8];
        top[8]=top[2];
        top[2]=t;

        t=back[6];
        back[6]=left[2];
        left[2]=front[2];
        front[2]=right[2];
        right[2]=t;

        t=back[7];
        back[7]=left[1];
        left[1]=front[1];
        front[1]=right[1];
        right[1]=t;

        t=back[8];
        back[8]=left[0];
        left[0]=front[0];
        front[0]=right[0];
        right[0]=t;


    }

    // Function for top Anticlockwise
    private void ta(){

        int t;

        t=top[1];
        top[1]=top[5];
        top[5]=top[7];
        top[7]=top[3];
        top[3]=t;

        t=top[0];
        top[0]=top[2];
        top[2]=top[8];
        top[8]=top[6];
        top[6]=t;

        t=back[6];
        back[6]=right[2];
        right[2]=front[2];
        front[2]=left[2];
        left[2]=t;

        t=back[7];
        back[7]=right[1];
        right[1]=front[1];
        front[1]=left[1];
        left[1]=t;

        t=back[8];
        back[8]=right[0];
        right[0]=front[0];
        front[0]=left[0];
        left[0]=t;

    }

    // function for Back Clockwise
    private void bac(){

        int t;

        t=back[1];
        back[1]=back[3];
        back[3]=back[7];
        back[7]=back[5];
        back[5]=t;

        t=back[0];
        back[0]=back[6];
        back[6]=back[8];
        back[8]=back[2];
        back[2]=t;

        t=bottom[6];
        bottom[6]=left[0];
        left[0]=top[2];
        top[2]=right[8];
        right[8]=t;

        t=bottom[7];
        bottom[7]=left[3];
        left[3]=top[1];
        top[1]=right[5];
        right[5]=t;

        t=bottom[8];
        bottom[8]=left[6];
        left[6]=top[0];
        top[0]=right[2];
        right[2]=t;


    }

    // Function for Back Anticlockwise

    private void baa(){

        int t;

        t=back[1];
        back[1]=back[5];
        back[5]=back[7];
        back[7]=back[3];
        back[3]=t;

        t=back[0];
        back[0]=back[2];
        back[2]=back[8];
        back[8]=back[6];
        back[6]=t;

        t=bottom[6];
        bottom[6]=right[8];
        right[8]=top[2];
        top[2]=left[0];
        left[0]=t;

        t=bottom[7];
        bottom[7]=right[5];
        right[5]=top[1];
        top[1]=left[3];
        left[3]=t;

        t=bottom[8];
        bottom[8]=right[2];
        right[2]=top[0];
        top[0]=left[6];
        left[6]=t;

    }


    //Function for bottom clockwise
    private void boc(){

        int t;

        t=bottom[1];
        bottom[1]=bottom[3];
        bottom[3]=bottom[7];
        bottom[7]=bottom[5];
        bottom[5]=t;

        t=bottom[0];
        bottom[0]=bottom[6];
        bottom[6]=bottom[8];
        bottom[8]=bottom[2];
        bottom[2]=t;

        t=front[6];
        front[6]=left[6];
        left[6]=back[2];
        back[2]=right[6];
        right[6]=t;

        t=front[7];
        front[7]=left[7];
        left[7]=back[1];
        back[1]=right[7];
        right[7]=t;

        t=front[8];
        front[8]=left[8];
        left[8]=back[0];
        back[0]=right[8];
        right[8]=t;

    }


    //Function for bottom Anticlockwise

    private void boa(){

        int t;

        t=bottom[1];
        bottom[1]=bottom[5];
        bottom[5]=bottom[7];
        bottom[7]=bottom[3];
        bottom[3]=t;

        t=bottom[0];
        bottom[0]=bottom[2];
        bottom[2]=bottom[8];
        bottom[8]=bottom[6];
        bottom[6]=t;

        t=front[6];
        front[6]=right[6];
        right[6]=back[2];
        back[2]=left[6];
        left[6]=t;

        t=front[7];
        front[7]=right[7];
        right[7]=back[1];
        back[1]=left[7];
        left[7]=t;

        t=front[8];
        front[8]=right[8];
        right[8]=back[0];
        back[0]=left[8];
        left[8]=t;


    }

    //Function for left clockwise

    private void lc(){

        int t;

        t=left[1];
        left[1]=left[3];
        left[3]=left[7];
        left[7]=left[5];
        left[5]=t;

        t=left[0];
        left[0]=left[6];
        left[6]=left[8];
        left[8]=left[2];
        left[2]=t;

        t=top[0];
        top[0]=back[0];
        back[0]=bottom[0];
        bottom[0]=front[0];
        front[0]=t;

        t=top[3];
        top[3]=back[3];
        back[3]=bottom[3];
        bottom[3]=front[3];
        front[3]=t;

        t=top[6];
        top[6]=back[6];
        back[6]=bottom[6];
        bottom[6]=front[6];
        front[6]=t;

    }

    // function for left anticlockwise

    private void la(){

        int t;

        t=left[1];
        left[1]=left[5];
        left[5]=left[7];
        left[7]=left[3];
        left[3]=t;

        t=left[0];
        left[0]=left[2];
        left[2]=left[8];
        left[8]=left[6];
        left[6]=t;

        t=top[0];
        top[0]=front[0];
        front[0]=bottom[0];
        bottom[0]=back[0];
        back[0]=t;

        t=top[3];
        top[3]=front[3];
        front[3]=bottom[3];
        bottom[3]=back[3];
        back[3]=t;

        t=top[6];
        top[6]=front[6];
        front[6]=bottom[6];
        bottom[6]=back[6];
        back[6]=t;

    }

    //function for right clockwise

    private void rc(){

        int t;

        t=right[1];
        right[1]=right[3];
        right[3]=right[7];
        right[7]=right[5];
        right[5]=t;

        t=right[0];
        right[0]=right[6];
        right[6]=right[8];
        right[8]=right[2];
        right[2]=t;

        t=top[8];
        top[8]=front[8];
        front[8]=bottom[8];
        bottom[8]=back[8];
        back[8]=t;

        t=top[5];
        top[5]=front[5];
        front[5]=bottom[5];
        bottom[5]=back[5];
        back[5]=t;

        t=top[2];
        top[2]=front[2];
        front[2]=bottom[2];
        bottom[2]=back[2];
        back[2]=t;

    }

    //function for right anticlockwise

    private void ra(){

        int t;

        t=right[1];
        right[1]=right[5];
        right[5]=right[7];
        right[7]=right[3];
        right[3]=t;

        t=right[0];
        right[0]=right[2];
        right[2]=right[8];
        right[8]=right[6];
        right[6]=t;

        t=top[8];
        top[8]=back[8];
        back[8]=bottom[8];
        bottom[8]=front[8];
        front[8]=t;

        t=top[5];
        top[5]=back[5];
        back[5]=bottom[5];
        bottom[5]=front[5];
        front[5]=t;

        t=top[2];
        top[2]=back[2];
        back[2]=bottom[2];
        bottom[2]=front[2];
        front[2]=t;

    }


    // function for second layer (To send to left)

    private void toLeft(int mapping){


        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white


        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[10];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[9];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[1];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[2];

    }


    // function for second layer (To send to right)

    private void toRight(int mapping){

        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white

        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[12];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[2];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[1];

    }




    // function for second layer (To send to right)

    private void greenPlus(int mapping){

        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white

        moveArr[ptr_moveArr++]=abc[1];
        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[12];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[2];

    }


    // function for second layer (To send to right)

    private void topAlign(int mapping){

        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white

        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[12];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[12];
    }



// function for top layer (To align to top layer corners)

    private void cornerAlign(int mapping){

        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white

        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[10];
        moveArr[ptr_moveArr++]=abc[3];
        moveArr[ptr_moveArr++]=abc[12];
        moveArr[ptr_moveArr++]=abc[4];
        moveArr[ptr_moveArr++]=abc[9];
    }

    // function for top layer corner orientation

    private void fourMoves(int mapping){

        int [] abc = new int[13];

        if(mapping == 1){
            abc[1] = 3;
            abc[2] = 4;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 7;
            abc[6] = 8;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 9;
            abc[10] = 10;
            abc[11] = 11;
            abc[12] = 12;
        }                           //for red

        if(mapping ==2){

            abc[1] = 9;
            abc[2] = 10;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 11;
            abc[6] = 12;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 7;
            abc[10] =8;
            abc[11] = 3;
            abc[12] = 4;
        }                           //for yellow

        if(mapping==3){

            abc[1] = 7;
            abc[2] = 8;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 3;
            abc[6] = 4;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 11;
            abc[10] = 12;
            abc[11] = 9;
            abc[12] = 10;

        }                           //for orange

        if(mapping==4){

            abc[1] = 11;
            abc[2] = 12;
            abc[3] = 5;
            abc[4] = 6;
            abc[5] = 9;
            abc[6] = 10;
            abc[7] = 1;
            abc[8] = 2;
            abc[9] = 3;
            abc[10] = 4;
            abc[11] = 7;
            abc[12] = 8;

        }                           //for white

        moveArr[ptr_moveArr++]=abc[12];
        moveArr[ptr_moveArr++]=abc[8];
        moveArr[ptr_moveArr++]=abc[11];
        moveArr[ptr_moveArr++]=abc[7];
    }


    private int isAligned(int mn){

        if(mn==0){
            if( (back[0]==3&&left[6]==5&&bottom[6]==4) || (back[0]==5&&left[6]==4&&bottom[6]==3)  || (back[0]==4&&left[6]==3&&bottom[6]==5))
                return 1;
        }
        else if(mn==2){
            if( (back[2]==6&&bottom[8]==3&&right[8]==4) || (back[2]==3&&bottom[8]==4&&right[8]==6) || (back[2]==4&&bottom[8]==6&&right[8]==3))
                return 1;

        }
        else if(mn==6){
            if( (back[6]==3&&left[0]==5&&top[0]==2) || (back[6]==2&&left[0]==3&&top[0]==5) || (back[6]==5&&left[0]==2&&top[0]==3))
            return 1;
        }

        else if(mn==8){
            if( (back[8]==2&&right[2]==3&&top[2]==6) || (back[8]==3&&right[2]==6&&top[2]==2) || (back[8]==6&&right[2]==2&&top[2]==3))
                return 1;
        }

        return 0;
    }




    /*
     Search function to search middle blocks
     This function will return address of the middle block which has color1, color2
     The color1's address will be returned
     address= (face_number*10) + position
     */
    private int search(int color1,int color2){

        //for front face

        if(front[1]==color1&&top[7]==color2)
          return 11;

        if(front[3]==color1&&left[5]==color2)
            return 13;

        if(front[7]==color1&&bottom[1]==color2)
            return 17;

        if(front[5]==color1&&right[3]==color2)
            return 15;



        //for top face

        if(top[1]==color1&&back[7]==color2)
            return 21;

        if(top[3]==color1&&left[1]==color2)
            return 23;

        if(top[7]==color1&&front[1]==color2)
            return 27;

        if(top[5]==color1&&right[1]==color2)
            return 25;


        //for back face

        if(back[1]==color1&&bottom[7]==color2)
            return 31;

        if(back[3]==color1&&left[3]==color2)
            return 33;

        if(back[7]==color1&&top[1]==color2)
            return 37;

        if(back[5]==color1&&right[5]==color2)
            return 35;


        //for bottom face


        if(bottom[1]==color1&&front[7]==color2)
            return 41;

        if(bottom[3]==color1&&left[7]==color2)
            return 43;

        if(bottom[7]==color1&&back[1]==color2)
            return 47;

        if(bottom[5]==color1&&right[7]==color2)
            return 45;


        //for left face

        if(left[1]==color1&&top[3]==color2)
            return 51;

        if(left[3]==color1&&back[3]==color2)
            return 53;

        if(left[7]==color1&&bottom[3]==color2)
            return 57;

        if(left[5]==color1&&front[3]==color2)
            return 55;


        //for right face

        if(right[1]==color1&&top[5]==color2)
            return 61;

        if(right[3]==color1&&front[5]==color2)
            return 63;

        if(right[7]==color1&&bottom[5]==color2)
            return 67;

        if(right[5]==color1&&back[5]==color2)
            return 65;


        return 0;
    }


    /*
     Search function to search corner blocks
     This function will return address of the corner block which has color1, color2 and color3
     The color1's address will be returned
     address= (face_number*10) + position
     */

    private int search1(int color1,int color2,int color3){

        //for front face

        if( (front[0]==color1&&top[6]==color2&&left[2]==color3) || (front[0]==color1&&top[6]==color3&&left[2]==color2 ))
         return 10;

        if( (front[6]==color1&&bottom[0]==color2&&left[8]==color3) || (front[6]==color1&&bottom[0]==color3&&left[8]==color2 ))
            return 16;

        if( (front[8]==color1&&bottom[2]==color2&&right[6]==color3) || (front[8]==color1&&bottom[2]==color3&&right[6]==color2 ))
            return 18;

        if( (front[2]==color1&&top[8]==color2&&right[0]==color3) || (front[2]==color1&&top[8]==color3&&right[0]==color2 ))
            return 12;

        //for top face

        if( (top[0]==color1&&back[6]==color2&&left[0]==color3) || (top[0]==color1&&back[6]==color3&&left[0]==color2 ))
            return 20;

        if( (top[6]==color1&&front[0]==color2&&left[2]==color3) || (top[6]==color1&&front[0]==color3&&left[2]==color2 ))
            return 26;

        if( (top[8]==color1&&front[2]==color2&&right[0]==color3) || (top[8]==color1&&front[2]==color3&&right[0]==color2 ))
            return 28;

        if( (top[2]==color1&&back[8]==color2&&right[2]==color3) || (top[2]==color1&&back[8]==color3&&right[2]==color2 ))
            return 22;


        //for back face

        if( (back[0]==color1&&bottom[6]==color2&&left[6]==color3) || (back[0]==color1&&bottom[6]==color3&&left[6]==color2 ))
            return 30;

        if( (back[6]==color1&&top[0]==color2&&left[0]==color3) || (back[6]==color1&&top[0]==color3&&left[0]==color2 ))
            return 36;

        if( (back[8]==color1&&top[2]==color2&&right[2]==color3) || (back[8]==color1&&top[2]==color3&&right[2]==color2 ))
            return 38;

        if( (back[2]==color1&&bottom[8]==color2&&right[8]==color3) || (back[2]==color1&&bottom[8]==color3&&right[8]==color2 ))
            return 32;


        //for bottom face

        if( (bottom[0]==color1&&front[6]==color2&&left[8]==color3) || (bottom[0]==color1&&front[6]==color3&&left[8]==color2 ))
            return 40;

        if( (bottom[6]==color1&&back[0]==color2&&left[6]==color3) || (bottom[6]==color1&&back[0]==color3&&left[6]==color2 ))
            return 46;

        if( (bottom[8]==color1&&back[2]==color2&&right[8]==color3) || (bottom[8]==color1&&back[2]==color3&&right[8]==color2 ))
            return 48;

        if( (bottom[2]==color1&&front[8]==color2&&right[6]==color3) || (bottom[2]==color1&&front[8]==color3&&right[6]==color2 ))
            return 42;


        //for left face

        if( (left[0]==color1&&back[6]==color2&&top[0]==color3) || (left[0]==color1&&back[6]==color3&&top[0]==color2 ))
            return 50;

        if( (left[6]==color1&&back[0]==color2&&bottom[6]==color3) || (left[6]==color1&&back[0]==color3&&bottom[6]==color2 ))
            return 56;

        if( (left[8]==color1&&front[6]==color2&&bottom[0]==color3) || (left[8]==color1&&front[6]==color3&&bottom[0]==color2 ))
            return 58;

        if( (left[2]==color1&&front[0]==color2&&top[6]==color3) || (left[2]==color1&&front[0]==color3&&top[6]==color2 ))
            return 52;


        //for right face

        if( (right[0]==color1&&front[2]==color2&&top[8]==color3) || (right[0]==color1&&front[2]==color3&&top[8]==color2 ))
            return 60;

        if( (right[6]==color1&&front[8]==color2&&bottom[2]==color3) || (right[6]==color1&&front[8]==color3&&bottom[2]==color2 ))
            return 66;

        if( (right[8]==color1&&back[2]==color2&&bottom[8]==color3) || (right[8]==color1&&back[2]==color3&&bottom[8]==color2 ))
            return 68;

        if( (right[2]==color1&&back[8]==color2&&top[2]==color3) || (right[2]==color1&&back[8]==color3&&top[2]==color2 ))
            return 62;

        return 0;
    }



    //------------------------- Functions for all steps to be performed ----------------------------

    //Function for Blue-Red

    private void step1(){

        int ll=search(1,2);

        if(ll==13){

            moveArr[ptr_moveArr++]=1;

        }

        if(ll==17){

            moveArr[ptr_moveArr++]=1;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==15){

            moveArr[ptr_moveArr++]=2;

        }

        if(ll==21){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=2;

        }

        if(ll==23){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==27){

            moveArr[ptr_moveArr++]=1;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=3;

        }
        if(ll==25){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=2;

        }

        if(ll==31){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=1;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==33){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==37){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=3;

        }

        if(ll==35){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=2;

        }

        if(ll==41){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=2;

        }

        if(ll==43){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==47){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=1;

        }

        if(ll==45){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=2;

        }

        if(ll==51){

            moveArr[ptr_moveArr++]=4;

        }

        if(ll==53){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=4;

        }

        if(ll==57){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=4;

        }

        if(ll==55){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=4;

        }

        if(ll==61){

            moveArr[ptr_moveArr++]=3;

        }

        if(ll==63){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=3;

        }

        if(ll==67){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=3;

        }

        if(ll==65){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=3;

        }



    }

    // Function for Blue-Yellow

    private void step2(){

        int ll=search(1,5);

        if(ll==17){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==15){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==21){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==23){

            moveArr[ptr_moveArr++]=9;
        }

        if(ll==25){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==31){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==35){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==41){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==43){

            moveArr[ptr_moveArr++]=10;

        }

        if(ll==47){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==45){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==51){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==53){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=10;

        }

        if(ll==57){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==55){
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==61){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==63){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==67){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==65){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=9;
        }


    }



    // Function for Blue-orange

    private void step3(){

        int ll=search(1,4);


        if(ll==15){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==21){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=8;
        }

        if(ll==23){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==25){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==31){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;

        }

        if(ll==35){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;

        }

        if(ll==41){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==43){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;

        }

        if(ll==47){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==45){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==51){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=7;

        }

        if(ll==53){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==57){

            moveArr[ptr_moveArr++]=7;
        }

        if(ll==61){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=8;

        }

        if(ll==63){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=8;

        }

        if(ll==67){

            moveArr[ptr_moveArr++]=8;


        }

        if(ll==65){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=8;
        }


    }

    // Function for Blue-white

    private void step4(){

        int ll=search(1,6);

        if(ll==21){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==23){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=4;

        }

        if(ll==25){

            moveArr[ptr_moveArr++]=12;
        }

        if(ll==31){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;

        }

        if(ll==35){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;

        }

        if(ll==43){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;

        }

        if(ll==47){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==45){

            moveArr[ptr_moveArr++]=11;
        }

        if(ll==51){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;

        }

        if(ll==53){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=7;

        }

        if(ll==57){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==61){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==63){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;


        }

        if(ll==67){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=11;


        }

        if(ll==65){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=12;
        }


    }



    //----------------------------------------------- Steps for corner blocks -------------------------------------------------------------

    // function for blue-red-yellow
    private void step5(){

        int ll=search1(1,2,5);

        if(ll==16){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==18){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==12){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==20){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==26){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==28){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==22){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==30){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==36){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==38){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==32){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==40){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==46){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==48){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==42){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==50){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==56){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==58){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==52){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==60){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==66){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==68){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==62){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

    }


    // function for blue-orange-yellow

    private void step6(){

        int ll=search1(1,4,5);


        if(ll==18){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==12){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==20){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==28){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==22){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==30){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==36){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==38){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==32){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==40){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==46){

            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==48){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==42){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==50){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==56){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
        }

        if(ll==58){

            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }


        if(ll==60){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==66){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==68){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
        }

        if(ll==62){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
        }

    }


    //function for blue-orange-white

    private void step7(){

        int ll=search1(1,4,6);

        if(ll==12){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
        }

        if(ll==20){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==28){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==22){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==30){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==36){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==38){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==32){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==46){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==48){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==42){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==50){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==56){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==60){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
        }

        if(ll==66){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==68){

            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==62){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=8;
        }

    }


    //function for blue-red-white

    private void step8(){

        int ll=search1(1,2,6);

        if(ll==20){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==28){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==22){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==30){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==36){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==38){
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==32){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==46){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==48){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
        }


        if(ll==50){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==56){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==60){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==68){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==62){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=12;
        }

    }


    // function for red-yellow


    private void step9(){

        int ll=search(2,5);


        if(ll==21){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==25){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==31){

            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==35){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;

        }

        if(ll==43){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==47){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==45){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==51){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==53){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==57){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==61){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
        }

        if(ll==67){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
        }

        if(ll==65){

            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
        }



    }



    //Function for Red-white

    private void step10(){

        int ll=search(2,6);


        if(ll==21){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
        }


        if(ll==31){

            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==35){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==43){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==47){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==45){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==53){

            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
        }

        if(ll==57){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=9;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=10;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==61){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==67){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
        }

        if(ll==65){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=4;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=3;
        }

    }





    //Function for Orange-white

    private void step11(){

        int ll=search(4,6);


        if(ll==21){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            toLeft(3);
        }


        if(ll==31){

            moveArr[ptr_moveArr++]=5;
            toRight(4);
        }

        if(ll==33){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            toRight(4);
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==35){

            toRight(4);
        }

        if(ll==43){

            toRight(3);
            moveArr[ptr_moveArr++]=7;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=8;
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=12;
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=11;
        }

        if(ll==47){

            toLeft(3);
        }


        if(ll==53){

            moveArr[ptr_moveArr++]=5;
            toLeft(3);
        }

        if(ll==57){

            toRight(3);
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            toLeft(3);
        }

        if(ll==67){

            toLeft(3);
            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            toLeft(3);
        }

        if(ll==65){

            moveArr[ptr_moveArr++]=6;
            toLeft(3);

        }



    }




    //Function for Orange-yellow

    private void step12(){

        int ll=search(4,5);


        if(ll==21){

            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            toRight(3);
        }


        if(ll==31){

            moveArr[ptr_moveArr++]=6;
            toLeft(2);
        }

        if(ll==33){

            toLeft(2);
        }

        if(ll==37){

            moveArr[ptr_moveArr++]=5;
            toLeft(2);
        }

        if(ll==35){

            moveArr[ptr_moveArr++]=6;
            moveArr[ptr_moveArr++]=6;
            toLeft(2);
        }

        if(ll==47){

            toRight(3);
        }


        if(ll==53){

            moveArr[ptr_moveArr++]=5;
            toRight(3);
        }

        if(ll==57){

            toRight(3);
            moveArr[ptr_moveArr++]=5;
            moveArr[ptr_moveArr++]=5;
            toRight(3);
        }

        if(ll==65){

            moveArr[ptr_moveArr++]=6;
            toRight(3);
        }

    }



    //function to make green plus at top face

    private void step13(){

        if(back[1]!=3&&back[3]!=3&&back[7]!=3&&back[5]!=3){

            greenPlus(1);
            greenPlus(3);
            greenPlus(3);
        }

        else if(back[7]==3&&back[5]==3&&back[1]!=3&&back[3]!=3){

            greenPlus(3);
            greenPlus(3);
        }

        else if(back[1]==3&&back[5]==3&&back[7]!=3&&back[3]!=3){

            greenPlus(2);
            greenPlus(2);
        }

        else if(back[1]==3&&back[3]==3&&back[7]!=3&&back[5]!=3){

            greenPlus(1);
            greenPlus(1);
        }

        else if(back[3]==3&&back[7]==3&&back[1]!=3&&back[5]!=3){

            greenPlus(4);
            greenPlus(4);
        }

        else if(back[1]==3&&back[7]==3&&back[5]!=3&&back[3]!=3){

            greenPlus(4);
        }

        else if(back[3]==3&&back[5]==3&&back[1]!=3&&back[7]!=3){

            greenPlus(1);
        }

    }


// function to align red top middle piece

    private void step14() {

        if (right[5] == 2) {
            moveArr[ptr_moveArr++] = 5;
        } else if (bottom[7] == 2) {
            moveArr[ptr_moveArr++] = 5;
            moveArr[ptr_moveArr++] = 5;
        } else if (left[3] == 2) {
            moveArr[ptr_moveArr++] = 6;
        }
    }

    private void step15(){

        if(right[5]!=6&&bottom[7]!=4&&left[3]!=5)
            topAlign(1);

        else if(top[1]==2&&bottom[7]==4&&right[5]!=6&&left[3]!=5){

            topAlign(1);
            topAlign(2);
            moveArr[ptr_moveArr++] = 5;
        }

        else if(top[1]==2&&left[3]==5&&bottom[7]!=4&&right[5]!=6){
            topAlign(3);
            moveArr[ptr_moveArr++] = 5;
        }

        else if(top[1]==2&&right[5]==6 &&left[3]!=5&&bottom[7]!=4){
            topAlign(2);
            moveArr[ptr_moveArr++] = 5;
        }

    }

    private void step16(){

        if(right[5]!=6&&bottom[7]!=4&&left[3]!=5){
            topAlign(1);
        }

    }

    private void step17(){

        if(isAligned(0)==0&&isAligned(2)==0&&isAligned(6)==0&&isAligned(8)==0){
            cornerAlign(1);
        }

    }

    private void step18(){

        if(isAligned(6)==1 && isAligned(0)==0 && isAligned(2)==0 && isAligned(8)==0)
            cornerAlign(2);

        else if(isAligned(2)==1 && isAligned(0)==0 && isAligned(6)==0 && isAligned(8)==0)
            cornerAlign(4);

        else if(isAligned(8)==1 && isAligned(0)==0 && isAligned(6)==0 && isAligned(2)==0)
            cornerAlign(1);

        else if(isAligned(0)==1 && isAligned(8)==0 && isAligned(6)==0 && isAligned(2)==0)
            cornerAlign(3);
    }


    //to orient the last layer corners
    private void step19(){

        int flag=0;
        if(bottom[6]==3){
            fourMoves(3);
            fourMoves(3);
            fourMoves(3);
            fourMoves(3);
            flag=1;
        }
        else if(left[6]==3){
            fourMoves(3);
            fourMoves(3);
            flag=1;
        }
        if(flag==1||back[0]!=3||back[2]!=3||back[6]!=3||back[8]!=3)
            moveArr[ptr_moveArr++] = 6;
    }

    //the final row displacement :)

    private void step20(){

        if(bottom[7]==5)
            moveArr[ptr_moveArr++] = 6;
        else if(bottom[7]==2) {
            moveArr[ptr_moveArr++] = 6;
            moveArr[ptr_moveArr++] = 6;
        }
        else if(bottom[7]==6)
            moveArr[ptr_moveArr++] = 5;

    }



    private int verifyCube(){

        int [] colorcount={0,0,0,0,0,0};
        for(int rss=0;rss<=8;rss++){

            colorcount[front[rss]-1]++;
            colorcount[top[rss]-1]++;
            colorcount[back[rss]-1]++;
            colorcount[bottom[rss]-1]++;
            colorcount[left[rss]-1]++;
            colorcount[right[rss]-1]++;
        }

        for(int ani=0;ani<=5;ani++)
            if(colorcount[ani]!=9)
                return 0;

        return 1;
    }

    public void showImage() {
        final Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        builder.setCanceledOnTouchOutside(true);
        ImageView imageView = new ImageView(this);

        if(disparr==1)
         imageView.setImageResource(R.drawable.cube_red_blue_yellow);
        if(disparr==2)
            imageView.setImageResource(R.drawable.cube_top);
        if(disparr==3)
            imageView.setImageResource(R.drawable.cube_back);
        if(disparr==4)
            imageView.setImageResource(R.drawable.cube_bottom);
        if(disparr==5)
            imageView.setImageResource(R.drawable.cube_left);
        if(disparr==6)
            imageView.setImageResource(R.drawable.cube_right);

        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(int h=0;h<=8;h++){

            front[h]=6;
            top[h]=6;
            back[h]=6;
            bottom[h]=6;
            left[h]=6;
            right[h]=6;

            front[4]=1;
            top[4]=2;
            back[4]=3;
            bottom[4]=4;
            left[4]=5;
            right[4]=6;
        }
        ptr_moveArr=0;
        displayFace();
    }

    @Override public void onBackPressed() { super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); }

}
