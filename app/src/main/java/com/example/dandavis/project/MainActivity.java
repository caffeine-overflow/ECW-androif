package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
                CopyDB(getBaseContext().getAssets().open("dan.db"),
                        new FileOutputStream(destPath + "/MyDatabase"));
            }
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

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

    public void onLogin(View view) {
        EditText mEdit   = findViewById(R.id.inputEmail);
        String email  = mEdit.getText().toString();
        EditText pEdit  = findViewById(R.id.inputPassword);
        String password  = pEdit.getText().toString();
        Log.i(password,"works");
        Cursor mCursor;
        db.open();
        mCursor = db.getUserByEmail(email, password);
        if(mCursor.getCount() != 0)  {
            mCursor.moveToFirst();
            Log.i(mCursor.getString(0),"checking password");
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("userId", mCursor.getInt(0));
            startActivity(intent);
        }
        db.close();
    }

    public void onSignUp(View view) {
        Intent intent = new Intent(this,ActivitySignUp.class);
        startActivity(intent);
    }
}
