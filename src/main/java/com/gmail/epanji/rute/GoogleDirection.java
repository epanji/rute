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

import java.util.List;

/**
 * Abstract class represent url and it's parameters needed to get
 * json from google directions.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class GoogleDirection {
    /**
     * URL to request json from google directions.
     */
    protected final String API_JSON = "http://maps.googleapis.com/maps/api/directions/json?";

    protected final String SEPARATOR = "&";
    protected final String LANGUAGE = "language";
    protected final String MODE = "mode";
    protected final String ALTERNATIVES = "alternatives";
    protected final String ORIGIN = "origin";
    protected final String DESTINATION = "destination";
    protected final String WAYPOINTS = "waypoints";
    protected final String WAYPOINT_SEPARATOR = "|";

    /**
     * The language in which to return results.
     *
     * default = id
     */
    protected String mLanguage = "id";

    /**
     * Specifies the mode of transport to use when calculating directions.
     *
     * default = driving
     */
    protected String mMode = "driving";

    /**
     * One route as default or set to true for more than one
     *
     * default = false
     */
    protected String mAlternatives = "false";

    /**
     * The address, textual latitude/longitude value, or
     * place ID from which you wish to calculate directions.
     */
    private String mOrigin;

    /**
     * The address, textual latitude/longitude value,
     * or place ID to which you wish to calculate directions.
     */
    private String mDestination;

    /**
     * Specifies an array of waypoints
     */
    private List<String> mWaypoints;

    /**
     * Class constructor specifying {@link #mOrigin origin}
     * and {@link #mDestination destination}.
     *
     * @param origin latitude/longitude value, or place ID
     * @param destination latitude/longitude value, or place ID
     */
    public GoogleDirection(String origin, String destination) {
        this.mOrigin = origin;
        this.mDestination = destination;
    }

    /**
     * Get origin.
     *
     * @return latitude/longitude value,or place ID
     */
    public String getOrigin() {
        return mOrigin;
    }

    /**
     * Set origin.
     *
     * @param origin latitude/longitude value, or place ID
     */
    public void setOrigin(String origin) {
        this.mOrigin = origin;
    }

    /**
     * Get destination.
     *
     * @return latitude/longitude value, or place ID
     */
    public String getDestination() {
        return mDestination;
    }

    /**
     * Set destination.
     *
     * @param destination latitude/longitude value, or place ID
     */
    public void setDestination(String destination) {
        this.mDestination = destination;
    }

    /**
     * Get waypoints.
     *
     * @return array of waypoints
     */
    public List<String> getWaypoints() {
        return mWaypoints;
    }

    /**
     * Set waypoints.
     *
     * @param waypoints array of waypoints
     */
    public void setWaypoints(List<String> waypoints) {
        this.mWaypoints = waypoints;
    }

    /**
     * Get final url with all parameters to be used on google directions.
     *
     * @return url with parameters
     * @see <a href="https://developers.google.com/maps/documentation/directions/">Directions</a>
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (null != mWaypoints) {
            if (!mWaypoints.isEmpty()) {
                sb.append(SEPARATOR).append(WAYPOINTS).append('=');
                for (String s : mWaypoints) {
                    sb.append(s).append(WAYPOINT_SEPARATOR);
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return API_JSON + ORIGIN + '=' + mOrigin + SEPARATOR
                + DESTINATION + '=' + mDestination + sb.toString() + SEPARATOR
                + LANGUAGE + '=' + mLanguage + SEPARATOR
                + MODE + '=' + mMode + SEPARATOR
                + ALTERNATIVES + '=' + mAlternatives;
    }
}