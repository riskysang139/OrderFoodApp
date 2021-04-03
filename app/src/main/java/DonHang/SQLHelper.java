package DonHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG="SQLHelper";
    static final String DB_NAME ="Food1.db";
    static final String DB_NAME_TABLE="Food1";
    static final int DB_VERSION=1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;
    public SQLHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TH thi goi toi function
        String queryCreateTable="create table Food1 (" +
                "id integer not null primary key autoincrement,"  +
                "name Text," +
                "image BLOB," +
                "price Text)";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TH nao thi goi toi function
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_NAME_TABLE);
            onCreate(db);
        }
    }
    public void insertMon(String name, String price, byte[] image) {
        sqLiteDatabase = getWritableDatabase();
        String sql="INSERT INTO Food1 VALUES(null,?,?,?)";
        SQLiteStatement statement=sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,price);
        statement.bindBlob(3,image);
        statement.executeInsert();
    }
    public Cursor getDataDonHang(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        return database.rawQuery(sql,null);
    }
    public int deleteProduct(String name) {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_NAME_TABLE, " name=?",
                new String[]{name});
    }

    public boolean delAllProduct() {
        int result;
        sqLiteDatabase = getWritableDatabase();
        result = sqLiteDatabase.delete(DB_NAME_TABLE, null, null);
        closeDB();
        if (result == 1) return true;
        else return false;
    }
    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
}
