package Fragments;

/**
 * Created by Daron on 9/13/2015.
 */
public class MainLogic {
    double mAmount, mTax, mInterest, mDownPayment, mTradeAmt, mRebateAmt, mFees, amount;
    int mTerm;
    boolean tradeIn, rebate;

    public MainLogic(double amount, double tax, double interest, double down, int terms, boolean tradeInCheck, boolean rebateCheck, double tradeAmt, double rebateAmt, double fees){
        mAmount = amount;
        mTax = tax/100;
        mInterest = interest/100;
        mTerm = terms;
        mDownPayment = down;
        tradeIn = tradeInCheck;
        rebate = rebateCheck;
        mTradeAmt = tradeAmt;
        mRebateAmt = rebateAmt;
        mFees = fees;
    }

    public double preTaxAmount(){
        amount = 0;
        if(tradeIn && rebate){
            amount = mAmount - (mTradeAmt + mRebateAmt);
        } else if (!tradeIn && rebate){
            amount = mAmount - mRebateAmt;
        } else if (tradeIn && !rebate){
            amount = mAmount - mTradeAmt;
        } else{
            amount = mAmount;
        }
        return amount;
    }

    public double taxAmount(){
        double tax = (preTaxAmount()+mFees) * mTax;
        amount = preTaxAmount() + tax + mFees;
        return amount;
    }

    public double amount(){
        double amt = 0;
        if(tradeIn && rebate){
            amt = taxAmount();
        } else if (!tradeIn && rebate){
            amt = taxAmount()-mTradeAmt;
        } else if (tradeIn && !rebate) {
            amt = taxAmount() - mRebateAmt;
        } else {
            amt = taxAmount()-(mRebateAmt+mTradeAmt);
        }
        double total = amt - mDownPayment;
        return total;
    }
    public double calculateMonthly(){
        double interest = mInterest/12;
        double rate = 1 - ( Math.pow((1+interest), -mTerm));
        double amount = amount() * interest;
        double total = amount/rate;
        return total;
    }
    public double getTotal(){
        return calculateMonthly() * mTerm;
    }
    public double getInterest(){
        return getTotal()- amount();
    }

}
