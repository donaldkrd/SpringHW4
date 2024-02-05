package ru.geekbrains.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Создать сервис "RegistrationService",
 * который принимает на вход данные о пользователе (имя, возраст, email),
 * создает пользователя с помощью UserService,
 * затем использует DataProcessingService для добавления пользователя в список
 * и выполнения операций над этим списком.
 * После выполнения каждой операции, использовать NotificationService
 * для вывода информации о выполненной операции.
 */
@Service
public class RegistrationService {
    private final UserService userService;
    private final DataProcessingService dataProcessingService;
    @Autowired
    public RegistrationService(UserService userService, DataProcessingService dataProcessingService) {
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
    }
    public User addUser(String name, int age, String email){
        User user = userService.createUser(name,age, email);
        dataProcessingService.addUserInList(user);
        userService.getNotificationService().addUser(user);
        return user;
    }


}
