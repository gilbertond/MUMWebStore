/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.controller;

import edu.mum.domain.Address;
import edu.mum.domain.Role;
import edu.mum.domain.UserDetail;
import edu.mum.dao.IUserCrudRepositoryService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author 985939
 */
@Controller
public class UserController {

    @Autowired
    IUserCrudRepositoryService crudRepositoryService;
    
    @RequestMapping(value = "/user")
    public String getLogin(Principal principal){
        if (principal == null) {
            return "redirect:login";
        }
        UserDetail userDetail = crudRepositoryService.findByEmail(principal.getName());
        if (userDetail!=null && userDetail.getRoles().contains(Role.ROLE_CLIENT)) {
            System.out.println("************************************");
            return "redirect:index";
        }
        return "/menu2";
    }
    
    @RequestMapping(value = "/signup")
    public String getSignup(){
        return "/signup";
    }
    
    @RequestMapping(value = "/deleteAccount")
    public String deleteAccount(HttpServletRequest request, HttpServletResponse response, Principal principal){
        UserDetail detail = crudRepositoryService.findByEmail(principal.getName());
        crudRepositoryService.delete(detail);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        return "redirect:login";
    }
    
    @RequestMapping(value = "/manageUsers")
    public String manageUsers(Model model){
        
        List<UserDetail> userdetails = (List<UserDetail>)crudRepositoryService.findByisStaff(Boolean.TRUE);
        
        model.addAttribute("users", userdetails);
        return "/manageUsers";
    }
    
    @RequestMapping(value = "/signupSave", method = RequestMethod.POST)
    public String getSignup(HttpServletRequest request, @ModelAttribute("userdetail") UserDetail userDetailx, final RedirectAttributes redirectAttributes){
        System.out.println("Posting.......");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetail userDetail = new UserDetail(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"),
                encoder.encode(request.getParameter("password")), Boolean.TRUE);
        userDetail.addAddress(request.getParameter("street"), request.getParameter("city"), request.getParameter("state"), 
                request.getParameter("country"), request.getParameter("zip"));
        userDetail.setPhone(request.getParameter("phone"));
        if (request.getParameter("street2") != null && !request.getParameter("street2").equals("")) {
            userDetail.addAddress(request.getParameter("street2"), request.getParameter("city2"), request.getParameter("state2"), 
                    request.getParameter("country2"), request.getParameter("zip2"));
        }
        
        if (request.getParameter("street3") != null && !request.getParameter("street3").equals("")) {
            userDetail.addAddress(request.getParameter("street3"), request.getParameter("city3"), request.getParameter("state3"), 
                    request.getParameter("country3"), request.getParameter("zip3"));
        }
        userDetail.setIsStaff(Boolean.FALSE);
        userDetail.addRole(Role.ROLE_USER);
        userDetail.addRole(Role.ROLE_CLIENT);
        
        crudRepositoryService.save(userDetail);
        redirectAttributes.addFlashAttribute("message", "<span class=\"alert alert-info\">Saved details</span>");
        
        return "redirect:login";
    }
    
    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
    public String userSave(HttpServletRequest request, final RedirectAttributes redirectAttributes, Principal principal){
        System.out.println("Posting.......");
        
        UserDetail u = crudRepositoryService.findByEmail(principal.getName());
        if(u != null){
            redirectAttributes.addFlashAttribute("message", "<span class=\"alert alert-info\">User with email already exists</span>");
            return "addNewUser";
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetail userDetail = new UserDetail(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"),
                encoder.encode(request.getParameter("password")), Boolean.TRUE);
        
        userDetail.setIsStaff(Boolean.TRUE);
        userDetail.addRole(Role.ROLE_USER);
        userDetail.addRole(Role.ROLE_ADMINISTRATOR);
        crudRepositoryService.save(userDetail);
        redirectAttributes.addFlashAttribute("message", "<span class=\"alert alert-info\">Saved details, please sign in to continue</span>");
        
        return "redirect:manageUsers";
    }
    
    @RequestMapping(value = "/updateUser")
    public String updateUser(){
        return "/updateUser";
    }
    
    @RequestMapping(value = "/addNewUser")
    public String addNewUser(){
        return "/addNewUser";
    }
    
    @RequestMapping(value = "/deleteUser")
    public String deleteUser(){
        return "redirect:manageUsers";
    }
    
    @RequestMapping(value = "/updatedetails", method = RequestMethod.POST)
    public String updateDetails(HttpServletRequest request){
        System.out.println("Posting.......");
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long id = Long.parseLong(request.getParameter("uid"));
        
        UserDetail userDetail = new UserDetail(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"),
                encoder.encode(request.getParameter("password")), Boolean.TRUE);
        userDetail.setUserId(id);
        userDetail.addAddress(request.getParameter("street"), request.getParameter("city"), request.getParameter("state"), 
                request.getParameter("country"), request.getParameter("zip"));
        
        if (request.getParameter("street2") != null && !request.getParameter("street2").equals("")) {
            userDetail.addAddress(request.getParameter("street2"), request.getParameter("city2"), request.getParameter("state2"), 
                    request.getParameter("country2"), request.getParameter("zip2"));
        }
        
        if (request.getParameter("street3") != null && !request.getParameter("street3").equals("")) {
            userDetail.addAddress(request.getParameter("street3"), request.getParameter("city3"), request.getParameter("state3"), 
                    request.getParameter("country3"), request.getParameter("zip3"));
        }
        
        userDetail.addRole(Role.ROLE_USER);
        crudRepositoryService.save(userDetail);
        return "/signup";
    }
}