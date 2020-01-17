package com.taeyoung.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewDetailActivity extends AppCompatActivity {

    Button callBtn;
    ImageView detailIMG;
    TextView helper, helperPhone, detailName, detailAge, detailSex, detailWeight, detailNeutered, detailChara, detailKind;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cat);

        callBtn = findViewById(R.id.callBtn);
        detailIMG = findViewById(R.id.detailIMG);
        helper = findViewById(R.id.helper);
        helperPhone = findViewById(R.id.helperPhone);
        detailName = findViewById(R.id.detailName);
        detailAge = findViewById(R.id.detailAge);
        detailSex = findViewById(R.id.detailSex);
        detailWeight = findViewById(R.id.detailWeight);
        detailNeutered = findViewById(R.id.detailNeutered);
        detailChara = findViewById(R.id.detailChara);
        detailKind = findViewById(R.id.detailKind);


        Intent it = getIntent();
        String data = it.getStringExtra("Data");
        String dataArr[] = data.split("#@");

        final String image = dataArr[0];
        //String com_test = 0popfile + "#@" + 1noticeNo + "#@" + 2KindCd + "#@" + 3age + "#@" + 4sexCd + "#@" + 5careNm + "#@" + 6careTel + "#@" + 7processState + "#@" + 8weight + "#@" + 9neuterYn + "#@" + 10mark;

        helperPhone.setText(dataArr[6]);
        helper.setText(dataArr[5]);

        detailName.setText(dataArr[1]);
        detailAge.setText(dataArr[3]);
        detailSex.setText(dataArr[4]);
        detailWeight.setText(dataArr[8]);
        detailChara.setText(dataArr[10]);


        String neut_str = dataArr[9];
        if(neut_str.equals("Y")){
            detailNeutered.setText("중성화 완료");
        }else if(neut_str.equals("N")){
            detailNeutered.setText("중성화 안함");
        }else {
            detailNeutered.setText("중성화 정보없음");
        }

        String kind_str = dataArr[2];

        if (kind_str.contains("고양이")){
            String catkind_sub = kind_str.substring(6);
            detailKind.setText(catkind_sub);
        }else  if (kind_str.contains("개")){
            String catkind_sub = kind_str.substring(4);
            detailKind.setText(catkind_sub);
        }

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
            detailIMG.setImageBitmap(bitmap);
        } catch (InterruptedException e) {

        }

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(NewDetailActivity.this);
                alertdialog.setMessage(helper.getText()+ "으로 전화하시겠습니까?");
                alertdialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+helperPhone));
                        startActivity(intent);
                    }
                });

                alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(NewDetailActivity.this, "취소 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = alertdialog.create();
                alert.setIcon(R.drawable.helper);
                alert.setTitle("전화 연결");
                alert.show();
            }
        });

        final Intent itPhoto = new Intent(NewDetailActivity.this, ImageActivity.class);
        itPhoto.putExtra("detail", image);
        detailIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(itPhoto);
            }
        });

    }
}
