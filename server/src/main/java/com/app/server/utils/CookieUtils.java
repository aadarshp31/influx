package com.app.server.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

  public static void setCookieValue(HttpServletResponse response, String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(60 * 60); // Token expiration time in seconds
    response.addCookie(cookie);
  }

  public static String getCookieValue(HttpServletRequest request, String key) {
    Map<String, String> map = new HashMap<String, String>();

    String[] cookieStrings = request.getHeader("Cookie").split(";");

    for (String item : cookieStrings) {
      String itemKey = item.split("=")[0].trim();
      String value = item.split("=")[1].trim();
      map.put(itemKey, value);
    }

    return map.get(key);
  }

}
