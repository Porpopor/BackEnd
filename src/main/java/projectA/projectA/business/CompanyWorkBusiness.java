package projectA.projectA.business;

import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyWorkException;
import projectA.projectA.mapper.CompanyWorkMapper;
import projectA.projectA.model.Response;
import projectA.projectA.model.UploadFileReq;
import projectA.projectA.model.companyWorkModel.CompanyWorkDelete;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;
import projectA.projectA.model.companyWorkModel.CompanyWorkResponse;
import projectA.projectA.repository.CompanyWorkRepository;
import projectA.projectA.service.CompanyWorkService;
import projectA.projectA.service.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyWorkBusiness {
    UploadFileReq url = new UploadFileReq();
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
        if (request.getWelfareBenefits().toString().isBlank()) {
            throw CompanyWorkException.welfareBenefitsNull();
        }
        if (request.getDetailWork().isBlank()) {
            throw CompanyWorkException.detailWorkNull();
        }
        if (request.getFeature().toString().isBlank()) {
            throw CompanyWorkException.featureNull();
        }
        if (request.getContact().isBlank()) {
            throw CompanyWorkException.contactNull();
        }

        CompanyWork companyWork = companyWorkService.createCompanyWork(company,
                request.getCompanyName(),
                request.getProvince(),
                request.getDistrict(),
                request.getJobTitle(),
                request.getSalary(),
                request.getWelfareBenefits(),
                request.getDetailWork(),
                request.getFeature(),
                request.getContact());
        return new Response().ok("profile","id",companyWork.getId());
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
        if (request.getWelfareBenefits().toString().isBlank()) {
            throw CompanyWorkException.welfareBenefitsNull();
        }
        if (request.getDetailWork().isBlank()) {
            throw CompanyWorkException.detailWorkNull();
        }
        if (request.getFeature().toString().isBlank()) {
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

    public Object listFindByIdCompany() throws BaseException {

        Company companyByIdToken = tokenService.getCompanyByIdToken();

//        System.out.println(companyByIdToken.getId());

        List<CompanyWork> companyWorks = companyWorkService.FindByIdCompany(companyByIdToken.getId());
        List<CompanyWorkResponse> companyWorkResponse = companyWorkMapper.toListCompanyWorkResponse(companyWorks);

        return new Response().ok("company work", "list", companyWorkResponse);

    }

    public Object findById(Integer id) throws CompanyWorkException {
        Optional<CompanyWork> byId = companyWorkRepository.findById(id);
        if (byId.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork comp = byId.get();
        CompanyWorkResponse companyWorkResponse = companyWorkMapper.toCompanyWorkResponse(comp);
        return new Response().ok("CompById", "data", companyWorkResponse);
    }

    public Object findByIdCompanyView(Integer id) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        Optional<CompanyWork> byId = companyWorkRepository.findById(id);
        if (byId.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork comp = byId.get();
        if(companyByIdToken.getId() != comp.getCompany().getId()){
            throw CompanyWorkException.notFoundId();
        }
        String substring = comp.getPicture().substring(1, comp.getPicture().length() - 1);
        String[] split = substring.split(", ");
        ArrayList<String> picture = new ArrayList<>();
        for (int i = 0; i < split.length; i++){
            picture.add(
//                    url.getHost() + url.getDirCompanyProfile() +
                    split[i]);
        }
        String image = picture.toString().substring(1, picture.toString().length() - 1);
        String[] split1 = image.split(", ");
        comp.setPicture(image);
        CompanyWorkResponse companyWorkResponse = companyWorkMapper.toCompanyWorkResponse(comp);
        return new Response().ok("CompById", "data", companyWorkResponse);
    }

    public Object editFindByIdView(CompanyWorkReq request) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();

        Optional<CompanyWork> byId = companyWorkRepository.findById(request.getId());
        if (byId.isEmpty()) {
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork companyWork = byId.get();

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
                request.getContact()
                );
        return new Response().success("แก้ไขสำเร็จ");
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

    public Object deleteByCompany(Integer id) throws BaseException {
        Company companyByIdToken = tokenService.getCompanyByIdToken();
        Optional<CompanyWork> byId = companyWorkService.findById(id);
        if (byId.isEmpty()){
            throw CompanyWorkException.notFoundId();
        }
        CompanyWork companyWork = byId.get();
        if (companyWork.getCompany().getId() != companyByIdToken.getId()){
            throw CompanyWorkException.notFoundId();
        }
        companyWorkService.deleteById(id);
        return new Response().success("Deleted");
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
