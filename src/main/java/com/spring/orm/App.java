package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

//		Student student = new Student(2324, "Ishaan Vadhan", "Pune");
//		int r = studentDao.insert(student);
//		System.out.println("Inserted student: " + r);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println();
			System.out.println("1. Add new student");
			System.out.println("2. Display all students");
			System.out.println("3. Display single student");
			System.out.println("4. Update student");
			System.out.println("5. Remove student");
			System.out.println("6. Exit");
			System.out.print("\nEnter your choice: ");

			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {
				case 1:
					System.out.print("Enter student id: ");
					int insId = Integer.parseInt(br.readLine());
					System.out.print("Enter student name: ");
					String insName = br.readLine();
					System.out.print("Enter student city: ");
					String insCity = br.readLine();

					Student s1 = new Student();
					s1.setId(insId);
					s1.setName(insName);
					s1.setCity(insCity);
					int r1 = studentDao.insert(s1);
					System.out.println("Inserted student: " + r1);

					break;
				case 2:
					List<Student> students = studentDao.getAll();
					for (Student s : students) {
						System.out.println(s);
					}

					break;
				case 3:
					System.out.print("Enter student id: ");
					int getId = Integer.parseInt(br.readLine());

					Student s2 = studentDao.get(getId);
					System.out.println(s2);

					break;
				case 4:
					System.out.print("Enter student id: ");
					int updId = Integer.parseInt(br.readLine());
					System.out.print("Enter student name: ");
					String updName = br.readLine();
					System.out.print("Enter student city: ");
					String updCity = br.readLine();

					Student s3 = new Student();
					s3.setId(updId);
					s3.setName(updName);
					s3.setCity(updCity);
					studentDao.update(s3);
					System.out.println("Student updated!");

					break;
				case 5:
					System.out.print("Enter student id: ");
					int delId = Integer.parseInt(br.readLine());

					studentDao.delete(delId);
					System.out.println("Student removed!");

					break;
				case 6:
					go = false;
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid option!");
			}
		}
		System.out.println("Thank you!");
	}
}
