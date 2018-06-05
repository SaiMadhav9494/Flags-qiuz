package com.example.psaimadhav.flags;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView title, details, id;
    ImageView myimage;
    int width, height,amt=0;
    String[] regions={"Africa","Asia","Europe","North_America","South_America"};
    boolean[] selected_items=new boolean[]{false,false,false,false,false};
    ArrayList<String> current_region= new ArrayList<String>(4);
    int t=0;
    int qno=1;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        setparameters();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.the_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.categories){
            display();
            return true;
        }
        else{
            Toast.makeText(MainActivity.this, "Something's wrong", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    public void display(){
        AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
        build.setTitle("Select a Region");
        build.setMultiChoiceItems(R.array.Continents,selected_items,new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which, boolean isChecked){
                if(isChecked) {
                    selected_items[which] = true;
                    current_region.add(regions[which]);
                    amt++;
                }
            }
        });
        build.setCancelable(false);
        build.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                if(amt>=4) {
                    if (!selected_items[0] && !selected_items[1] && !selected_items[2] && !selected_items[3] && !selected_items[4]) {
                        Toast.makeText(MainActivity.this, "Please select 4 regions", Toast.LENGTH_SHORT).show();
                        display();
                    } else {
                        Toast.makeText(MainActivity.this, "Great", Toast.LENGTH_SHORT).show();
                        Intent gotoOnePlease = new Intent(MainActivity.this, MainActivity2.class);
                        //gotoOnePlease.putExtra("list",selected_items);
                        Bundle extras = new Bundle();
                        extras.putStringArrayList("list1", current_region);
                        extras.putBooleanArray("list2", selected_items);
                        extras.putInt("question",qno);
                        extras.putInt("count",count);
                        gotoOnePlease.putExtras(extras);
                        startActivity(gotoOnePlease);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Please select 4 regions", Toast.LENGTH_SHORT).show();
                    display();
                }
            }
        });
        build.setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){
           @Override
            public void onClick(DialogInterface dialog, int which){

           }
        });
        AlertDialog done=build.create();
        done.show();
    }
    public void setparameters(){
        title=(TextView)findViewById(R.id.textView2);
        details=(TextView)findViewById(R.id.textView3);
        id=(TextView)findViewById(R.id.textView4);
        myimage=(ImageView)findViewById(R.id.imageView);

        FrameLayout.LayoutParams t1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.1));
        t1.setMargins((int)(width*.33),(int)(height*.15),0,0);
        title.setLayoutParams(t1);

        FrameLayout.LayoutParams t2= new FrameLayout.LayoutParams((int)(width*.5),(int)(height*.1));
        t2.setMargins((int)(width*.23),(int)(height*.45),0,0);
        details.setLayoutParams(t2);

        FrameLayout.LayoutParams t3= new FrameLayout.LayoutParams((int)(width*.2),(int)(height*.1));
        t3.setMargins((int)(width*.35),(int)(height*.5),0,0);
        id.setLayoutParams(t3);

        FrameLayout.LayoutParams i1= new FrameLayout.LayoutParams((int)(width*.4),(int)(height*.4));
        i1.setMargins((int)(width*.25),(int)(height*.12),0,0);
        myimage.setLayoutParams(i1);
    }
    @Override
    public void onActivityResult(int reqCode,int resultCode,Intent data){
        if(resultCode == 0){
            if (reqCode==0){
              display();
            }
        }
    }
}
