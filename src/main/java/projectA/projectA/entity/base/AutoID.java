package projectA.projectA.entity.base;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@MappedSuperclass
@EnableAutoConfiguration
public abstract class AutoID {
  @Id
//  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false, length = 36, unique = true)
  private Integer id;
}
