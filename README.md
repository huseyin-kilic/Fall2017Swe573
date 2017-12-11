# TTEvent

This project aims to create a notification system for the upcoming events based on selected categories and locations. 

More details are available at [github pages](https://huseyin-kilic.github.io/Fall2017Swe573/).

[![Build Status](https://travis-ci.org/huseyin-kilic/Fall2017Swe573.svg?branch=master)](https://travis-ci.org/huseyin-kilic/Fall2017Swe573)

**Time Tracking**

* Estimated time for an issue will be specified within the corresponding issue description. 
* For tracking the actual time spent on issues, free version of [toggl](https://toggl.com) will be used.
* Once an issue is completed, actual time spent on the task will be retrieved from [toggl](https://toggl.com) and noted on the issue with a comment.

**Issue Management**

[Issue Repository](https://github.com/huseyin-kilic/Fall2017Swe573/issues) is used for tracking and monitoring research and development tasks, new features, enhancements and bug fixes. Labels have been created for classifying and filtering issues:
* research: investigation on both functional area (domain knowledge, user stories, public APIs) and technical area (languages, frameworks and tools to be used)  
* documentation: creation of requirement analysis document, project planning document, design diagrams and UI mock-ups
* requirement: elitication and documentation and validation of user needs
* design: transformation of user requirements into an accurate level of abstraction which will be used during the implementation phase
* backend: low level design and implementation tasks on server side of the application
* frontend: creation of the user interface module using the mock-ups and corresponding backend services
* test: unit, component and e2e testing tasks
* bug: issues created for tracking self detected & reported malfunctionings
* help-wanted: tasks for which consultancy of a stakeholder is needed


**Local Installation Guide**
* Clone or download project to an appropriate directory in your computer.

`git clone https://github.com/huseyin-kilic/Fall2017Swe573.git`
* Make sure JDK and Maven binaries are installed, and “JAVA_HOME” and “M2_HOME” are defined as environment variables. You can follow [this link](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) for detailed instructions.
* Open a command line interface and navigate to project base directory. 
* Build and install the project to your local maven repository using the folowing command. 

`mvn clean install`
* Run the project using `mvn spring-boot:run`. Default port is 8080, optionally use `mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=PORT_NUMBER'` by changing PORT_NUMBER to run on a different port.
* Open your browser and navigate to http://localhost:8080/profile to access to the application. 

**Deployment Guide**
* Create a new account on Google Cloud Platform.
* Create a new AppEngine server and open the cloud shell utility and clone project to the remote server.

`git clone https://github.com/huseyin-kilic/Fall2017Swe573.git`
* Initialize project to run in Europe West-1 Region.

`gcloud app create --region europe-west1`
* Build and install the project to your local maven repository using the command `mvn clean install`.
* Deploy application into AppEngine environment

`mvn appengine:deploy`
* More detailed information about deployment of a spring boot application to Google Cloud Platform can be found [here](https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/index.html).


**Contributors**

This is an individual project to be maintained by the repository owner with the consultancy of the [course instructor](https://github.com/uskudarli).
