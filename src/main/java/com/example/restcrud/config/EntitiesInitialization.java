package com.example.restcrud.config;

import com.example.restcrud.commentary.Commentary;
import com.example.restcrud.commentary.CommentaryRepository;
import com.example.restcrud.thread.Thread;
import com.example.restcrud.thread.ThreadRepository;
import com.example.restcrud.user.User;
import com.example.restcrud.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class EntitiesInitialization {



    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ThreadRepository threadRepository, CommentaryRepository commentaryRepository) {
        return args -> {
            User myriam = new User(
                    "Myriam",
                    "myriam.khulba@emailprovider.com",
                    "Myriam",
                    "Khulba",
                    LocalDate.of(2000, Month.AUGUST, 5),
                    "$2y$10$z/M0ZN1SU4uGS.8cDIqmZuJdW2ciDXc0VIzX2NUNC.FWTdaavOFDS"
            );

            User alex = new User(
                    "Alexander",
                    "alexthegreat@caramail.com",
                    "Aleksander",
                    "Cohen",
                    LocalDate.of(1999, Month.DECEMBER, 17),
                    "$2y$10$z/M0ZN1SU4uGS.8cDIqmZuJdW2ciDXc0VIzX2NUNC.FWTdaavOFDS"
            );

            User martha = new User(
                    "MarthaRoberts",
                    "martha@caramail.com",
                    "Martha",
                    "Roberts",
                    LocalDate.of(2003, Month.DECEMBER, 12),
                    "$2y$10$z/M0ZN1SU4uGS.8cDIqmZuJdW2ciDXc0VIzX2NUNC.FWTdaavOFDS"
            );

            userRepository.saveAll(
                    List.of(myriam, alex, martha)
            );

            Thread thread1 = new Thread(
                    "Do not ever put salt in yoghurts because it can severely harm your stomach !",
                    "Did you ever add salt in your yoghurt ? Maybe once, but if you did, you should remember it. The salt " +
                            "reacts with enzymes contained in the yoghurt and form a highly damaging acid, causing you some gastric hazard.",
                    LocalDateTime.now(),
                    userRepository.findById(1L).orElse(null)
            );

            Thread thread2 = new Thread(
                    "Theme2",
                    "Content of 'Theme 2 ' thread",
                    LocalDateTime.now(),
                    userRepository.findById(2L).orElse(null)
            );

            Thread thread3 = new Thread(
                    "A third thread to populate this database",
                    "lorem ipsum dolor",
                    LocalDateTime.now(),
                    userRepository.findById(3L).orElse(null)
            );

            Thread thread4 = new Thread(
                    "Best Bernie Sanders Quote",
                    "I once again ask for your financial support",
                    LocalDateTime.now(),
                    userRepository.findById(3L).orElse(null)
            );

            threadRepository.saveAll(
                    List.of(thread1, thread2, thread3, thread4)
            );

            Commentary commentary1 = new Commentary(
                    "This is a short commentary",
                    LocalDateTime.now(),
                    userRepository.findById(3L).orElse(null),
                    threadRepository.findById(1L).orElse(null)
            );

            Commentary commentary2 = new Commentary(
                    "I sometimes wonder why is spring boot so hard to learn",
                    LocalDateTime.now(),
                    userRepository.findById(1L).orElse(null),
                    threadRepository.findById(1L).orElse(null)
            );

            Commentary commentary3 = new Commentary(
                    "This thread is surely the thread of the year, I give it the best possible rate",
                    LocalDateTime.now(),
                    userRepository.findById(3L).orElse(null),
                    threadRepository.findById(4L).orElse(null)
            );

            Commentary commentary4 = new Commentary(
                    "Commentaries could be more readable, if we add a little bit more punctuation to them.",
                    LocalDateTime.now(),
                    userRepository.findById(2L).orElse(null),
                    threadRepository.findById(2L).orElse(null)
            );

            Commentary commentary5 = new Commentary(
                    "The commentary of the year",
                    LocalDateTime.now(),
                    userRepository.findById(1L).orElse(null),
                    threadRepository.findById(4L).orElse(null)
            );

            Commentary commentary6 = new Commentary(
                    "+1",
                    LocalDateTime.now(),
                    userRepository.findById(3L).orElse(null),
                    threadRepository.findById(2L).orElse(null)
            );

            commentaryRepository.saveAll(
                    List.of(commentary1, commentary2, commentary3, commentary4, commentary5, commentary6)
            );
        };
    }

}
