package com.taeyoung.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
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

import java.util.ArrayList;

public class ListVIewAdapter extends BaseAdapter {
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
        ImageView imageCall = convertView.findViewById(R.id.imageCall);
        TextView EndingForm = convertView.findViewById(R.id.EndingForm);

        GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.round);
        catImage.setBackground(drawable);
        catImage.setClipToOutline(true);

        final ListVIewItem listVIewItem= listViewItemList.get(position);

        catImage.setImageDrawable(listVIewItem.geticon());
        catName.setText(listVIewItem.getName());
        catKind.setText("#"+listVIewItem.getKind());
        catAge.setText(listVIewItem.getAge());
        catSex.setText(listVIewItem.getSex());

        catKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KindActivity.class);
                context.startActivity(intent);
            }
        });


        if(listVIewItem.getStatus()=="완료"){
            EndingForm.setVisibility(View.VISIBLE);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "주인을 찾은 고양이입니다", Toast.LENGTH_SHORT).show();
                }
            });

        }else {

            catImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(context, DetailCatActivity.class);
                    it.putExtra("Image", pos);
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


            imageCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                    alertdialog.setMessage(listViewItemList.get(pos).getHelper() + "으로 전화하시겠습니까?");
                    alertdialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + listViewItemList.get(pos).getPhone()));
                            context.startActivity(intent);
                        }
                    });

                    alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, "취소 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alert = alertdialog.create();
                    alert.setIcon(R.drawable.helper);
                    alert.setTitle("전화연결");
                    alert.show();
                }
            });
        }



        return convertView;
    }

    public  void additem(Drawable ic, String name, String kind, String age, String sex, String helper, String phone, String status, String weight, String neut, String chara){
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
