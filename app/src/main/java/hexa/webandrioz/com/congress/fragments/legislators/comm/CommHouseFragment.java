package hexa.webandrioz.com.congress.fragments.legislators.comm;


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
import hexa.webandrioz.com.congress.activities.comm.CommDescriptionsActivity;
import hexa.webandrioz.com.congress.adapters.CommAdapter;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.model.CommModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommHouseFragment extends Fragment {

    ArrayList<CommModel> commModels;
    ListView mListView;
    SpotsDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_comm_house, container, false);
        commModels = new ArrayList<>();
        dialog = new SpotsDialog(getActivity());
        dialog.show();
        mListView = (ListView) v.findViewById(R.id.listview);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = Constants.COMMITTEES_HOUSE_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    Log.e("length", jsonArray.length() + "");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String parent="";
                        if(jsonArray.getJSONObject(i).has("parent_committee_id")){
                            parent=jsonArray.getJSONObject(i).getString("parent_committee_id");
                        }else{
                            parent="N.A.";
                        }
                        String office="";
                        if(jsonArray.getJSONObject(i).has("office")){
                            office=jsonArray.getJSONObject(i).getString("office");
                        }else{ office="N.A.";} String phone =" ";
                        if(jsonArray.getJSONObject(i).has("phone")){
                            phone=jsonArray.getJSONObject(i).getString("phone");
                        }else phone="N.A.";
                        commModels.add(new CommModel(

                                jsonArray.getJSONObject(i).getString("committee_id") ,
                                jsonArray.getJSONObject(i).getString("name"),
                                jsonArray.getJSONObject(i).getString("chamber"),
                                jsonArray.getJSONObject(i).getString("subcommittee"),
                                parent,
//                                jsonArray.getJSONObject(i).getString("parent_committee_id"),
                                phone,
                                office //office

                        ));
                    }
                    CommAdapter adapter = new CommAdapter(getActivity(), commModels);
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

