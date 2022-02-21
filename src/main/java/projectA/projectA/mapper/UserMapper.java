package projectA.projectA.mapper;

import org.mapstruct.Mapper;
import projectA.projectA.entity.User;
import projectA.projectA.model.userModel.RegisterResponse;
import projectA.projectA.model.userModel.UserEditResponse;
import projectA.projectA.model.userModel.UserProfileResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  RegisterResponse toRegisterResponse(User user);
  UserEditResponse UserEditToRegisterResponse(User user);
  UserProfileResponse UserProfileToResponse(User user);
  List<UserProfileResponse> UserProfileToUserProfileResponse(List<User> user);
}
