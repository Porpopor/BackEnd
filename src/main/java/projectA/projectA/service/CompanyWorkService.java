package projectA.projectA.service;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.exception.CompanyWorkException;
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

    public CompanyWork createCompanyWork(Company company,
                                         String companyName,
                                         String province,
                                         String district,
                                         String jobTitle,
                                         String salary,
                                         String welfareBenefits,
                                         String detailWork,
                                         String feature,
                                         String contact) {

        CompanyWork entity = new CompanyWork();

        entity.setCompany(company);
        entity.setCompanyName(companyName);
        entity.setProvince(province);
        entity.setDistrict(district);
        entity.setJobTitle(jobTitle);
        entity.setSalary(salary);
        entity.setWelfareBenefits(welfareBenefits);
        entity.setDetailWork(detailWork);
        entity.setFeature(feature);
        entity.setContact(contact);
        entity.setDate(new Date());
        entity.setUpdateDate(new Date());

        companyWorkRepository.save(entity);
        return entity;
    }

    public void editCompanyWork(
            CompanyWork companyWork,
            String companyName,
            String province,
            String district,
            String jobTitle,
            String salary,
            String welfareBenefits,
            String detailWork,
            String feature,
            String contact) {

        companyWork.setCompanyName(companyName);
        companyWork.setProvince(province);
        companyWork.setDistrict(district);
        companyWork.setJobTitle(jobTitle);
        companyWork.setSalary(salary);
        companyWork.setWelfareBenefits(welfareBenefits);
        companyWork.setDetailWork(detailWork);
        companyWork.setFeature(feature);
        companyWork.setContact(contact);
        companyWork.setUpdateDate(new Date());
        companyWorkRepository.save(companyWork);
    }

    public List<CompanyWork> findAll() {
        List<CompanyWork> all = companyWorkRepository.findAll();
        return all;
    }

    public List<CompanyWork> findByProvince(CompanyWorkReq request) {
        List<CompanyWork> byProvince = companyWorkRepository.findBySearchProvince(request.getProvince(), request.getCompanyName());
        return byProvince;
    }

    public CompanyWork findById(Integer id) throws CompanyWorkException {
        Optional<CompanyWork> byId = companyWorkRepository.findById(id);
        return byId.get();
    }

    public void deleteById(Integer id) {

        companyWorkRepository.deleteById(id);

    }

}
