package ru.geekbrains.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;
    private final UserService userService;
    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService, UserService userService){
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
    }
    @RequestMapping(value = "/filter/{age}", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, User>> filterByAge(@PathVariable("age") Integer age, @RequestBody Map<Long, User> users){
        return new ResponseEntity<>(this.dataProcessingService.filterUsersByAge(this.userService.getUsers(), age), HttpStatus.OK);
    }
    @RequestMapping(value = "/average", method = RequestMethod.POST)
    public ResponseEntity<Double> average(@RequestBody Map<Long, User> users){
        return new ResponseEntity<>(this.dataProcessingService.calculateAverageAge(this.userService.getUsers()), HttpStatus.OK);
    }
}
