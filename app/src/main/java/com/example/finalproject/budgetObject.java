package com.example.finalproject;

public class budgetObject {
     int earnings;
     int loans;


    public budgetObject(){}
    public budgetObject(int earnings, int loans) {
        this.earnings = earnings;
        this.loans = loans;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }
    public int total(){
        int total;
            total = getEarnings() + getLoans();

        return total;
    }

}
