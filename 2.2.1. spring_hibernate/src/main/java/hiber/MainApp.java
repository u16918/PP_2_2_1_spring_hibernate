package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Иван", "Иванов", "ivanov@mail.ru");
      User user2 = new User("Петр", "Петров", "petrov@mail.ru");
      User user3 = new User("Василий", "Васильев", "vasiliev@mail.ru");
      User user4 = new User("Роман", "Романов", "romanov@mail.ru");

      Car car1 = new Car("BMW", 5);
      Car car2 = new Car("Nissan", 2005);
      Car car3 = new Car("Lada", 15);
      Car car4 = new Car("Lexus", 660);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      try {
         System.out.println(userService.getUserAndCar("Largus", 55));
      } catch (Exception e) {
         System.out.println("Пользователь не найден.");
      }

      context.close();
   }
}
