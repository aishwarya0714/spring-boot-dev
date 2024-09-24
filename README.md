# Spring Annotations

- most of the spring boot work is done with annotations (@Annotation)
- we can add multiple depencency/methods smiply by using annotaion

# Annotations

### 1. @SpringBootApplication
- it marks our class as a main class in spring boot
- basically our main class contains this annotation
- it is equivalent to the 3 annotaions
  1. @EnableAutoConfiguration - ALL Configuration done automatically
  2. @ComponentScan - scan all packages to find bean
  3. @Configuration = bean declare here

### 2. @Configuration
- declaring bean inside config class and create object of another calss and inject it anywhere

### 3. @Bean
- declare a method with class object
- spring container will create object of class by using bean

### 4. @Autowired
If you have multiple constructors in your class, then you might need to use the @Autowired annotation to indicate which constructor Spring should use for dependency injection.

### 5. @Component
- spring will handle object life cycle for class which has @Component
- it include 3 annotations
    1. @Controller (COmponent, MVC Controller)
    2. @Service (Buisness logic)
    3. @Repository ()

![image](https://github.com/user-attachments/assets/5e7ae196-296e-4321-81e4-6fe2e4bba674)


### 6. @ComponentScan
- if we have component in another package it will scan, for that we need to add @ComponentScan(basePackage ={"package-name"})

### 7. @Qualifier
- specify which object need to be return

### 8. @Lazy
- when we have multiple objects of a class and we want to call specific one.. will simply make another one lazy so, its object would not created

# Spring IOC Inversion of Control




# Hibernate/JPA CRUD

## Step 1: Go to starter- start.spring.io

Add following dependencies 
![image](https://github.com/user-attachments/assets/3c3e9a99-4e93-466d-8ec7-31aa26f9faa5)

 

## Step 2: Now add database configuration to the application.properties 
```
#add database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

## Step 3:  Create Entity package and inside that create your entity class which include all your db details like – table name, columns, etc
1.	define field
2.	define constructor
3.	define getter/setter
4.	define toString()

 ```
 package com.codewithash.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
//    define field

    //  set it primary
    @Id

    //      let mysql handle it
    //     auto - increment on that given value
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private int id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;


//    define constructor

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(){

    }

//    define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    define toString() Method

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
``` 
## Step 4: Create a DAO Interface and define method to save our table details
```
package com.codewithash.cruddemo.Dao;

import com.codewithash.cruddemo.entity.Student;

public interface StudentDao {

//    define a method
    void save(Student theStudent);
}

```

## Step 5: Now will create implementation for DAO interface

Create a class to implement StudentDao and will override our save method here 
1.	define field for entity manager
2.	inject entity manager using constructor injection
3.	implement save method
```
package com.codewithash.cruddemo.Dao;

import com.codewithash.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// Specialized annotation for repositories,
// supports component scanning,
//translate JDBC exceptions
@Repository
public class StudentDaoImpl implements StudentDao{

//    define field for entity manager

    private EntityManager entityManager;


//    inject entity manager using constructor injection

    @Autowired  
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //    implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
    entityManager.persist((theStudent));
    }
}

```

## Step 6 : Now finally we go to our main class and add the data we want to inject into data database

```
package com.codewithash.cruddemo;

import com.codewithash.cruddemo.Dao.StudentDao;
import com.codewithash.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
       SpringApplication.run(CruddemoApplication.class, args);
    }

//  CommandLineRunner is from Spring boot framework
//  and executed after the spring beans have been loaded
    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao){

//     java 8 LAMBDA EXPRESSION
       return runner -> {
          createStudent(studentDao);
       };
    }

    private void createStudent(StudentDao studentDao) {
//     create student object
       System.out.println("Creating student object...");
       Student tempStudent1 = new Student("aish","kawale","apkawale@google.com");
       Student tempStudent2 = new Student("Sudiksha","kawale","sudiksha@google.com");
       Student tempStudent3 = new Student("pappu","bahiya","pappu@google.com");

//     save student object
       System.out.println("Saving student object...");
       studentDao.save(tempStudent1);
       studentDao.save(tempStudent2);
       studentDao.save(tempStudent3);

//     display id of saved student
       System.out.println("saved student. Generated Id: " + tempStudent1.getId());
       System.out.println("saved student. Generated Id: " + tempStudent2.getId());
       System.out.println("saved student. Generated Id: " + tempStudent3.getId());
    }
}

```





