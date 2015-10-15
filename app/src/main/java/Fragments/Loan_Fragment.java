package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.loanguru.R;

import java.text.NumberFormat;

/**
 * Created by Daron on 9/18/2015.
 */
public class Loan_Fragment extends Fragment{
    EditText term, interest, amount, downPayment, tax, rebateAmount, tradeAmount, fees;
    TextView total, interestText, monthly;
    RadioButton years, months;
    Button calculate_button;
    CheckBox tradeInTax, rebateTax;
    double mAmount, mInterest, mTax, mDownPayment, mTradeIn, mRebate, mFees;
    int mTerms;
    boolean tradeIn, rebate;
    MainLogic calculate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.loan_fragment, container, false);

        //load views
        calculate_button = (Button) view.findViewById(R.id.calculate_button);
        term = (EditText) view.findViewById(R.id.edit_term);
        interest = (EditText) view.findViewById(R.id.edit_interest);
        amount = (EditText) view.findViewById(R.id.edit_loan);
        downPayment = (EditText) view.findViewById(R.id.edit_down);
        tax = (EditText) view.findViewById(R.id.edit_tax);
        rebateAmount = (EditText) view.findViewById(R.id.rebates_amount);
        tradeAmount = (EditText) view.findViewById(R.id.tradeIn_Amount);
        fees = (EditText) view.findViewById(R.id.fees_amount);
        total = (TextView) view.findViewById(R.id.text_total);
        interestText = (TextView) view.findViewById(R.id.text_interestTotal);
        monthly = (TextView) view.findViewById(R.id.text_monthly);
        years = (RadioButton) view.findViewById(R.id.radio_year);
        months = (RadioButton) view.findViewById(R.id.radio_month);
        tradeInTax = (CheckBox) view.findViewById(R.id.tradeInTax_check);
        rebateTax = (CheckBox) view.findViewById(R.id.rebateTax_check);

        //OnCLickListeners
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers();
                getYears();
                tradeIn = tradeInTax.isChecked();
                rebate = rebateTax.isChecked();
                calculate = new MainLogic(mAmount, mTax, mInterest, mDownPayment, mTerms, tradeIn, rebate, mTradeIn, mRebate, mFees);
                monthly.setText(formatString(calculate.calculateMonthly()));
                total.setText(formatString(calculate.getTotal()));
                interestText.setText(formatString(calculate.getInterest()));
            }
        });
        calculate_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                reset();
                return true;
            }
        });
        years.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                months.setChecked(false);
            }
        });
        months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                years.setChecked(false);
            }
        });

        return view;
    }

    public String formatString(double num) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(num);
    }

    public void getYears() {
        if (years.isChecked()) {
            try {
                mTerms = Integer.parseInt(term.getText().toString()) * 12;
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Enter Terms", Toast.LENGTH_SHORT).show();
            }
        } else if (months.isChecked()) {
            try {
                mTerms = Integer.parseInt(term.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Enter Terms", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please select Years or Months", Toast.LENGTH_SHORT).show();
        }
    }

    public void numbers() {
        try {
            mAmount = Double.parseDouble(amount.getText().toString());
            mInterest = Double.parseDouble(interest.getText().toString());
            mTax = Double.parseDouble(tax.getText().toString());

        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Please Enter Top Fields", Toast.LENGTH_SHORT).show();
        }
        try {
            if(downPayment.getText().toString().trim().length() == 0){
                mDownPayment = 0;
            } else {
                mDownPayment = Double.parseDouble(downPayment.getText().toString());
            }
            if(tradeAmount.getText().toString().trim().length() == 0){
                mTradeIn = 0;
            } else {
                mTradeIn = Double.parseDouble(tradeAmount.getText().toString());
            }
            if(rebateAmount.getText().toString().trim().length() == 0){
                mRebate = 0;
            } else {
                mRebate = Double.parseDouble(rebateAmount.getText().toString());
            }
            if(fees.getText().toString().trim().length() == 0){
                mFees = 0;
            } else {
                mFees = Double.parseDouble(fees.getText().toString());
            }
        } catch (NumberFormatException e) {
        }
    }

    public double getMonthly(){
        return calculate.calculateMonthly();
    }

    public double getAmount(){
        return calculate.getTotal();
    }

    public double getInterest(){
        return mInterest;
    }

    public int getTerm(){
        return mTerms;
    }

    public boolean Check(){
        if(mAmount == 0.0){
            return false;
        } else {
            return true;
        }
    }

    public void reset(){
        term.setText("");
        interest.setText("");
        amount.setText("");
        downPayment.setText("");
        tax.setText("");
        rebateAmount.setText("");
        tradeAmount.setText("");
        fees.setText("");

        total.setText("$0.00");
        interestText.setText("$0.00");
        monthly.setText("$0.00");
    }



}
