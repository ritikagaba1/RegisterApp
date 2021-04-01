package com.example.registerapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import example.javatpoint.com.introonetimefirsttime.R;

public class  RegisterListAdapter extends BaseAdapter implements Filterable {
    Activity context;
    ArrayList<RegisterListAd> reportsData;
    ArrayList<RegisterListAd> reportsDataOriginal;

    public RegisterListAdapter(Activity context, ArrayList<RegisterListAd> reportsData) {
        super();
        this.context = context;
        this.reportsData = reportsData;

    }


    public int getCount() {
        // TODO Auto-generated method stub
        return reportsData.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                reportsData = (ArrayList<RegisterListAd>) results.values; // has

                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults(); // Holds the
                // results of a
                // filtering
                // operation in
                // values
                // List<String> FilteredArrList = new ArrayList<String>();
                List<RegisterListAd> FilteredArrList = new ArrayList<>();

                if (reportsDataOriginal == null) {
                    reportsDataOriginal = new ArrayList<>(reportsData); // saves

                }

                /********
                 *
                 * If constraint(CharSequence that is received) is null returns
                 * the mOriginalValues(Original) values else does the Filtering
                 * and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = reportsDataOriginal.size();
                    results.values = reportsDataOriginal;
                } else {

                    Locale locale = Locale.getDefault();
                    constraint = constraint.toString().toLowerCase(locale);
                    for (int i = 0; i < reportsDataOriginal.size(); i++) {
                        RegisterListAd model = reportsDataOriginal.get(i);

                        String data = model.getEmail_id();
                        if (data.toLowerCase(locale).contains(constraint.toString())) {
                            FilteredArrList.add(model);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;

                }
                return results;
            }
        };
        return filter;
    }


    private class ViewHolder {
        TextView customer_name;
        TextView email_id;
        TextView whatsappno;
        //        TextView station_id;
        TextView plan_date,plan_amount;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.custom_list_attendance, null);
            holder = new ViewHolder();
            holder.customer_name = (TextView) convertView.findViewById(R.id.customername);
            holder.whatsappno = (TextView) convertView.findViewById(R.id.whasappno);
            holder.email_id = (TextView) convertView.findViewById(R.id.emailid);
            holder.plan_date = (TextView) convertView.findViewById(R.id.plan_date);
            holder.plan_amount = (TextView) convertView.findViewById(R.id.planamount);

//            holder.bmr_id = (TextView) convertView.findViewById(R.id.bmr_id);

//            holder.auto_number = (TextView) convertView.findViewById(R.id.auto_number);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        RegisterListAd medicineListModel=reportsData.get(position);
        holder.customer_name.setText(medicineListModel.getCustomername());
        holder.email_id
                .setText(medicineListModel.getEmail_id());
        holder.whatsappno.setText(medicineListModel.getWhatsappno());

        holder.plan_date.setText(medicineListModel.getPlan_date());
        holder.plan_amount.setText(medicineListModel.getPlan_amount());
        return convertView;
    }
}