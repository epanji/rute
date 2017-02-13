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

import com.gmail.epanji.koneksiurl.KoneksiUrl;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class to build Route from json google directions.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RouteBuilder extends GoogleDirection {
    /**
     * String colors for routes in map
     */
    private List<Integer> mColors;

    /**
     * @see GoogleDirection#GoogleDirection(String, String)
     *
     * @param origin latitude/longitude value, or place ID
     * @param destination latitude/longitude value, or place ID
     */
    public RouteBuilder(String origin, String destination) {
        super(origin, destination);
        this.mColors = new ArrayList<>();
    }

    /**
     * Using {@link com.gmail.epanji.koneksiurl.KoneksiUrl} to get json
     * from {@link GoogleDirection#toString() url} and convert it
     * to Route object.
     *
     * @return the Route object.
     * @see Route
     */
    public List<Route> build() {
        List<Route> routes = new ArrayList<>();
        if (mColors.size() > 1) mAlternatives = "true";

        KoneksiUrl kUrl = new KoneksiUrl();
        String jsonStr = kUrl.execute(super.toString());
        try {
            JSONObject jo = new JSONObject(jsonStr);
            JSONArray jaRoutes = jo.optJSONArray(Route.ROUTES);
            for (int i = 0; i < jaRoutes.length(); i++) {
                JSONObject joRoute = jaRoutes.optJSONObject(i);

                Route route = new Route();
                if (mColors.size() > 0) route.setColor(mColors.get(i));

                List<Step> steps = new ArrayList<>();
                JSONArray jaLegs = joRoute.optJSONArray(Route.LEGS);
                for (int j = 0; j < jaLegs.length(); j++) {
                    JSONObject joLeg = jaLegs.optJSONObject(j);

                    JSONObject joDistance = joLeg.optJSONObject(Route.DISTANCE);
                    route.setDistance(joDistance.getString(Route.TEXT));

                    JSONObject joOverview = joRoute.optJSONObject(Route.OVERVIEW_POLYLINE);
                    route.setPoints(joOverview.getString(Route.POINTS));

                    JSONArray jaSteps = joLeg.optJSONArray(Route.STEPS);
                    if (jaSteps.length() > 0) {
                        for (int k = 0; k < jaSteps.length(); k++) {
                            JSONObject joStep = jaSteps.optJSONObject(k);

                            Step step = new Step();

                            JSONObject joStart = joStep.optJSONObject(Step.START_LOCATION);
                            step.setStartLocation(new LatLng(
                                    Double.valueOf(joStart.getString(Step.LAT)),
                                    Double.valueOf(joStart.getString(Step.LNG))));

                            JSONObject joEnd = joStep.optJSONObject(Step.END_LOCATION);
                            step.setEndLocation(new LatLng(
                                    Double.valueOf(joEnd.getString(Step.LAT)),
                                    Double.valueOf(joEnd.getString(Step.LNG))));

                            step.setHtmlInstructions(joStep.getString(Step.HTML_INSTRUCTIONS));
                            step.setDistance(joStep.optJSONObject(Step.DISTANCE).getString(Step.TEXT));
                            step.setDuration(joStep.optJSONObject(Step.DURATION).getString(Step.TEXT));
                            step.setPoints(joStep.optJSONObject(Step.POLYLINE).getString(Step.POINTS));

                            steps.add(step);
                        }
                    }
                }
                route.setSteps(steps);
                routes.add(route);
                if (i >= mColors.size()-1) break;
            }
        } catch (JSONException ignored) {}

        return routes;
    }

    /**
     * Get colors for routes.
     *
     * @return current list color
     */
    public List<Integer> getColors() {
        return mColors;
    }

    /**
     * Replace current list color with new list color.
     *
     * @param colors list of color for routes
     */
    public void setColors(List<Integer> colors) {
        mColors = colors;
    }

    /**
     * Clear current list color to be add later.
     */
    public void resetColors() {
        mColors.clear();
    }

    /**
     * Addition color to routes.
     *
     * @param color Integer color for route
     */
    public void addColor(int color) {
        mColors.add(color);
    }
}
