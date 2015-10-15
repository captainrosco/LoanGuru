package logic;

import android.widget.Toast;

import java.text.NumberFormat;

/**
 * Created by Daron on 9/16/2015.
 */
public class AffordabilityLogic {
    double mInterest, mMonthly;
    int mTerm;

    public AffordabilityLogic(double interest, double monthly, int term){
        mInterest = interest/100;
        mMonthly = monthly;
        mTerm = term;
    }

    public double total(){
        double i = mInterest/12;
        double iplus= i+1;
        double rate = (Math.pow((iplus), mTerm));
        double topRate = rate-1;
        double top = mMonthly * topRate;
        double bottom = i*rate;
        double total = top/bottom;
        return total;
    }

    public double interest(){
        return (total()*(mInterest*100))/100;
    }
}
