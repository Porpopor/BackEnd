package projectA.projectA.mapper;

import org.mapstruct.Mapper;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.model.companyWorkModel.CompanyWorkResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyWorkMapper {
  CompanyWorkResponse toCompanyWorkResponse(CompanyWork comp);

  List<CompanyWorkResponse>toListCompanyWorkResponse(List<CompanyWork> companyWorks);
}
