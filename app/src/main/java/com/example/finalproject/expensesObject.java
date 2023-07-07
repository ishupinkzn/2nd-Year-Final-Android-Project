package com.example.finalproject;

public class expensesObject {
     int rent;
     int utility;
     int internet;
     int phone;
     int housingtotal;
     int expenses;

    public expensesObject(){}
    public expensesObject(int rent, int utility, int internet, int phone, int housingtotal,int expenses) {
        this.rent = rent;
        this.utility = utility;
        this.internet=internet;
        this.phone=phone;
        this.housingtotal=housingtotal;
        this.expenses=expenses;
    }

    public int getInternet() {
        return internet;
    }
    public int getExpenses(int expenses) {
        return this.expenses=expenses;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getRent() {
        return rent;
    }


    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public int totalmonthly(){
        int total;
        total = rent+utility+internet+phone;
        return total;
    }
    public int monthlyrent(){
        int total;
        total = rent/12;
        return total;
    }
    public int monthlyphone(){
        int total;
        total = phone/12;
        return total;
    }
    public int monthlyutility(){
        int total;
        total = utility/12;
        return total;
    }
    public int monthlyinternet(){
        int total;
        total = internet/12;
        return total;
    }
    public int yearlyrent(){
        int total;
        total = rent*12;
        return total;
    }
    public int yearlyphone(){
        int total;
        total = phone*12;
        return total;
    }
    public int yearlyutility(){
        int total;
        total = utility*12;
        return total;
    }
    public int yearlyinternet(){
        int total;
        total = internet*12;
        return total;
    }



    public int totalannually(){
        int total;
        total = rent+utility+internet+phone;
        return total;
    }

    public int expenses(){
        return getExpenses(expenses) - totalmonthly();
    }


}
