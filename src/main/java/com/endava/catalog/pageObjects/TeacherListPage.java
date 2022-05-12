package com.endava.catalog.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.endava.catalog.models.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeacherListPage {

	private WebDriver driver;

	private By addTeacherButton = By.cssSelector( "button.mat-primary" );
	private By teacherRows = By.cssSelector( "mat-list app-teacher-item" );
	private By editTeacherButton = By.cssSelector( ".teacher-field:nth-child(3)" );
	private By deleteTeacherButton = By.cssSelector( ".teacher-field:nth-child(4)" );
	private By firstNameField = By.cssSelector( ".teacher-field:nth-child(5)" );
	private By lastNameField = By.cssSelector( ".teacher-field:nth-child(6)" );
	private By cnpField = By.cssSelector( ".teacher-field:nth-child(7)" );
	private By salaryField = By.cssSelector( ".teacher-field:nth-child(8)" );
	private By dateOfBirthField = By.cssSelector( ".teacher-field:nth-child(9)" );

	public TeacherListPage( WebDriver driver ) {
		this.driver = driver;
	}

	public AddTeacherPage goToAddTeacherPage() {
		driver.findElement( addTeacherButton ).click();
		return new AddTeacherPage( driver );
	}

	public Teacher getTeacherDetailsFromRow( Integer rowNumber ) {
		final List<WebElement> teacherRowsElements = driver.findElements( teacherRows );
		if ( rowNumber > 0 ) {
			rowNumber--;
		} else if ( rowNumber < 0 ) {
			rowNumber = teacherRowsElements.size() + rowNumber;
		} else {
			throw new RuntimeException( "0 not supported for row number" );
		}

		String firstName = teacherRowsElements.get( rowNumber ).findElement( firstNameField ).getText();
		String lastName = teacherRowsElements.get( rowNumber ).findElement( lastNameField ).getText();
		String cnp = teacherRowsElements.get( rowNumber ).findElement( cnpField ).getText();
		String salary = teacherRowsElements.get( rowNumber ).findElement( salaryField ).getText();
		String dateOfBirth = teacherRowsElements.get( rowNumber ).findElement( dateOfBirthField ).getText();

		return new Teacher( firstName, lastName, cnp, Integer.parseInt( salary ),
				LocalDate.parse( dateOfBirth, DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ) );
	}

	public AddTeacherPage editTeacher( Integer rowNumber ) {
		final List<WebElement> teacherRowsElements = driver.findElements( teacherRows );
		if ( rowNumber > 0 ) {
			rowNumber--;
		} else if ( rowNumber < 0 ) {
			rowNumber = teacherRowsElements.size() + rowNumber;
		} else {
			throw new RuntimeException( "0 not supported for row number" );
		}

		teacherRowsElements.get( rowNumber ).findElement( editTeacherButton ).click();
		return new AddTeacherPage( driver );
	}

	public Teacher deleteTeacher( Integer rowNumber ) {
		Teacher deletedTeacher = getTeacherDetailsFromRow( rowNumber );
		final List<WebElement> teacherRowsElements = driver.findElements( teacherRows );
		if ( rowNumber > 0 ) {
			rowNumber--;
		} else if ( rowNumber < 0 ) {
			rowNumber = teacherRowsElements.size() + rowNumber;
		} else {
			throw new RuntimeException( "0 not supported for row number" );
		}

		teacherRowsElements.get( rowNumber ).findElement( deleteTeacherButton ).click();
		driver.navigate().refresh();
		return deletedTeacher;
	}
}
