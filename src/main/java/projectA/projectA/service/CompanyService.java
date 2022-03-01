package projectA.projectA.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.Company;
import projectA.projectA.model.companyModel.CompanyEmailReq;
import projectA.projectA.repository.CompanyRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Company> findByEmail(String email) {

        return companyRepository.findByEmail(email);

    }

    public boolean matchPassword(String rawPassword, String endcodedPassword) {

        return passwordEncoder.matches(rawPassword, endcodedPassword);
    }

    public void register(String email, String companyName, String password, String phone, String type) {
        Company company = new Company();

        company.setEmail(email);
        company.setPassWord(passwordEncoder.encode(password));
        company.setPhone(phone);
        company.setCompanyName(companyName);
        company.setType(type);
        company.setDate(new Date());
        company.setUpdateDate(new Date());
        company.setNewEmail(email);
        company.setVerifyEmail(0);

        companyRepository.save(company);

    }

    public void editCompany(Company company,String companyName, String phone, String type){

        company.setCompanyName(companyName);
        company.setPhone(phone);
        company.setType(type);
        company.setUpdateDate(new Date());

        companyRepository.save(company);
    }

    public void changeEmail(Company company,String email){

        company.setNewEmail(email);
        company.setVerifyEmail(0);
        companyRepository.save(company);

    }

    public void verifyEmail(Company company,String email){
        company.setEmail(email);
        company.setVerifyEmail(1);
        companyRepository.save(company);
    }

    public void resetPassword(Company company,String newPassword){

        company.setPassWord(passwordEncoder.encode(newPassword));
        company.setUpdateDate(new Date());
        companyRepository.save(company);

    }

    public void changePassword(Company company, String password){
        company.setPassWord(passwordEncoder.encode(password));
        companyRepository.save(company);
    }

    public boolean existsByEmail(String email){
        boolean byEmail = companyRepository.existsByEmail(email);
        return byEmail;
    }
}
