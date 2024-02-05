package ru.geekbrains.SpringMVC;


import org.springframework.stereotype.Service;

/**
 * Создать два сервиса - "UserService" и "NotificationService".
 * UserService должен содержать метод createUser(String name, int age, String email),
 * который создает пользователя и возвращает его. NotificationService должен иметь метод notifyUser(User user),
 * который просто печатает сообщение о том, что пользователь был создан.
 * Ваша задача - использовать @Autowired в UserService для внедрения NotificationService
 * и вызвать метод notifyUser после создания нового пользователя.
 */
@Service
public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }
    public void addUser(User user){
        System.out.println("A new user has been added in list: " + user.getName());
    }
    public void sortUsersByAge(){
        System.out.println("All users was sorted by age");
    }
    public void filterUsersByAge(){
        System.out.println("All users was filtering by age");
    }
    public void calculateAverage(){
        System.out.println("Calculating average on users age is done");
    }
    public void getAll(){
        System.out.println("Get all users");
    }
}
