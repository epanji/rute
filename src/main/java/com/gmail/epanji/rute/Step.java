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

import com.google.android.gms.maps.model.LatLng;

/**
 * An object as a part of Route object to store detail information.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Step implements RouteInterface {
    /**
     * Coordinate start location.
     */
    private LatLng mStartLocation;

    /**
     * Coordinate end location.
     */
    private LatLng mEndLocation;

    /**
     * Instructions in html format.
     */
    private String mHtmlInstructions;

    /**
     * Distance between start and end location.
     */
    private String mDistance;

    /**
     * Duration from start to end location.
     */
    private String mDuration;

    /**
     * Encoded string.
     */
    private String mPoints;

    /**
     * Get start location.
     *
     * @return start location {@link LatLng}.
     */
    public LatLng getStartLocation() {
        return mStartLocation;
    }

    /**
     * Set start location.
     *
     * @param start start location
     */
    public void setStartLocation(LatLng start) {
        this.mStartLocation = start;
    }

    /**
     * Get end location.
     *
     * @return end location {@link LatLng}
     */
    public LatLng getEndLocation() {
        return mEndLocation;
    }

    /**
     * Set end location.
     *
     * @param end end location {@link LatLng}
     */
    public void setEndLocation(LatLng end) {
        this.mEndLocation = end;
    }

    /**
     * Get instructions in html format.
     *
     * @return instructions
     */
    public String getHtmlInstructions() {
        return mHtmlInstructions;
    }

    /**
     * Set html instructions from json google directions.
     *
     * @param html intructions
     */
    public void setHtmlInstructions(String html) {
        this.mHtmlInstructions = html;
    }

    /**
     * Get distance from this step to next.
     *
     * @return distance value
     */
    public String getDistance() {
        return mDistance;
    }

    /**
     * Set distance from json google directions.
     *
     * @param distance distance value
     */
    public void setDistance(String distance) {
        this.mDistance = distance;
    }

    /**
     * Get duration from this step to next.
     *
     * @return duration value
     */
    public String getDuration() {
        return mDuration;
    }

    /**
     * Set duration from json google directions.
     *
     * @param duration duration value
     */
    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    /**
     * Get encoded string represent all latitude
     * and longitude from this step to next.
     *
     * @return encoded string
     */
    public String getPoints() {
        return mPoints;
    }

    /**
     * Set encoded string from json google directions.
     *
     * @param points encoded string
     */
    public void setPoints(String points) {
        this.mPoints = points;
    }
}
