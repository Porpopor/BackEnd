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
  public static CompanyWorkException districtNull() {

    return new CompanyWorkException("district.null");

  }
  public static CompanyWorkException jobTitleNull() {

    return new CompanyWorkException("jobTitle.null");

  }
  public static CompanyWorkException salaryNull() {

    return new CompanyWorkException("salary.null");

  }
  public static CompanyWorkException welfareBenefitsNull() {

    return new CompanyWorkException("welfareBenefits.null");

  }
  public static CompanyWorkException detailWorkNull() {

    return new CompanyWorkException("detailWork.null");

  }
  public static CompanyWorkException featureNull() {

    return new CompanyWorkException("feature.null");

  }
  public static CompanyWorkException contactNull() {

    return new CompanyWorkException("contact.null");

  }

}