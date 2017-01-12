package hexa.webandrioz.com.congress.activities.comm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.activities.bills.BillsDiescriptionsActivity;
import hexa.webandrioz.com.congress.activities.legislators.SingleMemberActivity;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

public class CommDescriptionsActivity extends AppCompatActivity {
    String commId,commName,commChamber,commSubComm,parentCommId,phone,office;
    boolean b=true;
    int count;
    SharedPreferenceProvider sharedPreferenceProvider=new SharedPreferenceProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_descriptions);
        getSupportActionBar().setTitle("Committees Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        commId = getIntent().getStringExtra("commId");
        commName = getIntent().getStringExtra("commName");
        commChamber = getIntent().getStringExtra("commChamber");
        commSubComm = getIntent().getStringExtra("CommSubComm");
        parentCommId = getIntent().getStringExtra("commPComm");
        phone = getIntent().getStringExtra("commPhone");
        office=getIntent().getStringExtra("commOffice");

        ImageView chamberImage= (ImageView) findViewById(R.id.billImage);
        String imgUrl = Constants.SENATE_IMAGE_URL;
        if(commChamber.equals("house")){
          imgUrl="http://cs-server.usc.edu:45678/hw/hw8/images/h.png";
        }else if(commChamber.equals("senate")) {
            imgUrl = Constants.SENATE_IMAGE_URL;
        }
        Picasso.with(CommDescriptionsActivity.this).load(imgUrl).resize(200, 200).into(chamberImage);





        TextView commIdText= (TextView) findViewById(R.id.commId);
        TextView commNameText= (TextView) findViewById(R.id.commName);
        TextView commChamberText= (TextView) findViewById(R.id.commChamber);
        //  office
        final TextView commSubComText= (TextView) findViewById(R.id.commOffice);

        TextView commPCommText= (TextView) findViewById(R.id.commPComm);
        TextView commPhoneText= (TextView) findViewById(R.id.commContact);
        commIdText.setText(commId);
        commNameText.setText(commName);
        commChamberText.setText(commChamber);
        //
        commSubComText.setText(office);

        commPCommText.setText(parentCommId.toUpperCase());
        commPhoneText.setText(phone);


        final ImageView fav= (ImageView) findViewById(R.id.commFav);

        String totalC= sharedPreferenceProvider.fatchDataLegislators(CommDescriptionsActivity.this,"totalCommNumber");
        count=Integer.parseInt(totalC);
        Log.e("count",count+"");
        boolean c=false;
        for (int i=1;i<=count;i++) {
            String existFav = sharedPreferenceProvider.fatchDataLegislators(CommDescriptionsActivity.this, "commId" + i);
            Log.e("existFav", existFav);
            if(existFav.equals(commId)){
                c=true;
                count=i;
                break;
            }
        }
        if(c){
            fav.setImageResource(R.drawable.gold_star);
            b=false;
        }else{
            fav.setImageResource(R.drawable.favorite);
            b=true;
        }


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b) {
                    fav.setImageResource(R.drawable.gold_star);
                    count++;
                    Log.e("incrCount",count+"");
                    //increase count
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"totalCommNumber",""+count);
                    //save data
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commId"+count,commId);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commName"+count,commName);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commChamber"+count,""+commChamber);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commSubComm"+count,commSubComm);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commPComm"+count,parentCommId);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commPhone"+count,""+phone);
                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"commOffice"+count,office);



                    b=false;
                }else{
                    fav.setImageResource(R.drawable.favorite);
                    b=true;
                    Log.e("incrCoudajnqwknt",count+"");

                    //delete current data
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commId"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commName"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commChamber"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commSubComm"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commPComm"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commPhone"+count);
                    sharedPreferenceProvider.deleteKey(CommDescriptionsActivity.this,"commOffice"+count);
//                    count--;
//                    sharedPreferenceProvider.storeData(CommDescriptionsActivity.this,"totalCommNumber",""+count);//store count
//                    Log.e("dele",sharedPreferenceProvider.fatchDataLegislators(CommDescriptionsActivity.this,"totalCommNumber"));

                }
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
