package hexa.webandrioz.com.congress.fragments.legislators.bills;


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
import hexa.webandrioz.com.congress.activities.bills.BillsDiescriptionsActivity;
import hexa.webandrioz.com.congress.adapters.BillsAdapter;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.model.BillsModel;
import hexa.webandrioz.com.congress.utils.DateConversion;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewBillsFragment extends Fragment {


    ArrayList<BillsModel> billsModels;
    ListView mListView;
    SpotsDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_new_bills, container, false);
        billsModels = new ArrayList<>();
        dialog = new SpotsDialog(getActivity());
        dialog.show();
        mListView = (ListView) v.findViewById(R.id.listview);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = Constants.NEW_BILLS_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            Log.e("length", jsonArray.length() + "");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                String sponsor="";
                                sponsor=jsonArray.getJSONObject(i).getJSONObject("sponsor").getString("title")+
                                        " "+jsonArray.getJSONObject(i).getJSONObject("sponsor").getString("last_name")+
                                        ", "+jsonArray.getJSONObject(i).getJSONObject("sponsor").getString("first_name");



                                String title=" ";
                                if( jsonArray.getJSONObject(i).getString("short_title").equals("null")){
                                    title=jsonArray.getJSONObject(i).getString("official_title");
                                }else{
                                    title=  jsonArray.getJSONObject(i).getString("short_title")  ;
                                }
                                billsModels.add(new BillsModel(
                                        jsonArray.getJSONObject(i).getString("bill_id") ,
                                        title,//title
                                        jsonArray.getJSONObject(i).getString("bill_type"),
                                        sponsor,//sponsor
                                        jsonArray.getJSONObject(i).getString("chamber"),
                                        jsonArray.getJSONObject(i).getString("bill_type"),// status
                                        new DateConversion().DateConversion( jsonArray.getJSONObject(i).getString("introduced_on")),
                                        jsonArray.getJSONObject(i).getJSONObject("urls").getString("congress"),
                                        jsonArray.getJSONObject(i).getJSONObject("last_version").getString("version_name"),//version status
                                        jsonArray.getJSONObject(i).getJSONObject("last_version").getJSONObject("urls").getString("pdf")//BillUrl
                                ));
                            }
                            BillsAdapter adapter = new BillsAdapter(getActivity(), billsModels);
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

