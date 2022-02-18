package projectA.projectA.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

  private final UserRepository userRepository;

  @Value("${app.token.secret}")
  private String secret;

  @Value("${app.token.issuer}")
  private String issuer;

  public TokenService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String tokenize(User user) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, 60);
    Date expiresAt = calendar.getTime();

    return JWT.create()
      .withIssuer(issuer)
      .withClaim("principal", user.getId().toString())
      .withClaim("role", "USER")
      .withExpiresAt(expiresAt)
      .sign(algorithm());
  }

  public DecodedJWT verify(String token) {
    try {
      JWTVerifier verifier = JWT.require(algorithm())
        .withIssuer(issuer)
        .build();

      return verifier.verify(token);

    } catch (Exception e) {
      return null;
    }
  }

  private Algorithm algorithm() {
    return Algorithm.HMAC256(secret);
  }

  public User getUserByIdToken() throws BaseException {

    int userId = Integer.parseInt(this.userId());

    Optional<User> opt = userRepository.findById(userId);
    if (opt.isEmpty()) {
      throw UserException.notFoundId();
    }
    User user = opt.get();
    return user;
  }


  public String userId(){
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String userId = (String) authentication.getPrincipal();

    return userId;
  }

}
