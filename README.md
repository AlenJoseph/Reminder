# Reminder

A Java desktop Application with MySQL database for creating custom reminders.

### Prerequisites

1) Mysql
2) JAVA JDK 8

###  Getting Started(Installation) 

1) Import remainderdb.sql to the Mysql database, I used XAMPP as my Server.
2) In the file dbcon.java, change the server url to your configuration.
*  java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/remainderdb", "root", "");
3) If any error appear, check all the jar files in the Extra Jar folder, which is added to the project.


## Running the tests
1) After Configuring the system correctly, run the Home.java file.
2) It will take you to the Home page and at the same time, the Quartz scheduler's job thread start and runs in background.
3) There are two buttons on the Home page, one to Add Reminder and the other to View Reminder.
4) When you click on Add Reminder button, it will take you Add Reminder page where you can add reminder.
5) When you click on View Reminder button, it will take you View Reminder page where you can view, update and delete reminder
6) Note: Reminder is checked evey minute, so the reminder notification can be triggered at any second of the set time.




## Built With

* Netbeans 8.2
* Mysql Database
* Quartz Scheduler for running Job (Check database every 1 minute for reminder)




## Authors

* **Alen Joseph** - 


## Issue
 Audioplayer support is only upto java 1.8


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




