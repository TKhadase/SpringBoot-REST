package com.tushar.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private int sid;
	private String sname;
	private String result;
	private int age;
	
	private String[] projects;
	private Set<Long> phone;
	private List<Double> marks;
	private Map<String,String> idcards;
	
	//HAS-A property
	private Subjects subjects;
	
}
