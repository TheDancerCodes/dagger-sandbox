package com.thedancercodes.daggersandbox.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * AppModule will be used inside the AppComponent.
 *
 * All the application level dependencies for the project will be contained here (e.g) Retrofit
 * instance, Glide instance; anything that will exist & won't change for the entire lifetime of
 * the application.
 */

@Module
public class AppModule {

    @Provides
    static String someString(){
        return "this is a test string";
    }

    @Provides
    static boolean getApp(Application application) {
        // If application is null, this returns true & vice versa.
        return application == null;
    }
}
