package com.findx.wifiindoorpositioning.wifiindoorpositioning.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.splash)
                .setCover(R.mipmap.profile_cover)
                .setName("FindX")
                .setSubTitle("Ultrahack")
                .setBrief("Harry wouldn't have lost in the Hogwart's on his first day if he had this app")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink("user")
                .addFacebookLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        setContentView(view);
    }
}
