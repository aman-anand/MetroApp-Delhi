package com.vgroupinc.metroapp.showRoute;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vgroupinc.metroapp.R;
import com.vgroupinc.metroapp.Utils.Utils;
import com.vgroupinc.metroapp.base.AppController;
import com.vgroupinc.metroapp.base.bean.Station;
import com.vgroupinc.metroapp.base.views.MyAlert;
import com.vgroupinc.metroapp.routeFinder.bean.Journey;
import com.vgroupinc.metroapp.showRoute.adapter.RouteAdapter;

import java.util.ArrayList;
import java.util.HashSet;

public class ShowRouteDetails extends AppCompatActivity {
    private final String TAG = ShowRouteDetails.class.getSimpleName();
    private TextView from, to, fare, runtime, distance, lineChange;
    private ImageView image;
    private NestedScrollView nestedScrollView;
    private ListView listView;
    private Context context;
    private Journey journey;
    private RouteAdapter routeAdapter;
    private ArrayList<Station> stationArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        journey = (Journey) getIntent().getSerializableExtra("JOURNEY");
        if (Utils.getInstance().isNull(journey)) {
            MyAlert.getInstance(getBaseContext()).showError("Error in fetching data, Try again!");
        } else {
            Log.e(TAG, "Journey\n " + journey.getDistance() + "\n" + journey.getFare() + "\n" + "Stations \n");
            int[] st = journey.getStationList();
            for (int i = 0; i < st.length; i++) {
                Log.e(TAG, String.valueOf(st[i]) + "\n");
            }
        }
        initViews();

        stationArrayList = createALfromArray(journey.getStationList());
        routeAdapter = new RouteAdapter(context, stationArrayList);
        listView.setAdapter(routeAdapter);
        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewCompat.setNestedScrollingEnabled(listView, true);
    }

    /**
     * for creating a arraylist from the array formed from the result obtained from database
     * @param stationList array of station codes
     * @return arrayList containing list of station objects wrt journey
     */
    private ArrayList<Station> createALfromArray(int[] stationList) {
        ArrayList<Station> sList = new ArrayList<>();
        for (int i = 0; i < stationList.length; i++) {
            int id = stationList[i];
            for (int j = 0; j < AppController.getInstance().stations.size(); j++) {
                if (id == AppController.getInstance().stations.get(j).getStation_id()) {
                    sList.add(AppController.getInstance().stations.get(j));
                    break;
                }
            }
        }
        int linechangeCount = -1;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < sList.size(); i++) {
            hashSet.add(sList.get(i).getLine_id());
        }
        journey.setLineChange(hashSet.size() - 1);
        return sList;
    }

    private void updateView() {
        from.setText(journey.getFrom());
        to.setText(journey.getTo());
        fare.setText(journey.getFare());
        if (!journey.getRuntime().equals("N.A.")) {
            runtime.setText("" + Utils.getMinutesFromFormattedDuration(journey.getRuntime()));
        } else {
            runtime.setText("" + journey.getRuntime());

        }
        distance.setText(journey.getDistance());
        lineChange.setText("" + journey.getLineChange());
    }

    private void initViews() {
        context = ShowRouteDetails.this;
        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        fare = (TextView) findViewById(R.id.fare);
        runtime = (TextView) findViewById(R.id.runtime);
        distance = (TextView) findViewById(R.id.distance);
        lineChange = (TextView) findViewById(R.id.lineChange);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        listView = nestedScrollView.findViewById(R.id.listView);
        image = (ImageView) findViewById(R.id.image001);
//        image.setColorFilter(getResources().getColor(R.color.OrangeLine), PorterDuff.Mode.SRC_ATOP);

    }

}
