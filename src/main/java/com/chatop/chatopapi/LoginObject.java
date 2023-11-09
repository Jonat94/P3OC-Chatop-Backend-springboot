package com.chatop.chatopapi;

import com.chatop.chatopapi.model.Rental;

import lombok.Data;


@Data
public class LoginObject {
  private String username;
  private String password;

  public LoginObject(String username, String password) {
      this.username = username;
      this.password = password;
  }

  //getters setters...
}