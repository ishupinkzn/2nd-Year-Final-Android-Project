package com.example.finalproject;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;


public  class budget  extends Fragment   {
    TextView txtEarnings;
    TextView textLoans;
    TextView textIncome;
    TextView textexpenseamtTotalExpenses;
    TextView textTotalExpense;
    TextView textSurplus;
    TextView housing;
    Button buttonCalculateExpenses;
    private budgetObject budgetObject; // notice: declared but not instantiated (but could be)
    private expensesObject expensesObject; // notice: declared but not instantiated (but could be)
    int surplus;
    Menu Appmenu;
    String rent,utility,phone,internet,housingtot;
    String tut,book,education,fees;
    private String title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        return inflater.inflate(R.layout.budgetfragmentxml, container, false);

    }



    @Override
    public void onStart() {
        super.onStart();
        budgetObject = new budgetObject();
        expensesObject = new expensesObject();

        txtEarnings = (TextView) getView().findViewById(R.id.EarningsText);
        textLoans = (TextView) getView().findViewById(R.id.txtLoans);
        textIncome = (TextView) getView().findViewById(R.id.txtTotalIncome);
        textSurplus = (TextView) getView().findViewById(R.id.txtSurplus);
        textTotalExpense = (TextView) getView().findViewById(R.id.txtExpenses);
        buttonCalculateExpenses = (Button) getView().findViewById(R.id.accept_button);

        Appmenu=(Menu) getView().findViewById(R.id.menuitem_expense_accept);
        txtEarnings.setText("123");
        txtEarnings.getText();


        calculations();
        //create new bundle
        //get any argument (Bundle)
        Bundle arguments = getArguments();

            if (arguments != null) {
                textTotalExpense.setText(arguments.getString("expenseamounttotal","1000"));
                textIncome.setText(arguments.getString("totalincome","2000"));
                textLoans.setText(arguments.getString("loans","3000"));
                textSurplus.setText(arguments.getString("surplus","400"));

                txtEarnings.setText(arguments.getString("earning","123"));

                //to save expense amount form
                rent = (arguments.getString("expenseamtrent"));
                utility = arguments.getString("expenseamtutility");
                internet = arguments.getString("expenseamtinternet");
                phone = arguments.getString("expenseamtphone");
                housingtot = arguments.getString("expenseamthousing");

                tut = arguments.getString("expenseamttut");
                book = arguments.getString("expenseamtbook");
                fees = arguments.getString("expenseamtfees");
                education = arguments.getString("expenseamteducationcost");


            }




        buttonCalculateExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the functions to run calculations

                //send data to the main activity then to the fragment 1
                //set as bundle and send over to fragment 1
                Bundle result =  new Bundle();
                result.putString("budgetfragmenttotalincome", textIncome.getText().toString());

                result.putString("budgetfragmentloan", textLoans.getText().toString());
                result.putString("budgetfragmentearning",txtEarnings.getText().toString());

                //to save value of other form
                result.putString("rent",rent);
                result.putString("utility",utility);
                result.putString("internet",internet);
                result.putString("phone",phone);
                result.putString("housing",housingtot);
                result.putString("tut",tut);
                result.putString("books",book);
                result.putString("fees",fees);
                result.putString("education",education);





                //set child fragment result to it's parent fragment manager
                getParentFragmentManager().setFragmentResult("Frombudgetfragmentresult", result);

            }
        });



    }


    public void calculations(){

        txtEarnings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    budgetObject.setEarnings( Integer.parseInt(charSequence.toString()));

                    textIncome.setText(Integer.toString(budgetObject.total()));
                    expensesObject.getExpenses(Integer.parseInt(textIncome.getText().toString()));
                    surplus=Integer.parseInt(textIncome.getText().toString()) - Integer.parseInt(textTotalExpense.getText().toString());
                    textSurplus.setText(String.valueOf(surplus));


                }
                catch (NumberFormatException e){
                   // budgetObject.setEarnings(0);
                }
            }
              @Override
            public void afterTextChanged(Editable editable) {
//avoid triggering event when text is empty

              }
        });
//
//
////another action listener
        textLoans.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                           }
            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {

                    budgetObject.setLoans( Integer.parseInt(charSequence.toString()));

                    textIncome.setText(Integer.toString(budgetObject.total()));
                    surplus=Integer.parseInt(textIncome.getText().toString()) - Integer.parseInt(textTotalExpense.getText().toString());
                    textSurplus.setText(String.valueOf(surplus));
                    expensesObject.getExpenses(Integer.parseInt(textIncome.getText().toString()));


                }
                catch (NumberFormatException e){
                   // budgetObject.setLoans(0);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        Appmenu=menu;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Appmenu.findItem(R.id.menuitem_expense_accept2).setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.menuitem_expense_accept) {
            Appmenu.findItem(R.id.menuitem_expense_accept).setTitle("Accept"); // or “Accept”

            //set as bundle and send over to fragment 1
            Bundle result =  new Bundle();
            result.putString("budgetfragmenttotalincome", textIncome.getText().toString());

            result.putString("budgetfragmentloan", textLoans.getText().toString());
            result.putString("budgetfragmentearning",txtEarnings.getText().toString());

            //to save value of other form
            result.putString("rent",rent);
            result.putString("utility",utility);
            result.putString("internet",internet);
            result.putString("phone",phone);
            result.putString("housing",housingtot);
            result.putString("tut",tut);
            result.putString("books",book);
            result.putString("fees",fees);
            result.putString("education",education);





            //set child fragment result to it's parent fragment manager
            getParentFragmentManager().setFragmentResult("Frombudgetfragmentresult", result);

    }




        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onPause() {
        super.onPause();
    }
}




