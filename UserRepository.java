import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Main;
import org.example.SomeService;
import org.junit.jupiter.api.*;

/*
Задачи со звездочкой
Наделить админскими правами пользователя (User),
добавить функцию в UserRepository (TDD), 
которая будет разлогинивать всех пользователей, кроме админов
*/

public class UserRepository {
    private List<User> users = new ArrayList<>();
    
    public void addUser(User user) {
      if (user.isAuthenticate()) {
        users.add(user);
      }
      if (user.isAdmin()) {
        user.setAdmin(true);
      }
    }
    
    public List<User> getUsers() {
      return users;
    }
    
    public void logoutAllUsersExceptAdmins() {
      for (User user : users) {
        if (!user.isAdmin()) {
          user.setAuthenticate(false);
        }
      }
    }
  }
  
@Test
public void logoutAllUsersExceptAdminsTest() {
    UserRepository userRepository = new UserRepository();
    // Добавление пользователей в репозиторий
    userRepository.addUser(new User("John", false));
    userRepository.addUser(new User("Jane", true));
    userRepository.addUser(new User("Jack", false));

    // Вызов метода
    userRepository.logoutAllUsersExceptAdmins();

    // Утверждения для проверки правильности работы метода
    assertTrue(userRepository.getUser("John").isLoggedIn() == false);
    assertTrue(userRepository.getUser("Jane").isLoggedIn() == true);
    assertTrue(userRepository.getUser("Jack").isLoggedIn() == false);
}
