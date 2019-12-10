package com.example.demojpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "Employees")
@ToString
@EqualsAndHashCode(of = { "name", "age" })
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Employee {

	@Id
	@javax.persistence.Id
	private Long id;

	@NonNull
	private String name;

	@Column(nullable = true)
	private int age;

}
