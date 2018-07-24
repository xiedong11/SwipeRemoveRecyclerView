package com.zhuandian.swiperemoveitemrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        List<ItemEntity> datas = new ArrayList<>();
        datas.add(new ItemEntity("1------数据",false));
        datas.add(new ItemEntity("2------数据",false));
        datas.add(new ItemEntity("3------数据",false));
        datas.add(new ItemEntity("4------数据",false));
        datas.add(new ItemEntity("5------数据",false));
        datas.add(new ItemEntity("6------数据",false));
        datas.add(new ItemEntity("7------数据",false));
        datas.add(new ItemEntity("8------数据",false));
        datas.add(new ItemEntity("9------数据",false));
        datas.add(new ItemEntity("1------数据",false));
        datas.add(new ItemEntity("2------数据",false));
        datas.add(new ItemEntity("3------数据",false));
        datas.add(new ItemEntity("4------数据",false));
        datas.add(new ItemEntity("5------数据",false));
        datas.add(new ItemEntity("6------数据",false));
        datas.add(new ItemEntity("7------数据",false));
        datas.add(new ItemEntity("8------数据",false));
        datas.add(new ItemEntity("9------数据",false));

        itemAdapter = new ItemAdapter(datas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setOnSwipeItemDelClickListener(new ItemAdapter.OnSwipeItemDelClickListener() {
            @Override
            public void delItem(int position) {
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                itemAdapter.removeItemByPosition(position);
            }
        });

    }
}
