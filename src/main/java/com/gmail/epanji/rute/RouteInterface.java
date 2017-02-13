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

/**
 * An interface used in Route and Step.
 *
 * @author Panji Kusuma
 * @version 1.0.0
 */
@SuppressWarnings("WeakerAccess")
public interface RouteInterface {
    String START_LOCATION = "start_location";
    String END_LOCATION = "end_location";
    String HTML_INSTRUCTIONS = "html_instructions";
    String DISTANCE = "distance";
    String DURATION = "duration";
    String POLYLINE = "polyline";
    String POINTS = "points";
    String TEXT = "text";
    String LAT = "lat";
    String LNG = "lng";
}