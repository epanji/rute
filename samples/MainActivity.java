package com.gmail.epanji.library;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmail.epanji.rute.Route;
import com.gmail.epanji.rute.RouteAdapter;
import com.gmail.epanji.rute.RouteBuilder;
import com.gmail.epanji.rute.RouteView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RouteView mRouteView;

    private Double mOriginLat = -7.792329;
    private Double mOriginLng = 110.407895;

    private Double mDestinationLat = -7.784654;
    private Double mDestinationLng = 110.395058;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRouteView = (RouteView) getSupportFragmentManager()
			.findFragmentById(R.id.rvMain1);
        mRouteView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        try {
            List<Route> lr = new Sample().execute().get();
            if (!lr.isEmpty()) {
                mRouteView.setOrigin(new MarkerOptions()
									 .position(new LatLng(mOriginLat,mOriginLng))
									 .title("This is origin"));

                mRouteView.setDestination(new MarkerOptions()
										  .position(new LatLng(mDestinationLat,mDestinationLng))
										  .title("This is destination"));

                mRouteView.setAdapter(new RouteAdapter(MainActivity.this, map, lr));

				//                add your own icon you like for points.
				//                mRouteView.setAdapter(
				//                        new RouteAdapter(MainActivity.this, map, lr, R.mipmap.my_point));
            }
        } catch (InterruptedException | ExecutionException ignored) {}
    }

    private class Sample extends AsyncTask<Void, Void, List<Route>> {
        @Override
        protected List<Route> doInBackground(Void... params) {
            String origin = mOriginLat + "," + mOriginLng;
            String destination = mDestinationLat + "," + mDestinationLng;

            RouteBuilder rb = new RouteBuilder(origin, destination);
            rb.addColor(0xFF63B3EA); // if not set, default color will be used.

			//            add more colors means alternatives = true.
            rb.addColor(Color.GREEN);
            rb.addColor(Color.parseColor("#85FFF7"));

			//            add waypoints sometimes make results less than colors you add.
            String[] waypoints = {"-7.796239,110.401054", "-7.7963694,110.39262"};
            rb.setWaypoints(Arrays.asList(waypoints));

            return rb.build();
        }
    }
}
