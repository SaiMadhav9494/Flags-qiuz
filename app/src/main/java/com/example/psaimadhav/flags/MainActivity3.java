package com.example.psaimadhav.flags;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    TextView question, result, choose;
    Button opt[]=new Button[4];
    ImageView im;
    int i,m, n=-1,k=0,x=0;
    int width, height,qno,count;
    AssetManager mgr;
    ArrayList<String> regions= new ArrayList<String>(4);
    boolean[] checked_regions= new boolean[5];
    String[] flag_list;
    Animation rotateit;
    Animator shakeit,shakeit1,shakeit2,shakeit3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        question=(TextView)findViewById(R.id.textView8);
        result=(TextView)findViewById(R.id.textView9);
        choose=(TextView)findViewById(R.id.textView10);
        opt[0]=(Button) findViewById(R.id.button5);
        opt[1]=(Button) findViewById(R.id.button6);
        opt[2]=(Button) findViewById(R.id.button7);
        opt[3]=(Button) findViewById(R.id.button8);
        im=(ImageView)findViewById(R.id.imageView6);
        rotateit= AnimationUtils.loadAnimation(this,R.anim.rotate);
        shakeit=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.shake);
        shakeit1=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.shake);
        shakeit2=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.shake);
        shakeit3=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.shake);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        setparameters();
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            regions= extras.getStringArrayList("list3");
            checked_regions = extras.getBooleanArray("list4");
            qno=extras.getInt("question");
            count=extras.getInt("count");
        }
        question.setText("Question:"+String.valueOf(qno)+"of 10");
        //for (i=1;i<=10;i++){
        //if(i%2!=0){
        for(i=0;i<checked_regions.length;i++)
        {
            if(checked_regions[i]){
                n++;
            }
        }
                //=getIntent().getExtras().getInt("list4");
        Random rand= new Random();
        final int nn= rand.nextInt(n);
         m= rand.nextInt(16);
        mgr= getAssets();
        flag_list= new String[4];
        try {
            flag_list = mgr.list(regions.get(nn));
            InputStream image = mgr.open(regions.get(nn) + "/" + flag_list[m]);
            Drawable drawable = Drawable.createFromStream(image, null);
            im.setImageDrawable(drawable);
            qno++;

        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("list","folder not found");
        }

        opt[nn].setText(flag_list[m].substring(flag_list[m].indexOf('-')+1).replace(".png",""));
        k=0;
        while(k<4)
        {
            if(k==nn)
            {
                k++;
            }
            else
            {
                x= rand.nextInt(16);
                opt[k].setText(flag_list[x].substring(flag_list[x].indexOf('-')+1).replace(".png",""));
                k++;
            }
        }

        opt[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(opt[0].getText()==opt[nn].getText()){
                    result.setText("Correct");
                    result.setTextColor(Color.GREEN);
                    im.startAnimation(rotateit);
                    delay();
                }
                else
                {
                    result.setText("Incorrect");
                    result.setTextColor(Color.RED);
                    delay2();
                    shakeit.setTarget(im);
                    shakeit.start();
                    count++;
                }
            }
        });
        opt[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(opt[1].getText()==opt[nn].getText()){
                    result.setText("Correct");
                    result.setTextColor(Color.GREEN);
                    im.startAnimation(rotateit);
                    delay();
                }
                else
                {
                    result.setText("Incorrect");
                    result.setTextColor(Color.RED);
                    delay2();
                    shakeit1.setTarget(im);
                    shakeit1.start();
                    count++;
                }
            }
        });
        opt[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(opt[2].getText()==opt[nn].getText()){
                    result.setText("Correct");
                    result.setTextColor(Color.GREEN);
                    im.startAnimation(rotateit);
                    delay();
                }
                else
                {
                    result.setText("Incorrect");
                    result.setTextColor(Color.RED);
                    delay2();
                    shakeit2.setTarget(im);
                    shakeit2.start();
                    count++;
                }
            }
        });
        opt[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(opt[3].getText()==opt[nn].getText()){
                    result.setText("Correct");
                    result.setTextColor(Color.GREEN);
                    im.startAnimation(rotateit);
                    delay();
                }
                else
                {
                    result.setText("Incorrect");
                    result.setTextColor(Color.RED);
                    delay2();
                    shakeit3.setTarget(im);
                    shakeit3.start();
                    count++;
                }
            }
        });

    }
    public void delay2(){
        opt[0].setEnabled(false);
        opt[1].setEnabled(false);
        opt[2].setEnabled(false);
        opt[3].setEnabled(false);
        Handler hx= new Handler();
        hx.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity3.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        opt[0].setEnabled(true);
                        opt[1].setEnabled(true);
                        opt[2].setEnabled(true);
                        opt[3].setEnabled(true);

                    }
                });
            }
        },3000);
    }
    public void delay(){
        opt[0].setEnabled(false);
        opt[1].setEnabled(false);
        opt[2].setEnabled(false);
        opt[3].setEnabled(false);
        Handler hx= new Handler();
        hx.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity3.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Intent bb= new Intent(MainActivity3.this,MainActivity2.class);
                        Bundle extras = new Bundle();
                        extras.putBooleanArray("list2", checked_regions);
                        extras.putInt("question",qno);
                        extras.putInt("count",count);
                        bb.putExtras(extras);
                        //setResult(100,bb);
                      //  bb.putExtra("list6",qno);
                        startActivity(bb);
                    }
                });
            }
        },3000);
    }
    public void setparameters(){
        FrameLayout.LayoutParams t1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        t1.setMargins((int)(width*0),(int)(height*.025),0,0);
        question.setLayoutParams(t1);

        FrameLayout.LayoutParams t2= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        t2.setMargins((int)(width*.35),(int)(height*.13),0,0);
        result.setLayoutParams(t2);

        FrameLayout.LayoutParams t3= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        t3.setMargins((int)(width*.27),(int)(height*.55),0,0);
        choose.setLayoutParams(t3);

        FrameLayout.LayoutParams b1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b1.setMargins((int)(width*.05),(int)(height*.6),0,0);
        opt[0].setLayoutParams(b1);

        FrameLayout.LayoutParams b2= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b2.setMargins((int)(width*.45),(int)(height*.6),0,0);
        opt[1].setLayoutParams(b2);

        FrameLayout.LayoutParams b3= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b3.setMargins((int)(width*.05),(int)(height*.7),0,0);
        opt[2].setLayoutParams(b3);

        FrameLayout.LayoutParams b4= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b4.setMargins((int)(width*.45),(int)(height*.7),0,0);
        opt[3].setLayoutParams(b4);

        FrameLayout.LayoutParams i1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.3));
        i1.setMargins((int)(width*.25),(int)(height*.2),0,0);
        im.setLayoutParams(i1);
    }
}
