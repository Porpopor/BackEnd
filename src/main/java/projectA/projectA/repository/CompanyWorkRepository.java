package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectA.projectA.entity.CompanyWork;

public interface CompanyWorkRepository extends JpaRepository<CompanyWork,Integer> {


}
