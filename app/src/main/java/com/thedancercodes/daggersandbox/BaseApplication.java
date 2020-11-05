package com.thedancercodes.daggersandbox;

import com.thedancercodes.daggersandbox.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * This is an Application Level class.
 * It persists across the entire lifetime of the application.
 *
 * DaggerAppComponent - we instantiate AppComponent in BaseApplication because we want it to persist
 * across the entire lifetime of the application.
 */
public class BaseApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
