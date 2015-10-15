package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.loanguru.R;

import java.text.NumberFormat;

import logic.AffordabilityLogic;

/**
 * Created by Daron on 9/18/2015.
 */
public class Afford_Fragment extends Fragment {
    EditText mMonthly, mInterest, mTerm;
    TextView mAfford;
    RadioButton yearsCheck, monthsCheck;
    Button calculate_button;
    double monthly, interest;
    int term;
    AffordabilityLogic calculate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.afford_fragment, container, false);
        mMonthly = (EditText) v.findViewById(R.id.edit_monthly);
        mInterest = (EditText) v.findViewById(R.id.edit_interest);
        mTerm = (EditText) v.findViewById(R.id.edit_term);
        mAfford = (TextView) v.findViewById(R.id.affordText);
        yearsCheck = (RadioButton) v.findViewById(R.id.years);
        monthsCheck = (RadioButton) v.findViewById(R.id.months);
        calculate_button = (Button) v.findViewById(R.id.afford_button);
        //OnClickListeners
        yearsCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthsCheck.setChecked(false);
            }
        });
        monthsCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearsCheck.setChecked(false);
            }
        });
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int months = terms();
                numbers();
                calculate = new AffordabilityLogic(interest, monthly, months);
                mAfford.setText(formatString(calculate.total()));
            }
        });
        v.setBackgroundColor(Color.WHITE);
        return v;
    }


    public int terms() {
        int months = 0;
        if (yearsCheck.isChecked()) {
            try {
                months = Integer.parseInt(mTerm.getText().toString()) * 12;
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Enter Terms", Toast.LENGTH_SHORT).show();
            }
        } else if (monthsCheck.isChecked()) {
            try {
                months = Integer.parseInt(mTerm.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Enter Terms", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please select Years or Months", Toast.LENGTH_SHORT).show();
        }
        return months;
    }

    public void numbers(){
        try{
            monthly = Double.parseDouble(mMonthly.getText().toString());
            interest = Double.parseDouble(mInterest.getText().toString());
            if(interest > 100) {
                Toast.makeText(getActivity(), "Interest must be under 100%", Toast.LENGTH_SHORT).show();
                interest = 0;
            }
            term = Integer.parseInt(mTerm.getText().toString());
        } catch(NumberFormatException e){
            Toast.makeText(getActivity(), "Please enter all fields.", Toast.LENGTH_SHORT).show();
        }

    }

    public String formatString(double num) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(num);
    }

}
