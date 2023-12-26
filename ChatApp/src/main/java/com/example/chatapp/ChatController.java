package com.example.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatRepository chatRepository;


    @GetMapping(value = "/chat/id/{chatId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessages(@PathVariable Integer chatId) {
        return chatRepository.findByChatId(chatId)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/chat")
    public Mono<Chat> newMessage(@RequestBody Chat chat) {
        // TODO : call the workout microservice to see if the user exists


        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }
}