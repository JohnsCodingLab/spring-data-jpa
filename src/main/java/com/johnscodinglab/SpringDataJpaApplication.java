package com.johnscodinglab;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@johnscodinglab.edu", firstName, lastName);
            int age = faker.number().numberBetween(17, 55);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    age
            );
            student.addBook(new Book("Clean Code", LocalDateTime.now()));
            student.addBook(new Book("Clean Code 2", LocalDateTime.now()));
            student.addBook(new Book("Clean Code 3", LocalDateTime.now()));


            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
            student.setStudentIdCard(studentIdCard);

            studentRepository.save(student);
        };
    }

//    private static void generateRandomStudent(StudentRepository studentRepository) {
//        Faker faker = new Faker();
//        for (int i = 0; i < 20; i++) {
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//            String email = String.format("%s.%s@johnscodinglab.edu", firstName, lastName);
//            int age = faker.number().numberBetween(17, 55);
//           Student student = new Student(
//                    firstName,
//                    lastName,
//                    email,
//                    age
//            );
//            studentRepository.save(student);
//        }
//    }

}
