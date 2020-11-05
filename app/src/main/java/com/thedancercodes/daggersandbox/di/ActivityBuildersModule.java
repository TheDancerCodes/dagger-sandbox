package com.thedancercodes.daggersandbox.di;

import com.thedancercodes.daggersandbox.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Contains the potential clients that you can inject into.
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}
