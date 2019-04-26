package com.dzakdzaks.qrcode;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketResultActivity extends AppCompatActivity {

    private static final String TAG = TicketResultActivity.class.getSimpleName();

    private TextView txtName, txtDuration, txtDirector, txtGenre, txtRating, txtPrice, txtError;
    private ImageView imgPoster;
    private Button btnBuy;
    private ProgressBar progressBar;
    private TicketView ticketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = findViewById(R.id.name);
        txtDirector = findViewById(R.id.director);
        txtDuration = findViewById(R.id.duration);
        txtPrice = findViewById(R.id.price);
        txtRating = findViewById(R.id.rating);
        imgPoster = findViewById(R.id.poster);
        txtGenre = findViewById(R.id.genre);
        btnBuy = findViewById(R.id.btn_buy);
        imgPoster = findViewById(R.id.poster);
        txtError = findViewById(R.id.txt_error);
        ticketView = findViewById(R.id.layout_ticket);
        progressBar = findViewById(R.id.progressBar);

        String barcode = getIntent().getStringExtra("code");

        // close the activity in case of empty barcode
        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(getApplicationContext(), "Barcode is empty!", Toast.LENGTH_LONG).show();
            finish();
        }

        // search the barcode
        searchBarcode(barcode);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchBarcode(String barcode) {
        ApiService apiService = InitLibrary.getInstance();
        Call<ResponseMovie> call = apiService.getMovies(barcode);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                if (response.isSuccessful()) {
                    ResponseMovie movie = response.body();
                    if (movie != null) {
                        txtName.setText(movie.getName());
                        txtDirector.setText(movie.getDirector());
                        txtDuration.setText(movie.getDuration());
                        txtGenre.setText(movie.getGenre());
                        txtRating.setText("" + movie.getRating());
                        txtPrice.setText(movie.getPrice());
                        Toast.makeText(TicketResultActivity.this, movie.getName(), Toast.LENGTH_SHORT).show();
                        Glide.with(getApplicationContext()).load(movie.getPoster()).into(imgPoster);

                        if (movie.isReleased()) {
                            btnBuy.setText(getString(R.string.btn_buy_now));
                            btnBuy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        } else {
                            btnBuy.setText(getString(R.string.btn_coming_soon));
                            btnBuy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_disabled));
                        }
                        ticketView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    } else {
                        // movie not found
                        showNoTicket();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.e(TAG, "JSON Exception: " + t.getMessage());
                showNoTicket();
                Toast.makeText(getApplicationContext(), "Error occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

}
