package projectA.projectA.entity;


import lombok.Data;
import projectA.projectA.entity.base.AutoID;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "choice_apply_work")
public class Choice_apply_work extends AutoID {

    @ManyToOne
    @JoinColumn(name = "company_work_id",nullable = false)
    private CompanyWork CompanyWork;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestProfile guest;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Choice_apply_work.Type type;

    public enum Type {GUEST,USER}
}
