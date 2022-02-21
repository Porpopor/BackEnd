package projectA.projectA.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import projectA.projectA.service.TokenService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class Response {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private int status;

  public Object ok(String message,String key, Object value) {

    Map<Object, Object> res = new HashMap<>();

    res.put("timestamp", timestamp);
    res.put("status", HttpStatus.OK.value());
    res.put("message", message);
    res.put("error",status = 0 );

    Map<Object, Object> data = new HashMap<>();

    data.put(key, value);
    res.put("data", data);

    return res;
  }

  public Object okLogin(String message,String key, Object value,String profile, Object user) {

    Map<Object, Object> res = new HashMap<>();

    res.put("timestamp", timestamp);
    res.put("status", HttpStatus.OK.value());
    res.put("message", message);
    res.put("error",status = 0 );

    Map<Object, Object> data = new HashMap<>();
    Map<Object, Object> data2 = new HashMap<>();

    data.put(key, value);
    data2.put(profile, user);
    res.put("data", data);
    res.put("profile", data2);

    return res;
  }

  public Object success(String message) {

    Map<Object, Object> res = new HashMap<>();

    res.put("timestamp", timestamp);
    res.put("status", HttpStatus.OK.value());
    res.put("message", message);


    return res;


  }
}
