//package projectA.projectA.service;
//
//import org.springframework.stereotype.Service;
//import projectA.projectA.entity.User;
//import projectA.projectA.entity.UserProfile;
//import projectA.projectA.repository.UserProfileRepository;
//
//import java.util.Optional;
//
//@Service
//public class UserProfileService {
//
//  private final UserProfileRepository userProfileRepository;
//
//  public UserProfileService(UserProfileRepository userRepository) {
//    this.userProfileRepository = userRepository;
//  }
//
//  public Optional<UserProfile> findByUser(User user){
//
//    return userProfileRepository.findByUser(user);
//
//  }
//
//  public UserProfile userProfileCreate(User user,String phone, String nameCompany){
//    UserProfile userProfile = new UserProfile();
//    userProfile.setUser(user);
//    userProfile.setPhone(phone);
//    userProfile.setNameCompany(nameCompany);
//    return userProfileRepository.save(userProfile);
//  }
//}
