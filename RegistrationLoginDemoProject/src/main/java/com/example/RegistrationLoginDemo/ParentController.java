package com.example.RegistrationLoginDemo;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@Controller
public class ParentController {

    //Logger logger = LoggerFactory.getLogger(ParentController.class);
    //private static final Logger logger = LoggerFactory.getLogger(ParentController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")

    public String show(){
        return "home";
    }
    //handler for handling registrtion page
    @GetMapping("/registration")
    public String registrationPage(User user){

        return "registrationPage";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@ModelAttribute("user") User user){
        Logger.getLogger("new registration initiated......");
        System.out.println(user);
        var hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(15));
        user.setPassword(hashedPassword);
        userRepository.save(user);
        Logger.getLogger("new registration saved......");
        return "Data Saved Successfully!!";
    }
    @GetMapping("/loginPage")
    public String loginPage(User user){
    return "loginPage";
    }
    @PostMapping("/login")
    @ResponseBody
    public String loginProcess(@RequestParam("userName") String userName
            ,@RequestParam("password") String password){
            User dbUser= userRepository.findByUserName(userName);

            if (BCrypt.checkpw(password,dbUser.getPassword())){
                return "loginSuccessful!";
            }else{
                return "login failed!";
            }
        //return "welcome";
    }
    @DeleteMapping("/deletePage")
    public String deletePage(User user){
        Logger.getLogger("Delete page called......");
        return "DeletePage";
    }
   // @DeleteMapping("/deleteRecordByName")
//   @RequestMapping(value = "/delete/{user}")
//    //@ResponseBody
//    public void deleteRecord(@PathVariable(="userName") User user){
//        if (user.getName().equals("sarah")) {
//            Logger.getLogger("User Delete page called......");
//            userRepository.deleteByName("sarah");
//            Logger.getLogger("User Deleted......"+user.getName());
//        }
//    }

    @DeleteMapping("{userName}")
    public ResponseEntity<String> deleteTodo(@PathVariable("userName") String userName){
        userRepository.deleteByName("sarah");
        return ResponseEntity.ok("Todo deleted successfully!.");
    }

}
