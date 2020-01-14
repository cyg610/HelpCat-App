package com.taeyoung.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {



    ListView listView;
    ListVIewAdapter adapter;
    private ListVIewItem data;
    private ArrayList<ListVIewItem> arrays;

    LinearLayout agoCalendar,timeCalendar;
    TextView search, agoText, timeText;
    int y=0, m=0, d=0;
    boolean initem = false, inAge = false, inCareAddr = false, inCareNm = false, isPopFile = false;
    boolean inKindCd = false, inNeuterYn = false, inNoticeNo = false, inCaretel = false, inProcessState=false;
    boolean insexCd = false, inWeight = false ,inMark = false;

    String age = null, careAddr = null, careNm = null, popfile = null, KindCd = null, neuterYn=null, noticeNo=null, careTel=null;
    String processState = null, sexCd = null, weight = null, mark = null;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        SimpleDateFormat formatter = new SimpleDateFormat (
                "yyyyMMdd", Locale.KOREA );
        Date currentTime = new Date ( );
        final String time = formatter.format ( currentTime );
        final String ago = Integer.parseInt(time)-10+"";



        listView = findViewById(R.id.listview);
        adapter = new ListVIewAdapter(arrays);
        agoCalendar = findViewById(R.id.agoCalendar);
        timeCalendar = findViewById(R.id.timeCalendar);
        search = findViewById(R.id.search);
        agoText = findViewById(R.id.agoText);
        timeText = findViewById(R.id.timetext);


        agoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageDate();
            }
        });

        timeCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDate();
            }
        });



        arrays = new ArrayList<ListVIewItem>();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        final int pageNumber= 1;
        apiGoGo(ago,time,pageNumber);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrays.clear();

            //    String ago_str = agoText.getText().subSequence(0,4)+""+agoText.getText().subSequence(6,8)+agoText.getText().subSequence(10,12);
             //   String time_str = timeText.getText().subSequence(0,4)+""+timeText.getText().subSequence(6,8)+timeText.getText().subSequence(10,12);
                String ago_str="20151112";
                String time_str="20151130";

               Log.d("fsfsdfs",""+ago_str+"   "+time_str);
                apiGoGo(ago_str,time_str,pageNumber);

            }
        });
    }

    void ageDate() {
        Calendar c = Calendar.getInstance();
        int nYear = c.get(Calendar.YEAR);
        int nMon = c.get(Calendar.MONTH);
        int nDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String strDate = String.valueOf(year) + "년 ";
                        strDate += String.valueOf(monthOfYear+1) + "월 ";
                        strDate += String.valueOf(dayOfMonth) + "일";

                        agoText.setText(strDate);
                    }
                };

        DatePickerDialog oDialog = new DatePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mDateSetListener, nYear, nMon, nDay);
        oDialog.show();

    }

    void timeDate() {
        Calendar c = Calendar.getInstance();
        int nYear = c.get(Calendar.YEAR);
        int nMon = c.get(Calendar.MONTH);
        int nDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String strDate = String.valueOf(year) + "년 ";
                        strDate += String.valueOf(monthOfYear+1) + "월 ";
                        strDate += String.valueOf(dayOfMonth) + "일";

                        timeText.setText(strDate);


                    }
                };

        DatePickerDialog oDialog = new DatePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mDateSetListener, nYear, nMon, nDay);
        oDialog.show();

    }

    private void apiGoGo(String ago, String time, int pageNumber){

        try{


            URL url = new URL("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde="+ago+"&endde="+time+"&pageNo="+pageNumber+"&numOfRows=500&ServiceKey=1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D&_returnType=json");


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

                            if (KindCd.contains("고양이")) {
                                initData(popfile, noticeNo, KindCd, age, sexCd, careNm, careTel, processState, weight, neuterYn, mark);
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



    private void initData(String popfile, String noticeNo, String kindCd, String age, String sexCd, String careNm, String careTel, String state, String weight, String neut, String specialMark){
        data = new ListVIewItem();

        adapter.additem(popfile,noticeNo,kindCd, age, sexCd, careNm,careTel,state,weight,neut,specialMark);
        arrays.add(data);
    }
}