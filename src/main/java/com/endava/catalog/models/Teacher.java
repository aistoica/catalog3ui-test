package com.endava.catalog.models;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher {

	private String firstName;
	private String lastName;
	private String cnp;
	private Integer salary;
	private LocalDate birthDate;

	public Teacher() {
	}

	public Teacher( String firstName, String lastName, String cnp, Integer salary, LocalDate birthDate ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.cnp = cnp;
		this.salary = salary;
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp( String cnp ) {
		this.cnp = cnp;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary( Integer salary ) {
		this.salary = salary;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate( LocalDate birthDate ) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o )
			return true;
		if ( o == null || getClass() != o.getClass() )
			return false;
		Teacher teacher = (Teacher) o;
		return Objects.equals( firstName, teacher.firstName ) &&
				Objects.equals( lastName, teacher.lastName ) &&
				Objects.equals( cnp, teacher.cnp ) &&
				Objects.equals( salary, teacher.salary ) &&
				Objects.equals( birthDate, teacher.birthDate );
	}

	@Override
	public int hashCode() {
		return Objects.hash( firstName, lastName, cnp, salary, birthDate );
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", cnp='" + cnp + '\'' +
				", salary=" + salary +
				", birthDate=" + birthDate +
				'}';
	}
}
