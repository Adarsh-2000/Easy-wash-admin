package com.example.easycarwashadmin;

public class ProductModel {

    private String name,date,phone,time,carno;
    private String c;
    private String can;


    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public ProductModel(String c) {
        this.c = c;
    }

    public ProductModel() {
    }

    public ProductModel(String name, String date, String phone, String time, String carno) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.time = time;
        this.carno = carno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }
}
