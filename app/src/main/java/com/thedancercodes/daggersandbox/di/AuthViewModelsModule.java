package com.thedancercodes.daggersandbox.di;

import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Responsible for dependencies for the AuthViewModel.
 *
 * Responsible for injecting the ViewModel for AuthViewModel.
 */
@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap // Mapping into a Key
    @ViewModelKey(AuthViewModel.class) // This dependency/class is getting mapped to the key
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);


}
