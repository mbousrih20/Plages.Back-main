package com.example.Plages;

import com.example.Plages.Controllers.PaysController;
import com.example.Plages.DALs.ConcessionRepo.ConcessionRepository;
import com.example.Plages.DALs.FileRepositories.FileRepository;
import com.example.Plages.DALs.ParasoleRepositories.ParasoleRepository;
import com.example.Plages.DALs.PaysRepositories.PaysRepository;
import com.example.Plages.DALs.UserRepositories.IUserRepository;
import com.example.Plages.DALs.UserRepositories.RoleRepository;
import com.example.Plages.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.example.Plages.Controllers.PaysController;
import com.example.Plages.DALs.PaysRepositories.PaysRepository;
import com.example.Plages.DALs.UserRepositories.IUserRepository;
import com.example.Plages.DALs.UserRepositories.RoleRepository;
import com.example.Plages.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepo; // Remplacez VotreRepository par votre propre repository
    @Autowired
    private IUserRepository  userRepo;
    @Autowired
    private PaysRepository repoPays;
    @Autowired
    private ConcessionRepository repoConcession;
    @Autowired
    private FileRepository repoFile;
    @Autowired
    private ParasoleRepository repoParasole;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role1 = new Role(ERole.ROLE_CLIENT);
        Role role2 = new Role(ERole.ROLE_ADMIN);

        Pays pays1 = new Pays("001","Italie");
        Pays pays2 = new Pays("002","France");
        Pays pays3 = new Pays("003","Portugal");
        Pays pays4 = new Pays("004","Espagne");
        Pays pays5 = new Pays("005","Belgique");
        Pays pays6 = new Pays("006","Suisse");
        Pays pays7 = new Pays("007","Allemagne");
        Pays pays8 = new Pays("008","Autriche");
        Pays pays9 = new Pays("009","Angleterre");
        Pays pays10 = new Pays("010","Grèce");

        roleRepo.save(role1);
        roleRepo.save(role2);
        Set<Role> roles = new HashSet<>();
        User admin = new User("admin","admin@gmail.com",encoder.encode("Admin.2024")) ;
        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        admin.setRoles(roles);
        userRepo.save(admin);

        repoPays.save(pays1);
        repoPays.save(pays2);
        repoPays.save(pays3);
        repoPays.save(pays4);
        repoPays.save(pays5);
        repoPays.save(pays6);
        repoPays.save(pays7);
        repoPays.save(pays8);
        repoPays.save(pays9);
        repoPays.save(pays10);

        Concession concession = new Concession("Miramar","Biarritz",admin);


        repoConcession.save(concession);
        for (int i = 1; i <= 8; i++) {
            File file = new File(i, 90 - 10*i , concession);
            repoFile.save(file);
            for (int j = 1; j <= 36; j++) {
                Parasole parasole = new Parasole(j, file);
                repoParasole.save(parasole);
            }
        }




    }
}

