## Concept1
### 1. Object

*We implemented it as an application so that we can conveniently use the product rental service provided by the software department*

<img src="https://user-images.githubusercontent.com/43931412/206673657-e0820c60-70f2-4135-9df4-4b688c01de24.png" width="600" height="350"/>

### 2. Function
*This application consists of three main categories*

<img src="https://user-images.githubusercontent.com/43931412/206677639-046c3729-9e36-4778-9505-182b7df3bcc0.png" width="600" height="350"/>

### 3. Implementation

<img src="https://user-images.githubusercontent.com/43931412/206678487-7459b6a2-317c-4efc-af62-4024b4cde4b0.png" width="400" height="240"/>

<img src="https://user-images.githubusercontent.com/43931412/206678961-b1f359da-52a1-43ce-90f9-0d8c6e5583cb.png" width="600" height="350"/>

- We recorded the user's information on the server using phpmyAdmin, and also recorded the information for managing the item.

</br>

## Requirement Analysis - Functional
### 1. User Requirement definition
- The rental system shall shows the user whether the rental items can be rented, the number of items remaining, and where they can be rented.
- The rental system shall save the user's student information and rental information are automatically when a user applies for a rent.
- The rental system shall save the user's student information and rental information are automatically when a user applies for a return.
- The rental system shall shows to users about their rental records.

</br>

### 2. System Requirement specification
- The rental system shall provide user to be able to sign in and log in within the application.
- If the user clicks rental button, it shall be automatically store user's personal information, rental items, date and time into the server database.
- The number of borrowable items shall be automatically reduced along with rent request.
- The rental system shall read the user's borrowing history from the server database and display it in the application.
- The rental system shall read the current number of items remaining and where they can be rented from the server's database and display them in the application.

</br>

## Requirement Analysis - Non Functional
### 1. Usability
- Easy enough for users to use the app for the first time without instructions
- Users can easily check their rental history
- Users can easily check item status

</br>

### 2. Reliability
- Application smoothly send and receive data to server
- Entire system active well and high accuracy is provided
- Ensure that other users cannot access user's personal rental records 

</br>

### 3. Performance
- When user logs in, the account can linked within 1 Seconds
- User can check their rental history within 1 Seconds
- User can check whether item is borrowable and where they can rent it within 1 second
- When user request rental/return, it stores in server database within 1 seconds

</br>

## Requirement Analysis - User Scenario
### 1. User-Scenario

- User wants to borrow something.
- User can run the application and login first .
- Then user can see if the item user want to borrow is available and where user can borrow it from.
- Then user goes to the place where user will rent the item.
- User clicks the rental button, and shows it to the manager. 
- Then the manager will provides the goods to User.
- Users can check their rental records after renting a item, so they can know when to return the item.
- After using item, user go to rental place and click return button.
- Then return item to manager.

## System Modeling
### 1. Activity Diagram
- In the activity diagram, after launching the app, the login window appears, and it expresses what happens during the app, from login confirmation, membership registration, product inquiry, product reservation, and app termination.
### 2. Class Diagram
- The class diagram defines the classes of login, membership, reservation, return, commodity lookup, database, and server to be used in the system, and shows the relationship between these classes as a diagram.
### 3. Class Diagram
- The use case diagram shows the relationship with the server as a use case according to the user's position.

<img src="https://user-images.githubusercontent.com/43931412/206682794-5dd0414d-2346-4535-95c9-55b33bf3bd61.png" width="600" height="350"/>

<img src="https://user-images.githubusercontent.com/43931412/206682802-837256fc-1bd4-4438-b9ee-17c87a1082a5.png" width="600" height="350"/>

<img src="https://user-images.githubusercontent.com/43931412/206682809-e63a2835-62a1-4085-97ed-ef7457a354d9.png" width="600" height="350"/>

</br>

## Architecture Design
### 1. Client-Server Architecture
- The system has a client-server pattern that helps you manage user and reservation information.
- Multiple users can access the system at the same time, and DB server manages user information, reservation time, return time, and product information.
### 2. Repository Architecture
- Since many users use it at once, it is designed to consistently manage data with a DB server called phpMyAdmin in order to efficiently exchange data.
### 3. Model-View-Controller Pattern
- The system has MVC patterns for login and reservation functions. 
- The model receives reservation and return time information and updates it on the controller.
- In View, ListView displays the commodity name, reservation time, return time, and updated list.

<img src="https://user-images.githubusercontent.com/43931412/206682811-9fb8c0df-c4b7-42e1-add5-f23043fec080.png" width="600" height="350"/>

<img src="https://user-images.githubusercontent.com/43931412/206682813-e6bc472c-caca-4afb-b4de-57b4913acfbb.png" width="600" height="350"/>

<img src="https://user-images.githubusercontent.com/43931412/206682814-6242f228-3a1c-4118-ac63-a936680cf886.png" width="600" height="350"/>





</br>

### Demo Video

![image1](https://user-images.githubusercontent.com/43931412/146496636-f17b2e29-6f4e-4e15-8b28-b46ff1fcb9be.gif)

