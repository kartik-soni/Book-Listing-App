package com.example.kartik.booklistingapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Books>> {

    EditText userInput;
    ImageButton searchButton;
    ListView booksListView;
    TextView noResult;
    private static final int BOOK_LOADER_ID = 1;
    private BooksAdapter mBooksAdapter;
    static final String SEARCH_RESULTS = "booksSearchResults";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noResult = findViewById(R.id.no_data_found);
        userInput = findViewById(R.id.edit_Text);
        searchButton = findViewById(R.id.imageButton);
        booksListView = findViewById(R.id.listView);
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(BOOK_LOADER_ID, null, MainActivity.this);
                    if (loaderManager != null) {
                        loaderManager.restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
                        noResult.setVisibility(View.GONE);
                    }
                }
            });

            mBooksAdapter = new BooksAdapter(this, new ArrayList<Books>());
            booksListView.setAdapter(mBooksAdapter);
            if (savedInstanceState != null) {
                Books[] books = (Books[]) savedInstanceState.getParcelableArray(SEARCH_RESULTS);
                mBooksAdapter.addAll(books);
            }
        } else {
            Toast.makeText(MainActivity.this, R.string.noInternet, Toast.LENGTH_LONG).show();
        }
    }


    private String getUrlForHttpRequest() {
        final String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=search+";
        String formatUserInput = userInput.getText().toString().trim().replaceAll("\\s+", "+");
        String url = baseUrl + formatUserInput;
        return url;
    }

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);
        return new BooksLoader(this, getUrlForHttpRequest());
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Clear the adapter of previous earthquake data
        mBooksAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mBooksAdapter.addAll(books);
        } else {
            noResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
// Loader reset, so we can clear out our existing data.
        mBooksAdapter.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Books[] books = new Books[mBooksAdapter.getCount()];
        for (int i = 0; i < books.length; i++) {
            books[i] = mBooksAdapter.getItem(i);
        }
        outState.putParcelableArray(SEARCH_RESULTS, books);
    }
}