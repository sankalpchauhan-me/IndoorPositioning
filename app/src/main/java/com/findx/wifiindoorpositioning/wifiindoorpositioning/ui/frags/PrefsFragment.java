package com.findx.wifiindoorpositioning.wifiindoorpositioning.ui.frags;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;

/**
 * Created by sankalp on 13/04/19.
 */

public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make sure default values are applied.  In a real app, you would
        // want this in a shared function that is used to retrieve the
        // SharedPreferences wherever they are needed.
//        PreferenceManager.setDefaultValues(getActivity(),
//                R.xml.advanced_preferences, false);

        addPreferencesFromResource(R.xml.settings);
    }
}
