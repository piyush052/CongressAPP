package hexa.webandrioz.com.congress.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.customList.StringMatcher;
import hexa.webandrioz.com.congress.model.BillsModel;
import hexa.webandrioz.com.congress.model.ByStatesModel;
import hexa.webandrioz.com.congress.model.LegislatorsModel;

/**
 * Created by gipsy_danger on 23/11/16.
 */

public class SimpleListViewAdapter extends ArrayAdapter<LegislatorsModel> implements SectionIndexer {
    ArrayList<LegislatorsModel> name=new ArrayList<>();
    Context con;
    int x=0;
    private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public SimpleListViewAdapter(FragmentActivity activity, ArrayList<LegislatorsModel> byStatesModels) {
        super(activity, R.layout.custom_list_layout,byStatesModels);
         name=byStatesModels;
         con=activity;
         x=2;
//        li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Log.e("constructor","sdhisjdkn");
         Collections.sort(name, LegislatorsModel.FruitNameComparator);
    }

    public SimpleListViewAdapter(FragmentActivity activity, ArrayList<LegislatorsModel> legislatorsModels, String s) {
        super(activity, R.layout.custom_list_layout,legislatorsModels);
        name=legislatorsModels;
        con=activity;
         x=1;
        Collections.sort(name,LegislatorsModel.StateComperator);
    }

    public SimpleListViewAdapter(FragmentActivity activity, ArrayList<LegislatorsModel> legislatorsModels, String senate, String senate1) {
        super(activity, R.layout.custom_list_layout,legislatorsModels);

        name=legislatorsModels;
        con=activity;
        x=2;
        Collections.sort(name, LegislatorsModel.FruitNameComparator);
    }

    public SimpleListViewAdapter(FragmentActivity activity, ArrayList<LegislatorsModel> legislatorsModels, int i, String senate1) {
        super(activity, R.layout.custom_list_layout,legislatorsModels);

        name=legislatorsModels;
        con=activity;
        mSections=senate1;
        x=2;
        Collections.sort(name, LegislatorsModel.FruitNameComparator);
    }

    @Override
    public int getCount() {
        return name.size();
    }
    @Override
    public LegislatorsModel getItem(int position) {
        // TODO Auto-generated method stub
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.custom_list_layout,null);
        Holder holder;

            holder=new Holder();

            holder.tv = (TextView) convertView.findViewById(R.id.name);
            holder.tv2= (TextView) convertView.findViewById(R.id.secondLine);
            holder.img= (ImageView) convertView.findViewById(R.id.image);
            Picasso.with(con).load(Constants.IMAGE_URL+name.get(position).getImageUrl()+".jpg").resize(200, 200).into(holder.img);

           String str=name.get(position).getName();
           String s[]=str.split("  ");

            holder.tv.setText(s[1]);
            holder.tv2.setText(name.get(position).getSecondLine());
        return convertView;
    }

    private static class Holder {
        TextView tv,tv2;
        ImageView img;
    }
    @Override
    public int getPositionForSection(int section) {
        // If there is no item for current section, previous section will be selected
        for (int i = section; i >= 0; i--) {
            for (int j = 0; j < getCount(); j++) {
                if (i == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        if(x==1) {
                            if (StringMatcher.match(String.valueOf(name.get(k).getState().charAt(0)), String.valueOf(k)))
                                return j;
                        }else {
                            if (StringMatcher.match(String.valueOf(name.get(k).getLastName().charAt(0)), String.valueOf(k)))
                                return j;
                        }
                    }
                } else {
                    if(x==1) {
                        if (StringMatcher.match(String.valueOf(name.get(j).getState().charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }else{
                        if (StringMatcher.match(String.valueOf(name.get(j).getLastName().charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        String[] sections = new String[mSections.length()];
        Log.e("mSection Length",""+mSections.length());

        for (int i = 0; i < mSections.length(); i++)
            sections[i] = String.valueOf(mSections.charAt(i));
        return sections;
    }

}
