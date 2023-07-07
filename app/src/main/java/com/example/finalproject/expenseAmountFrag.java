package com.example.finalproject;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class expenseAmountFrag extends Fragment {
    TextView textRent;
    TextView textUtilities;
    TextView textphone;
    TextView textInternet;
    TextView textTuition;
    TextView textBooks;
    TextView textOtherFees;
    TextView textEducationCost;
    private RadioButton annually;
    private RadioButton monthly;
    private RadioGroup radiogrp;
    TextView housing;
    String loans;
    private expensesObject expenses; // notice: declared but not instantiated (but could be)
    private HousingObject housingObj;
    private TextView textTotalExpense;
     String totalIncome;
    Button buttonaccept;
    int surplus;
    String earnings ;
    String expense,splus;
    Menu Appmenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.expenseamtfragmentxml, container, false);

    }


    @Override
    public void onStart() {

        super.onStart();
        expenses = new expensesObject();
        housingObj=new HousingObject();

        textRent = (TextView) getView().findViewById(R.id.txtRent);
        textUtilities = (TextView) getView().findViewById(R.id.txtUtilities);
        textInternet = (TextView) getView().findViewById(R.id.txtInternet);
        textphone = (TextView) getView().findViewById(R.id.txtPhone);
        housing = (TextView) getView().findViewById(R.id.txtTotalHousingCosts);
        textTuition = (TextView) getView().findViewById(R.id.txtTuition);
        textBooks = (TextView) getView().findViewById(R.id.txtBooks);
        textOtherFees= (TextView) getView().findViewById(R.id.txtOtherFees);
        textEducationCost = (TextView) getView().findViewById(R.id.txtEducationCosts);
        textTotalExpense = (TextView) getView().findViewById(R.id.txtExpenseAmountTotalExpenses);
        annually = (RadioButton) getView().findViewById(R.id.radioannualy);
        monthly = (RadioButton) getView().findViewById(R.id.radiomonthly);
        radiogrp=(RadioGroup)getView().findViewById(R.id.radioGroup1);
        buttonaccept=(Button)getView().findViewById(R.id.accept_button);


        calculations3();
       radioactivity(); //it will call fragments from budget fragment
//get any argument (Bundle)
        Bundle arguments = getArguments();
        if (arguments != null) {


            totalIncome=(arguments.getString("budgetfragmenttotalincome"));
            loans=(arguments.getString("budgetfragmentloan"));
            earnings=(arguments.getString("budgetfragmentearning"));
            textRent.setText(arguments.getString("rent","111"));
            textUtilities.setText(arguments.getString("utility","2234"));
            textInternet.setText(arguments.getString("internet","4567"));
            textphone.setText(arguments.getString("phone","5678"));
            housing.setText(arguments.getString("housing","9800"));
            textTuition.setText(arguments.getString("tut","2300"));
            textBooks.setText(arguments.getString("books","4290"));
            textOtherFees.setText(arguments.getString("fees","1200"));
            textEducationCost.setText(arguments.getString("education","1200"));


        }
        buttonaccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //goDataEntry();
                Bundle result = new Bundle();
                result.putString("expenseamounttotal",textTotalExpense.getText().toString() );
                result.putString("totalincome",totalIncome);
                result.putString("loans",loans);
                surplus=Integer.parseInt(totalIncome.toString())- Integer.parseInt(textTotalExpense.getText().toString());
                result.putString("surplus", String.valueOf(surplus));
                result.putString("earning",String.valueOf(earnings));

                //to save the state on this form
                result.putString("expenseamtrent",textRent.getText().toString());
                result.putString("expenseamtutility",textUtilities.getText().toString());
                result.putString("expenseamtinternet",textInternet.getText().toString());
                result.putString("expenseamtphone",textphone.getText().toString());
                result.putString("expenseamthousing",housing.getText().toString());

                result.putString("expenseamttut",textTuition.getText().toString());
                  result.putString("expenseamtbook",textBooks.getText().toString());
                result.putString("expenseamtfees",textOtherFees.getText().toString());
                result.putString("expenseamteducationcost",textEducationCost.getText().toString());



                getParentFragmentManager().setFragmentResult("Fromexpenseamtfrag", result);
            }
        });


    }


    public void radioactivity(){
        //get any argument (Bundle)
        Bundle arguments = getArguments();
        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radiomonthly) {
                   // housing.setText(arguments.getString("budgetfragmenthousingtotal"));
                    housing.setText(Integer.toString(expenses.totalmonthly()));

                    textRent.setText(Integer.toString(expenses.monthlyrent()));
                    textUtilities.setText(Integer.toString(expenses.monthlyutility()));
                    textphone.setText(Integer.toString(expenses.monthlyphone()));
                    textInternet.setText(Integer.toString(expenses.monthlyinternet()));

                    textTuition.setText(Integer.toString(housingObj.monthlytuition()));
                    textBooks.setText(Integer.toString(housingObj.monthlyBooks()));
                    textOtherFees.setText(Integer.toString(housingObj.monthlyOtherfees()));


                } else if (checkedId == R.id.radioannualy) {
                    housing.setText(Integer.toString(expenses.totalannually()));
                    textRent.setText(Integer.toString(expenses.yearlyrent()));
                    textUtilities.setText(Integer.toString(expenses.yearlyutility()));
                    textphone.setText(Integer.toString(expenses.yearlyphone()));
                    textInternet.setText(Integer.toString(expenses.yearlyinternet()));

                    textTuition.setText(Integer.toString(housingObj.yearlytuition()));
                    textBooks.setText(Integer.toString(housingObj.yearlyBooks()));
                    textOtherFees.setText(Integer.toString(housingObj.yearlyOtherfees()));


                   // housing.setText(arguments.getString("budgetfragmenthousingtotal2"));

                }
            }
        });
    }
    public void calculations3() {
        textRent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                housing.setText("0");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    expenses.setRent(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                       expenses.setRent(Integer.parseInt(charSequence.toString()));
                        housing.setText(Integer.toString(expenses.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );

                    } else if (annually.isChecked() == true) {
                        // housing.setText(Integer.toString(expensesObject.totalmonthly()));
                       expenses.setRent(Integer.parseInt(charSequence.toString()));
                        housing.setText(Integer.toString(expenses.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );

                    }


                }  catch (NumberFormatException e){
                    expenses.setRent(0);
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
        textUtilities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    expenses.setUtility(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        expenses.setUtility(Integer.parseInt(charSequence.toString()));

                        housing.setText(Integer.toString(expenses.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );

                    } else if (annually.isChecked() == true) {
                        expenses.setUtility(Integer.parseInt(charSequence.toString()));
                        housing.setText(Integer.toString(expenses.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );
                    }
                }  catch (NumberFormatException e){
                    expenses.setUtility(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInternet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    expenses.setInternet(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        expenses.setInternet(Integer.parseInt(charSequence.toString()));

                        housing.setText(Integer.toString(expenses.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );

                    } else if (annually.isChecked() == true) {
                        // housing.setText(Integer.toString(expensesObject.totalmonthly()));
                        expenses.setInternet(Integer.parseInt(charSequence.toString()));

                        housing.setText(Integer.toString(expenses.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );


                    }
                }  catch (NumberFormatException e){
                    expenses.setInternet(0);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    expenses.setPhone(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        expenses.setPhone(Integer.parseInt(charSequence.toString()));
                        housing.setText(Integer.toString(expenses.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );

                    } else if (annually.isChecked() == true) {
                        // housing.setText(Integer.toString(expensesObject.totalmonthly()));
                        expenses.setPhone(Integer.parseInt(charSequence.toString()));

                        housing.setText(Integer.toString(expenses.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );


                    }
                }  catch (NumberFormatException e){
                    expenses.setPhone(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textTuition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textEducationCost.setText("0");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    housingObj.setTuition(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        housingObj.setTuition(Integer.parseInt(charSequence.toString()));

                        textEducationCost.setText(Integer.toString(housingObj.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );



                    } else if (annually.isChecked() == true) {
                        housingObj.setTuition(Integer.parseInt(charSequence.toString()));

                        textEducationCost.setText(Integer.toString(housingObj.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );


                    }


                }  catch (NumberFormatException e){
                    housingObj.setTuition(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
//avoid triggering event when text is empty

            }
        });

        ////another action listener
        textBooks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    housingObj.setBooks(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        housingObj.setBooks(Integer.parseInt(charSequence.toString()));

                        textEducationCost.setText(Integer.toString(housingObj.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );


                    } else if (annually.isChecked() == true) {
                        // housing.setText(Integer.toString(expensesObject.totalmonthly()));
                        housingObj.setBooks(Integer.parseInt(charSequence.toString()));

                        textEducationCost.setText(Integer.toString(housingObj.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );


                    }                }  catch (NumberFormatException e){
                    housingObj.setBooks(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textOtherFees.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    housingObj.setOtherfees(Integer.parseInt(charSequence.toString()));
                    if (monthly.isChecked() == true) {
                        housingObj.setOtherfees(Integer.parseInt(charSequence.toString()));

                        textEducationCost.setText(Integer.toString(housingObj.totalmonthly()));
                        textTotalExpense.setText(Integer.toString(expenses.totalmonthly() + housingObj.totalmonthly()) );


                    } else if (annually.isChecked() == true) {
                        // housing.setText(Integer.toString(expensesObject.totalmonthly()));
                        housingObj.setOtherfees(Integer.parseInt(charSequence.toString()));
                        textEducationCost.setText(Integer.toString(housingObj.totalannually()));
                        textTotalExpense.setText(Integer.toString(expenses.totalannually() + housingObj.totalannually()) );


                    }                }  catch (NumberFormatException e){
                    housingObj.setOtherfees(0);
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
        Appmenu.findItem(R.id.menuitem_expense_accept).setVisible(false);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Appmenu.findItem(R.id.menuitem_expense_accept2).setTitle("Expense"); // or “Accept”

        int id = item.getItemId();
        if (id == R.id.menuitem_expense_accept2) {

            Bundle result = new Bundle();
            result.putString("expenseamounttotal",textTotalExpense.getText().toString() );
            result.putString("totalincome",totalIncome);
            result.putString("loans",loans);
            surplus=Integer.parseInt(totalIncome.toString())- Integer.parseInt(textTotalExpense.getText().toString());
            result.putString("surplus", String.valueOf(surplus));
            result.putString("earning",String.valueOf(earnings));

            //to save the state on this form
            result.putString("expenseamtrent",textRent.getText().toString());
            result.putString("expenseamtutility",textUtilities.getText().toString());
            result.putString("expenseamtinternet",textInternet.getText().toString());
            result.putString("expenseamtphone",textphone.getText().toString());
            result.putString("expenseamthousing",housing.getText().toString());

            result.putString("expenseamttut",textTuition.getText().toString());
            result.putString("expenseamtbook",textBooks.getText().toString());
            result.putString("expenseamtfees",textOtherFees.getText().toString());
            result.putString("expenseamteducationcost",textEducationCost.getText().toString());



            getParentFragmentManager().setFragmentResult("Fromexpenseamtfrag", result);
        }



        return super.onOptionsItemSelected(item);


    }
}
