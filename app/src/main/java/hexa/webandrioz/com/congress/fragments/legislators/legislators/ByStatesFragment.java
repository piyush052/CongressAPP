package hexa.webandrioz.com.congress.fragments.legislators.legislators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.activities.legislators.SingleMemberActivity;
import hexa.webandrioz.com.congress.adapters.SimpleListViewAdapter;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.model.ByStatesModel;
import hexa.webandrioz.com.congress.model.LegislatorsModel;
import hexa.webandrioz.com.congress.utils.DateConversion;


public class ByStatesFragment extends Fragment {
     ArrayList<LegislatorsModel> legislatorsModels;
     ListView mListView;
    SpotsDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_by_states, container, false);
        legislatorsModels = new ArrayList<>();
        dialog = new SpotsDialog(getActivity());
        dialog.show();
        mListView = (ListView) v.findViewById(R.id.listview);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = Constants.STATE_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            Log.e("length", jsonArray.length() + "");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                String fax=" ";
                                if(jsonArray.getJSONObject(i).getString("fax").equals("null")){
                                    fax="N.A.";
                                }else{
                                   fax=jsonArray.getJSONObject(i).getString("fax");
                                }


                                String secondLine=" ";

                                if( jsonArray.getJSONObject(i).getString("district").equals("null")){
                                    secondLine=   "("+  jsonArray.getJSONObject(i).getString("party")+")"
                                            + jsonArray.getJSONObject(i).getString("state_name")+"- District -N.A.";

                                }else{
                                    secondLine=   "("+  jsonArray.getJSONObject(i).getString("party")+")"
                                            + jsonArray.getJSONObject(i).getString("state_name")+"- District "+
                                            jsonArray.getJSONObject(i).getString("district");
                                }
                                String twitter=" ",fac,website=" ";
                                if(!jsonArray.getJSONObject(i).has("twitter_id")){
                                    twitter=" ";
                                }else{
                                    twitter=jsonArray.getJSONObject(i).getString("twitter_id");
                                }
                                if(!jsonArray.getJSONObject(i).has("facebook_id")){
                                    fac=" ";
                                }else{
                                    fac=jsonArray.getJSONObject(i).getString("facebook_id");
                                }
                                if(jsonArray.getJSONObject(i).has("website")){
                                    website=jsonArray.getJSONObject(i).getString("website");
                                }else website=" ";

                                String title="";
                                if(jsonArray.getJSONObject(i).has("title")){
                                    title=jsonArray.getJSONObject(i).getString("title");
                                }else{
                                    title="";
                                }

                                String term=""+new DateConversion().term(jsonArray.getJSONObject(i).getString("term_start"),
                                        jsonArray.getJSONObject(i).getString("term_end"));
                                legislatorsModels.add(new LegislatorsModel(
                                      title+"  "+  jsonArray.getJSONObject(i).getString("last_name")+", "+jsonArray.getJSONObject(i).getString("first_name"),
                                        jsonArray.getJSONObject(i).getString("oc_email"),
                                        jsonArray.getJSONObject(i).getString("chamber"),
                                        jsonArray.getJSONObject(i).getString("phone"),
                                        new DateConversion().DateConversion(jsonArray.getJSONObject(i).getString("term_start")),
                                        new DateConversion().DateConversion(jsonArray.getJSONObject(i).getString("term_end")),
                                        term,//term
                                        jsonArray.getJSONObject(i).getString("office"),//office
                                        jsonArray.getJSONObject(i).getString("state_name"),//state
                                        fax,//fax
                                        new DateConversion().DateConversion(jsonArray.getJSONObject(i).getString("birthday")),//bday
                                        jsonArray.getJSONObject(i).getString("party"),//party
                                        jsonArray.getJSONObject(i).getString("district"),//party_logo
                                        jsonArray.getJSONObject(i).getString("bioguide_id"),// imageUrl
                                        fac,// jsonArray.getJSONObject(i).getString("facebook_id"),//facebook
                                        twitter,// jsonArray.getJSONObject(i).getString("twitter_id"),//twitter
                                        website,
                                       secondLine, jsonArray.getJSONObject(i).getString("last_name")
                                ));
                            }
                            SimpleListViewAdapter adapter=new SimpleListViewAdapter(getActivity(),legislatorsModels,"State");
                            mListView.setAdapter(adapter);
                            mListView.setFastScrollEnabled(true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error ", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
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
                in.putExtra("lastName",legislatorsModels.get(i).getLastName());

                getActivity().startActivity(in);
            }
        });

        return v;
    }
}
