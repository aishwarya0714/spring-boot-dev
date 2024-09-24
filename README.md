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



