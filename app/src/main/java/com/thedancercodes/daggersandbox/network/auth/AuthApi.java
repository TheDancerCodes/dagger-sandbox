package com.thedancercodes.daggersandbox.network.auth;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface that holds the methods for accessing the API endpoints required for Authentication.
 */
public interface AuthApi {

    @GET
    Call<ResponseBody> getFakeStuff();

}
