package com.dzakdzaks.qrcode;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitLibrary {


        public static Retrofit setInit() {
            return new Retrofit.Builder().baseUrl("https://api.androidhive.info/barcodes/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static ApiService getInstance() {
            return setInit().create(ApiService.class);
        }
}
