package logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.NumberFormat;

/**
 * Created by Daron on 9/19/2015.
 */
public class AmortizationItem{
    double mMonth, mInterest, mPrinciple, mUnpaid;
    int mTerm;

    public AmortizationItem(double interest, double month, double principle, int term, double unpaid) {
        mInterest = interest;
        mMonth = month;
        mPrinciple = principle;
        mTerm = term;
        mUnpaid = unpaid;
    }
    public String formatString(double num) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(num);
    }

    public String getInterest() {
        return formatString(mInterest);
    }

    public String getMonth() {

        return formatString(mMonth);
    }

    public String getPrinciple() {
        return formatString(mPrinciple);
    }

    public String getTerm() {
        return Integer.toString(mTerm);
    }

    public String getUnpaid() {
        return formatString(mUnpaid);
    }




}
