package com.tushar.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class T_Bank_Accounts  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accno;
	
	@NonNull
	private String  name;
	@NonNull
	private Double balance;
	
	@NonNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate openingDt;
	@NonNull
	private String isActive;
	
}
