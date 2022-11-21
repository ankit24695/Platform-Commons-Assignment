# Platform-Commons
Student Management System Backend Application

# About
This application manages most of the backend services which are required by Education Institution. It manages the registration of student to an Institution and their allocation to different courses by an admin. Most of the serive are limited to admin and some are limited to student.

# How To Use
Step-1) Clone the source code to new folder in your system. Import the maven project in your IDE.

Step-2) In MySQL command line, create a database with name studentdb and use that database.

Step-3) Run the application. All the required table will be created in the studentdb database.

Step-4) Use this url:- http://localhost:8888/swagger-ui/ once the server is running to use all the services.


# How to Proceed

Step-1) Create the admin using the Post method (/registerAdmin) in AdminController.

Step-2) Admin need to login to use remaining service of AdminController. Use the Post method(/adminLogin) for admin login.

Step-3) After logging in as admin, a message will be received in response which contains the uuid. Use that uuid as a key to use remaining services of AdminController.

Step-4) Student need to login to use the services of StudentController. Use the Post method(/studentLogin) for student login. 

Step-5) After logging in as student, a message will be received in response which contains the uuid. Use that uuid as a key to use remaining services of StudentController.

Note:- Enter DOB in "yyyy-mm-dd" format.

# Video Tutorial

Link:- https://drive.google.com/file/d/17CzrDC7a5nEPIhV-XeUeLXV63Nl45-dl/view?usp=sharing

# Tech Stack

Java, Hibernate, SpringBoot, MySQL



