package ru.geekbrains.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DataProcessingService {
    private final UserService userService;

    @Autowired
    public DataProcessingService(UserService userService) {
        this.userService = userService;
    }

    public Map<Long, User> filterUsersByAge(Map<Long, User> users, int age) {
        userService.getNotificationService().filterUsersByAge();
        List<User> temp = new ArrayList<>();
        for (User user : new ArrayList<>(users.values())) {
            if (user.getAge() > age) {
                temp.add(user);
            }
        }
        Map<Long, User> filterList = new ConcurrentHashMap<>();
        temp.forEach(user -> filterList.put(user.getId(), user));
        return filterList;
    }

    public double calculateAverageAge(Map<Long, User> users) {
        userService.getNotificationService().calculateAverage();
        return new ArrayList<>(users.values()).stream().mapToDouble(User::getAge).average().orElse(0);
    }

    public void addUserInList(User user) {
        userService.getUsers().put(user.getId(), user);
    }
}
