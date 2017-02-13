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

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to decode a string which is encoded by google directions.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class PointsDecoder {
    /**
     * Decodes the argument which is assumed to be encoded by google directions.
     *
     * @param s the encoded string.
     * @return the decoded list {@link com.google.android.gms.maps.model.LatLng}
     */
    public static List<LatLng> decode(String s) {
        List<LatLng> points = new ArrayList<>();

        sIndex = 0;

        int lat = 0;
        int lng = 0;

        while (sIndex < s.length()) {
            lat += addition(s);
            lng += addition(s);
            LatLng point = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            points.add(point);
        }

        return points;
    }

    private static int sIndex;

    private static int addition(String s) {
        int current;

        int shift = 0;
        int result = 0;

        do {
            current = s.charAt(sIndex++) - 63;
            result |= (current & 0x1f) << shift;
            shift += 5;
        } while (current >= 0x20);

        return ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
    }
}