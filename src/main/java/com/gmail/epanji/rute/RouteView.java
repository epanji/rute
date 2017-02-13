package com.gmail.epanji.rute;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Fragment to use in layout / xml as a
 * {@link com.google.android.gms.maps.GoogleMap} container.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RouteView extends SupportMapFragment {
    /**
     * This view adapter.
     */
    private RouteAdapter mAdapter;

    /**
     * Marker options represent origin coordinate.
     */
    private MarkerOptions mOrigin;

    /**
     * Marker options represent destination coordinate.
     */
    private MarkerOptions mDestination;

    /**
     * Set marker options represent origin.
     *
     * @param origin MarkerOptions for origin
     * @see MarkerOptions
     */
    public void setOrigin(MarkerOptions origin){
        this.mOrigin = origin;
    }

    /**
     * Set marker options represent destination.
     *
     * @param destination MarkerOptions for destination
     * @see MarkerOptions
     */
    public void setDestination(MarkerOptions destination) {
        this.mDestination = destination;
    }

    /**
     * Set this view adapter.
     *
     * @param adapter the RouteAdapter
     * @see RouteAdapter #RouteAdapter(Context, GoogleMap, List)
     */
    public void setAdapter(RouteAdapter adapter) {
        this.mAdapter = adapter;
        drawMarker();
        drawPolyline();
    }

    private void drawMarker() {
        if (null != mAdapter) {
            GoogleMap map = mAdapter.getMap();
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    mAdapter.getRoutes().get(0).getSteps().get(0).getStartLocation(),
                    mAdapter.getZoom()));
            if (null != mOrigin) map.addMarker(mOrigin);
            for (int i=0; i < mAdapter.getCount(); i++) {
                map.addMarker((MarkerOptions) mAdapter.getItem(i));
            }
            if (null != mDestination) map.addMarker(mDestination);
        }
    }

    private void drawPolyline() {
        if (null != mAdapter) {
            GoogleMap map = mAdapter.getMap();
            List<Route> routes = mAdapter.getRoutes();
            LatLng src = null;
            LatLng dest = null;
            int color = 0;
            for (Route r : routes) {
                List<LatLng> listLatLng = PointsDecoder.decode(r.getPoints());
                for (int i = 0; i < listLatLng.size() - 1; i++) {
                    src = listLatLng.get(i);
                    dest = listLatLng.get(i + 1);
                    if (null != mOrigin & color == 0) {
                        map.addPolyline(new PolylineOptions()
                                .add(mOrigin.getPosition(),
                                        new LatLng(src.latitude, src.longitude))
                                .color(r.getColor()).geodesic(true)
                                .width(4));
                    }
                    color = r.getColor();
                    map.addPolyline(new PolylineOptions()
                            .add(new LatLng(src.latitude, src.longitude),
                                    new LatLng(dest.latitude, dest.longitude))
                            .color(color).geodesic(true)
                            .width(4));
                }
            }
            if (null != mDestination & null != dest) {
                map.addPolyline(new PolylineOptions()
                        .add(new LatLng(dest.latitude, src.longitude),
                                mDestination.getPosition())
                        .color(color).geodesic(true)
                        .width(4));
            }
        }
    }
}
