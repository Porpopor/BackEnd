package projectA.projectA.exception;

public class UserException extends BaseException {
  public UserException(String code) {
    super("user." + code);
  }

  public static UserException notId() {

    return new UserException("not.find.id");

  }

  public static UserException notFound() {

    return new UserException("not.found");

  }

  public static UserException createEmailDuplicated() {

    return new UserException("register.email.duplicated");

  }
  public static UserException createPhoneDuplicated() {

    return new UserException("register.phone.duplicated");

  }

  public static UserException createEmailNull() {

    return new UserException("register.email.null");

  }

  public static UserException createPasswordNull() {

    return new UserException("register.password.null");

  }

  public static UserException createFirstNameNull() {

    return new UserException("register.firstname.null");

  }

  public static UserException createLastNameNull(){

    return new UserException("request.lastname.null");

  }

  public static UserException requestInvalid() {

    return new UserException("request.invalid ");

  }

  public static UserException accessDenied() {

    return new UserException("access.denied");

  }
  public static UserException passwordIncorrect () {

    return new UserException("password.incorrect");

  }
  public static UserException expires () {

    return new UserException("token.expires");

  }
  public static UserException allFormNull(){
    return new UserException("allForm.null");
  }

}
