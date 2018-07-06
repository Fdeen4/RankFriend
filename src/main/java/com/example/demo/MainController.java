package com.example.demo;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.annotation.PostConstruct;
@Controller
public class MainController {


    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String Intro (Model model){
        model.addAttribute("job", new User());
        return "createuser";
    }
    @GetMapping("/list")
    public String listJobs (Model model){
        model.addAttribute("job", userRepository.findAll());
        return "userprofile";
    }
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("job") User jobs, BindingResult result)
    {
        if (result.hasErrors()) {
            return "createuser";
        }
        userRepository.save(jobs);
        return "redirect:/list";
    }
    @RequestMapping("/detail/{id}")
    public String showJobs(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", userRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateJobs(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", userRepository.findById(id).get());
        return "createuser";
    }

    @RequestMapping("/delete/{id}")
    public String delJobs(@PathVariable("id") long id){
        userRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/rank")
    public String rank(Model model){
        model.addAttribute("job",new User());
        return "rankfriends";
    }
    @RequestMapping("/l")
    public String list(@ModelAttribute("job") User message, Model model)
    {
        userRepository.save(message);
        model.addAttribute("job",userRepository.findAll());
        model.addAttribute("job",new User());
        return "list";
    }
}



