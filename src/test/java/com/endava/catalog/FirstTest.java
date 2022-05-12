package com.endava.catalog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.catalog.data.DataGenerator;
import com.endava.catalog.models.Teacher;
import com.endava.catalog.pageObjects.AddTeacherPage;
import com.endava.catalog.pageObjects.TeacherListPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class FirstTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private DataGenerator dataGenerator = new DataGenerator();

	@BeforeEach
	public void setUp() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.get( "http://localhost:4200" );
		driver.manage().window().maximize();
		wait = new WebDriverWait( driver, Duration.ofSeconds( 10 ) );
	}

	@AfterEach
	public void tearDown() throws InterruptedException {
		//		Thread.sleep( 5000 );
		driver.close();
	}

	@Test
	public void shouldOpenPage() throws InterruptedException {
		Teacher teacher = dataGenerator.getNewTeacher();

		TeacherListPage teacherListPage = new TeacherListPage( driver );
		final Teacher actualTeacher = teacherListPage.goToAddTeacherPage()
				.addValidTeacher( teacher )
				.getTeacherDetailsFromRow( -1 );

		assertThat( actualTeacher, is( teacher ) );
	}

	@Test
	public void shouldFailToAddTeacherGivenFirstNameEmpty() {
		Teacher teacher = dataGenerator.getNewTeacher();
		teacher.setFirstName( "" );
		teacher.setLastName( "" );

		TeacherListPage teacherListPage = new TeacherListPage( driver );
		AddTeacherPage addTeacherPage = teacherListPage.goToAddTeacherPage();
		addTeacherPage.addTeacher( teacher );
		final List<String> allInvalidFields = addTeacherPage.getAllInvalidFields();

		assertThat( allInvalidFields, containsInAnyOrder( "firstName", "lastName" ) );
	}

	@Test
	public void shouldEditTeacher() {
		Teacher teacher = dataGenerator.getNewTeacher();

		TeacherListPage teacherListPage = new TeacherListPage( driver );
		teacherListPage.goToAddTeacherPage()
				.addValidTeacher( dataGenerator.getNewTeacher() )
				.editTeacher( 1 )
				.addValidTeacher( teacher );

		final Teacher actualTeacher = teacherListPage.getTeacherDetailsFromRow( 1 );
		assertThat( actualTeacher, is( teacher ) );
	}

	@Test
	public void shouldDeleteTeacher() {
		TeacherListPage teacherListPage = new TeacherListPage( driver );
		final Teacher deletedTeacher = teacherListPage
				.goToAddTeacherPage()
				.addValidTeacher( dataGenerator.getNewTeacher() )
				.deleteTeacher( 1 );
		final Teacher actualTeacherOnRow = teacherListPage.getTeacherDetailsFromRow( 1 );

		assertThat( actualTeacherOnRow, not( deletedTeacher ) );

	}

}
