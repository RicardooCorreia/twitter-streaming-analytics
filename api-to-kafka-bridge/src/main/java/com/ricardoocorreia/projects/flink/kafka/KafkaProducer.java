package com.ricardoocorreia.projects.flink.kafka;

import com.ricardoocorreia.projects.flink.twitter.application.api.TweetsProvider;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Outgoing;


@ApplicationScoped
@RequiredArgsConstructor
public class KafkaProducer {

    private final TweetsProvider tweetsProvider;

    @Outgoing("tweets")
    public Multi<String> generate() {

        return Multi.createFrom()
                .emitter(multiEmitter ->
                        tweetsProvider.get()
                                .map(multiEmitter::emit));
    }
}
