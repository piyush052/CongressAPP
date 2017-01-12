package hexa.webandrioz.com.congress.fragments.legislators.favourites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.activities.comm.CommDescriptionsActivity;
import hexa.webandrioz.com.congress.adapters.CommAdapter;
import hexa.webandrioz.com.congress.adapters.SimpleListViewAdapter;
import hexa.webandrioz.com.congress.model.CommModel;
import hexa.webandrioz.com.congress.model.LegislatorsModel;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegCommFragment extends Fragment {


    ArrayList<CommModel> commModels;
    ListView mListView;
    SpotsDialog dialog;
    String id,name,chamber,commSubComm,parentId,phone,office;
    int count;
    SharedPreferenceProvider sharedPreferenceProvider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_leg_comm, container, false);
        commModels = new ArrayList<>();

        mListView = (ListView) v.findViewById(R.id.listview);
        sharedPreferenceProvider = new SharedPreferenceProvider();
        String totalC = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "totalCommNumber");
        count = Integer.parseInt(totalC);

        for (int i = 1; i <= count; i++) {
            id=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commId"+i);
            name=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commName"+i);
            chamber=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commChamber"+i);
            commSubComm=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commSubComm"+i);
            parentId=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commPComm"+i);
            phone=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commPhone"+i);
            office=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"commOffice"+i);

            if(!id.equals("0"))
            commModels.add(new CommModel(id,name,chamber,commSubComm,parentId,phone,office));
        }
        CommAdapter adapter = new CommAdapter(getActivity(), commModels);
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getActivity(), CommDescriptionsActivity.class);
                in.putExtra("commId",commModels.get(i).getCommId());
                in.putExtra("commName",commModels.get(i).getCommName());
                in.putExtra("commChamber",commModels.get(i).getCommChamber());
                in.putExtra("commSubComm",commModels.get(i).getCommSubComm());
                in.putExtra("commPComm",commModels.get(i).getParentCommId());
                in.putExtra("commPhone",commModels.get(i).getPhone());
                in.putExtra("commOffice",commModels.get(i).getOffice());
                getActivity().startActivity(in);

            }
        });

        return v;
    }
}