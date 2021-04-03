package YeuThich;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import Json.ChiTietMonAn;

public class YeuThichSQL extends SQLiteOpenHelper {
    private static final String TAG1="SQLHelper1";
    static final String DB_NAME1 ="Love2.db";
    static final String DB_NAME_TABLE1="Love2";
    static final int DB_VERSION1=2;
    SQLiteDatabase sqLiteDatabase1;
    ContentValues contentValues1;
    Cursor cursor1;
    public YeuThichSQL(Context context){
        super(context,DB_NAME1,null,DB_VERSION1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TH thi goi toi function
        String queryCreateTable1="create table Love2 (" +
                "id integer not null primary key autoincrement,"  +
                "name Text," +
                "image BLOB)";
        db.execSQL(queryCreateTable1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TH nao thi goi toi function
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_NAME_TABLE1);
            onCreate(db);
        }
    }
    public void insertMon(String name,byte[] image) {
        sqLiteDatabase1 = getWritableDatabase();
        String sql="INSERT INTO Love2 VALUES(null,?,?)";
        SQLiteStatement statement=sqLiteDatabase1.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindBlob(2,image);
        statement.executeInsert();
    }
   public Cursor getDataYeuThich(String sql)
   {
       SQLiteDatabase database=getWritableDatabase();
       return database.rawQuery(sql,null);
   }
    public int deleteProduct(String name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_NAME_TABLE1, " name=?",
                new String[]{name});
    }
    private void closeDB() {
        if (sqLiteDatabase1 != null) sqLiteDatabase1.close();
        if (contentValues1 != null) contentValues1.clear();
        if (cursor1 != null) cursor1.close();
    }
}

