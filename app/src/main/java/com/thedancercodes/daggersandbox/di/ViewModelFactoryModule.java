package com.thedancercodes.daggersandbox.di;

import androidx.lifecycle.ViewModelProvider;

import com.thedancercodes.daggersandbox.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Responsible for providing the dependencies during dependency injection for the
 * ViewModelProviderFactory class.
 */
@Module
public abstract class ViewModelFactoryModule {

    // Providing an instance of the ViewModelProvider Factory.
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}
