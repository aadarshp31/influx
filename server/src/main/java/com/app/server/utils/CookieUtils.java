package com.app.server.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contains methods to handle cookie related actions
 * 
 * @author @aadarshp31
 */
@Component
public class CookieUtils {

  @Autowired
  private HttpServletResponse response;
  @Autowired
  private HttpServletRequest request;

  /**
   * Sets key value pair to cookie for current HttpServletResponse
   * 
   * @param key   key to be stored in cookie
   * @param value value to be stored in cookie
   * @author @aadarshp31
   */
  public void setCookieValue(String key, String value) {
    Cookie cookie = new Cookie(key, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(60 * 60 * 12); // 12 hours token expiration time in seconds
    response.addCookie(cookie);
  }

  /**
   * Sets key value pair to cookie for current HttpServletResponse
   * 
   * @param key    key to be stored in cookie
   * @param value  value to be stored in cookie
   * @param maxAge expiration duration
   * @author @aadarshp31
   */
  public void setCookieValue(String key, String value, Integer maxAge) {
    Cookie cookie = new Cookie(key, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge); // expiration time in seconds
    response.addCookie(cookie);
  }

  /**
   * Gets value for a key stored in the cookie in the current HttpServletRequest
   * 
   * @param key key for the value to be fetched from cookie
   * @return value stored in the cookie again the provided key
   * @author @aadarshp31
   */
  public String getCookieValue(String key) {
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
