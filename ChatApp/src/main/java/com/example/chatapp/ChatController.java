package com.example.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatRepository chatRepository;
    private final WebClient webClient;


    @GetMapping(value = "/chat/id/{chatId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessages(@PathVariable Integer chatId) {
        return chatRepository.findByChatId(chatId)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(error -> {
                    // Handle errors, log, etc.
                })
                .onErrorResume(error -> Flux.empty());
    }


        @PostMapping("/chat")
        @ResponseStatus(HttpStatus.OK)
        public Mono<Void> newMessage (@RequestBody Chat chat, @RequestHeader(HttpHeaders.AUTHORIZATION) String
        authorizationHeader){
            chat.setCreatedAt(LocalDateTime.now());

            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(8082)
                            .path("/auth/{receiverId}")
                            .build(chat.getReceiver()))
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(result -> {
                        if (Boolean.TRUE.equals(result)) {
                            return chatRepository.save(chat).then();
                        } else {
                            return Mono.error(new IllegalArgumentException("User does not exist, enter a valid Id"));
                        }
                    });
        }


    }