package projectA.projectA.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
import projectA.projectA.model.userModel.AdminReq;
import projectA.projectA.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.createAdmin();
    }

    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    public Optional<User> findById(int id) {

        return userRepository.findById(id);

    }


    public boolean matchPassword(String rawPassword, String endcodedPassword) {
        return passwordEncoder.matches(rawPassword, endcodedPassword);
    }

    public void ChangePassWord(User user, String passWord) {

        user.setPassWord(passwordEncoder.encode(passWord));
        user.setUpdateDate(new Date());
        userRepository.save(user);
    }

    public void createUser(String email, String firstname, String lastname, String sex, String password, String phone) throws BaseException {

        User entity = new User();
        entity.setEmail(email);
        entity.setFirstName(firstname);
        entity.setLastName(lastname);
        entity.setSex(sex);
        entity.setPassWord(passwordEncoder.encode(password));
        entity.setPhone(phone);
        entity.setVerifyEmail(0);
        entity.setDate(new Date());
        entity.setNewEmail(email);
        entity.setUpdateDate(new Date());

        userRepository.save(entity);
    }

    public void upDateProfile(User user, String firstName, String lastName, String sex, String phone) throws BaseException {

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSex(sex);
        user.setPhone(phone);
        user.setUpdateDate(new Date());
        userRepository.save(user);

    }
    public void changeEmail(User user,String email){

        user.setEmail(email);
        user.setVerifyEmail(0);
        user.setUpdateDate(new Date());
        userRepository.save(user);
    }

    public void createAdmin() {

        AdminReq request = new AdminReq();

        if (userRepository.existsByEmail(request.getEmail())) {
            return;
        }
        User entity = new User();

        entity.setEmail(request.getEmail());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setRole(request.getRole());
        entity.setDate(new Date());
        entity.setPhone("0610720339");
        entity.setVerifyEmail(1);
        entity.setSex("ชาย");
        entity.setUpdateDate(new Date());
        entity.setNewEmail(request.getEmail());
        entity.setPassWord(passwordEncoder.encode(request.getPassWord()));

        userRepository.save(entity);
    }

    public void verifyEmail(User user) {

        user.setVerifyEmail(1);
        user.setUpdateDate(new Date());
        userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public Optional<User> findById(Integer id) {

        Optional<User> byId = userRepository.findById(id);

        return byId;
    }

    public boolean existsByEmail(String email){
        boolean byEmail = userRepository.existsByEmail(email);
        return byEmail;
    }
}
