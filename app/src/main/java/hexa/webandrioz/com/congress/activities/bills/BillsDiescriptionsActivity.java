package hexa.webandrioz.com.congress.activities.bills;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.activities.comm.CommDescriptionsActivity;
import hexa.webandrioz.com.congress.activities.legislators.SingleMemberActivity;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

public class BillsDiescriptionsActivity extends AppCompatActivity {
    String billId,title,billType,sponsers,chamber,status,introducedOn,congressUrl,versionStatus,billUrl,index,fragment;
     boolean b=true;
    int count;
    SharedPreferenceProvider sharedPreferenceProvider=new SharedPreferenceProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_diescriptions);
        getSupportActionBar().setTitle("Bill Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        billId=getIntent().getStringExtra("billId");
        title=getIntent().getStringExtra("billTitle");
        billType=getIntent().getStringExtra("billType");
        sponsers=getIntent().getStringExtra("sponsers");
        chamber=getIntent().getStringExtra("chambers");
        status=getIntent().getStringExtra("status");
        introducedOn=getIntent().getStringExtra("introducedOn");
        congressUrl=getIntent().getStringExtra("Congress_url");
        versionStatus=getIntent().getStringExtra("versionStatus");
        billUrl=getIntent().getStringExtra("billUrl");



        TextView billIdText= (TextView) findViewById(R.id.billId);
        final TextView billTitleText= (TextView) findViewById(R.id.billTitle);
        TextView billtypeText= (TextView) findViewById(R.id.billType);
        TextView billSponsorText= (TextView) findViewById(R.id.billSponsor);
        TextView billChamberText= (TextView) findViewById(R.id.billChamber);
        TextView billStatusText= (TextView) findViewById(R.id.billStatus);
        TextView billIntroText= (TextView) findViewById(R.id.billIntroducedOn);
        final TextView billCongressUrlText= (TextView) findViewById(R.id.billCongressUrl);
        TextView billVersionStatusText= (TextView) findViewById(R.id.billVersionStatus);
        final TextView billUrlText= (TextView) findViewById(R.id.billUrl);

        billIdText.setText(billId.toUpperCase());
        billTitleText.setText(title);
        billtypeText.setText(billType.toUpperCase());
        billSponsorText.setText(sponsers);
        billChamberText.setText(chamber);
        billStatusText.setText(status);
        billIntroText.setText(introducedOn);
        billCongressUrlText.setText(congressUrl);
        billVersionStatusText.setText(versionStatus);
        billUrlText.setText(billUrl);
        billCongressUrlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = billCongressUrlText.getText().toString();
                if(!(url.equals("null"))) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }else Toast.makeText(BillsDiescriptionsActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
            }
        });
        billUrlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = billUrlText.getText().toString();
                if(!(url.equals("null"))) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }else Toast.makeText(BillsDiescriptionsActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView fav= (ImageView) findViewById(R.id.billFavourite);

        String totalC= sharedPreferenceProvider.fatchDataLegislators(BillsDiescriptionsActivity.this,"totalBillsNumber");
        count=Integer.parseInt(totalC);
        Log.e("count",count+"");
        boolean c=false;
        for (int i=1;i<=count;i++) {
            String existFav = sharedPreferenceProvider.fatchDataLegislators(BillsDiescriptionsActivity.this, "billsId" + i);
            Log.e("existFav", existFav);
            if(existFav.equals(billId)){
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
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"totalBillsNumber",""+count);
                    //save data
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsId"+count,billId);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsType"+count,billType);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsTitle"+count,""+title);

                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsSponsors"+count,sponsers);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsChamber"+count,chamber);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsStatus"+count,""+status);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsIntroducedOn"+count,introducedOn);

                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsCongressUrl"+count,congressUrl);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsVersionStatus"+count,""+versionStatus);
                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"billsUrl"+count,billUrl);

                    b=false;
                }else{
                    fav.setImageResource(R.drawable.favorite);
                    b=true;
                    Log.e("incrCoudajnqwknt",count+"");

                    //delete current data
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsId"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsType"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsTitle"+count);

                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsSponsors"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsChamber"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsStatus"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsIntroducedOn"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsCongressUrl"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsVersionStatus"+count);
                    sharedPreferenceProvider.deleteKey(BillsDiescriptionsActivity.this,"billsUrl"+count);

//                    count--;
//                    sharedPreferenceProvider.storeData(BillsDiescriptionsActivity.this,"totalBillsNumber",""+count);//store count
//                    Log.e("dele",sharedPreferenceProvider.fatchDataLegislators(BillsDiescriptionsActivity.this,"totalNumber"));

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
