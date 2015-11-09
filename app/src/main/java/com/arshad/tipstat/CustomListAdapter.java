package com.arshad.tipstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter implements Filterable {

    private List<MembersData> dataList;
    private List<MembersData> tempList;
    private Context mContext;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Context context, List<MembersData> results) {
        this.dataList = results;
        this.tempList = results;
        this.mContext = context;
    }

    static class ViewHolder {
        NetworkImageView mainImage;
        TextView title;
    }


    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        convertView = inflater.inflate(R.layout.single_row, null);
        holder = new ViewHolder();
        holder.mainImage = (NetworkImageView) convertView.findViewById(R.id.image);
        holder.title = (TextView) convertView.findViewById(R.id.title);
        convertView.setTag(holder);

        holder.title.setText(dataList.get(position).getStatus());
        holder.mainImage.setImageUrl(dataList.get(position).getImage(), imageLoader);

        return convertView;
    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {

        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                dataList = (List<MembersData>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    List<MembersData> filteredItems = new ArrayList<MembersData>();
                    for (int i = 0; i < tempList.size(); i++) {
                        MembersData data = tempList.get(i);
                        if (data.getStatus().toLowerCase().contains(constraint.toString().toLowerCase())
                               || (data.getHeight()+"").startsWith(constraint.toString().toLowerCase())
                                || (data.getWeight()+"").startsWith(constraint.toString().toLowerCase())) {
                            filteredItems.add(data);
                        }
                    }

                    results.count = filteredItems.size();
                    results.values = filteredItems;

                }
                else
                {
                    synchronized (this) {
                        results.values = tempList;
                        results.count = tempList.size();
                    }
                }
                return results;
            }
        };

        return filter;
    }

}
