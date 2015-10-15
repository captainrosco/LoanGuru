package logic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.android.loanguru.R;

import Fragments.Afford_Fragment;
import Fragments.Amortization_Fragment;
import Fragments.Loan_Fragment;
import Fragments.No_Data;


public class MainActivity extends AppCompatActivity {
    Loan_Fragment loanFragment;
    Amortization_Fragment amortization_fragment;
    No_Data no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        loanFragment = new Loan_Fragment();
        openFragment(loanFragment);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.affordability:
                Afford_Fragment affordability = new Afford_Fragment();
                openFragment(affordability);
                return true;
            case R.id.amortization:
                if(loanFragment.Check()) {
                    amortization_fragment = new Amortization_Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("month", loanFragment.getMonthly());
                    bundle.putDouble("total", loanFragment.getAmount());
                    bundle.putInt("term", loanFragment.getTerm());
                    bundle.putDouble("interest", loanFragment.getInterest());
                    amortization_fragment.setArguments(bundle);
                    openFragment(amortization_fragment);
                } else {
                    no_data = new No_Data();
                    openFragment(no_data);
                }
                return true;
            case R.id.home: {
                openFragment(loanFragment);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
