package com.arshad.tipstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class PortfolioAdapter extends BaseAdapter{

    private List<PortfolioData> dataList;
    private Context mContext;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PortfolioAdapter(Context context, List<PortfolioData> results) {
        this.dataList = results;
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
        convertView = inflater.inflate(R.layout.portfolio_single_row, null);
        holder = new ViewHolder();
        holder.mainImage = (NetworkImageView) convertView.findViewById(R.id.image);
        holder.title = (TextView) convertView.findViewById(R.id.title);
        convertView.setTag(holder);

        holder.title.setText(dataList.get(position).getName());
        holder.mainImage.setBackgroundResource(dataList.get(position).getImageRes());

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

}
