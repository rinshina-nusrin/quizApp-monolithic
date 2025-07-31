package com.sample.quizapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
//@Table(name = "questionnaire", schema = "question")  //if there is different table name
@Data
public class Questionnaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String question;
	
	private String option_a;
	
	private String option_b;
	
	private String option_c;
	
	private String option_d;
	
	private String correctAnswer;
	
	private String category;
	
	private String difficultyLevel;
	
}
