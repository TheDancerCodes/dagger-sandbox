package com.thedancercodes.daggersandbox.di;

import com.thedancercodes.daggersandbox.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Contains the potential clients that you can inject into.
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

}
