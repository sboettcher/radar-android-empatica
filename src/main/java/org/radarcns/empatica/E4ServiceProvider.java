/*
 * Copyright 2017 The Hyve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.radarcns.empatica;

import android.os.Bundle;
import android.os.Parcelable;

import org.radarcns.android.RadarConfiguration;
import org.radarcns.android.device.DeviceServiceProvider;

import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.BLUETOOTH_ADMIN;

public class E4ServiceProvider extends DeviceServiceProvider<E4DeviceStatus> {
    @Override
    public Class<?> getServiceClass() {
        return E4Service.class;
    }

    @Override
    public Parcelable.Creator<E4DeviceStatus> getStateCreator() {
        return E4DeviceStatus.CREATOR;
    }

    @Override
    public boolean hasDetailView() {
        return true;
    }

    public void showDetailView() {
        new E4HeartbeatToast(getActivity()).execute(getConnection());
    }

    @Override
    protected void configure(Bundle bundle) {
        super.configure(bundle);
        getConfig().putExtras(bundle, RadarConfiguration.EMPATICA_API_KEY);
    }

    @Override
    public String getDisplayName() {
        return getActivity().getString(R.string.empaticaE4DisplayName);
    }

    @Override
    public boolean isFilterable() {
        return true;
    }

    @Override
    public List<String> needsPermissions() {
        return Arrays.asList(ACCESS_COARSE_LOCATION, BLUETOOTH, BLUETOOTH_ADMIN);
    }
}