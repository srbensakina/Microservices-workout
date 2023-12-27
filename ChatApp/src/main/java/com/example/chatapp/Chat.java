package com.example.chatapp;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private String message;
    private Integer sender;
    private Integer receiver;
    private Integer chatId;

    private LocalDateTime createdAt;
}