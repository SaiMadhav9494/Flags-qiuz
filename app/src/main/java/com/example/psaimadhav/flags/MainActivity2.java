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

public class MainActivity2 extends AppCompatActivity {
    TextView question,result,choose;
    Button opt1,opt2,opt3,opt4;
    ImageView im1,im2,im3,im4;
    int width,height,i;
    int n=-1;
    int qno,count;
    int nn,nn1,nn2,nn3,nn4;
    String[] flag_list;
    boolean[] checked_regions=new boolean[5];
    ArrayList<String> regions= new ArrayList<String>(4);
    AssetManager mgr;
    Animation rotateit;
    Animator shakeit,shakeit1,shakeit2,shakeit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        question=(TextView)findViewById(R.id.textView5);
        result=(TextView)findViewById(R.id.textView6);
        choose=(TextView)findViewById(R.id.textView7);
        opt1=(Button) findViewById(R.id.button);
        opt2=(Button) findViewById(R.id.button2);
        opt3=(Button) findViewById(R.id.button3);
        opt4=(Button) findViewById(R.id.button4);
        im1=(ImageView)findViewById(R.id.imageView2);
        im2=(ImageView)findViewById(R.id.imageView3);
        im3=(ImageView)findViewById(R.id.imageView4);
        im4=(ImageView)findViewById(R.id.imageView5);
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
        Bundle extras= getIntent().getExtras();
        if(extras!= null) {
            checked_regions = extras.getBooleanArray("list2");
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
        getregions(checked_regions);
        Random rand=new Random();
        nn= rand.nextInt(n);
        int m= rand.nextInt(16);
        int a= rand.nextInt(16);
        int b= rand.nextInt(16);
        int c= rand.nextInt(16);
        mgr= getAssets();
        if(qno<=10) {
            flag_list = new String[10];
            try {
                flag_list = mgr.list(regions.get(nn));
                InputStream image = mgr.open(regions.get(nn) + "/" + flag_list[m]);
                InputStream image1 = mgr.open(regions.get(nn) + "/" + flag_list[a]);
                InputStream image2 = mgr.open(regions.get(nn) + "/" + flag_list[b]);
                InputStream image3 = mgr.open(regions.get(nn) + "/" + flag_list[c]);
                Drawable drawable = Drawable.createFromStream(image, null);
                Drawable drawable1 = Drawable.createFromStream(image1, null);
                Drawable drawable2 = Drawable.createFromStream(image2, null);
                Drawable drawable3 = Drawable.createFromStream(image3, null);
                im1.setImageDrawable(drawable);
                im2.setImageDrawable(drawable1);
                im3.setImageDrawable(drawable2);
                im4.setImageDrawable(drawable3);
                qno++;
                if (regions.get(0) != null) {
                    opt1.setText(regions.get(0));
                } else {
                    opt1.setText("Asia");
                }
                if (regions.get(1) != null) {
                    opt2.setText(regions.get(1));
                } else {
                    opt2.setText("Africa");
                }
                if (regions.get(2) != null) {
                    opt3.setText(regions.get(2));
                } else {
                    opt3.setText("Europe");
                }
                if (regions.get(3) != null) {
                    opt4.setText(regions.get(3));
                } else {
                    opt4.setText("North America");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("list", "folder not found");
            }
            opt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opt1.getText() == regions.get(nn)) {
                        result.setText("Correct");
                        result.setTextColor(Color.GREEN);
                        opt1.startAnimation(rotateit);
                        delay2();
                    } else {
                        result.setText("Incorrect");
                        shakeit.setTarget(opt1);
                        result.setTextColor(Color.RED);
                        delay();
                        shakeit.start();
                        count++;
                    }
                }
            });
            opt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opt2.getText() == regions.get(nn)) {
                        result.setText("Correct");
                        result.setTextColor(Color.GREEN);
                        opt2.startAnimation(rotateit);
                        delay2();
                    } else {
                        result.setText("Incorrect");
                        result.setTextColor(Color.RED);
                        delay();
                        shakeit1.setTarget(opt2);
                        shakeit1.start();
                        count++;
                    }
                }
            });
            opt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opt3.getText() == regions.get(nn)) {
                        result.setText("Correct");
                        result.setTextColor(Color.GREEN);
                        opt3.startAnimation(rotateit);
                        delay2();
                    } else {
                        result.setText("Incorrect");
                        result.setTextColor(Color.RED);
                        delay();
                        shakeit2.setTarget(opt3);
                        shakeit2.start();
                        count++;
                    }
                }
            });
            opt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opt4.getText() == regions.get(nn)) {
                        result.setText("Correct");
                        result.setTextColor(Color.GREEN);
                        opt4.startAnimation(rotateit);
                        delay2();
                    } else {
                        result.setText("Incorrect");
                        result.setTextColor(Color.RED);
                        delay();
                        shakeit3.setTarget(opt4);
                        shakeit3.start();
                        count++;
                    }
                }
            });

            //}
            //else if(i%2==0){
            //delay(i);
            //}
            //Intent gg= new Intent();
            //setResult(0,gg);
            //finish();
        }
        else{
            Intent reset= new Intent(MainActivity2.this,MainActivity4.class);
            extras.putInt("count",count);
            reset.putExtras(extras);
            startActivity(reset);
        }
    }
    public void getregions(boolean[] b){
        if(b[0]){
            regions.add("Africa");
        }
        if(b[1]){
            regions.add("Asia");
        }
        if(b[2]){
            regions.add("Europe");
        }
        if(b[3]){
            regions.add("North_America");
        }
        if(b[4]){
            regions.add("South_America");
        }
        //regions.add(null);
        //regions.add(null);
        //regions.add(null);
    }
    public void delay2(){
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);
        Handler hx= new Handler();
        hx.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity2.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Intent gototwoPlease = new Intent(MainActivity2.this, MainActivity3.class);
                        Bundle extras = new Bundle();
                        extras.putStringArrayList("list3", regions);
                        extras.putBooleanArray("list4", checked_regions);
                        extras.putInt("question",qno);
                        extras.putInt("count",count);
                        gototwoPlease.putExtras(extras);
                        startActivity(gototwoPlease);
                    }
                });
            }
        },3000);
    }
   public void delay(){
       opt1.setEnabled(false);
       opt2.setEnabled(false);
       opt3.setEnabled(false);
       opt4.setEnabled(false);
        Handler hx= new Handler();
        hx.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity2.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        opt1.setEnabled(true);
                        opt2.setEnabled(true);
                        opt3.setEnabled(true);
                        opt4.setEnabled(true);

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
        t2.setMargins((int)(width*.32),(int)(height*.13),0,0);
        result.setLayoutParams(t2);

        FrameLayout.LayoutParams t3= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        t3.setMargins((int)(width*.27),(int)(height*.55),0,0);
        choose.setLayoutParams(t3);

        FrameLayout.LayoutParams b1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b1.setMargins((int)(width*.05),(int)(height*.6),0,0);
        opt1.setLayoutParams(b1);

        FrameLayout.LayoutParams b2= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b2.setMargins((int)(width*.45),(int)(height*.6),0,0);
        opt2.setLayoutParams(b2);

        FrameLayout.LayoutParams b3= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b3.setMargins((int)(width*.05),(int)(height*.7),0,0);
        opt3.setLayoutParams(b3);

        FrameLayout.LayoutParams b4= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        b4.setMargins((int)(width*.45),(int)(height*.7),0,0);
        opt4.setLayoutParams(b4);

        FrameLayout.LayoutParams i1= new FrameLayout.LayoutParams((int)(width*.3),(int)(height*.1));
        i1.setMargins((int)(width*.05),(int)(height*.25),0,0);
        im1.setLayoutParams(i1);

        FrameLayout.LayoutParams i2= new FrameLayout.LayoutParams((int)(width*.3),(int)(height*.1));
        i2.setMargins((int)(width*.45),(int)(height*.25),0,0);
        im2.setLayoutParams(i2);

        FrameLayout.LayoutParams i3= new FrameLayout.LayoutParams((int)(width*.3),(int)(height*.1));
        i3.setMargins((int)(width*.05),(int)(height*.4),0,0);
        im3.setLayoutParams(i3);

        FrameLayout.LayoutParams i4= new FrameLayout.LayoutParams((int)(width*.3),(int)(height*.1));
        i4.setMargins((int)(width*.45),(int)(height*.4),0,0);
        im4.setLayoutParams(i4);
    }
    /*@Override
    public void onActivityResult(int reqCode,int resultCode,Intent data){
        if(reqCode == 100){
            if (resultCode != 0){
                qno=data.getIntExtra("list6",0);
            }
        }
    }*/
}
