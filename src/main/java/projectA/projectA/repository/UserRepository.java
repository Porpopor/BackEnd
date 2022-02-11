package projectA.projectA.repository;

import org.springframework.data.repository.CrudRepository;
import projectA.projectA.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);
}
