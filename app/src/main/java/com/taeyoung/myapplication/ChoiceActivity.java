package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChoiceActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    RecyclerView catRe, dogRe;
    NewAdapter adapter = new NewAdapter();
    NewAdapter adapter1 = new NewAdapter();
    boolean initem = false, inAge = false, inCareAddr = false, inCareNm = false, isPopFile = false;
    boolean inKindCd = false, inNeuterYn = false, inNoticeNo = false, inCaretel = false, inProcessState=false;
    boolean insexCd = false, inWeight = false ,inMark = false;

    String age = null, careAddr = null, careNm = null, popfile = null, KindCd = null, neuterYn=null, noticeNo=null, careTel=null;
    String processState = null, sexCd = null, weight = null, mark = null;

    LinearLayout catIntent, dogIntent, check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        catRe = findViewById(R.id.catRe);
        dogRe = findViewById(R.id.dogRe);
        catIntent = findViewById(R.id.catIntent);
        dogIntent = findViewById(R.id.dogIntent);
        check = findViewById(R.id.check);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("잠시만 기다려 주세요");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
        progressDialog.dismiss();

        StrictMode.enableDefaults();
        SimpleDateFormat formatter = new SimpleDateFormat (
                "yyyyMMdd", Locale.KOREA );
        Date currentTime = new Date ( );
        final String time = formatter.format ( currentTime );
        final String ago = Integer.parseInt(time)-10+"";



        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this);
        cLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        catRe.setLayoutManager(cLayoutManager);
        catRe.setAdapter(adapter1);



        LinearLayoutManager dLayoutManager = new LinearLayoutManager(this);
        dLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        dogRe.setLayoutManager(dLayoutManager);
        dogRe.setAdapter(adapter);

        apiGoGo(ago,time);


        adapter.NewItemClick(new NewAdapter.NewInterface() {
            @Override
            public void onItemClick(View v, int pos, String _str) {
                Intent intent = new Intent(ChoiceActivity.this, NewDetailActivity.class);
                String str = _str;
                intent.putExtra("Data",_str);
                startActivity(intent);
            }
        });

        adapter1.NewItemClick(new NewAdapter.NewInterface() {
            @Override
            public void onItemClick(View v, int pos, String _str) {
                Intent intent = new Intent(ChoiceActivity.this, NewDetailActivity.class);
                String str = _str;
                intent.putExtra("Data",_str);
                startActivity(intent);
            }
        });

        catIntent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("Who","고양이");
                startActivity(intent);
            }
        });

        dogIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                intent.putExtra("Who","개");
                progressDialog.show();
                startActivity(intent);

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, KindActivity.class);
                startActivity(intent);
            }
        });

    }

    private void apiGoGo(String ago, String time){

        try{


            URL url = new URL("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde="+ago+"&endde="+time+"&pageNo="+1+"&numOfRows=100&ServiceKey=1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D&_returnType=json");


            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("age")){ //title 만나면 내용을 받을수 있게 하자
                            inAge = true;
                        }
                        if(parser.getName().equals("careAddr")){ //address 만나면 내용을 받을수 있게 하자
                            inCareAddr = true;
                        }
                        if(parser.getName().equals("careNm")){ //mapx 만나면 내용을 받을수 있게 하자
                            inCareNm = true;
                        }
                        if(parser.getName().equals("popfile")){ //mapy 만나면 내용을 받을수 있게 하자
                            isPopFile = true;
                        }
                        if(parser.getName().equals("kindCd")){ //mapy 만나면 내용을 받을수 있게 하자
                            inKindCd = true;
                        }
                        if(parser.getName().equals("neuterYn")){ //mapy 만나면 내용을 받을수 있게 하자
                            inNeuterYn = true;
                        }
                        if(parser.getName().equals("noticeNo")){ //mapy 만나면 내용을 받을수 있게 하자
                            inNoticeNo = true;
                        }
                        if(parser.getName().equals("careTel")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCaretel = true;
                        }
                        if(parser.getName().equals("processState")){ //mapy 만나면 내용을 받을수 있게 하자
                            inProcessState = true;
                        }
                        if(parser.getName().equals("sexCd")){ //mapy 만나면 내용을 받을수 있게 하자
                            insexCd = true;
                        }
                        if(parser.getName().equals("weight")){ //mapy 만나면 내용을 받을수 있게 하자
                            inWeight = true;
                        }
                        if(parser.getName().equals("specialMark")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMark = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력

                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inAge){ //isTitle이 true일 때 태그의 내용을 저장.
                            age = parser.getText();
                            inAge = false;
                        }
                        if(inCareAddr){ //isAddress이 true일 때 태그의 내용을 저장.
                            careAddr = parser.getText();
                            inCareAddr = false;
                        }
                        if(inCareNm){ //isMapx이 true일 때 태그의 내용을 저장.
                            careNm = parser.getText();
                            inCareNm = false;
                        }
                        if(isPopFile){ //isMapy이 true일 때 태그의 내용을 저장.
                            popfile = parser.getText();
                            isPopFile = false;
                        }
                        if(inKindCd){ //isMapy이 true일 때 태그의 내용을 저장.

                            KindCd = parser.getText();


                            inKindCd = false;


                        }
                        if(inNeuterYn){ //isMapy이 true일 때 태그의 내용을 저장.
                            neuterYn = parser.getText();
                            inNeuterYn = false;
                        }
                        if(inNoticeNo){ //isMapy이 true일 때 태그의 내용을 저장.
                            noticeNo = parser.getText();
                            inNoticeNo = false;
                        }
                        if(inCaretel){ //isMapy이 true일 때 태그의 내용을 저장.
                            careTel = parser.getText();
                            inCaretel = false;
                        }
                        if(inProcessState){ //isMapy이 true일 때 태그의 내용을 저장.
                            processState = parser.getText();
                            inProcessState = false;
                        }
                        if(insexCd){ //isMapy이 true일 때 태그의 내용을 저장.
                            sexCd = parser.getText();
                            insexCd = false;
                        }
                        if(inWeight){ //isMapy이 true일 때 태그의 내용을 저장.
                            weight = parser.getText();
                            inWeight = false;
                        }
                        if(inMark){ //isMapy이 true일 때 태그의 내용을 저장.
                            mark = parser.getText();
                            inMark = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){

                            Log.d("AGAGAG", "주소 : "+ age +"\n 충전기 타입: "+ careAddr +"\n 충전소ID : " + careNm
                                    +"\n 충전기 명칭 : " + popfile +  "\n 충전기 상태 코드 : " + KindCd+ "\n 충전 방식 : " + neuterYn
                                    +"\n 충전소 ID : " +noticeNo + "\n 충전소 명칭 : " + careTel + "\n 위도 : " +processState
                                    +"\n 경도 : " +sexCd +"\n 충전기상태갱신시각 : " +weight+"\n"+"   "+inMark);


                           // initData(popfile, noticeNo, KindCd, age, sexCd, careNm, careTel, processState, weight, neuterYn, mark);

                            if(KindCd.contains("고양이")) {
                                String com_test = popfile + "#@" + noticeNo + "#@" + KindCd + "#@" + age + "#@" + sexCd + "#@" + careNm + "#@" + careTel + "#@" + processState + "#@" + weight + "#@" + neuterYn + "#@" + mark;
                                Log.d("what", com_test);
                                adapter1.myNewItem(com_test);
                            }else if (KindCd.contains("개")){
                                String com_test = popfile + "#@" + noticeNo + "#@" + KindCd + "#@" + age + "#@" + sexCd + "#@" + careNm + "#@" + careTel + "#@" + processState + "#@" + weight + "#@" + neuterYn + "#@" + mark;
                                Log.d("what", com_test);
                                adapter.myNewItem(com_test);
                            }


                            initem = false;
                        }
                        break;
                }


                parserEvent = parser.next();



            }

        } catch(Exception e){

        }
    }



}
