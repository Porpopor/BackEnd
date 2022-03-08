package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectA.projectA.entity.TestEntity;

public interface TestEntityRepo extends JpaRepository<TestEntity,Integer> {
}
