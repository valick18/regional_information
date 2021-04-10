package com.example.regional_information.API;

import com.example.regional_information.Information;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerAPI {

    @GET("information")
    Call<List<Information>> getInfoList();

    @GET("information")
    Information getInfoByID(long productId);

    @POST("createInformation")
    void createInfoReg(Information regInfo);

}