package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;

import java.util.List;
import java.util.Optional;

public interface CompanyWorkRepository extends JpaRepository<CompanyWork,Integer> {

  Optional<CompanyWork> findById(Integer id);

  List<CompanyWork>findByUser(User user);

  List<CompanyWork>findByProvince(String comp);
}
