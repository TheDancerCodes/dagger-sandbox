package com.thedancercodes.daggersandbox.di.main;

import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.di.ViewModelKey;
import com.thedancercodes.daggersandbox.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Responsible for dependencies for the ProfileViewModule,
 *
 * Responsible for injecting the ViewModel for ProfileViewModule,
 */
@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

}
