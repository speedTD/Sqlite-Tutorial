package com.developer.dinhduy.sqlitetutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    public static DBSqlite sqlite;
    RecyclerView recyclerView;
    public  static FoodAdapter adapter;
    public static List<Food> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlite=new DBSqlite(getBaseContext());
        Log.d("AAA", "onCreate: databse ");
        sqlite.OpenDB();


        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new FoodAdapter(list,this);
        Getdata();
        recyclerView.setAdapter(adapter);
    }

    public static void Getdata(){
        //ok sorry
        list.clear();
        Cursor cursor=sqlite.GETALL();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            list.add(new Food(id,name,cursor.getBlob(2)));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        sqlite.CLOSE_DB();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item,menu);
        getMenuInflater().inflate(R.menu.menu_search_item,menu);
        MenuItem item=menu.findItem(R.id.search_bar);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_add){
            startActivity(new Intent(MainActivity.this,InsertFood.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        List<Food> foodList=new ArrayList<>();
        for(Food food :list){
            String name=food.getName().toLowerCase();
            if(name.contains(newText)){
                foodList.add(food);
            }
        }
        adapter.find(foodList);
        return false;
    }
}
