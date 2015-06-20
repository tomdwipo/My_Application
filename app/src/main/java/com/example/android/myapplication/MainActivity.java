package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener{
    private ListView listv;
    private Button reset;
    final String[] list = new String[]{
            "fadfsg","hggg","ghgfF","f","h","g","h","e","t", "w","p","a",  "f","h","g","h","e","t", "w","p","a"
            ,  "f","h","g","h","e","t", "w","p","a",  "f","h","g","h","e","t", "w","p","a","w","f"
    };

    private ArrayList<Country> countries = new ArrayList<>();

    private void initialdumies(){
        Country country = new Country("Indonesia");
        country.setCapitalCity("jakarta");
        country.setImageId(R.drawable.androidparty);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialdumies();

        reset = (Button)findViewById(R.id.reset);

        listv = (ListView)findViewById(R.id.list);
        final String[] list = new String[]{
                "fadfsg","hggg","ghgfF","h","e","t", "w","p","a",  "f","h","g","h","e","t", "w","p","a"
                ,  "f","h","g","h","e","t", "w","p","a",  "f","h","g","h","e","t", "w","p","a","w","f"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listv.setAdapter(adapter);
        listv.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView sSearchView =(SearchView) MenuItemCompat.getActionView(searchItem);
        //if()
     sSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset (View v){

        reset.setText("Clear");

    }
    public void filter (String keyword){
        ArrayList<String> result = new ArrayList<String>();
        for(int i =0 ; i<  list.length;i++){
            String country = list[i];
            if (country.toLowerCase().contains(keyword)){
                result.add(country);

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
                result);
        listv.setAdapter(adapter);

        if (keyword.equals("f")){
            setContentView(R.layout.activity_detail);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(getApplicationContext(), "keyword = " + newText, Toast.LENGTH_LONG).show();
        filter(newText);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Go to DetailActivity and send clicked item's data
        String countries = listv.getAdapter().getItem(position).toString();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("item",countries);
        startActivity(intent);
    }
}
