package projectA.projectA.exception;

public class CompanyException extends BaseException{
  public CompanyException(String code) {
    super(code);
  }

  public static CompanyException notFoundId() {

    return new CompanyException("id.not.found");

  }
  public static CompanyException notFoundEmail() {

    return new CompanyException("email.not.found");

  }

  public static CompanyException emailVerified() {

    return new CompanyException("email.verified");

  }

  public static CompanyException loginFail() {

    return new CompanyException("login.Fail");

  }

  public static CompanyException emailNull() {

    return new CompanyException("email.null");

  }

  public static CompanyException nameNull() {

    return new CompanyException("name.null");

  }

  public static CompanyException passwordNull() {

    return new CompanyException("password.null");

  }

  public static CompanyException passwordIncorrect(){

    return new CompanyException("passwordIncorrect");
  }

  public static CompanyException passwordDuplicated(){

    return new CompanyException("passwordDuplicated");
  }

  public static CompanyException phoneNull(){

    return new CompanyException("phone.null");

  }

  public static CompanyException typeNull(){

    return new CompanyException("type.null");

  }
}
