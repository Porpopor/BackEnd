package projectA.projectA.mapper;

import org.mapstruct.Mapper;
import projectA.projectA.entity.User;
import projectA.projectA.entity.UserProfile;
import projectA.projectA.model.userModel.RegisterResponse;
import projectA.projectA.model.userModel.UserEditResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
  RegisterResponse toRegisterResponse(User user);
  UserEditResponse UserEditToRegisterResponse(User user);
  UserProfile UserProfileToUserProfileResponse(UserProfile user);
}
