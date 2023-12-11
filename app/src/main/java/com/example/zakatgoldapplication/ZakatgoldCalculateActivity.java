package com.example.zakatgoldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class ZakatgoldCalculateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText input_weightofgold;
    EditText input_currentgoldvalue;
    TextView title_zakatgold;
    TextView weightofgold;
    TextView typeofgold;
    TextView currentgoldvalue;
    TextView rm;
    TextView payable;
    TextView text_output;
    TextView text_output2;
    RadioButton radioButton_wear;
    RadioButton radioButton_keep;
    Button calculate;
    Button reset;
    Toolbar myToolbar;
    ImageView home_button;
    ImageView calculator_button;
    ImageView information_button;
    ImageView share_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zakatgold_calculate);

        // Initialize your views
        input_weightofgold = findViewById(R.id.input_weightofgold);
        input_currentgoldvalue = findViewById(R.id.input_currentgoldvalue);
        title_zakatgold = findViewById(R.id.title_zakatgold);
        weightofgold = findViewById(R.id.weightofgold);
        typeofgold = findViewById(R.id.typeofgold);
        currentgoldvalue = findViewById(R.id.currentgoldvalue);
        rm = findViewById(R.id.rm);
        payable = findViewById(R.id.payable);
        text_output = findViewById(R.id.text_output);
        text_output2 = findViewById(R.id.text_output2);
        radioButton_wear = findViewById(R.id.radioButton_wear);
        radioButton_keep = findViewById(R.id.radioButton_keep);
        calculate = findViewById(R.id.calculate);
        reset = findViewById(R.id.reset);

        ImageView homeButton = findViewById(R.id.home_button);
        ImageView calculatorButton = findViewById(R.id.calculator_button);
        ImageView informationButton = findViewById(R.id.information_button);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("Zakat Gold Calculator");
        myToolbar.setSubtitle("Calculator");
        getSupportActionBar().setTitle("Zakat Gold Calculator");


        // Assuming you have the references to your views
        Button calculateButton = findViewById(R.id.calculate);
        Button resetButton = findViewById(R.id.reset);

        // Apply custom colors
        calculateButton.setBackgroundColor(ContextCompat.getColor(this, R.color.Goldenrod));
        calculateButton.setTextColor(ContextCompat.getColor(this, R.color.white));

        resetButton.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        resetButton.setTextColor(ContextCompat.getColor(this, R.color.white));

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to activity_main.xml (Home)
                Intent intent = new Intent(ZakatgoldCalculateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to zakatgold_calculate.xml (Calculator)
                Intent intent = new Intent(ZakatgoldCalculateActivity.this, ZakatgoldCalculateActivity.class);
                startActivity(intent);
            }
        });

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to information_page.xml (Information)
                Intent intent = new Intent(ZakatgoldCalculateActivity.this, information_page.class);
                startActivity(intent);
            }
        });


        // Set onClickListener for the calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        // Set onClickListener for the reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void calculateZakat() {
        String weightOfGoldStr = input_weightofgold.getText().toString().trim();
        String currentGoldValueStr = input_currentgoldvalue.getText().toString().trim();

        if (weightOfGoldStr.isEmpty() || currentGoldValueStr.isEmpty()) {
            // Show error message if any of the input fields is empty
            showError("Incomplete! Please enter weight of gold or current gold value.");
            return;
        }

        // Retrieve user input
        double weightOfGold = Double.parseDouble(weightOfGoldStr);
        double currentGoldValue = Double.parseDouble(currentGoldValueStr);
        boolean isWearingGold = radioButton_wear.isChecked();

        // Calculate total value of the gold
        // double totalGoldValue = weightOfGold * currentGoldValue;

        // Calculate total gold value that is zakat payable
        double totalGoldValueZakatPayable;
        if (isWearingGold) {
            totalGoldValueZakatPayable = (weightOfGold - 200) * currentGoldValue;
        } else {
            totalGoldValueZakatPayable = (weightOfGold - 85) * currentGoldValue;
        }

        // Display results
        payable.setText("Zakat Payable: RM");
        text_output2.setText(String.valueOf(totalGoldValueZakatPayable));

        // Calculate total zakat
        double totalZakat = Math.max(0, totalGoldValueZakatPayable * 0.025);

        // Additional output for total Zakat
        rm.setText("Total Zakat: RM");
        text_output.setText(String.valueOf(totalZakat));
    }

    private void showError(String errorMessage) {
        // Display an error message to the user (e.g., using a Toast)
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void resetFields() {
        // Reset all fields to their initial values
        input_weightofgold.setText("");
        input_currentgoldvalue.setText("");
        radioButton_wear.setChecked(false);
        radioButton_keep.setChecked(false);

        // Show a message indicating that fields have been reset
        showToast("Fields reset!");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

        String title = "Share via";
        Intent shareIntent = Intent.createChooser(sendIntent, title);
        startActivity(shareIntent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
    }
}
