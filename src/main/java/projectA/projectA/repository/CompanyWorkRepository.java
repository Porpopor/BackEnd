package projectA.projectA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.User;

import java.util.List;
import java.util.Optional;

public interface CompanyWorkRepository extends JpaRepository<CompanyWork, Integer> {

  Optional<CompanyWork> findById(Integer id);

  List<CompanyWork> findByUser(User user);

  @Query("""
            select a
            from CompanyWork a
            where a.province
            like %:province%
            and a.name
            like %:name%
            order by a.id desc
            """)
  List<CompanyWork> findBySearchProvince(@Param("province") String comp, @Param("name") String  name);


//  select * from company_work where province like '%ข%'
}
