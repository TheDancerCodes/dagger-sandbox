package com.thedancercodes.daggersandbox.di;

import android.app.Application;

import com.thedancercodes.daggersandbox.BaseApplication;
import com.thedancercodes.daggersandbox.SessionManager;

import javax.inject.Singleton;

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
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    // Makes SessionManager accessible from the AppComponent & is kept alive as long as the
    // application is alive.
    SessionManager sessionManager();

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
