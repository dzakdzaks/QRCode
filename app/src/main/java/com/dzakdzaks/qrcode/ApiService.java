package com.dzakdzaks.qrcode;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search.php")
    Call<ResponseMovie> getMovies(
            @Query("code") String code
    );
}
