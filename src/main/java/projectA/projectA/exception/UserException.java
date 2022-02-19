package projectA.projectA.exception;

public class UserException extends BaseException {
  public UserException(String code) {
    super(code);
  }

  public static UserException notFoundId() {

    return new UserException("id.not.found");

  }

  public static UserException EmailDuplicated() {

    return new UserException("register.email.duplicated");

  }

  public static UserException EmailNull() {

    return new UserException("register.email.null");

  }

  public static UserException createPasswordNull() {

    return new UserException("password.null");

  }

  public static UserException FirstNameNull() {

    return new UserException("firstname.null");

  }

  public static UserException LastNameNull() {

    return new UserException("lastname.null");

  }

  public static UserException requestInvalid() {

    return new UserException("request.invalid ");

  }

  public static UserException accessDenied() {

    return new UserException("access.denied");

  }

  public static UserException passwordIncorrect() {

    return new UserException("password.incorrect");

  }

  public static UserException expires() {

    return new UserException("token.expires");

  }

  public static UserException allFormNull() {

    return new UserException("allForm.null");
  }

  public static UserException loginFailEmailNotFound(){

    return new UserException("Login.fail");
  }

  public static UserException loginFailPasswordIncorrect(){

    return new UserException("Login.fail");
  }
}
