package com.thedancercodes.daggersandbox.ui.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A generic class that contains data and status about loading this data.
 *
 * This wrapper class wraps around some data <T> (User object in our case)
 * @param <T>
 */
public class AuthResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public AuthResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> AuthResource<T> authenticated (@Nullable T data) {
        return new AuthResource<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> AuthResource<T> error(@NonNull String msg, @Nullable T data) {
        return new AuthResource<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> AuthResource<T> loading(@Nullable T data) {
        return new AuthResource<>(AuthStatus.LOADING, data, null);
    }

    public static <T> AuthResource<T> logout () {
        return new AuthResource<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    // The 4 different states that a request can have when we are trying to authenticate a user.
    public enum AuthStatus { AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}

}
