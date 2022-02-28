package projectA.projectA.entity;

import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@Entity
@Table(name = "guest_profile")
public class GuestProfile extends AutoID {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "guest",orphanRemoval = true)
    private List<Choice_apply_work> choice;
}
