package com.sample.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.quizapp.Entities.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
