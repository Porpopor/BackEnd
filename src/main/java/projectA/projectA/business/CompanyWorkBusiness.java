package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyWorkException;
import projectA.projectA.mapper.CompanyWorkMapper;
import projectA.projectA.model.Response;
import projectA.projectA.model.companyWorkModel.CompanyWorkDelete;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;
import projectA.projectA.model.companyWorkModel.CompanyWorkResponse;
import projectA.projectA.repository.CompanyWorkRepository;
import projectA.projectA.service.CompanyWorkService;
import projectA.projectA.service.TokenService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyWorkBusiness {

  private final TokenService tokenService;
  private final CompanyWorkService companyWorkService;
  private final CompanyWorkMapper companyWorkMapper;
  private final CompanyWorkRepository companyWorkRepository;

  public CompanyWorkBusiness(TokenService tokenService, CompanyWorkService companyWorkService, CompanyWorkMapper companyWorkMapper, CompanyWorkRepository companyWorkRepository) {
    this.tokenService = tokenService;
    this.companyWorkService = companyWorkService;
    this.companyWorkMapper = companyWorkMapper;
    this.companyWorkRepository = companyWorkRepository;
  }

  public Object createCompanyWork(CompanyWorkReq request) throws BaseException {
    User user = tokenService.getUserByIdToken();

    if (request.getName().isEmpty() || request.getName().equals("") || request.getName().contains(" ")) {
      throw CompanyWorkException.nameNull();
    }

    if (request.getProvince().isEmpty() || request.getProvince().equals("") || request.getProvince().contains(" ")) {
      throw CompanyWorkException.provinceNull();
    }

    CompanyWork companyWork = companyWorkService.createCompanyWork(user, request.getName(), request.getDetail(), request.getProvince());
    CompanyWorkResponse work = companyWorkMapper.toCompanyWorkResponse(companyWork);
    return new Response().ok("create", "companyWork", work);
  }

  public Object editCompanyWork(CompanyWorkReq request) throws BaseException {
    User user = tokenService.getUserByIdToken();

    Optional<CompanyWork> comp = companyWorkRepository.findById(request.getId());

    if (comp.isEmpty()) {
      throw CompanyWorkException.notFoundId();
    }

    if (request.getName().isEmpty() || request.getName().equals("") || request.getName().contains(" ")) {
      throw CompanyWorkException.nameNull();
    }

    if (request.getProvince().isEmpty() || request.getProvince().equals("") || request.getProvince().contains(" ")) {
      throw CompanyWorkException.provinceNull();
    }

    companyWorkService.editCompanyWork(user, request.getId(), request.getName(), request.getDetail(), request.getProvince());
    return new Response().success("แก้ไขสำเร็จ");
  }

  public Object listByUser() throws BaseException {
    User user = tokenService.getUserByIdToken();

    List<CompanyWork> byUser = companyWorkService.findByUser(user);

    List<CompanyWorkResponse> comp = companyWorkMapper.toListCompanyWorkResponse(byUser);

    return new Response().ok("Success", "companyWork", comp);
  }

  public Object listAll() throws BaseException {

    List<CompanyWork> all = companyWorkService.findAll();

    List<CompanyWorkResponse> comp = companyWorkMapper.toListCompanyWorkResponse(all);

    return new Response().ok("Success", "companyWork", comp);
  }

  public Object listAllByProvince(CompanyWorkReq request) throws BaseException {

    List<CompanyWork> province = companyWorkService.findByProvince(request);

    List<CompanyWorkResponse> comp = companyWorkMapper.toListCompanyWorkResponse(province);

    return new Response().ok("Success", "companyWork", comp);
  }

  public Object delete(CompanyWorkDelete request) throws BaseException {

    User user = tokenService.getUserByIdToken();

    if (!companyWorkService.existsByIdAndUser(request.getId(), user)) {
      throw CompanyWorkException.notFoundId();
    }

    companyWorkService.deleteById(request.getId());
    return new Response().success("Delete Success");
  }

  public Object deleteByAdmin(CompanyWorkDelete request) throws BaseException {
    User user = tokenService.getUserByIdToken();

    Optional<CompanyWork> comp = companyWorkRepository.findById(request.getId());
    if (comp.isEmpty()){
      throw CompanyWorkException.notFoundId();
    }
    companyWorkService.deleteById(request.getId());
    return new Response().success("Delete Success");

  }
}
