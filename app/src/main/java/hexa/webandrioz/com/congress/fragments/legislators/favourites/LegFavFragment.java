package hexa.webandrioz.com.congress.fragments.legislators.favourites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import dmax.dialog.SpotsDialog;
import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.activities.legislators.SingleMemberActivity;
import hexa.webandrioz.com.congress.adapters.SimpleListViewAdapter;
import hexa.webandrioz.com.congress.model.LegislatorsModel;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegFavFragment extends Fragment {


    ArrayList<LegislatorsModel> legislatorsModels;
    ListView mListView;
    SpotsDialog dialog;
    String name, imageUrl, secondLine;
    int count;
    SharedPreferenceProvider sharedPreferenceProvider;
    String indexText="",lastName;
    TextView index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_leg_fav, container, false);
        legislatorsModels = new ArrayList<>();
        index= (TextView) v.findViewById(R.id.indexTest);
        index.setText(indexText.toUpperCase());


        mListView = (ListView) v.findViewById(R.id.listview);
        sharedPreferenceProvider = new SharedPreferenceProvider();
        String totalC = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "totalNumber");
        count = Integer.parseInt(totalC);

        for (int i = 1; i <= count; i++) {
            name = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoName" + i);
            imageUrl = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoImage" + i);
            secondLine = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoSecondLine" + i);
            lastName = sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoLastName" + i);
//            indexText = indexText + "" + lastName.charAt(0) + "\n";
            if (!name.equals("0")) {
                indexText = indexText + "" + lastName.charAt(0) ;
                legislatorsModels.add(new LegislatorsModel(
                        name,
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoEmail" + i),
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoChamber" + i),
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoContact" + i),//phone
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoSTerm" + i),
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoETerm" + i),
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoTerm" + i),//term
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoOffice" + i),//office
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoState" + i),//state
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoFax" + i),//fax
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoBday" + i),//bday
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoParty" + i),//party
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoPartyLogo" + i),//party_logo
                        imageUrl,// imageUrl
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoFacebook" + i),
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoTwitter" + i),// jsonArray.getJSONObject(i).getString("twitter_id"),//twitter
                        sharedPreferenceProvider.fatchDataLegislators(getActivity(), "legoWebsite" + i),
                        secondLine, lastName));
            }
        }

        char[] chars = indexText.toCharArray();
        Arrays.sort(chars);
        String [] s=new String[chars.length];
        for(int x=0;x<chars.length;x++){
            s[x]=""+chars[x];
        }
        String[] c = new HashSet<CharSequence>(Arrays.asList(s)).toArray(new String[0]);

        StringBuilder sb=new StringBuilder();
        for(int j=0;j<c.length;j++) {
            sb.append((c[j] + "\n").toUpperCase());
        }
        index.append(sb.toString());

        SimpleListViewAdapter adapter = new SimpleListViewAdapter(getActivity(), legislatorsModels,1,sb.toString());
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getActivity(), SingleMemberActivity.class);
                in.putExtra("name",legislatorsModels.get(i).getName());
                in.putExtra("email",legislatorsModels.get(i).getEmail());
                in.putExtra("chamber",legislatorsModels.get(i).getChamber());
                in.putExtra("contact",legislatorsModels.get(i).getContact());
                in.putExtra("sterm",legislatorsModels.get(i).getsTerm());
                in.putExtra("eTerm",legislatorsModels.get(i).geteTerm());
                in.putExtra("office",legislatorsModels.get(i).getOffice());
                in.putExtra("state",legislatorsModels.get(i).getState());
                in.putExtra("fax",legislatorsModels.get(i).getFax());
                in.putExtra("bday",legislatorsModels.get(i).getBday());
                in.putExtra("party",legislatorsModels.get(i).getParty());
                in.putExtra("partyLogo",legislatorsModels.get(i).getPartyLogo());
                in.putExtra("imageUrl",legislatorsModels.get(i).getImageUrl());
                in.putExtra("facebook",legislatorsModels.get(i).getFacebook());
                in.putExtra("twitter",legislatorsModels.get(i).getTwitter());
                in.putExtra("website",legislatorsModels.get(i).getWebsite());
                in.putExtra("term",legislatorsModels.get(i).getTerm());
                in.putExtra("secondLine",legislatorsModels.get(i).getSecondLine());

                getActivity().startActivity(in);
            }
        });
        return v;
    }
}