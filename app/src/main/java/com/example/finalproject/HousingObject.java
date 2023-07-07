package com.example.finalproject;

public class HousingObject {
     int tuition;
     int books;
     int otherfees;

    public HousingObject(){}

    public HousingObject(int tuition, int books, int otherfees) {
        this.tuition = tuition;
        this.books = books;
        this.otherfees = otherfees;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public int getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(int otherfees) {
        this.otherfees = otherfees;
    }

    public int totalmonthly(){
        int total;
        total = tuition+books+otherfees;
        return total;
    }
    public int totalannually(){
        int total;
        total = tuition+books+otherfees;
        return total;
    }

    public int monthlytuition(){
        int total;
        total = tuition/12;
        return total;
    }
    public int monthlyBooks(){
        int total;
        total = books/12;
        return total;
    }
    public int monthlyOtherfees(){
        int total;
        total = otherfees/12;
        return total;
    }
    public int yearlytuition(){
        int total;
        total = tuition*12;
        return total;
    }
    public int yearlyBooks(){
        int total;
        total = books*12;
        return total;
    }
    public int yearlyOtherfees(){
        int total;
        total = otherfees*12;
        return total;
    }



}
