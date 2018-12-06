package com.example.hurshi.rxcachedemo;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CommonService {
    @GET("users/hurshi1")
    Single<User> getUsers();
}
