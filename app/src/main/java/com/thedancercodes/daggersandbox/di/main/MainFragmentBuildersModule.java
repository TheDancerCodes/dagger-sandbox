package com.thedancercodes.daggersandbox.di.main;

import com.thedancercodes.daggersandbox.ui.main.posts.PostsFragment;
import com.thedancercodes.daggersandbox.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class will contain all the injectors for the Fragments inside MainComponent.
 */
@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();

}
