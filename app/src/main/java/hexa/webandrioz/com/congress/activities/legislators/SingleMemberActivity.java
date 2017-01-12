package hexa.webandrioz.com.congress.activities.legislators;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hexa.webandrioz.com.congress.R;
import hexa.webandrioz.com.congress.constants.Constants;
import hexa.webandrioz.com.congress.utils.SharedPreferenceProvider;

public class SingleMemberActivity extends AppCompatActivity {
    boolean b = true;
    SharedPreferenceProvider sharedPreferenceProvider = new SharedPreferenceProvider();

    String name, email, chamber, contact, sTerm, eTerm, term, office, state, fax, bday, party, partyLogo, imageUrl, facebook, twitter, website, secondLine = "kd";
    int count;
    String flagUrl,lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_member);

        getSupportActionBar().setTitle("Legislators Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ImageView fav = (ImageView) findViewById(R.id.legislatorsFav);


        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        chamber = getIntent().getStringExtra("chamber");
        contact = getIntent().getStringExtra("contact");
        sTerm = getIntent().getStringExtra("sterm");
        eTerm = getIntent().getStringExtra("eTerm");
        office = getIntent().getStringExtra("office");
        state = getIntent().getStringExtra("state");
        fax = getIntent().getStringExtra("fax");
        bday = getIntent().getStringExtra("bday");
        party = getIntent().getStringExtra("party");
        partyLogo = getIntent().getStringExtra("partyLogo");
        imageUrl = getIntent().getStringExtra("imageUrl");
        facebook = getIntent().getStringExtra("facebook");
        twitter = getIntent().getStringExtra("twitter");
        website = getIntent().getStringExtra("website");
        term = getIntent().getStringExtra("term");
        lastName=getIntent().getStringExtra("lastName");
        secondLine = getIntent().getStringExtra("secondLine");
        if(email.equals("null")){
            email="N.A.";
        }


        TextView partyName= (TextView) findViewById(R.id.legoParty);
        if(party.equals("R")){
            partyName.setText("Republic");
            partyName.setTextSize(18);
            flagUrl="http://cs-server.usc.edu:45678/hw/hw8/images/r.png";
        }else{
            partyName.setText("Democratic");
            partyName.setTextSize(18);

            flagUrl="http://cs-server.usc.edu:45678/hw/hw8/images/d.png";

        }
        ImageView flag= (ImageView) findViewById(R.id.flag);

        Picasso.with(SingleMemberActivity.this).load(flagUrl).resize(200, 200).into(flag);


        String totalC = sharedPreferenceProvider.fatchDataLegislators(SingleMemberActivity.this, "totalNumber");
        count = Integer.parseInt(totalC);
        Log.e("count", count + "");
        boolean c = false;
        for (int i = 1; i <= count; i++) {
            String existFav = sharedPreferenceProvider.fatchDataLegislators(SingleMemberActivity.this, "legoName" + i);
            Log.e("existFav", existFav+"  "+name);
            if (existFav.equals(name)) {
                c = true;
                count=i;
                Log.e("mission","accomplished");
                break;
            }
        }
        if (c) {
            fav.setImageResource(R.drawable.gold_star);
            b = false;
        } else {
            fav.setImageResource(R.drawable.favorite);
            b = true;
        }




        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b) {
                    fav.setImageResource(R.drawable.gold_star);
                    count++;
                    Log.e("incrCount", count + "");
                    //increase count
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "totalNumber", "" + count);
                    //save data
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoName" + count, name);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoEmail" + count, email);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoChamber" + count, chamber);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoContact" + count, contact);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoSTerm" + count, sTerm);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoETerm" + count, eTerm);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoOffice" + count, office);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoState" + count, state);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoFax" + count, fax);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoBday" + count, bday);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoParty" + count, party);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoPartyLogo" + count, partyLogo);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoImage" + count, "" + imageUrl);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoFacebook" + count, facebook);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoTwitter" + count, twitter);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoWebsite" + count, website);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoTerm" + count, "" + term);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoSecondLine" + count, "" + secondLine);
                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "legoLastName" + count, "" + lastName);





                    b = false;
                } else {
                    fav.setImageResource(R.drawable.favorite);
                    b = true;
                    Log.e("incrCoudajnqwknt", count + "");

                    //delete current data
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoName" + count, name);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoEmail" + count, email);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoChamber" + count, chamber);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoContact" + count, contact);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoSTerm" + count, sTerm);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoETerm" + count, eTerm);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoOffice" + count, office);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoState" + count, state);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoFax" + count, fax);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoBday" + count, bday);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoParty" + count, party);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoPartyLogo" + count, partyLogo);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoImage" + count, "" + imageUrl);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoFacebook" + count, facebook);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoTwitter" + count, twitter);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoWebsite" + count, website);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoTerm" + count, "" + term);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoSecondLine" + count, "" + secondLine);
                    sharedPreferenceProvider.deleteKey(SingleMemberActivity.this, "legoLastName" + count, "" + lastName);

//                    count--;
//                    sharedPreferenceProvider.storeData(SingleMemberActivity.this, "totalNumber", "" + count);//store count
//                    Log.e("dele", sharedPreferenceProvider.fatchDataLegislators(SingleMemberActivity.this, "totalNumber"));

                }
            }
        });

        ImageView personImage = (ImageView) findViewById(R.id.personImage);
        Log.e("Single user", Constants.IMAGE_URL + imageUrl + ".jpg");
        Picasso.with(SingleMemberActivity.this).load(Constants.IMAGE_URL + imageUrl + ".jpg").resize(200, 200).into(personImage);
        final ImageView facebookimage = (ImageView) findViewById(R.id.facebook);
        facebookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!facebook.equals(" ")) {
                    String url = "https://www.facebook.com/" + facebook;
                    Log.e("facebookUrl", url);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(SingleMemberActivity.this, "URL Not Found", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ImageView twitterImage = (ImageView) findViewById(R.id.twitter);
        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!twitter.equals(" ")) {
                    String url = "https://www.twitter.com/" + twitter;
                    Log.e("tweetUrl", url);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(SingleMemberActivity.this, "URL Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        Log.e("facebookUrl",website);
        ImageView web = (ImageView) findViewById(R.id.earth);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!website.equals(" ")) {
                    String url = website;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(SingleMemberActivity.this, "URL Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });



        TextView pbText= (TextView) findViewById(R.id.pbText);
        pbText.setText(term+"%");
        pbText.setTextColor(Color.parseColor("#000000"));
        ProgressBar pb= (ProgressBar) findViewById(R.id.pb);
        pb.setProgress(Integer.parseInt(term));
        pb.setMinimumHeight(40);

        TextView nameText = (TextView) findViewById(R.id.legName);
        final TextView emailText = (TextView) findViewById(R.id.legEmail);
        TextView chamberText = (TextView) findViewById(R.id.legChamber);
        TextView contactText = (TextView) findViewById(R.id.legContact);
        TextView sTermText = (TextView) findViewById(R.id.legStartTerm);
        TextView eTermText = (TextView) findViewById(R.id.legEndterm);
//        TextView termText = (TextView) findViewById(R.id.legTerm);
        TextView officeText = (TextView) findViewById(R.id.legOffice);
        TextView stateText = (TextView) findViewById(R.id.legState);
        TextView faxText = (TextView) findViewById(R.id.legFax);
        TextView bdayText = (TextView) findViewById(R.id.legBday);
        nameText.setText(name);
        emailText.setText(email);
        chamberText.setText(chamber);
        contactText.setText(contact);
        sTermText.setText(sTerm);
        eTermText.setText(eTerm);
//        termText.setText(term);
        officeText.setText(office);
        stateText.setText(state);
        faxText.setText(fax);
        bdayText.setText(bday);
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailText.getText().toString().equals("N.A.")){
                    Toast.makeText(SingleMemberActivity.this,"Email Is Not Available",Toast.LENGTH_SHORT).show();
                }else{
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto",emailText.getText().toString(), null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(emailIntent, "Send Email..."));
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
