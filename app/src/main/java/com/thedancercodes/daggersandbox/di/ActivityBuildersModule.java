package com.thedancercodes.daggersandbox.di;

import com.thedancercodes.daggersandbox.di.auth.AuthModule;
import com.thedancercodes.daggersandbox.di.auth.AuthViewModelsModule;
import com.thedancercodes.daggersandbox.di.main.MainFragmentBuildersModule;
import com.thedancercodes.daggersandbox.ui.auth.AuthActivity;
import com.thedancercodes.daggersandbox.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * ActivityBuildersModule is representative of the AuthComponent (AuthActivitySubComponent) and
 * MainComponent (MainActivitySubComponent)
 *
 * Contains the potential clients that you can inject into - AuthActivity, MainActivity.
 *
 * NOTE: Both AuthViewModelsModule & AuthModule exist inside the AuthActivity SubComponent.
 *
 * NOTE: The AuthActivity SubComponent is a SubComponent of the AppComponent.
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
