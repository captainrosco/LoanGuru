package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.loanguru.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import logic.AmortizationItem;

/**
 * Created by Daron on 9/19/2015.
 */
public class Amortization_Adapter extends BaseAdapter {
    private Activity mContext;
    private List<AmortizationItem> mList;
    private LayoutInflater mLayoutInflater = null;

    public Amortization_Adapter(Activity context, List<AmortizationItem> list){
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.amortization_list_item, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else{
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.term.setText(mList.get(position).getTerm());
        viewHolder.month.setText(mList.get(position).getMonth());
        viewHolder.interest.setText(mList.get(position).getInterest());
        viewHolder.princple.setText(mList.get(position).getPrinciple());
        viewHolder.unpaid.setText(mList.get(position).getUnpaid());
        return v;
    }

    class CompleteListViewHolder{
        public TextView term, month, interest, princple, unpaid;
        public CompleteListViewHolder(View base){
            term = (TextView) base.findViewById(R.id.monthNumText);
            month = (TextView) base.findViewById(R.id.monthlyPaid);
            interest = (TextView) base.findViewById(R.id.interestPaid);
            princple = (TextView) base.findViewById(R.id.princplePaid);
            unpaid = (TextView) base.findViewById(R.id.unpaidText);
         }
    }

    public void update(ArrayList<AmortizationItem> list){
        mList = list;
        this.notifyDataSetChanged();
    }
}


