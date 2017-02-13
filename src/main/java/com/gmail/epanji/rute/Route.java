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
 * An object represent json from google directions.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Route implements RouteInterface, ColorInterface {
    static final String ROUTES = "routes";
    static final String OVERVIEW_POLYLINE = "overview_polyline";
    static final String LEGS = "legs";
    static final String STEPS = "steps";

    /**
     * List Step object which stored detail information.
     * @see Step
     */
    protected List<Step> mSteps;

    /**
     * Encoded string represent all latitude
     * and longitude from {@link GoogleDirection#mOrigin origin}
     * to {@link GoogleDirection#mDestination destination}.
     */
    protected String mPoints;

    /**
     * Distance from {@link GoogleDirection#mOrigin origin}
     * to {@link GoogleDirection#mDestination destination}.
     */
    protected String mDistance;

    /**
     * Color for current route.
     *
     * default = {@link #DEFAULT_COLOR}
     */
    protected int mColor = DEFAULT_COLOR;

    /**
     * Get encoded string {@link #mPoints points}.
     *
     * @return encoded string
     */
    public String getPoints() {
        return mPoints;
    }

    /**
     * Set encoded string from json google directions.
     *
     * @param mPoints encoded string
     */
    public void setPoints(String mPoints) {
        this.mPoints = mPoints;
    }

    /**
     * Get {@link #mDistance distance}.
     *
     * @return distance string
     */
    public String getDistance() {
        return mDistance;
    }

    /**
     * Set distance string from json google directions.
     *
     * @param mDistance distance string
     */
    public void setDistance(String mDistance) {
        this.mDistance = mDistance;
    }

    /**
     * Get list Step object which stored detail information.
     *
     * @return list Step object
     */
    public List<Step> getSteps() {
        return mSteps;
    }

    /**
     * Set list Step object that created from json google directions.
     *
     * @param steps list Step object
     */
    public void setSteps(List<Step> steps) {
        this.mSteps = steps;
    }

    /**
     * Get current route {@link #mColor color}.
     *
     * @return int color for current route
     */
    @Override
    public int getColor() {
        return mColor;
    }

    /**
     * Set current route {@link #mColor color}.
     *
     * @param color int color for current route
     */
    @Override
    public void setColor(int color) {
        this.mColor = color;
    }
}
