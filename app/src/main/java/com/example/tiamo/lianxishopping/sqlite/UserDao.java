package com.example.tiamo.lianxishopping.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiamo.lianxishopping.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private MySql mySql;
    private SQLiteDatabase database;

    public UserDao(Context context) {
        mySql = new MySql(context);
        database = mySql.getReadableDatabase();
    }

    public void insert(String name){
        ContentValues values = new ContentValues();
        values.put("name",name);
        database.insert("user",null,values);
    }

    public void del(String name){
        database.delete("user","name = ?",new String[]{name});
    }

    public List<UserBean> query(){
        List<UserBean> list = new ArrayList<>();
        Cursor query = database.query("user", null, null, null, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex("name"));
            UserBean bean = new UserBean(name);
            list.add(bean);
        }
        return list;
    }
}
