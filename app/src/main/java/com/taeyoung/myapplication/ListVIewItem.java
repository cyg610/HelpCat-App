package com.taeyoung.myapplication;

import android.graphics.drawable.Drawable;

public class ListVIewItem {
    private Drawable iconDrawable;
    private String nameStr, kindStr, ageStr, sexStr, helperStr,phoneStr, statusStr,weightStr, neutStr, charaStr;

    public void seticon(Drawable icon){ iconDrawable = icon; }
    public void setName(String name){ nameStr = name; }
    public void setKind(String age){ kindStr = age; }
    public void setAge(String age){ ageStr = age; }
    public void setSex(String sex){ sexStr = sex; }
    public void setHelper(String helper){ helperStr = helper; }
    public void setPhone(String phone){phoneStr = phone;}
    public void setStatus(String status){statusStr = status;}
    public void  setWeight(String weight){weightStr = weight;}
    public void  setNeut(String neut){neutStr = neut; }
    public void  setChar(String chara){charaStr= chara;}

    public Drawable geticon(){ return  this.iconDrawable; }
    public String getName(){ return this.nameStr; }
    public String getKind(){ return this.kindStr; }
    public String getAge(){ return this.ageStr; }
    public String getSex(){ return this.sexStr; }
    public String getHelper(){ return this.helperStr; }
    public String getPhone(){return this.phoneStr;}
    public String getStatus(){return this.statusStr;}
    public String getWeight(){return this.weightStr;}
    public String getNeut(){return this.neutStr;}
    public String getChara(){return this.charaStr;}


    public void setData(Drawable ic, String name, String kind, String age, String sex, String helper, String phone, String status, String weight, String neut, String chara){
        iconDrawable = ic;
       nameStr = name;
       kindStr = kind;
       ageStr = age;
       sexStr = sex;
       helperStr = helper;
       phoneStr = phone;
       statusStr = status;
        weightStr = weight;
        neutStr = neut;
        charaStr = chara;

    }
}
