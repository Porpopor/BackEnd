package projectA.projectA.service;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.repository.CompanyWorkRepository;

import java.util.List;

@Service
public class CompanyWorkService {

  private final CompanyWorkRepository companyWorkRepository;

  public CompanyWorkService(CompanyWorkRepository companyWorkRepository) {
    this.companyWorkRepository = companyWorkRepository;
  }

  public CompanyWork createCompanyWork(User user, String detail){

    CompanyWork entity = new CompanyWork();

    entity.setUser(user);
    entity.setDetail(detail);
    companyWorkRepository.save(entity);
    return entity;
  }

  public void editCompanyWork(User user, int id, String detail){

    CompanyWork entity = new CompanyWork();

    entity.setUser(user);
    entity.setId(id);
    entity.setDetail(detail);
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
}
