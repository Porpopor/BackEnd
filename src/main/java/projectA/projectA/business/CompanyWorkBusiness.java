package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
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
        Company company = tokenService.getCompanyByIdToken();

        if (request.getCompanyName().isBlank()) {
            throw CompanyWorkException.nameNull();
        }

        if (request.getProvince().isBlank()) {
            throw CompanyWorkException.provinceNull();
        }

        if (request.getDistrict().isBlank()) {
            throw CompanyWorkException.districtNull();
        }

        if (request.getJobTitle().isBlank()) {
            throw CompanyWorkException.jobTitleNull();
        }

        if (request.getSalary().isBlank()) {
            throw CompanyWorkException.salaryNull();
        }
        if (request.getWelfareBenefits().isBlank()) {
            throw CompanyWorkException.welfareBenefitsNull();
        }
        if (request.getDetailWork().isBlank()) {
            throw CompanyWorkException.detailWorkNull();
        }
        if (request.getFeature().isBlank()) {
            throw CompanyWorkException.featureNull();
        }
        if (request.getContact().isBlank()) {
            throw CompanyWorkException.contactNull();
        }

        companyWorkService.createCompanyWork(company,
                request.getCompanyName(),
                request.getProvince(),
                request.getDistrict(),
                request.getJobTitle(),
                request.getSalary(),
                request.getWelfareBenefits(),
                request.getDetailWork(),
                request.getFeature(),
                request.getContact());
        return new Response().success("test");
    }

    public Object editCompanyWork(Integer id, CompanyWorkReq request) throws BaseException {
        Company company = tokenService.getCompanyByIdToken();

        Optional<CompanyWork> comp = companyWorkRepository.findById(id);
        if (comp.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork companyWork = comp.get();

        if (!companyWork.getCompany().getId().equals(company.getId())) {
            throw CompanyWorkException.notFoundId();
        }

        if (request.getCompanyName().isBlank()) {
            throw CompanyWorkException.nameNull();
        }

        if (request.getProvince().isBlank()) {
            throw CompanyWorkException.provinceNull();
        }

        if (request.getDistrict().isBlank()) {
            throw CompanyWorkException.districtNull();
        }

        if (request.getJobTitle().isBlank()) {
            throw CompanyWorkException.jobTitleNull();
        }

        if (request.getSalary().isBlank()) {
            throw CompanyWorkException.salaryNull();
        }
        if (request.getWelfareBenefits().isBlank()) {
            throw CompanyWorkException.welfareBenefitsNull();
        }
        if (request.getDetailWork().isBlank()) {
            throw CompanyWorkException.detailWorkNull();
        }
        if (request.getFeature().isBlank()) {
            throw CompanyWorkException.featureNull();
        }
        if (request.getContact().isBlank()) {
            throw CompanyWorkException.contactNull();
        }


        companyWorkService.editCompanyWork(
                companyWork,
                request.getCompanyName(),
                request.getProvince(),
                request.getDistrict(),
                request.getJobTitle(),
                request.getSalary(),
                request.getWelfareBenefits(),
                request.getDetailWork(),
                request.getFeature(),
                request.getContact());
        return new Response().success("แก้ไขสำเร็จ");
    }

    public Object findById(CompanyWorkReq id) throws CompanyWorkException {
        Optional<CompanyWork> byId = companyWorkRepository.findById(id.getId());
        if (byId.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork comp = byId.get();
        CompanyWorkResponse companyWorkResponse = companyWorkMapper.toCompanyWorkResponse(comp);
        return new Response().ok("CompById", "data", companyWorkResponse);
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

    public Object deleteByAdmin(CompanyWorkDelete request) throws BaseException {
        User user = tokenService.getUserByIdToken();

        Optional<CompanyWork> comp = companyWorkRepository.findById(request.getId());
        if (comp.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        companyWorkService.deleteById(request.getId());
        return new Response().success("Delete Success");

    }
}
