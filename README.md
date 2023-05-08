# MEDICAL CENTER API
(This is a preliminary demo of a Medical Management System made to use in a Medical Institution).

Medical Center API is an application developed under the REST API architecture criteria, which allows optimal integration with external servers and database like SQL. It is designed to be used mainly by the personal of a medical institution, like doctors, administrators, receptionists, pharmaceutics and patients.

## How to configure
1) Medical Center used SQL database, so required configure an instance and schema in MySQL with the name "medical_center_DB", this will be database saving routes.
2) Run the program to give permission to the controller routes to make changes.
3) Controller routes can be tested using POSTMAN or any other HTTP request and test programme (At the final of this chapter you can find a link with JSON raw information to facilitate test it).

## Specifications
With the correct use of MEDICAL CENTER API, you will be able to:
- Create Users: administrators, doctors, recepcionist, pharmacists and patient accounts.
- Use Medical Calculator and Score: these will be accompanied by their respective classifications, treatment suggestions and will be recorded in a "Medical Report".
- Create appointments with day, time and assigned doctor.
- Register insurance and benefits in the database that can later be applied to patient treatments.
- Add medications to the stock, prescribe to the patient and allow their delivery with the discount that corresponds to them according to their medical insurance.
- View data and appointments of patients.

## Permissions:
#### ADMINS permissions:
-	Create or delete another Admin Users.
-	Create or delete Doctors, Patients, Secretary & Pharmacy Users.
-	He can find All Users and find by ID to.
- Watch Insurance List and add new ones.

#### DOCTORS permissions:
-	Add new patients.
-	Find all and a specific patient by ID, Name or nin.
- Read, create and use the tables and scores of the system, which will be recorded in the Medical Reports.
- Create "Pharmacy Orders" to give to patients and to be received by pharmacists.

#### RECEPTIONIST permissions:
-	Create and give appointments.
-	Find all and a specific patient by ID, Name or nin.

#### PHARMACY permissions:
-	Add Medications in Store / Manage Stock.
- Check and Delivery Medication to patients from Pharmacy Orders create for Doctors.

#### PATIENTS permissions:
-	Find their appointments.

## Main technologies used:
- Java / Spring / SpringBoot : to write the API code by using the libraries provided by both frameworks.
- JPA: to map and relate the API with the database.
- MySQL: to review and administrate the database.
- Unit testing : // (In future updates) to test the functionality of all repository routes and controllers. //


## Information for Devs:
- The API code is written with the naming conventions, among others, we use PascalCase for the name of Models, and their homonyms in SQL with snake_case. It's suggested for future modifications follows the same convention to keep clean the project.
- The root model of the repository, controllers, models and services were correctly organized in different packages, giving the possibility to future updates and allow growth of the project, which will facilitate the development of the API architecture.
- Each method that calls a setter is accompanied by a comment with details about your implementation.

## App done by:
- [Emmanuel Escobar](https://github.com/Emmascobar)
