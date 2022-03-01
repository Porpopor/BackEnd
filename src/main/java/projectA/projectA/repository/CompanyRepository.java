package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectA.projectA.entity.Company;
import projectA.projectA.entity.User;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByEmail(String email);

    Optional<Company> findById(int compId);

    boolean existsByEmail(String email);
}
