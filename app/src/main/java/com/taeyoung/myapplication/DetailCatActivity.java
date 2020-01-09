package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailCatActivity extends AppCompatActivity {

    Button callBtn;
    ImageView detailIMG;
    TextView helper, helperPhone, detailName, detailAge, detailSex, detailWeight, detailNeutered, detailChara, detailKind;

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
       int imageNumber = it.getIntExtra("Image", 0);
       if(imageNumber == 0){
            detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c201901754));
       }else if(imageNumber == 1){
           detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c201912041612465));
       }else if(imageNumber ==2){
           detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c20191204161268));
        }else if(imageNumber ==3){
           detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c20191204111216));
       }else if(imageNumber == 4){
           detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c201912041012571));
       }else if(imageNumber == 5){

           detailIMG.setImageDrawable(getResources().getDrawable(R.drawable.c201912040912407));
       }

       final String phone = it.getStringExtra("Phone");
        helperPhone.setText(phone);
        final String helperStr = it.getStringExtra("helper");
        helper.setText(helperStr);

        detailName.setText(it.getStringExtra("Name"));
        detailAge.setText(it.getStringExtra("Age"));
        detailSex.setText(it.getStringExtra("Sex"));
        detailWeight.setText(it.getStringExtra("weight")+"(Kg)");
        detailNeutered.setText(it.getStringExtra("neut"));
        detailChara.setText(it.getStringExtra("chara"));
        detailKind.setText(it.getStringExtra("kind"));

       callBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(DetailCatActivity.this);
               alertdialog.setMessage(helperStr+ "으로 전화하시겠습니까?");
                alertdialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                        startActivity(intent);
                    }
                });

                alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DetailCatActivity.this, "취소 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = alertdialog.create();
                alert.setIcon(R.drawable.helper);
                alert.setTitle("전화 연결");
                alert.show();
           }
       });
    }
}
