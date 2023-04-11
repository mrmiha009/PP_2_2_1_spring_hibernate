package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru",
              new Car("Tesla", 3)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru",
              new Car("GAZ", 3110)));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru",
              new Car("UAZ", 452)));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru",
              new Car("VAZ", 2109)));
      User user9 = new User("User9", "Lastname9", "user9@mail.ru");
      user9.setUserCar(new Car("VAZ", 2110));
      userService.add(user9);
      //carService.add(new Car("Tesla", 3));
      //carService.add(new Car("GAZ", 3110));
      //carService.add(new Car("UAZ", 452));
      //carService.add(new Car("VAZ", 2109));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getUserCar().getModel());
         System.out.println("Car series = "+user.getUserCar().getSeries());
         System.out.println();
      }
      List<User> carOwners = userService.carOwners("Tesla", 3);
         for (User carOwner : carOwners) {
            System.out.println(carOwner.getUserCar()+ " owner is " + carOwner.getFirstName()
                    +" "+ carOwner.getLastName());
         }
      context.close();
   }
}
