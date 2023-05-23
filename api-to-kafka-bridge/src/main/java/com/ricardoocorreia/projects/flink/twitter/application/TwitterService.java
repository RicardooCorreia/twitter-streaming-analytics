package com.ricardoocorreia.projects.flink.twitter.application;

import com.ricardoocorreia.projects.flink.twitter.application.api.TweetsProvider;
import com.twitter.clientlib.ApiException;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
@RequiredArgsConstructor
public class TwitterService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private final TweetsProvider tweetsProvider;

    void startup(@Observes StartupEvent event) {

        executorService.submit(() -> {
            try {
                tweetsProvider.get()
                        .forEach(System.out::println);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
