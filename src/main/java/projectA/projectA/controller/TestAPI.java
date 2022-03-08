package projectA.projectA.controller;

import org.springframework.web.bind.annotation.*;
import projectA.projectA.entity.Choice_apply_work;
import projectA.projectA.entity.CompanyWork;
import projectA.projectA.entity.TestEntity;
import projectA.projectA.entity.User;
import projectA.projectA.repository.*;

import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestAPI {

    private final Choice_apple_workRepository choice_apple_workRepository;
    private final GuestProfileRepository guestProfileRepository;
    private final UserRepository userRepository;
    private final CompanyWorkRepository companyWorkRepository;
    private final TestEntityRepo testEntityRepo;

    public TestAPI(Choice_apple_workRepository choice_apple_workRepository, GuestProfileRepository guestProfileRepository, UserRepository userRepository, CompanyWorkRepository companyWorkRepository, TestEntityRepo testEntityRepo) {
        this.choice_apple_workRepository = choice_apple_workRepository;
        this.guestProfileRepository = guestProfileRepository;
        this.userRepository = userRepository;
        this.companyWorkRepository = companyWorkRepository;
        this.testEntityRepo = testEntityRepo;
    }
//
//
    @GetMapping("/user")
    public Object user() {
        Optional<CompanyWork> byId = companyWorkRepository.findById(userTest.com_id);
        CompanyWork companyWork = byId.get();
        Optional<User> byId1 = userRepository.findById(userTest.user_id);
        User user = byId1.get();
        Choice_apply_work choice_apply_work = new Choice_apply_work();

        choice_apply_work.setUser(user);
        choice_apply_work.setCompanyWork(companyWork);
        choice_apply_work.setType(Choice_apply_work.Type.USER);
        choice_apple_workRepository.save(choice_apply_work);
return "";
    }
//
//    @GetMapping("/guest2")
//    public Object guest2() {
//        Optional<CompanyWork> byId = companyWorkRepository.findById(userTest.com_id);
//        CompanyWork companyWork = byId.get();
//        Optional<GuestProfile> byId1 = guestProfileRepository.findById(5);
//        GuestProfile guestProfile = byId1.get();
//
//        Choice_apply_work choice_apply_work = new Choice_apply_work();
//
//        choice_apply_work.setGuest(guestProfile);
//        choice_apply_work.setCompanyWork(companyWork);
//        choice_apply_work.setType(Choice_apply_work.Type.USER);
//        choice_apple_workRepository.save(choice_apply_work);
//        return "";
//    }
//
//    @GetMapping("/guest")
//    public Object guest(){
//        Optional<CompanyWork> byId = companyWorkRepository.findById(userTest.com_id);
//        CompanyWork companyWork = byId.get();
//        GuestProfile guestProfile = new GuestProfile();
//        guestProfile.setFirstName("krissakorn");
//        guestProfile.setLastName("chaphakdee");
//        guestProfileRepository.save(guestProfile);
//        return "";
//    }
//

    @PostMapping("/add")
    public Object add(@RequestBody Object request){
        TestEntity test = new TestEntity();
        test.setTest(request.toString());
        testEntityRepo.save(test);
        return test;
    }


    interface userTest{
        Integer com_id = 25;
        Integer user_id=21;


    }

    interface test{

    }


}
