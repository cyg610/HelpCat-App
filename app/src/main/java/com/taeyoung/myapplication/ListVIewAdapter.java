package com.taeyoung.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListVIewAdapter extends BaseAdapter {
    Bitmap bitmap;
    private ArrayList<ListVIewItem> listViewItemList = new ArrayList<ListVIewItem>();
    public  ListVIewAdapter(ArrayList<ListVIewItem> arrays){

    }

    public int getCount(){
        return listViewItemList.size();
    }

    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
        }



        ImageView catImage = convertView.findViewById(R.id.catIMG);
        TextView catName = convertView.findViewById(R.id.catNAME);
        TextView catKind = convertView.findViewById(R.id.catKIND);
        TextView catAge = convertView.findViewById(R.id.catAGE);
        TextView catSex = convertView.findViewById(R.id.catSEX);
        TextView EndingForm = convertView.findViewById(R.id.EndingForm);

        GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.round);
        catImage.setBackground(drawable);
        catImage.setClipToOutline(true);

        final ListVIewItem listVIewItem= listViewItemList.get(position);

        catName.setText(listVIewItem.getName());
        catAge.setText(listVIewItem.getAge());
        catSex.setText(listVIewItem.getSex());

        String catkind_sub;
        if(listVIewItem.getKind().contains("고양이")){
           catkind_sub = listVIewItem.getKind().substring(6);
        }else {
            catkind_sub = listVIewItem.getKind().substring(4);
        }


        catKind.setText("#"+catkind_sub);

        catKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KindActivity.class);
                context.startActivity(intent);
            }
        });


        if(listVIewItem.getStatus().contains("종료")){

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "완료된 모집공고입니다", Toast.LENGTH_SHORT).show();
                }
            });

        }else {

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(context, DetailCatActivity.class);
                    it.putExtra("Image", listViewItemList.get(pos).geticon());
                    it.putExtra("Name", listViewItemList.get(pos).getName());
                    it.putExtra("Age", listViewItemList.get(pos).getAge());
                    it.putExtra("Sex", listViewItemList.get(pos).getSex());
                    it.putExtra("Phone", listViewItemList.get(pos).getPhone());
                    it.putExtra("helper", listViewItemList.get(pos).getHelper());
                    it.putExtra("weight", listViewItemList.get(pos).getWeight());
                    it.putExtra("neut", listViewItemList.get(pos).getNeut());
                    it.putExtra("chara", listViewItemList.get(pos).getChara());
                    it.putExtra("kind", listViewItemList.get(pos).getKind());
                    context.startActivity(it);
                }
            });



            Thread mThread = new Thread() {
                public void run() {

                    try {
                        URL url = new URL(listViewItemList.get(pos).geticon());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap = (Bitmap) BitmapFactory.decodeStream(is);


                    } catch (IOException ex) {

                    }
                }
            };

            mThread.start();
            try {
                mThread.join();
                catImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {

            }



        }



        return convertView;
    }

    public  void additem(String ic, String name, String kind, String age, String sex, String helper, String phone, String status, String weight, String neut, String chara){
        ListVIewItem item = new ListVIewItem();
        item.seticon(ic);
        item.setName(name);
        item.setKind(kind);
        item.setAge(age);
        item.setSex(sex);
        item.setHelper(helper);
        item.setPhone(phone);
        item.setStatus(status);
        item.setWeight(weight);
        item.setNeut(neut);
        item.setChar(chara);

    listViewItemList.add(item);

    }

}
