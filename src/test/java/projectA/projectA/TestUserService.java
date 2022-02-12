package projectA.projectA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projectA.projectA.entity.User;
import projectA.projectA.exception.BaseException;
import projectA.projectA.service.UserService;

@SpringBootTest
class TestUserService {

  @Autowired
  private UserService userService;

	@Test
	void testCreate() throws BaseException {
    User user = userService.create(
      TestData.email,
      TestData.password,
      TestData.name,
      TestData.lastname
    );

    Assertions.assertNotNull(user);
    Assertions.assertNotNull(user.getId());

    Assertions.assertEquals(TestData.email,user.getEmail());

    boolean isMatched = userService.matchPassword(TestData.password, user.getPassWord());

    Assertions.assertEquals(TestData.name,user.getFirstName());
    Assertions.assertEquals(TestData.lastname,user.getLastName());
	}

  @Test
  void testUpdate() {
  }

  @Test
  void testDelete() {
  }

  interface TestData{
    String email = "@gssmsaiqsl.com";

    String password = "12345";

    String name = "qwdqwd";

    String lastname = "qwdqwd";

  }

}
