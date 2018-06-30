package com.developer.dinhduy.sqlitetutorial;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBSqlite extends SQLiteOpenHelper {

    private static  final  String DATABSE_NAME="abc.db";
    private static  final  String TABLE_NAME="info";
    private static  final  String COL_1="ID";
    private static  final  String COL_2="NAME";
    private static  final  String COL_3="PICTURE";
 //AUTOINCREMENT auto +1 in ID
    private static  final  String CREATE_DB="CREATE TABLE IF NOT EXISTS "+TABLE_NAME +" ( "
            +COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_2 +" TEXT,"+
             COL_3+" BLOB)";
    public DBSqlite(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  static SQLiteDatabase MYDB;
    //CREATE METHOD INSERT DATA =..
    public  void INSERT(String name,byte [] picture){
        String sql="INSERT INTO "+TABLE_NAME +" VALUES (null,?,?)";
        SQLiteStatement statement=MYDB.compileStatement(sql);
        //statement insert id will auto
        statement.bindString(1,name);
        statement.bindBlob(2,picture);

        statement.executeInsert();

    }

    public  void OpenDB(){
        MYDB=getReadableDatabase();
    }
    public void CLOSE_DB(){
        if(MYDB!=null&&MYDB.isOpen()){
            MYDB.close();
        }
    }
    public Cursor GETALL(){
        String sql="SELECT * FROM "+TABLE_NAME;
        return MYDB.rawQuery(sql,null);

    }
    public long DELETE(int id){
        String sql="ID ="+id;
        return MYDB.delete(TABLE_NAME,sql,null);

    }
}
