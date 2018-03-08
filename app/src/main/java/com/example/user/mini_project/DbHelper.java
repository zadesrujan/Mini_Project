package com.example.user.mini_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14-02-2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created DbHelper class which extends SQLiteOpenHelper which is used for dataabase.

    public static final String DATABASE_NAME = "stopwatch.db";
    //public is a method and fields can be accessed by the members of any class.
    //static is you can call a method, even if you've never created the object to which it belongs!.
    //we use final keyword with variables to specify its values are not to be changed.
    //string are a sequence of characters.
    //created database name as stopwatch
    public static final String TABLE_NAME = "laptimings";
    //public is a method and fields can be accessed by the members of any class.
    //static is you can call a method, even if you've never created the object to which it belongs!.
    //we use final keyword with variables to specify its values are not to be changed.
    //string are a sequence of characters.
    //created table name as laptimings.
    public static final String COL_1 = "id";
    //public is a method and fields can be accessed by the members of any class.
    //static is you can call a method, even if you've never created the object to which it belongs!.
    //we use final keyword with variables to specify its values are not to be changed.
    //string are a sequence of characters.
    //created column 1 as id.
    public static final String COL_2 = "time";
    //public is a method and fields can be accessed by the members of any class.
    //static is you can call a method, even if you've never created the object to which it belongs!.
    //we use final keyword with variables to specify its values are not to be changed.
    //string are a sequence of characters.
    //created column 2 as time.

    Context context;
    //declaring the context.

    public DbHelper(Context context) {
        //its a constructor when we create the data base which includes context.
        super(context, "stopwatch.db", null, 7);
        //Context allows access to application-specific resources and classes, as well as calls for application-level.
        //given the database name and given as null and we are giving the version as 5.
        this.context = context;
        //we are intializing the context with the declared context and with the constructor.
    }

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onCreate(SQLiteDatabase db) {
        //this is the method where we create the table,columns in the database
        db.execSQL("CREATE TABLE "+TABLE_NAME +"(id INTEGER PRIMARY KEY AUTOINCREMENT,time TEXT);");
        //for excution of this we use db.exexSQL and we create the table and column which we are giving
        //auto incement so that it must increased by 1 and the column 2 as time which is text.
    }

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //we are using onUpgrade method to upgrade the version of the application which will get the features from old version
        //as well as the new features which are added to new version
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public void insertData(String t){
        //we are using insert data method to insert the data which we want to insert in the database.
        SQLiteDatabase database = this.getWritableDatabase();
        //created as new object as database and saying the data base to write in the database
        ContentValues contentValues = new ContentValues();
        //created the object as contentvalues which is used to store the values.
        contentValues.put("time",t);
        //storing the value of the string t the column 2 that is time.

        long result=database.insert(TABLE_NAME,null,contentValues);
        //giving long result for storing the results as long as we require and that is showing that
        //to insert in table which is lap timings the values from the content values.
        if(result!=-1) {
            //using if condition if result is not equals to 1
            Toast.makeText(context, "Inserted in DataBase", Toast.LENGTH_SHORT).show();
            //showing the toast as inserted and showing for some seconds
        }
    else{
            //using else condition if result is equal to 1
        Toast.makeText(context, "Not Inserted", Toast.LENGTH_SHORT).show();
        //showing the toast that the data is not inserted.
    }
}
    public List<String> getLapDetails(){
        //giving string name as list and getting the lapdetails from the class lapdetails and storing in the list.
        List<String>stringList = new ArrayList<>();
        //created new arraylist and sending the list into new arraylist.
        SQLiteDatabase database = this.getReadableDatabase();
        //created the new object as database and saying that to read the data base by giving getReadable method.
        Cursor cursor = database.rawQuery("SELECT * FROM laptimings",null);
        //cursor is interface provides random read-write access to the result set returned by a database query.
        //raw Query is used for Runs the provided SQL and returns a Cursor over the result set.
        //giving the SQL statement as select from lap timings which will select all th rows from table and giving
        //selection arguments as null.
        if(cursor.moveToFirst()){
            //using if condition we are saying that to cursor if moved to first
            while (!cursor.isAfterLast()){
                //we are using while loop and using cursor is not equals to after last so that the cursor will go to
                //last row and it must not move to next one.
               String time = cursor.getString(cursor.getColumnIndex("time"));
               //given string is time and getting details from the column time and from the cursor.
                stringList.add(time);
                //adding the time which we get fromthe coulmn to the list.
                cursor.moveToNext();
                //saying to the cursor to move from one row to next row.
            }
        }
return stringList;
    }
}
