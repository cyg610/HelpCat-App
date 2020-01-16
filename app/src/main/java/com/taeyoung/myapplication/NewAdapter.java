package com.taeyoung.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyNewHolder> {

    private ArrayList<String> NewData = new ArrayList<>();
    private NewAdapter.NewInterface nListener = null;
    Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public NewAdapter.MyNewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newitem, parent,false);
        return new MyNewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.MyNewHolder holder, int position) {
        holder.NewOnBind(NewData.get(position));
    }

    @Override
    public int getItemCount() {
        return NewData.size();
    }

    void myNewItem(String data){
        NewData.add(data);
    }

    public interface NewInterface{
        void onItemClick(View v, int pos, String _str);
    }

    public void NewItemClick(NewAdapter.NewInterface _onNewItemClickListener){
        this.nListener = _onNewItemClickListener;
    }

    public class MyNewHolder extends RecyclerView.ViewHolder {
        ImageView newImage;
        TextView newName, newAddr;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyNewHolder(@NonNull View itemView) {
            super(itemView);

            newImage = itemView.findViewById(R.id.newImage);
            newName  = itemView.findViewById(R.id.newName);
            newAddr = itemView.findViewById(R.id.newAddr);

            GradientDrawable drawable = (GradientDrawable) itemView.getContext().getDrawable(R.drawable.round);
            newImage.setBackground(drawable);
            newImage.setClipToOutline(true);
        }
        void NewOnBind(final String str){
           final String DataArr[] = str.split("#@");

           String kind_str;
           if(DataArr[2].contains("고양이")){
               kind_str = DataArr[2].substring(6);
           }else {
               kind_str = DataArr[2].substring(4);
           }
           newName.setText(kind_str+ "("+DataArr[3]+"/"+DataArr[4]+")");
           newAddr.setText(DataArr[5]);


            Thread mThread = new Thread() {
                public void run() {

                    try {
                        URL url = new URL(DataArr[0]);
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
                newImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {

            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(nListener != null){
                            nListener.onItemClick(v,pos,NewData.get(pos));
                        }
                    }
                }
            });
        }
    }
}
