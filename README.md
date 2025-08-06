Build tool : Maven
Test Tool : Selenium
Test Framework : Cucumber BDD with Page Object Model
Programming Language :Java
HTML Report : Cucumber HTML Report
POM - Project Object Model
Test Pattern - Cucumber BDD (Behaviour Driven Development)
POM is an XML file that contains information about the project and configuration details used by Maven to build the project.
Link to the report : To view the report , right click on the cucumber-reports.html reveal in finder and then right click the report and open with Google Chrome or any other supported browser.
To run the test : 
Prerequisite :Make the chromeDriver executable on any of the operating system , in my case it's mac so I hvae followed the below steps: 
chmod +x chromedriver -First command 
chmod +x hudl-login-tests/chromedriver -Second Command


Go to the terminal and navigate to the folder hudl-login-tests and run the below command replace the emaill and pwd with valid credentials.

Third Command - export HUDL_EMAIL="yourvalidemailaddress@gmail.com" && export HUDL_PASSWORD="yourvalidpassword" && mvn test on the terminal.


<img width="1625" height="917" alt="image" src="https://github.com/user-attachments/assets/851c0159-aeb4-4ede-99c6-7f4805bef03a" />
