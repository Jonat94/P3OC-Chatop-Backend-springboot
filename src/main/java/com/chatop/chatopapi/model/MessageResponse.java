package com.chatop.chatopapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse implements Response {
private String message;
}

