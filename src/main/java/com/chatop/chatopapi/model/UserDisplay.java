package com.chatop.chatopapi.model;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDisplay 
{
private long Id;
private String username;
private String name;
private String createdAt;
private String updatedAt;

}
