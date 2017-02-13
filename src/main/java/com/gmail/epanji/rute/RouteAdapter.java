/*
 * Copyright (C) 2017  Panji Kusuma
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmail.epanji.rute;

import android.content.Context;
import android.os.Build;
import android.text.Html;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * RouteAdapter object acts as a bridge between
 * {@link RouteView} and list of {@link Route}.
 *
 * @see Route
 * @see RouteView
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RouteAdapter {
    /**
     * The list Route object.
     */
    private List<Route> mRoutes;

    /**
     * Zoom value for GoogleMap.
     */
    private float mZoom;

    /**
     * Android content context.
     */
    private Context mContext;

    /**
     * The GoogleMap.
     */
    private GoogleMap mMap;

    /**
     * List MarkerOptions.
     */
    private List<MarkerOptions> mItems;

    /**
     * Integer represent icon
     */
    private int mIcon;

    /**
     * Class constructor specifying {@link Context context},
     * {@link GoogleMap map} and list of {@link Route route}.
     *
     * @param c context
     * @param map google map
     * @param routes list of route
     */
    public RouteAdapter(Context c, GoogleMap map, List<Route> routes) {
        this.mRoutes = routes;
        this.mZoom = 14;

        this.mContext = c;
        this.mMap = map;
        this.mItems = new ArrayList<>();
        this.mIcon = R.mipmap.ic_point;
        setItems();
    }

    /**
     * Class constructor specifying {@link Context context},
     * {@link GoogleMap map}, list of {@link Route route}
     * and icon.
     *
     * @param c context
     * @param map google map
     * @param routes list of route
     * @param icon int represent icon
     */
    public RouteAdapter(Context c, GoogleMap map, List<Route> routes, int icon) {
        this.mRoutes = routes;
        this.mZoom = 14;

        this.mContext = c;
        this.mMap = map;
        this.mItems = new ArrayList<>();
        this.mIcon = icon;
        setItems();
    }

    @SuppressWarnings("deprecation")
    private void setItems() {
        for (Route r : mRoutes) {
            int i = 1;
            for (Step s : r.getSteps()) {
                MarkerOptions options = new MarkerOptions()
                        .position(s.getStartLocation())
                        .title(mContext.getString(R.string.route_title, i))
                        .anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory.fromResource(mIcon));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    options.snippet(String.valueOf(Html.fromHtml(s.getHtmlInstructions(),
                            Html.FROM_HTML_MODE_COMPACT)));
                } else {
                    options.snippet(String.valueOf(Html.fromHtml(s.getHtmlInstructions())));
                }

                mItems.add(options);
                i++;
            }
        }
    }

    /**
     * Get {@link GoogleMap map}.
     *
     * @return google map
     */
    public GoogleMap getMap() {
        return mMap;
    }

    /**
     * Get list of {@link Route route}.
     *
     * @return List of Route object
     */
    public List<Route> getRoutes() {
        return mRoutes;
    }

    /**
     * Get size of this adapter items.
     *
     * @return Size value
     */
    public int getCount() {
        return mItems.size();
    }

    /**
     * Get item from adapter by item's position.
     *
     * @param position index of item
     * @return Object {@link Object item}
     */
    public Object getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Get id item from adapter by item's position.
     *
     * @param position index of item
     * @return id of item
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get zoom value to be used on map.
     *
     * @return zoom value
     */
    public float getZoom() {
        return mZoom;
    }

    /**
     * Set this zoom value.
     *
     * @param zoom zoom value
     */
    public void setZoom(float zoom) {
        this.mZoom = zoom;
    }
}
