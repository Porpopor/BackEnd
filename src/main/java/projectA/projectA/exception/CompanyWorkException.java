package projectA.projectA.exception;

public class CompanyWorkException extends BaseException{
  public CompanyWorkException(String code) {
    super(code);
  }

  public static CompanyWorkException notFoundId() {

    return new CompanyWorkException("id.not.found");

  }

  public static CompanyWorkException nameNull() {

    return new CompanyWorkException("name.null");

  }
  public static CompanyWorkException provinceNull() {

    return new CompanyWorkException("province.null");

  }
}
