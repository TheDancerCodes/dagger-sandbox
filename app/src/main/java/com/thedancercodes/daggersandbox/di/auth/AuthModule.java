package com.thedancercodes.daggersandbox.di.auth;

import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import javax.inject.Named;
import javax.inject.Singleton;

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

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someUser() {
        return new User();
    }

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
