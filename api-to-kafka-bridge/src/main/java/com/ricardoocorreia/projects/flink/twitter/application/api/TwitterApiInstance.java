package com.ricardoocorreia.projects.flink.twitter.application.api;

import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TwitterApiInstance {
    public static final String TWITTER_TOKEN_PROPERTY = "twitter.token";

    @Getter(AccessLevel.PACKAGE)
    private final TwitterApi instance;

    public TwitterApiInstance(@ConfigProperty(name = TWITTER_TOKEN_PROPERTY) String bearerToken) {

        TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(bearerToken);
        instance = new TwitterApi(credentials);
    }
}
