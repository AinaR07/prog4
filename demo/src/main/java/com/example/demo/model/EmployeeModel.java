package com.example.demo.model;


import lombok.*;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeModel {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private MultipartFile imageFile; // Ajout du fichier image
    private Optional<String> image; // Optionnel pour stocker l'image en base64

}