package projectA.projectA.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectA.projectA.business.CompanyWorkBusiness;
import projectA.projectA.business.UserBusiness;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.CompanyWorkException;
import projectA.projectA.model.companyWorkModel.CompanyWorkDelete;
import projectA.projectA.model.companyWorkModel.CompanyWorkReq;

@RestController
@RequestMapping("/company-work")
public class CompanyWorkController {

    private final CompanyWorkBusiness companyWorkBusiness;
    private final UserBusiness userBusiness;

    public CompanyWorkController(CompanyWorkBusiness companyWorkBusiness, UserBusiness userBusiness) {
        this.companyWorkBusiness = companyWorkBusiness;
        this.userBusiness = userBusiness;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createComp(@RequestBody CompanyWorkReq request) throws BaseException {
        Object response = companyWorkBusiness.createCompanyWork(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Object> editComp(@PathVariable Integer id, @RequestBody CompanyWorkReq request) throws BaseException {
        Object response = companyWorkBusiness.editCompanyWork(id,request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll() throws BaseException {
        Object response = companyWorkBusiness.listAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/listAllByProvince")
    public ResponseEntity<Object> listAllByProvince(@RequestBody CompanyWorkReq request) throws BaseException {
        Object response = companyWorkBusiness.listAllByProvince(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/view-byid")
    public ResponseEntity<Object> findById(@RequestBody CompanyWorkReq req) throws CompanyWorkException {
        Object response = companyWorkBusiness.findById(req);
        return ResponseEntity.ok(response);
    }
}

