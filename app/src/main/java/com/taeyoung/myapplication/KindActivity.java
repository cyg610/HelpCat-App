package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KindActivity extends AppCompatActivity {

    LinearLayout checkbox, noPage,clear;
    TextView yesbtn, nobtn, answer, question;
    int deps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);

        checkbox = findViewById(R.id.checkbox);
        noPage = findViewById(R.id.NoPage);
        clear = findViewById(R.id.clear);
        yesbtn = findViewById(R.id.yesBtn);
        nobtn = findViewById(R.id.noBtn);
        answer = findViewById(R.id.noBtn);
        question = findViewById(R.id.question);

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (deps){
                    case 0:
                        question.setText("개, 고양이는 10~15년 이상 삽니다. 결혼, 임신, 유학, 이사 등으로 가정환경이 바뀌어도 한번 인연을 맺은 동물은 끝가지 책임지고 보살피겠다는 결심이 있습니까?");
                        deps=1;
                        break;
                    case  1:
                        question.setText("모든 가족과 합의가 되었습니까?");
                        deps=2;
                        break;

                    case 2:
                        question.setText("반려동물을 기른경험이 있나요?\\n없더라도 내 동물을 위해 공부할 각오가 되어 있나요?\"");
                        deps=3;
                        break;

                    case 3:
                        question.setText("아플 때 적절한 치료를 해 주고, 중성화수술을 실천할 생각이신가요?");
                        deps=4;
                        break;
                    case 4:
                        question.setText("입양으로 인한 경제적 부담을 짊어질 의사와 능력이 있나요?");
                        deps=5;
                        break;
                    case 5:
                        question.setText("집에서 키우시는 다른 동물과 잘 어울릴 수 있을까요?");
                        deps=6;
                        break;
                    case  6:
                        checkbox.setVisibility(View.INVISIBLE);
                        clear.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox.setVisibility(View.INVISIBLE);
                noPage.setVisibility(View.VISIBLE);
            }
        });

    }
}
