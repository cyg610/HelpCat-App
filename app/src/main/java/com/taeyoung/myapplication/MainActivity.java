package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListVIewAdapter adapter;
    private ListVIewItem data;
    private ArrayList<ListVIewItem> arrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        adapter = new ListVIewAdapter(arrays);

        arrays = new ArrayList<ListVIewItem>();
        initData();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }
    private void initData(){
        data = new ListVIewItem();

        adapter.additem(getResources().getDrawable(R.drawable.c201901754),"충북-청주-2019-01754","한국고양이", "1세", "암컷", "반려동물보호센터","043-201-2298","진행중","1.2","X","얘만한 애교덩어리 못 봤어요!");
        arrays.add(data);

        adapter.additem(getResources().getDrawable(R.drawable.c201912041612465),"강원-속초-2019-00585","러시안블루", "3세", "수컷", "유기동물보호소","033-633-2519","완료","6","O","사람을 잘 따름");
        arrays.add(data);

        adapter.additem(getResources().getDrawable(R.drawable.c20191204161268),"경기-용인-2019-00868","믹스", "4세", "수컷", "용인시 동물보호센터","031-324-3463","진행중","3.9","x","얌전하고 애교 많은 아이");
        arrays.add(data);

        adapter.additem(getResources().getDrawable(R.drawable.c20191204111216),"충북-청주-2019-01753","벵갈", "2세", "암컷", "반려동물보호센터","031-201-2298","진행중","3","x","크림색에 뱅갈무늬 연한 하늘색 눈동자가 매력적인 아이예요");
        arrays.add(data);


        adapter.additem(getResources().getDrawable(R.drawable.c201912041012571),"경남-밀양-2019-02222","한국고양이", "2달", "암컷", "밀양시동물보호소","010-5656-6665","진행중","1.5","x","사람을 잘 따르고 2개월 아이");
        arrays.add(data);

        adapter.additem(getResources().getDrawable(R.drawable.c201912040912407),"대전-중구-2019-00832","아메리칸 쇼트헤어", "2세", "미상", "대전동물보호세터","042-825-1118 ","진행중","1.5","O","19-821-822 동시구조 신고자임시분양");
        arrays.add(data);



    }
}