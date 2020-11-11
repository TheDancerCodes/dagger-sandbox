package com.thedancercodes.daggersandbox.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;


/**
 * ViewModelKey is a MapKey - Can be used to map similar dependencies and group them together.
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    // Define the type of mapping - some class that extends ViewModel
    Class<? extends ViewModel> value();

}
