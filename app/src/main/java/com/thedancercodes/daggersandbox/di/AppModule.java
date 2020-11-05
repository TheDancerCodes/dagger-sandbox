package com.thedancercodes.daggersandbox.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.thedancercodes.daggersandbox.R;

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
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    // This dependency provides the Glide instance.
    // It depends on the RequestOptions dependency above.
    @Provides
    static RequestManager provideGlideInstance(Application application,
                                               RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

}
