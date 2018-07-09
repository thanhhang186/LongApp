package com.khtn.healthcare.view.api;

import com.khtn.healthcare.view.pojo.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserApi {
        @POST("index.php")
        Call<UserResponse> login(@Query("cmd") String cmd, @Query("username") String username, @Query("password") String password);

        @POST("index.php")
        Call<UserResponse> logout(@Query("cmd") String cmd, @Query("token") String token);

        @POST("index.php")
        Call<UserResponse> changePass(@Query("cmd") String cmd, @Query("token") String token, @Query("password") String password, @Query("new_password") String new_password);

        @POST("index.php")
        Call<UserResponse> getData(@Query("cmd") String cmd, @Query("token") String token, @Query("code_id") String code_id);
}
