package ru.geekbrains.SpringMVC;

/**
 * Для удобства:
 * POST
 * localhost:8080/users/add?name=Garry Potter&age=14&email=garry@hgw.ru
 * localhost:8080/users/add?name=Germiona Grainger&age=14&email=germiona@hgw.ru
 * localhost:8080/users/add?name=Ron Wisley&age=15&email=ron@hgw.ru
 * localhost:8080/users/add?name=Remus John Lupin&age=45&email=lupin@hgw.ru
 * localhost:8080/users/add?name=Severus Snape1&age=46&email=severus@hgw.ru
 * GET
 * localhost:8080/users/getAll
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RegistrationService registrationService;
    @Autowired
    public UserController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Map<Long,User>> getUserList(){
        userService.getNotificationService().getAll();
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam int age, @RequestParam String email){
        return new ResponseEntity<>(this.userService.createUser(name, age, email), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestParam String name, @RequestParam int age, @RequestParam String email){
        return new ResponseEntity<>(this.registrationService.addUser(name, age, email), HttpStatus.CREATED);
    }
    @GetMapping
    public String listUsers(Model model){
        model.addAttribute("users", new ArrayList<>(this.userService.getUsers().values()));
        return "users";
    }
    @GetMapping("/{id}")
    public String changeUser(@PathVariable int id , Model model){
        model.addAttribute("user", this.userService.openUser(id));
        return "userProfile";
    }
}
