package projectA.projectA.mapper;


import org.mapstruct.Mapper;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.model.companyModel.CompanyProfileResponse;
import projectA.projectA.model.companyWorkModel.CompanyWorkResponse;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyProfileResponse CompanyProfileToResponse(Company comp);
}
