package mvc.mvc.controller;


import mvc.mvc.model.services.UserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserRoleDTO userRoleDTO;

    @Autowired
    public UserController(UserRoleDTO userRoleDTO) {
        this.userRoleDTO = userRoleDTO;
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam String role_name){
        userRoleDTO.saveRole(role_name);
        return "redirect:/";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String pass, @RequestParam String role_name){
        userRoleDTO.saveUser(name, pass, role_name);
        return "redirect:/";
    }
}
