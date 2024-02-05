package ru.geekbrains.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService implements Serializable {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong();
    private final NotificationService notificationService;
    @Autowired
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    public Map<Long, User> getUsers() {
        return users;
    }

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setId(counter.incrementAndGet());
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        notificationService.notifyUser(user);
        return user;
    }
    public NotificationService getNotificationService() {
        return notificationService;
    }
    public User openUser(long id){
        return users.get(id);
    }
}
