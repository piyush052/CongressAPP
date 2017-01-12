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
import hexa.webandrioz.com.congress.activities.bills.BillsDiescriptionsActivity;
import hexa.webandrioz.com.congress.adapters.BillsAdapter;
import hexa.webandrioz.com.congress.adapters.SimpleListViewAdapter;
import hexa.webandrioz.com.congress.model.BillsModel;
import hexa.webandrioz.com.congress.model.LegislatorsModel;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavBillsFragment extends Fragment {


    ArrayList<BillsModel> billsModels;
    ListView mListView;
    SpotsDialog dialog;
    String billId, billTitle, billtype,sponsors,chamber,status,introON,congressUrl,versionStatus,billUrl;
    int count;
    SharedPreferenceProvider sharedPreferenceProvider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fav_bills, container, false);
        billsModels = new ArrayList<>();

        mListView = (ListView) v.findViewById(R.id.listview);
        sharedPreferenceProvider = new SharedPreferenceProvider();
        String totalC = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "totalBillsNumber");
        count = Integer.parseInt(totalC);

        for (int i = 1; i <= count; i++) {
            billId=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsId"+i);
            billTitle=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsTitle"+i);
            billtype=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsType"+i);

            sponsors=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsSponsors"+i);
            chamber=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsChamber"+i);
            status=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsStatus"+i);

            introON=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsIntroducedOn"+i);
            congressUrl=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsCongressUrl"+i);
            versionStatus=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsVersionStatus"+i);

            billUrl=sharedPreferenceProvider.fatchDataLegislators(getActivity(),"billsUrl"+i);

            if(!billId.equals("0"))
            billsModels.add(new BillsModel(billId,billTitle,billtype,sponsors,chamber,status,introON,congressUrl,versionStatus,billUrl
            ));
        }
        BillsAdapter adapter = new BillsAdapter(getActivity(), billsModels);

        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getActivity(), BillsDiescriptionsActivity.class);
                in.putExtra("billId",billsModels.get(i).getBillId());
                in.putExtra("billTitle",billsModels.get(i).getTitle());
                in.putExtra("billType",billsModels.get(i).getBillType());
                in.putExtra("sponsers",billsModels.get(i).getSponsers());
                in.putExtra("chambers",billsModels.get(i).getChamber());
                in.putExtra("status","New");
                in.putExtra("introducedOn",billsModels.get(i).getIntroducedOn());
                in.putExtra("Congress_url",billsModels.get(i).getCongressUrl());
                in.putExtra("versionStatus",billsModels.get(i).getVersionStatus());
                in.putExtra("billUrl",billsModels.get(i).getBillUrl());


                getActivity().startActivity(in);
            }
        });

        return v;
    }
}
