package com.vgroupinc.metroapp.routeFinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vgroupinc.metroapp.R;
import com.vgroupinc.metroapp.base.AppController;
import com.vgroupinc.metroapp.base.adapter.StationAdapter;
import com.vgroupinc.metroapp.base.bean.Station;
import com.vgroupinc.metroapp.base.views.MyAlert;
import com.vgroupinc.metroapp.base.views.MyProgressBar;
import com.vgroupinc.metroapp.routeFinder.bean.Journey;
import com.vgroupinc.metroapp.routeFinder.dao.Dao;
import com.vgroupinc.metroapp.showRoute.ShowRouteDetails;

public class RouteFinder extends AppCompatActivity {
    Dao dao;
    MyProgressBar myProgressBar = MyProgressBar.getProgressDialogInstance();
    private AutoCompleteTextView fromAc, toAc;
    private TextView searchBtn;
    private ImageButton swap;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_finder);
        init();
    }

    private void init() {
        context = RouteFinder.this;
        dao = new Dao();
        myProgressBar.showProgress("Initilizing, Please Wait!", context);
        fromAc = (AutoCompleteTextView) findViewById(R.id.ACfrom);
        toAc = (AutoCompleteTextView) findViewById(R.id.ACto);
        searchBtn = (TextView) findViewById(R.id.search);
        swap = (ImageButton) findViewById(R.id.swap);

        dao.initArrayList();
//        ArrayAdapter<Station> adapter = new ArrayAdapter<>(context,
//                android.R.layout.simple_dropdown_item_1line, AppController.getInstance().stations);
        StationAdapter stationAdapter = new StationAdapter(context, R.layout.station_item, AppController.getInstance().stations);
        myProgressBar.dismissProgressDialog();
        fromAc.setThreshold(1);
        toAc.setThreshold(1);
        fromAc.setAdapter(stationAdapter);
        toAc.setAdapter(stationAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyData();
            }

        });
        toAc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    verifyData();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(toAc.getWindowToken(), 0);

                    return true;
                }
                return false;
            }

        });

    }

    private void verifyData() {

        myProgressBar.showProgress("Searching...", context);
        int check = 0;
        int from_ID = 0;
        int to_ID = 0;
        String from = fromAc.getText().toString().trim();
        String to = toAc.getText().toString().trim();
        if (from.equals("")){
            MyAlert.getInstance(context).showError("Please Select From Station");
            return;
        }else if (to.equals("")){
            MyAlert.getInstance(context).showError("Please Select To Station");
            return;
        }
        for (Station station : AppController.getInstance().stations) {
            if (station.getStation_name().trim().equalsIgnoreCase(from)) {
                check++;
                from_ID = station.getStation_id();
            }
            if (station.getStation_name().trim().equalsIgnoreCase(to)) {
                check++;
                to_ID = station.getStation_id();
            }
            if (check == 2) {
                break;
            }
        }
        if (check == 2) {
//            Toast.makeText(context, "from"+from_ID+"\n to"+to_ID, Toast.LENGTH_SHORT).show();
            Journey journey = dao.findRoute(from_ID, to_ID, from, to);
            if (journey == null) {
                MyAlert.getInstance(context).showError("Database error, Try again");
            } else {
                Intent intent = new Intent(context, ShowRouteDetails.class);
                intent.putExtra("JOURNEY", journey);
                startActivity(intent);
            }
        } else {
            MyAlert.getInstance(context).showError("Select Stations from drop down only!");

        }
        myProgressBar.dismissProgressDialog();
    }
}
