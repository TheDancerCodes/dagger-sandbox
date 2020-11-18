package com.thedancercodes.daggersandbox.di.auth;

import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Contains all the dependencies for the Auth SubComponent.
 *
 * NOTE: We can access the Retrofit instance here because the AuthModule is inside a SubComponent;
 *       See ActivityBuildersModule.
 */
@Module
public class AuthModule {

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
