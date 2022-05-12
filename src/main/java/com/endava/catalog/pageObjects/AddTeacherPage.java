package com.endava.catalog.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.catalog.models.Teacher;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AddTeacherPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By firstNameInput = By.cssSelector( "input[name='firstName']" );
	private By lastNameInput = By.cssSelector( "input[name='lastName']" );
	private By cnpInput = By.cssSelector( "input[name='cnp']" );
	private By salaryInput = By.cssSelector( "input[name='salary']" );
	private By dateOfBirthInput = By.cssSelector( "input[name='dateOfBirth']" );
	private By submitButton = By.cssSelector( "button[type='submit']" );
	private By invalidFields = By.cssSelector( "input.ng-invalid" );

	public AddTeacherPage( WebDriver driver ) {
		this.driver = driver;
		this.wait = new WebDriverWait( driver, Duration.ofSeconds( 10 ) );
	}

	public TeacherListPage addValidTeacher( Teacher teacher ) {
		addTeacher( teacher );
		wait.until( ExpectedConditions.alertIsPresent() );
		driver.switchTo().alert().accept();
		driver.navigate().refresh();

		return new TeacherListPage( driver );
	}

	public void addTeacher(Teacher teacher) {
//		Actions actions = new Actions( driver );
//		actions.moveToElement( driver.findElement( firstNameInput ) )
//				.keyDown( Keys.CONTROL )
//				.sendKeys( "A" )
//				.release()
//				.sendKeys( Keys.DELETE )
//				.sendKeys( teacher.getFirstName() )
//				.perform();

		driver.findElement( firstNameInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( firstNameInput ).sendKeys( teacher.getFirstName() );
		driver.findElement( lastNameInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( lastNameInput ).sendKeys( teacher.getLastName() );
		driver.findElement( cnpInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( cnpInput ).sendKeys( teacher.getCnp() );
		driver.findElement( salaryInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( salaryInput ).sendKeys( teacher.getSalary().toString() );
		driver.findElement( dateOfBirthInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( dateOfBirthInput )
				.sendKeys( teacher.getBirthDate().format( DateTimeFormatter.ofPattern( "MM/dd/yyyy" ) ) );
		driver.findElement( submitButton ).submit();
	}

	public List<String> getAllInvalidFields() {
		return driver.findElements( invalidFields )
				.stream().map( e -> e.getAttribute( "name" ) )
				.collect( Collectors.toList());
	}
}
