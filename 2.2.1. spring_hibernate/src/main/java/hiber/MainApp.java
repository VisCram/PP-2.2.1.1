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

        User user1 = new User("User1", "Lastname1", "user1@mail.io");
        User user2 = new User("User2", "Lastname2", "user2@mail.io");
        User user3 = new User("User3", "Lastname3", "user3@mail.io");

        Car car1 = new Car("volvo", 1, user1);
        Car car2 = new Car("bmw", 2, user2);
        Car car3 = new Car("suzuki", 3, user3);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
//                new Car("jhs", 2)));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
//                new Car("bb", 55)));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
//                new Car("cc", 66)));

        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        User user = userService.getUserByCar("volvo", 1);
        System.out.println(user);
        context.close();
    }
}
