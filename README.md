
# Britransfer

---
## Overview

---
This project is a simplified representation of banking & wire transfer web application
engulfing the basic features that an average user might consider necessary in a personal financial management interface.<br><br>
The features mentioned in the prelude will entail registering to the application, opening personal bank account, the abilities to make deposits, withdrawals, and wire-transfers
within the system to other accounts.<br>


---

## Tech Requirements



As the application utilizes Spring Boot, some requirements must be present on your system. <br>
- [JAVA](https://java.com/en/download/help/download_options.html#windows)
- [MAVEN](https://maven.apache.org/install.html)
- [REACT](https://www.youtube.com/watch?v=IbWXHfz91_Y&t=201s)
- [PSQL](https://www.postgresql.org/download/)
- [Postman](https://www.postman.com/downloads/)


The application's back-end is built with spring boot, implementing the following features (if unfamiliar, interested or both don't hesitate to check out the documentations):
<br> - [Official Apache Maven Documentation](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/htmlsingle/)
<br> - [Spring](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
<br> - [Spring Security](https://docs.spring.io/spring-security/reference/)
<br> - [Spring JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

---
## Setting up the project

Make sure that the requirements listed above are fulfilled on your system. After that, you should clone the repository, and open it with your favourite IDE (the creator advises [Jetbrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)).
After opening the project, navigate to the *<b>properties.yml*</b> file in the repository and fill out the missing blanks with your Psql username and password (additionally it is advised to set up / sync your IDE and database management application).
<br>
After this stage the application is ready to run. For more details and use case explanation follow the <b>*How To Use*</b> section below.

---
## How To Use

After setting up the requirements, start the project. The next step involves Postman, launch the application and follow the listed steps below (as for additional help, and faster testcases before doing so, open the testCaseUtil.txt file in the repository).<br>
Required methods and endpoints are contained within the document. Registrations are necessary because the application auto generates bank account numbers on the occasion of user registry. These account numbers can be obtained from the database.

Happy testing!

---
## Current state of the project

The application is currently in the requested state of being an MVP (minimum valuable product). This is the 0.1 iteration of the app, the first iteration (as envisioned) would entail a full registry service, with email validation (that is the purpose of the token), the refactoration of bank account generation method, to evade the "Primitive obsession" code smell early on. It would also entail transfer validation via the same email-service singleton pattern.<br>
The iterations to come would present features as:
- fully functioning front end to the project
- multiple accounts with different currency types
- external API calls for exchange management between currencies (as on accounts as on transfer occesions)
- later on the introduction of bank management system with private database and statistic calls (like: how much money does the app handle at a given time, daily transfers etc.)
- the previous feature will require the full implementation of different authentications
- more detailed and fully secure security implementations, JWT auth.
- a fully joined table system in a backup database
- a payment and card transaction validation system
- and a fully comprehensive and working microservice-architecture with API Gateways (if the package structure did not make it obvious)

---

## Final Toughts...

---

This project idea was brought to you by Tradebrite Ltd. bestowing the specifications on me to deliver the most of it I can at my current level of experties.<br>
As relevant information was not designated in the project description (test coverage, test types, required SQL dialect, preferred front end technology etc.) the state of the repository at the time of creating the README represents my interpretation of MVP.