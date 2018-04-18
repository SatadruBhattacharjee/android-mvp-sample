package com.tribe.sample.data.remote;

public interface NetworkCallback {
    void success(String body);
    void failure(String body);
}

