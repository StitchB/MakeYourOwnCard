package com.example.android.makeyourowncard;

import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;
import android.text.Spannable;

public class MainActivity extends AppCompatActivity {

    private int lastImageCounter = 1;
    private View expandedInfoContainer;
    private View collapsedInfoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide action bar
        hideActionBar();

        //Set 'activity_main' as a main design file
        setContentView(R.layout.activity_main);

        // Remove underlines from links
        TextView emailAddress;
        emailAddress = findViewById(R.id.email_address);
        if(emailAddress != null) {
            StringUtil.removeUnderlines((Spannable)emailAddress.getText());
        }
        TextView phoneNumber;
        phoneNumber = findViewById(R.id.phone_number);
        if(phoneNumber != null) {
            StringUtil.removeUnderlines((Spannable)phoneNumber.getText());
        }
        TextView facebook;
        facebook = findViewById(R.id.facebook);
        if(facebook != null) {
            StringUtil.removeUnderlines((Spannable)facebook.getText());
        }
        TextView twitter;
        twitter = findViewById(R.id.twitter);
        if(twitter != null) {
            StringUtil.removeUnderlines((Spannable)twitter.getText());
        }

        //Add on click listener event for the 'Next Image' button
        final ImageButton buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Start image transition
                startTransition();
            }
        });

        //Prepare info containers
        expandedInfoContainer = findViewById(R.id.expanded_container);
        expandedInfoContainer.setVisibility(View.INVISIBLE);
        collapsedInfoContainer = findViewById(R.id.collapsed_container);

        //Add on click listener event for the 'Expand Info' button
        final ImageButton buttonExpandInfo = findViewById(R.id.button_expand);
        buttonExpandInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                expandedInfoContainer.setVisibility(View.VISIBLE);
                collapsedInfoContainer.setVisibility(View.INVISIBLE);
            }
        });

        //Add on click listener event for the 'Collapse Info' button
        final ImageButton buttonCollapseInfo = findViewById(R.id.button_collapse);
        buttonCollapseInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                expandedInfoContainer.setVisibility(View.INVISIBLE);
                collapsedInfoContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    //Hide action bar
    private void hideActionBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Start image transition
    private void startTransition() {
        //First image
        String firstImage = "dog_"+lastImageCounter;
        int firstImageId = getResources().getIdentifier(firstImage,"drawable", getPackageName());

        //Second image
        if(lastImageCounter == 3) {
            lastImageCounter = 1;
        }
        else {
            lastImageCounter++;
        }
        String secondImage = "dog_"+lastImageCounter;
        int secondImageId = getResources().getIdentifier(secondImage,"drawable", getPackageName());

        //Prepare resources for transition
        Drawable backgrounds[] = new Drawable[2];
        Resources res = getResources();
        backgrounds[0] = res.getDrawable(firstImageId);
        backgrounds[1] = res.getDrawable(secondImageId);

        //Apply transition into main image view & start animation
        TransitionDrawable mainImageTransition = new TransitionDrawable(backgrounds);

        ImageView image = findViewById(R.id.main_image);
        image.setImageDrawable(mainImageTransition);

        mainImageTransition.setCrossFadeEnabled(true);
        mainImageTransition.startTransition(3000);
    }
}
