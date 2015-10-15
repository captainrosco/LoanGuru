package Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.loanguru.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Adapters.Amortization_Adapter;
import logic.AmortizationItem;

/**
 * Created by Daron on 9/19/2015.
 */
public class Amortization_Fragment extends Fragment {
    ArrayList<AmortizationItem> items;
    double month, interest, total;
    int term;
    EditText monthly;
    SeekBar slider;
    float mLastMotionX;
    Bundle bundle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        month = bundle.getDouble("month");
        total = bundle.getDouble("total");
        term = bundle.getInt("term");
        interest = bundle.getDouble("interest")/100;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.amortization_fragment, container, false);
        monthly = (EditText) view.findViewById(R.id.slider_text);
        monthly.setText(formatString(month));
        view.setBackgroundColor(Color.WHITE);
        ListView list = (ListView) view.findViewById(R.id.amortization_list);
        createList();
        final Amortization_Adapter amortizationAdapter = new Amortization_Adapter(getActivity(), items);
        list.setAdapter(amortizationAdapter);
        monthly.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    month = Double.parseDouble(monthly.getText().toString().trim());
                    createList();
                    amortizationAdapter.update(items);

                }
            }
        });
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return view;
    }


    public void createList() {
        items = new ArrayList<AmortizationItem>();
        double unpaid = total;
        int mTerm = 0;
        double princple = 0;
        for (int i = 0; i < unpaid; i++) {
            double mInterest = (interest/12) * unpaid;
            princple = month - mInterest;
            mTerm = mTerm+1;
            unpaid -= month;
            if(unpaid < 0){
                unpaid = 0;
            }
            AmortizationItem item = new AmortizationItem(mInterest, month, princple, mTerm, unpaid);
            items.add(item);
        }

    }
    public String formatString(double num) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(num);
    }

}
