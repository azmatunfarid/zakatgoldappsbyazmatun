package com.example.zakatgoldapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; // Add this import statement
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class information_page extends AppCompatActivity implements View.OnClickListener {

    Button share;
    Toolbar myToolbar;
    ImageView home_button;
    ImageView calculator_button;
    ImageView information_button;
    ImageView share_button;
    TextView website_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("Zakat Gold Calculator");
        myToolbar.setSubtitle("About Page");
        getSupportActionBar().setTitle("Zakat Gold Calculator");

        TextView websiteLink = findViewById(R.id.website_link);
        Linkify.addLinks(websiteLink, Linkify.WEB_URLS);

        ImageView homeButton = findViewById(R.id.home_button);
        ImageView calculatorButton = findViewById(R.id.calculator_button);
        ImageView informationButton = findViewById(R.id.information_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to activity_main.xml (Home)
                Intent intent = new Intent(information_page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to zakatgold_calculate.xml (Calculator)
                Intent intent = new Intent(information_page.this, ZakatgoldCalculateActivity.class);
                startActivity(intent);
            }
        });

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to information_page.xml (Information)
                Intent intent = new Intent(information_page.this, information_page.class);
                startActivity(intent);
            }
        });
    }

    private void setToolbarTitleFont(Toolbar toolbar, String title) {
        // Load the custom font
        Typeface typeface = getResources().getFont(R.font.roboto_black);

        // Set the custom font and color for the toolbar title
        int titleId = getResources().getIdentifier("action_bar_title", "id", getPackageName());
        if (titleId > 0) {
            TextView titleTextView = toolbar.findViewById(titleId);
            if (titleTextView != null) {
                titleTextView.setTypeface(typeface);
                titleTextView.setTextColor(Color.BLACK); // Set your desired title text color
                titleTextView.setText(title);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "GitHub: Zakat Gold Application by Azmatun - https://github.com/azmatunfarid/zakatgoldappsbyazmatun");

        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
    }
}
