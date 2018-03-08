package com.example.user.mini_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LapDetails extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created LapDetails and extends with AppCompatActivity which is Parent class.

    ListView listView;
    DbHelper dbHelper;
    Button button;
    ArrayAdapter<String> adapter;
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
        setContentView(R.layout.activity_lap_details);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        listView =findViewById(R.id.lv);
        //giving the id of the listview from the layout.
        dbHelper = new DbHelper(LapDetails.this);

        List<String>stringList=new ArrayList<>();
        //created new arraylist as string list.
        stringList = dbHelper.getLapDetails();
        //getting dphelper class and getting the lap details and storing it into the stringlist.
        adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringList);
        //created ArrayAdapter as string and storing the string list to the adapter.
        listView.setAdapter(adapter);
        //attaching the adapter to the listview.

        Button button = (Button) findViewById(R.id.button);
        //giving an id to the button
        button.setOnClickListener(new View.OnClickListener() {
            //setting OnClickListener so that the button to perform some action
            public void onClick(View v) {
                //created the view method
                // TODO Auto-generated method stub
                Intent returnBtn = new Intent(getApplicationContext(),MainActivity.class);
                //using intent we are getting the details of application and saying to return to main activity.
                startActivity(returnBtn);
                //starting the activity.
            }
        });
    }
}
