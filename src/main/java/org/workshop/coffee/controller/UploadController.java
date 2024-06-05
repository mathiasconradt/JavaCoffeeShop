package org.workshop.coffee.controller;

import org.workshop.coffee.domain.Person;
import org.workshop.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class UploadController {

    @Autowired
    private PersonService personService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/uploadimage")
    public String displayUploadForm() {
        return "person/upload";
    }

    @PostMapping("/uploadimage")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file, Principal principal) throws IOException {

//        model.addAttribute("msg", "Uploaded images: " + name);
//        getPerson(model, principal).setProfilePic(name);
//        personService.savePerson(getPerson(model, principal));

        // Get file and save it in the UPLOAD_DIRECTORY
        var name = file.getOriginalFilename().replace(" ", "_");
        var fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, name);

        // validate that the fileNameAndPath is not vulnerable to path traversal
        if (!fileNameAndPath.normalize().startsWith(Paths.get(UPLOAD_DIRECTORY).normalize())) {
            model.addAttribute("msg", "Invalid file path");
            return "person/upload";
        }

        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + name);
        getPerson(model, principal).setProfilePic(name);
        personService.savePerson(getPerson(model, principal));

        return "person/upload";
    }

    @GetMapping("/uploadimage2")
    public String displayUploadForm2() {

        return "person/upload";
    }

    public Person getPerson(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message", "ERROR");
            return null;
        }

        var user = principal.getName();
        var person = personService.findByUsername(user);
        return person;
    }
}