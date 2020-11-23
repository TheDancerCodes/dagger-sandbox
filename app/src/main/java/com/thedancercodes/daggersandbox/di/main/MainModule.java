package com.thedancercodes.daggersandbox.di.main;

import com.thedancercodes.daggersandbox.network.main.MainApi;
import com.thedancercodes.daggersandbox.ui.main.posts.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Contains all the dependencies for the MainActivity SubComponent.
 *
 * NOTE: We can access the Retrofit instance here because the MainModule is inside a SubComponent;
 *       See ActivityBuildersModule.
 *
 * NOTE: The MainApi only exists within the MainComponent, it can't be accessed in the AuthComponent
 */
@Module
public class MainModule {

    /*
     * Allow us to inject the adapter as a dependency.
     */
    @Provides
    static PostRecyclerAdapter provideAdapter() {
        return new PostRecyclerAdapter();
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

}
