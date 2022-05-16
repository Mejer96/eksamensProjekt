package com.example.demo.Controllers;

import Models.Employee;
import Services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {
    private LoginService loginService = new LoginService();

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/")
    public String getLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) throws SQLException {
        Employee employee = loginService.employeeLogin(username, password);
        httpSession.setAttribute("user", employee);

        if (employee.getEmploymentRoleID() == 1){
            return "redirect:/dataregistrering";
        }
        else if (employee.getEmploymentRoleID() == 2){
            return "redirect:/forretningsudvikler";
        }
        else if (employee.getEmploymentRoleID() == 3){
            return "redirect:/skade";
        }
        else {
            return "login";
        }
    }

    @GetMapping("/log-out")
    public String logOut(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }
}
