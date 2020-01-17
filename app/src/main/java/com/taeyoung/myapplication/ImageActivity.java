package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {


    PhotoView photoView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        photoView = findViewById(R.id.photoView);
        Intent itPhoto = getIntent();
        final String image = itPhoto.getStringExtra("detail");


        Thread mThread = new Thread() {
            public void run() {

                try {
                    URL url = new URL(image);
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
            photoView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {

        }

    }
}
