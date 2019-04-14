package com.findx.wifiindoorpositioning.wifiindoorpositioning.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;
import com.ramotion.foldingcell.FoldingCell;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.utils.SpotlightListener;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button user;
    Button admin;
    ProgressBar progress;
    Button about;

    String usageId = new Date().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Extra);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar eventToolbar = (Toolbar) findViewById(R.id.activity_event_toolbar);
        setSupportActionBar(eventToolbar);
        ActionBar bar = getSupportActionBar();
        if(bar!=null){
            bar.setTitle(Html.fromHtml("<font color='#ffffff'><B>" + "ULTRAHACK: FindX" + "</B></font>"));
        }

        progress = findViewById(R.id.eventProgress);
        about = findViewById(R.id.aboutBTN);

        Glide.with(this).load("https://ultrahack.org/images/UH-logo_sq.png").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progress.setVisibility(View.GONE);
                return false;
            }
        }).into((ImageView) findViewById(R.id.mainImage));

        user = findViewById(R.id.usrBTN);
        admin = findViewById(R.id.adminBTN);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
            }
        });


        new SpotlightView.Builder(this)
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(Color.parseColor("#eb273f"))
                .headingTvSize(32)
                .headingTvText("User Panel")
                .subHeadingTvColor(Color.parseColor("#ffffff"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Here the user can access the venue \n he does not need to know any \n details about the backend \n It Just Works!")
                .maskColor(Color.parseColor("#dc000000"))
                .target(findViewById(R.id.usrBTN))
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .setListener(new SpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        new SpotlightView.Builder(MainActivity.this)
                                .introAnimationDuration(400)
                                .enableRevealAnimation(true)
                                .performClick(true)
                                .fadeinTextDuration(400)
                                .headingTvColor(Color.parseColor("#eb273f"))
                                .headingTvSize(32)
                                .headingTvText("Admin Panel")
                                .subHeadingTvColor(Color.parseColor("#ffffff"))
                                .subHeadingTvSize(16)
                                .subHeadingTvText("Admin Panel is where all the magic happens. \n Calibrate Once And Run Forever!")
                                .maskColor(Color.parseColor("#dc000000"))
                                .target(findViewById(R.id.adminBTN))
                                .lineAnimDuration(400)
                                .lineAndArcColor(Color.parseColor("#eb273f"))
                                .dismissOnTouch(true)
                                .dismissOnBackPress(true)
                                .enableDismissAfterShown(true)
                                .usageId(usageId+"1") //UNIQUE ID
                                .show();
                    }
                })
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(usageId)//UNIQUE ID
                .show();

        // get our folding cellfinal
        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });


        //Example Data for onboarding

    }



}
