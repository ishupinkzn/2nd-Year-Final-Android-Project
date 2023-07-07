package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Menu Appmenu;
    expenseAmountFrag expenseAmountFrag1=new expenseAmountFrag();
    budget bud=new budget();
    String vara;
    budget budget1=new budget();
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we need a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
    //load fragment 2 by default
    // now use the FragmentManager to create / start a fragment transaction
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    //Fragment2 fragment2 = new Fragment2();
    budget pf = new budget();

    //pass some date (argument) to the fragment
    Bundle argument = new Bundle();

    pf.setArguments(argument);
    fragmentTransaction.replace(R.id.fragment_container_view, pf, "budget").addToBackStack(null);

    fragmentTransaction.commit();

        //set listener to receive result from fragment 2 and pass on to fragment 1
        // Set a Listener to receive results from Fragment 2 (and then pass this data on to Fragment 2)
        fragmentManager.setFragmentResultListener("Frombudgetfragmentresult", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("budgetfragmenttotalincome");
                String result2= bundle.getString("budgetfragmentloan");
                String result3= bundle.getString("budgetfragmentearning");
                String rent=bundle.getString("rent");
                String utility=bundle.getString("utility");
                String internet=bundle.getString("internet");
                String phone=bundle.getString("phone");
                String housing=bundle.getString("housing");
                String tut=bundle.getString("tut");
                String books=bundle.getString("books");
               String fees=bundle.getString("fees");
              String education=bundle.getString("education");

                // Do something with the result

                // Start Fragment 1 and pass it the result data as a arguments in a bundle
                expenseAmountFrag lsf = new expenseAmountFrag();

                // Pass some data (ina a bundle) (called argument) to the Fragment
                Bundle arguments = new Bundle();
                arguments.putString("budgetfragmenttotalincome", result);
                arguments.putString("budgetfragmentloan", result2);
                arguments.putString("budgetfragmentearning", result3);
                arguments.putString("rent",rent);
                arguments.putString("utility",utility);
                arguments.putString("internet",internet);
                arguments.putString("phone",phone);
                arguments.putString("housing",housing);

                arguments.putString("tut",tut);
                arguments.putString("books",books);
                arguments.putString("fees",fees);
               arguments.putString("education",education);

                lsf.setArguments(arguments);


                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view, lsf, "frag1").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // Set a Listener to receive results from Fragment 1 (and then pass this data on to Fragment 2)
        fragmentManager.setFragmentResultListener("Fromexpenseamtfrag", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("expenseamounttotal");
                String result2=bundle.getString("totalincome");

                String result3=bundle.getString("surplus");
                String result4=bundle.getString("loans");
                String result5=bundle.getString("earning");

                String rent=bundle.getString("expenseamtrent");
                String utility=bundle.getString("expenseamtutility");
                String internet=bundle.getString("expenseamtinternet");
                String phone=bundle.getString("expenseamtphone");
                String housingtot=bundle.getString("expenseamthousing");


                String tut= bundle.getString("expenseamttut");
                String book=bundle.getString("expenseamtbook");
                String fees=bundle.getString("expenseamtfees");
                String education=bundle.getString("expenseamteducationcost");


                // Do something with the result

                // Start Fragment 1 and pass it the result data as a arguments in a bundle
                budget pf = new budget();

                // Pass some data (ina a bundle) (called argument) to the Fragment
                Bundle arguments = new Bundle();
                arguments.putString("expenseamounttotal",result);
                arguments.putString("totalincome",result2);
                arguments.putString("surplus",result3);
                arguments.putString("loans",result4);
                arguments.putString("earning",result5);
                arguments.putString("expenseamtrent",rent);
                arguments.putString("expenseamtutility",utility);
                arguments.putString("expenseamtphone",phone);
                arguments.putString("expenseamtinternet",internet);
                arguments.putString("expenseamthousing",housingtot);
                arguments.putString("expenseamttut",tut);
                arguments.putString("expenseamtbook",book);
                arguments.putString("expenseamtfees",fees);
                arguments.putString("expenseamteducationcost",education);


                pf.setArguments(arguments);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.fragment_container_view, pf, "budgetfrag").addToBackStack(null);

                fragmentTransaction.commit();
            }
        });


    }


}