package com.chatop.chatopapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  // @RequestBody maps web request's body to method parameter LoginObject
  @PostMapping(path = "/login" )
  public boolean validateLogin(@RequestBody LoginObject login) {
	  System.out.println("ggggggg");
      if (login == null) return false;
      String username = login.getUsername();
      String password = login.getPassword();
System.out.println(username);
      // simple check
      if ("mkyong".equalsIgnoreCase(username) && "123456".equals(password)) {
          return true;
      } else {
          return false;
      }
  }

}
