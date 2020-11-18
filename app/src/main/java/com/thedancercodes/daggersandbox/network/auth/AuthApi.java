package com.thedancercodes.daggersandbox.network.auth;

import com.thedancercodes.daggersandbox.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface that holds the methods for accessing the API endpoints required for Authentication.
 */
public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);

}
