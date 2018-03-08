package com.example.user.mini_project;
//Package objects contain version information about the implementation and specification of a Java package.
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    TextView tv;
    Button Start,Stop,Reset;
    private int seconds=0;
    private boolean running;
    DbHelper dbHelper;
    String time;
    //declaring the variables.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design
        runTimer();
        //declaring the runTimer.

        tv = (TextView)findViewById(R.id.tv);
        //getting the id textview from the layout.
        Start =(Button)findViewById(R.id.start_button);
        //getting the id button from the layout.
        Stop =(Button)findViewById(R.id.stop_button);
        //getting the id button from the layout.
        Reset =(Button)findViewById(R.id.reset_button);
        //getting the id button from the layout.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getting the id toolvar from the layout.
        dbHelper = new DbHelper(MainActivity.this);

        setSupportActionBar(toolbar);

        Start.setOnClickListener(new View.OnClickListener() {
            //setting the setOnclicklistner to the start button
            @Override
            public void onClick(View v) {
                //giving the method that when on clicling on the button what action should perform
                running=true;
                //giving the running time is true and saying that to start the function.
                Toast.makeText(MainActivity.this, "Activity Started", Toast.LENGTH_SHORT).show();
                //giving a message as stopwatch is started.
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            //setting the setOnclicklistner to the start button
            @Override
            public void onClick(View v) {
                //giving the method that when on clicling on the button what action should perform
            running=false;
                //giving the running time is true and saying that to start the function.
            dbHelper.insertData(time);
            //getting the class of dphelper and inserting the data that is time.
                Log.d("TIME ADDED IS",time);
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            //setting the setOnclicklistner to the start button
            @Override
            public void onClick(View v) {
                //giving the method that when on clicling on the button what action should perform
                running=false;
                //giving the running time is true and saying that to start the function.
                seconds=0;
                //returning the seconds to 0.
                Toast.makeText(MainActivity.this,"Reseted",Toast.LENGTH_LONG).show();
                //Giving the message that stopwatch is reseted.
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //creating the object and giving the id as fab from the layout.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting the fab button to on click listner to show some function
                Intent intent = new Intent(MainActivity.this,LapDetails.class);
                //giving intent to the fab button so that we are linking the main ativity with the lapdetail class.
                startActivity(intent);
                //startig the activity.
            }
        });
    }
    private void runTimer(){
        //giving the runTimer method so that to change the text to the current timer.
        final TextView textView=findViewById(R.id.tv);
        //initalizing the object and giving the id of the textview from the layout.
        final Handler handler=new Handler();
        //craeted object as handler which is used to excute the code in future.
        handler.post(new Runnable() {
            @Override
            public void run() {
                //in handler we use post to excute the code at this moment.
                int hours=seconds/3600;
                //giving hours as seconds which is divided by 3600.
                int minutes=(seconds%3600)/60;
                //giving minutes as seconds which is percentaged by the 3600 and which is divided by 60.
                int secs=seconds%60;
                //giving the seconds as secs and which is percentaged by 60.
                 time=String.format("%d:%02d:%02d",hours,minutes,secs);
                 //created string name as time and giving the format of the string so that we can show in the app.
                textView.setText(time);
                //setting the textview to the string name time.
                if(running){
                    //using if condition the run time will be increased by 1.
                    seconds++;
                }
                handler.postDelayed(this,1000);
                //used as handler post delay method we can run the program at certain amount of time we are giving here as 1000 milli
                //seconds which is equal to 1 second.
            }
        });
    }
    }
