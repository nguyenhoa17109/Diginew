package com.nguyenhoa.diginew.categories;

import com.nguyenhoa.diginew.model.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface ApiService {

    @GET("api/p/")
    Call<List<Province>> getNameProvinces();
}

