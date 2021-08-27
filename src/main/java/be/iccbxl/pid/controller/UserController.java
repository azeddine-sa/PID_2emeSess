package be.iccbxl.pid.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import be.iccbxl.pid.model.Locality;
import be.iccbxl.pid.model.Role;
import be.iccbxl.pid.model.RoleRepository;
import be.iccbxl.pid.model.User;
import be.iccbxl.pid.model.UserRepository;
import be.iccbxl.pid.model.UserRole;
import be.iccbxl.pid.model.UserRoleService;
import be.iccbxl.pid.model.UserService;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleService userRoleService;


    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = service.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("title", "Liste des utilisateurs");

        return "user/index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "user/register";
    }
    
    @PostMapping("user/register")
    public String addUser(@Valid User user, BindingResult result, Model model) {
       
        if (result.hasErrors()) {
            return "register";
        }

        String passwordCrypt = user.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        String encodedPassword = passwordEncoder.encode(passwordCrypt);  

        user.setPassword(encodedPassword);

        service.addUser(user);

        UserRole userRole = new UserRole(Math.toIntExact(user.getId()),2);

        userRoleService.addUserRole(userRole);
     
        return "redirect:/login";
    }

    @GetMapping("/users/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = service.getAllUsers();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID", "E-mail", "Firstname", "Lastname", "Langue", "Created date"};
        String[] nameMapping = {"id", "email", "firstname", "lastname", "langue", "created_at"};

        csvWriter.writeHeader(csvHeader);

        for (User user : listUsers) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();

    }
}
