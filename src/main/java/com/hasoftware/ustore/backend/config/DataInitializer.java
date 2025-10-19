package com.hasoftware.ustore.backend.config;

import com.hasoftware.ustore.backend.entity.Role;
import com.hasoftware.ustore.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            Role userRole = new Role(Role.RoleName.ROLE_USER);
            userRole.setDescription("Default role for all users");
            roleRepository.save(userRole);

            Role adminRole = new Role(Role.RoleName.ROLE_ADMIN);
            adminRole.setDescription("Administrator role with full access");
            roleRepository.save(adminRole);

            Role moderatorRole = new Role(Role.RoleName.ROLE_MODERATOR);
            moderatorRole.setDescription("Moderator role with limited admin access");
            roleRepository.save(moderatorRole);

            System.out.println("Roles initialized successfully!");
        }
    }
}
