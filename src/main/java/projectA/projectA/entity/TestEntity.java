package projectA.projectA.entity;

import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "test")
public class TestEntity extends AutoID {

    @Column()
    @Lob
    private String test;
}
