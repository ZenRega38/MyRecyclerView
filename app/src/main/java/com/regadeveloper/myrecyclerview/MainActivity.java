package com.regadeveloper.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView  rvPhlwn;
    private ArrayList<Phlwn> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvPhlwn = findViewById(R.id.rcv);
        rvPhlwn.setHasFixedSize(true);

        list.addAll(PhlwnData.getListData());
        showRecyclerList();


    }

    private void showRecyclerList(){
        rvPhlwn.setLayoutManager(new LinearLayoutManager(this));
        ListPhlwnAdapt listPhlwnAdapt = new ListPhlwnAdapt(list);
        rvPhlwn.setAdapter(listPhlwnAdapt);

        listPhlwnAdapt.setOnItemClickCallback(new ListPhlwnAdapt.OnItemClickCallback() {
            @Override
            public void onItemClicked(Phlwn data) {
                showSelectedPhlwn(data);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.act_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.act_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.act_cardview:
                title  = "Mode CardView";
                showRecyclerCard();
                break;
        }
    }

    private void showRecyclerGrid() {
        rvPhlwn.setLayoutManager(new GridLayoutManager(this, 2));
        GridPhlwnAdapt gridPhlwnAdapt = new GridPhlwnAdapt(list);
        rvPhlwn.setAdapter(gridPhlwnAdapt);

        gridPhlwnAdapt.setOnItemClickCallback(new GridPhlwnAdapt.OnItemClickCallback() {
            @Override
            public void onItemClicked (Phlwn data){
                showSelectedPhlwn(data);
            }
        });
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showRecyclerCard() {
        rvPhlwn.setLayoutManager(new LinearLayoutManager(this));
        CardViewPhlwnAdapt cardViewPhlwnAdapt = new CardViewPhlwnAdapt(list);
        rvPhlwn.setAdapter(cardViewPhlwnAdapt);
    }

    private void showSelectedPhlwn(Phlwn phlwn) {
        Toast.makeText(this, "Kamu memilih " + phlwn.getPhlwn(), Toast.LENGTH_SHORT).show();
    }
}
