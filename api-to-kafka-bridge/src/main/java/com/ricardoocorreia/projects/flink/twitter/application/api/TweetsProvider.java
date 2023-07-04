package com.ricardoocorreia.projects.flink.twitter.application.api;

import com.twitter.clientlib.ApiException;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class TweetsProvider {

    private final TwitterApiInstance instance;

    private BufferedReader reader;

    @SneakyThrows({ApiException.class})
    public Stream<String> get() {

        InputStream apiInputStream =
                instance.getInstance()
                        .tweets()
                        .sampleStream()
                        .executeWithHttpInfo();

        reader = new BufferedReader(
                new InputStreamReader(apiInputStream));

        return Stream.generate(this::readTweet);
    }

    @SneakyThrows({IOException.class})
    private String readTweet() {

        return reader.readLine();
    }
}
