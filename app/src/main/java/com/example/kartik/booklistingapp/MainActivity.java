package com.example.kartik.booklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userInput;
    ImageButton searchButton;
    TextView noResultFound;
    ListView listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.edit_Text);
        searchButton = findViewById(R.id.imageButton);
        noResultFound = findViewById(R.id.no_Result_found);
        listItem = findViewById(R.id.listView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(getUrlForHttpRequest());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    private String getUrlForHttpRequest() {
        final String Url = "https://www.googleapis.com/books/v1/volumes?q="+userInput.getText().toString();
        return Url;
    }
}
