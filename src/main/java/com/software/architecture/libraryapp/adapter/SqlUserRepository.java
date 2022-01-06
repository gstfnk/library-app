package com.software.architecture.libraryapp.adapter;

import com.software.architecture.libraryapp.model.Genders;
import com.software.architecture.libraryapp.model.RegistrationQuestions;
import com.software.architecture.libraryapp.model.User;
import com.software.architecture.libraryapp.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

public interface SqlUserRepository extends UserRepository, JpaRepository<User,Integer> {

    @Override
    Optional<User> findByEmail(String email);

    @Override
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value =
                    //" VALUES (?1, ?2, ?3, ?4, string_to_array(?5, ','), ?6, ?7, ?8, ?9)"
                    "INSERT INTO users (first_name, last_name, email, password, roles, registration_question, registration_question_answer, gender, birth_date, account_creation_date)"
                    + " VALUES (?1, ?2, ?3, ?4, string_to_array(?5, ','), ?6, ?7, ?8, ?9, ?10)")
    void save(String firstName, String lastName, String email, String password, String roles,
              String registrationQuestion, String registrationQuestionAnswer,
              String gender, LocalDate birthDate, LocalDate accountCreationDate);
}
