package com.thedancercodes.daggersandbox.di;

import android.app.Application;

import com.thedancercodes.daggersandbox.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Component interface that persist the entire lifetime of the application.
 *
 * It creates a graph of the dependencies in your project that it can use to find out where it
 * should get those dependencies when they are needed.
 *
 * Here we inject BaseApplication into AppComponent & BaseApplication will be a client of the
 * AppComponent service.
 */
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    /*
     * Overriding the component builder method.
     *
     * Basically, we are overriding the regular builder and returning a type of AppComponent.
     *
     * @BindsInstance used when you want to bind a particular instance of an object to the component
     * at the time of its construction.
     */
    @Component.Builder
    interface Builder {

        // Binding an Application instance to the ApplicationComponent
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
