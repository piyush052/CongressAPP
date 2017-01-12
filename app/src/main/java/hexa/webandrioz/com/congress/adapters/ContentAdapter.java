package hexa.webandrioz.com.congress.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.customList.StringMatcher;
import hexa.webandrioz.com.congress.model.ByStatesModel;

/**
 * Created by gipsy_danger on 23/11/16.
 */

public class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {
    ArrayList<ByStatesModel> name=new ArrayList<>();

    private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Context con;

    public ContentAdapter(Context context, int textViewResourceId,
                          List<String> objects) {
        super(context, textViewResourceId, objects);
    }

    public ContentAdapter(FragmentActivity activity, ArrayList<ByStatesModel> mItems) {
        super(activity, R.layout.custom_list_layout);
        name=mItems;
        con=activity;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =li.inflate(R.layout.custom_list_layout, null);

        Holder holder=new Holder();


        holder.tv= (TextView) convertView.findViewById(R.id.name);
        holder.tv.setText(name.get(position).getName());



        return convertView;
    }

    @Override
    public int getPositionForSection(int section) {
        // If there is no item for current section, previous section will be selected
        for (int i = section; i >= 0; i--) {
            for (int j = 0; j < getCount(); j++) {
                if (i == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
                            return j;
                    }
                } else {
                    if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
                        return j;
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
        for (int i = 0; i < mSections.length(); i++)
            sections[i] = String.valueOf(mSections.charAt(i));
        return sections;
    }

    private class Holder {
        TextView tv;
    }

}
