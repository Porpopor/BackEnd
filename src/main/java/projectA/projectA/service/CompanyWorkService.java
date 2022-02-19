package projectA.projectA.service;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;
import projectA.projectA.repository.CompanyWorkRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyWorkService {

  private final CompanyWorkRepository companyWorkRepository;

  public CompanyWorkService(CompanyWorkRepository companyWorkRepository) {
    this.companyWorkRepository = companyWorkRepository;
  }

  public CompanyWork createCompanyWork(User user, String detail, String province){

    CompanyWork entity = new CompanyWork();

    entity.setUser(user);
    entity.setDetail(detail);
    entity.setProvince(province);
    companyWorkRepository.save(entity);
    return entity;
  }

  public void editCompanyWork(User user, int id, String detail, String province){

    Optional<CompanyWork> comp = companyWorkRepository.findById(id);

    CompanyWork entity = comp.get();

    entity.setUser(user);
    entity.setDetail(detail);
    entity.setProvince(province);
    entity.setUpdateDate(new Date());
    companyWorkRepository.save(entity);
  }

  public List<CompanyWork>findByUser(User user){
    List<CompanyWork> companyWorks = companyWorkRepository.findByUser(user);
    return companyWorks;
  }

  public List<CompanyWork>findAll(){
    List<CompanyWork>all = companyWorkRepository.findAll();
    return all;
  }

  public List<CompanyWork>findByProvince(CompanyWorkReq request){
    List<CompanyWork> byProvince = companyWorkRepository.findByProvince(request.getProvince());
    return byProvince;
  }
}
