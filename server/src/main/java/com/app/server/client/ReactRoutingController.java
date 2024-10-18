package com.app.server.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactRoutingController {

  @GetMapping("/{path:[^\\.]*}")
  public String redirect() {
      // Forward to `index.html` to handle the client-side routes
      return "forward:/index.html";
  }
  
}
