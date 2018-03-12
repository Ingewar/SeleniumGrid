## Functional tests framework

This is a template of functional test automation framework based on Java and Selenium 3.* with TestNG and Grid. Its include all necessary libraries in pom.xml file and in /main/resources folder. You can simple add new test in created tests files or create your own.

### Getting Started

**Prerequisites**

To run this suite from command line you should install Maven first on your local machine.

**Running tests**

To start suite from CLI run following command: `mvn clean test`.
You could also specify OS and browser via `-D` flag. For example: `mvn clean test -Dos="Mac" -Dbrowser="chrome"`.
Also you can run tests local by adding flag `-DuseGrid=false`.
To run specific test add `-D` flag with class and test name like this: `mvn clean -Dtest=GoogleTests#openGoogleStartPage test -Dos=mac -Dbrowser=chrome -DuseGrid=false`.

**Build With**

 - Maven - Dependency Management and project builder
 - Selenium WebDriver - basic testing tool to interact with browsers
 - TestNG - testing framework to group, run tests and creating reports

**Author**

 - Kovtun Ihor - AQA Engineer
