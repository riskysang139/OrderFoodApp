package NguoiDung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class UserHelper extends SQLiteOpenHelper {
    private static final String TAG="SQLHelper";
    static final String DB_NAME ="user1.db";
    static final String DB_NAME_TABLE="user1";
    static final int DB_VERSION=1;
    SQLiteDatabase sqLiteDatabase1;
    ContentValues contentValues;
    Cursor cursor;
    public UserHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable1="create table user1 (" +
                "id integer not null primary key autoincrement,"  +
                "name Text," +
                "sdt Text," +
                "email Text,"+
                "taiKhoan Text,"+
                "matKhau Text,"+
                "online Text)";
        db.execSQL(queryCreateTable1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_NAME_TABLE);
            onCreate(db);
        }
    }
    public void insertUser(String name, String sdt, String email,String taiKhoan,String matKhau, String online) {
        sqLiteDatabase1 = getWritableDatabase();
        String sql="INSERT INTO user1 VALUES(null,?,?,?,?,?,?)";
        SQLiteStatement statement=sqLiteDatabase1.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,sdt);
        statement.bindString(3,email);
        statement.bindString(4,taiKhoan);
        statement.bindString(5,matKhau);
        statement.bindString(6,online);
        statement.executeInsert();
    }
    public Cursor getUser(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        return database.rawQuery(sql,null);
    }
}
