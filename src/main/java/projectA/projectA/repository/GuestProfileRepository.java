package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.GuestProfile;

public interface GuestProfileRepository extends JpaRepository<GuestProfile, Integer> {
}
