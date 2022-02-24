package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectA.projectA.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

//  Optional<User> findByFirstName(String name);


  boolean existsByEmail(String email);

    Optional<User> findById(int userId);

    @Query("""
          select a 
          from User a,CompanyWork b 
          where a.id = :id 
          and a.id = b.user.id
          """)
    List<User>findByIdCompany(@Param("id") Integer user);

}
