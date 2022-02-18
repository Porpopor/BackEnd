package projectA.projectA.service;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.repository.CompanyWorkRepository;

@Service
public class CompanyWorkService {

  private final CompanyWorkRepository companyWorkRepository;

  public CompanyWorkService(CompanyWorkRepository companyWorkRepository) {
    this.companyWorkRepository = companyWorkRepository;
  }

  public void createCompanyWork(User user,String detail){

    CompanyWork entity = new CompanyWork();

    entity.setUser(user);
    entity.setDetail(detail);
    companyWorkRepository.save(entity);
  }
}
