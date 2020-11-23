package com.thedancercodes.daggersandbox.network.main;

import com.thedancercodes.daggersandbox.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface that holds the methods for accessing the API endpoints required for retrieving Posts.
 */
public interface MainApi {

    // posts?userId=1
    @GET
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
}
