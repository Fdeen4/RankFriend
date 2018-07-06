
package com.example.demo.config;
import com.example.demo.AppRoleRepository;
import com.example.demo.models.User;
import com.example.demo.models.AppRole;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

    @Component
    public class DataLoader implements CommandLineRunner {

        //Include repositories for each item that will be saved in the data loader
        @Autowired
        UserRepository userRepository;

        @Autowired
        AppRoleRepository roleRepository;

        //Include a password encododer for data (using bcrypt hashing algorithm, but can use others)

        @Override
        public void run(String... strings) throws Exception {


            AppRole aRole = new AppRole();
            aRole.setRole("USER");
            roleRepository.save(aRole);
        }
    }