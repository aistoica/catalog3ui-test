package com.endava.catalog.data;

import com.endava.catalog.models.Teacher;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;

public class DataGenerator {

	private Faker faker = new Faker();

	public Teacher getNewTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName() );
		teacher.setLastName( faker.name().lastName() );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( faker.number().numberBetween( 1, 10000000 ) );
		teacher.setBirthDate( faker.date().birthday().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate() );

		return teacher;
	}
}
