package com.findx.wifiindoorpositioning.wifiindoorpositioning.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.ui.frags.PrefsFragment;

/**
 * Created by sankalp on 13/04/19.
 */

public class UnifiedNavigationActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment()).commit();

    }
}
