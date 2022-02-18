package projectA.projectA.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.exception.UserException;
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
  }

  public Optional<User> findByEmail(String email){

    return userRepository.findByEmail(email);

  }
  public Optional<User> findById(int id){

    return userRepository.findById(id);

  }



  public boolean matchPassword(String rawPassword, String endcodedPassword){
    return passwordEncoder.matches(rawPassword, endcodedPassword);
  }

  public void upDateProfile(User user, String firstName , String lastName, String phone, String nameCompany) throws UserException {

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPhone(phone);
    user.setNameCompany(nameCompany);
    userRepository.save(user);

  }

  public User create(String email, String firstname, String lastname, String password) throws BaseException {
    if (Objects.isNull(email)) {
      throw UserException.EmailNull();
    }
    if (Objects.isNull(firstname)|| firstname.equals("")){
      throw UserException.FirstNameNull();
    }
    if (Objects.isNull(lastname)|| lastname.equals("")){
      throw UserException.LastNameNull();
    }
    if (Objects.isNull(password)|| password.equals("")){
      throw UserException.createPasswordNull();
    }
    if(userRepository.existsByEmail(email)){
      throw UserException.EmailDuplicated();
    }

    User entity = new User();
    entity.setEmail(email);
    entity.setFirstName(firstname);
    entity.setLastName(lastname);
    entity.setPassWord(passwordEncoder.encode(password));
    entity.setDate(new Date());

    return userRepository.save(entity);
  }

  public List<User> findAll(){
    List<User> all = userRepository.findAll();
    return all;
  }
}
