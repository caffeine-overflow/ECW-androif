package com.example.dandavis.project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 2;

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        }
    }

    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public Cursor getUserByEmail(String email, String password) throws SQLException
    {
        Cursor  mCursor = db.rawQuery("SELECT * FROM users WHERE email =? AND password = ?", new String[] {email, password});
        return mCursor;
    }

    public void registerUser(String name, String email, String password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("name", name);
        initialValues.put("email", email);
        initialValues.put("password", password);
        db.insert("users", null, initialValues);
    }

    public Cursor getItemsByCategory(int id) throws SQLException
    {
        Cursor mCursor = db.query(true, "items", new String[] {"id", "name", "price"}, "category_id" + "=" + id, null, null, null, null, null);
        return mCursor;
    }
    public Cursor getAllCategories()
    {
        return db.query("categories", new String[] {"id", "name"}, null, null, null, null, null);
    }

    public Cursor getProductById(int id) throws SQLException
    {
        Cursor mCursor = db.query(true, "items", new String[] {"id", "name", "price"}, "id" + "=" + id, null, null, null, null, null);
        return mCursor;
    }
}
