package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // database
        db = new DBAdapter(this);
        // get the existing database file or from the assets folder if doesn't exist
        try
        {
            String destPath = "data/data/"+getPackageName()+"/databases";
            File f = new File(destPath);
            if(!f.exists()){
                f.mkdirs();
                //copy db from assets folder
                CopyDB(getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath + "/MyDB"));
            }
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        db.open();
        Cursor c;
        c = db.getAllContacts();
        Log.i("fdf","Damsdm");
        if(c.moveToFirst())
        {
            do{
                Log.i(c.getString(3),"Damm");
            } while(c.moveToNext());
        }
        db.close();
    }

    // copy database from assets to phone
    // items in the assets folder preserve filename
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //Copy one byte at a time
        byte [] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }
}
