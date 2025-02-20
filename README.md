THE UNIVERSITY OF ECONOMICS AND FINANCE
 INFORMATION TECHNOLOGY DEPARTMENT






FINAL REPORT

[241.ITE1221E.A01E] 
Mobile Device Programming



Project:  Customer Management

						Lecturer : Le Viet Linh


GROUP 13

Nguyễn Ngọc Thịnh – 215101362 	
Hồ Tấn Trí –  215201089
 

 



INTRODUCTION





The Customer Management System is an Android application designed to simplify the management and organization of customer data for small to medium-sized businesses. With an intuitive interface, users can easily add, view, update, and delete customer information, enhancing efficiency and accessibility.
This project, developed by a team of two members, includes several key features:
-	Customer Data Management: Effortlessly input, edit, and delete customer records, including details such as name, age, address, salary, and logical deletion via a DELETE_DATE field.
-	Salary Adjustment and Notifications: Easily update salaries with automated notifications detailing the changes and the time of the update.
-	Advanced Search: Quickly find customers by name or address, streamlining access to specific records.
-	Settings Customization: A dedicated settings menu allows users to toggle the visibility of fields like age, address, and salary, as well as control background music playback.
-	Responsive Layout: The application seamlessly adapts to both portrait and landscape orientations, ensuring a user-friendly experience across various devices.
-	Music Control: Enjoy continuous background music with options to play or pause based on user preference.
I.	Project Overview
1.	Project Introduction
The Customer Management System is an Android-based application designed to simplify the process of managing customer data. This project aims to assist small to medium-sized businesses in effectively handling their customer information. The system features a user-friendly interface that allows users to efficiently add, update, delete, and search for customer records.
1.1	 Customer Information Management: 
-	Users can input, edit, and store customer details such as ID, name, age, address, salary, and deletion status (logical deletion using DELETE_DATE).
1.2	 Salary Management: 
-	The application provides a feature to increase customer salary, and it generates notifications with detailed information about the salary adjustment and the timestamp of the update. 
1.3	 Logical Deletion: 
-	Instead of physically deleting records, customers can be marked as "deleted" by setting a DELETE_DATE, ensuring data integrity and history preservation. 
1.4	 Customizable Display Settings: 
-	The system offers customizable display options, allowing users to toggle the visibility of specific fields (age, address, salary) to suit their needs. 
1.5	 Search Functionality: 
- The app allows users to search for customer records based on name or address, improving efficiency in locating specific information.
1.6	 Background Music:
-  A continuous background music feature is included, with an option to enable or disable it in the app settings. The music persists across app sessions based on the user’s preference.
1.7	 Responsive Design
-  The layout of the application is adaptable, providing an optimal user experience in both portrait and landscape modes.

The project development followed a structured approach, utilizing Figma for designing user interfaces and SQLite for data storage. The team adopted best practices in Android development to ensure that the application is robust, scalable, and user-friendly.

This project serves as a comprehensive solution for businesses aiming to enhance their customer management processes by offering an intuitive platform for handling customer data, while also providing flexibility and customization based on the business's specific needs.
2.	Application Scope
The Customer Management System application is designed to streamline and improve the management of customer data for businesses. The scope of the application covers the following areas :  
2.1	   Customer Data Management : 
-	The app enables users to create, read, update, and logically delete customer records. Each customer record contains key information such as the customer’s name, age, address, and salary.
-	Users can navigate through customer records, view detailed information for each customer, and easily manage multiple records. 
2.2	   Logical Deletion and Data Integrity : 
-	Instead of permanently deleting customer records, the system uses a "logical deletion" mechanism by setting a DELETE_DATE. This ensures that customer data is retained for historical or auditing purposes while still being marked as "deleted" within the system.
-	This method enhances data integrity, providing businesses with an option to restore or audit customer information later if needed. 
2.3	   Salary Management: 
-	Users can increase customer salaries through a simple interface. After inputting the amount, the salary is updated, and a notification is triggered. The notification includes the amount increased and the time of the update, ensuring transparency in salary management. 
2.4	   Customizable Display Settings: 
-	The application includes settings where users can toggle the visibility of sensitive customer information, such as age, salary, and address. This feature allows businesses to customize the data view according to specific requirements or preferences.
2.5	 Search and Filtering Capabilities:
-	The app provides powerful search functionality, enabling users to quickly find customer records based on names or addresses. This makes it easier for businesses to locate and manage specific customers, even in large datasets.
2.6	 Background Music Feature:
-	The application includes an optional background music feature that can be turned on or off based on user preferences. Music plays continuously across sessions until explicitly stopped, offering a personalized experience for users.
2.7	 Multi-Screen Layout and Navigation:
-	The system features multiple screens, including Home, Search, Customer Details, and Settings. The app uses intuitive navigation to switch between these screens, providing a smooth user experience.
-	The app's layout is also responsive, meaning it adjusts automatically to both portrait and landscape orientations, offering flexibility in how the app is used on different devices.
2.8	 Task Management and Scheduling:
-	The application includes task scheduling capabilities, allowing users to track and manage various tasks related to customer management. This helps businesses keep organized and ensures timely updates to customer records.
II.	Requirements Analysis
The Customer Management System is developed to meet specific functional and non-functional requirements that ensure the smooth operation and usability of the application. These requirements are divided into different categories:
1.	Functional Requirements
These requirements define the core functionalities that the application must provide to users.
1.1	    Customer Data Management: 
-	The system must allow users to add new customer records with fields such as ID, Name, Age, Address, and Salary.
-	The system must allow users to update existing customer information.
-	The system must allow users to view detailed information of each customer, with navigation options to move between previous, next, first, and last customers.
-	The system must allow users to logically delete customer records by setting a DELETE_DATE, without permanently removing the record.
1.2	    Salary Management: 
-	The system must provide an interface for users to increase customer salaries by inputting an increment amount.
-	A notification must be generated that logs the salary increase amount and timestamp when the update occurs.
1.3	    Search Functionality: 
-	The system must allow users to search for customer records based on Name or Address.
1.4	    Settings and Customization:
-	The system must provide switch buttons in the settings menu to allow users to hide or show customer details, such as Age, Address, and Salary, when viewing or updating records.
-	The system must include an option to enable or disable background music, with the ability to continue playing across sessions based on user preference.
1.5	    Multi-Screen Navigation: 
-	The application must feature intuitive navigation between multiple screens, including Home, Search, Customer Details, and Settings.
-	The layout must adjust automatically for both portrait and landscape modes.
1.6	    Task Management and Scheduling:
The system must include functionality for task management, where users can organize and schedule tasks related to customer management, such as updates, salary increases, or record audits.
2.	 Non - Functional Requirements
These requirements define the performance and quality attributes of the system.
1.1	   Usability:
-	The system must provide a user-friendly interface that can be easily understood and used by non-technical users.
-	Navigation and interaction with the app should be smooth and intuitive, with clear labels and minimal complexity.
1.2	   Performance:
-	The system must be responsive, ensuring fast access to customer data, updates, and search results.
-	The app must efficiently handle a large number of customer records without significant performance degradation.
1.3	   Reliability:
-	The system must ensure data integrity, particularly with the logical deletion feature. Customer records marked for deletion should not be physically removed, ensuring data is retained for auditing purposes.
-	The notification system must reliably generate and display notifications related to salary increases.
1.4	   Compatibility:
-	The application must run on Android devices with versions API level 21 and above.
-	The system must be compatible with various screen sizes and orientations to provide a seamless experience on both phones and tablets.
1.5	   Security:
-	The system must securely store customer information in a local database and ensure the confidentiality of sensitive data.
-	The app should include mechanisms to protect against unauthorized access to sensitive information such as salary and age.
1.6	   Maintainability:
-	The system must be designed in a modular way, allowing for easy updates and modifications, particularly in the areas of UI, data handling, and settings.
1.7	   Scalability:
-	The system must be scalable, allowing future enhancements to handle more customer records, additional features, or integration with external systems.
III.	System Design
1.	Overall Architecture
The Customer Management System is designed using a modular and scalable architecture to ensure ease of maintenance, flexibility, and performance. The system design is divided into multiple components that collectively handle data management, user interactions, and system services. The architecture follows the Model-View-Controller (MVC) design pattern, which separates the business logic, user interface, and data layers for clear and manageable development :
1.8	 System Architecture.
-	Presentation Layer (View): This layer is responsible for displaying the user interface to interact with the system. It includes all the GUI components for adding, updating, searching, and viewing customer data, as well as settings and notifications. Layouts are designed to adapt to both portrait and landscape orientations.
-	Business Logic Layer (Controller): This layer contains the application logic, handling the interactions between the view and the data layers. The controllers are responsible for processing user inputs, validating data, updating the database, and managing communication between the various UI components and the underlying data.
-	Data Layer (Model): This layer is responsible for managing customer data and ensuring its persistence. It includes SQLite database tables for storing customer records, salary information, and logical deletion timestamps. The model layer also handles data retrieval, insertion, and updating as per the system requirements.
2.	Database Design
The system uses an SQLite database for storing customer data locally on the device. The database is designed to efficiently store and retrieve customer records, ensuring quick access for display, updates, and searches :
Customer Table: This table stores all customer-related data, with fields that include:
-	ID: Unique identifier for each customer.
-	Name: The full name of the customer.
-	Age: The customer’s age.
-	Address: The customer’s address.
-	Salary: The current salary of the customer.
-	DELETE_DATE: A timestamp for logical deletion, marking when a customer record is flagged for deletion.
Customer Table Schema:
Field	Type	Description
ID	INTEGER	Primary key, auto-incremented
Name	TEXT	Full name of the customer
Age	INTEGER	Customer’s age
Address	TEXT	Customer’s address
Salary	REAL	Customer’s salary
DELETE_DATE	TEXT	Logical deletion timestamp
-	Notification Log Table: This table logs salary updates, storing information about each salary increment, including the amount, timestamp, and customer ID.
Notification Log Table Schema:
Field	Type	Description
ID	INTEGER	Primary key, auto-incremented
Customer_ID	INTEGER	Foreign key referencing customer
Amount	REAL	Salary increment amount
Timestamp	TEXT	Date and time of the increment

3.	User Interface Design
The UI is designed with simplicity and ease of navigation in mind, allowing users to quickly perform tasks related to customer management.
-	Home Screen: Displays a list of all customers with options to view, edit, search, and delete (logical) customer data.
-	Customer Details Screen: Allows users to view the details of a specific customer, including options to update the information, navigate between customers (previous/next), and hide or show specific details like age, address, or salary using the settings.
-	Add/Update Customer Screen: Provides forms for inputting or updating customer data. This screen includes validation to ensure accurate data entry.
-	Settings Screen: Contains switch buttons for toggling the visibility of customer details (age, address, salary), as well as an option to play or stop background music. Settings are stored using SharedPreferences to persist user preferences between sessions..
4.	Application Logic
The business logic is implemented in the Controller layer, which manages the interactions between the user inputs, the UI, and the database. Key components of the business logic include:
-	Customer Management: Handles CRUD (Create, Read, Update, Delete) operations on the customer database. Logical deletions are handled by updating the DELETE_DATE field, while salary increases are logged in the notification table.
-	Salary Management: Provides the logic to input and process salary increments, updating the customer’s salary in the database and generating a notification with the increment details.
-	Search Functionality: Allows users to search customer records based on the Name or Address fields. This logic is optimized for performance, ensuring that search results are returned quickly even with a large dataset.
-	Settings Management: Handles the saving and retrieval of user preferences using SharedPreferences. These preferences control the visibility of certain customer details and the background music feature, ensuring a personalized experience for each user.
-	Background Music Service: A background service that plays music based on user preferences, continuing even when the app is minimized. This service is managed using Android’s service framework and responds to changes in the settings..
5.	Layout Adaptability
The app supports both portrait and landscape modes, ensuring that the layout adapts dynamically when the device orientation changes. This is achieved using Constraint Layout and Relative Layout, which adjust component positioning and size based on the screen orientation.
6.	Notifications
The system uses Android’s Notification Manager to display notifications when a customer’s salary is increased. Notifications include details about the amount of the increment and the time the update occurred, providing a clear record for users.


IV.	Application Functions
The Customer Management Application includes a variety of functions to support the full management lifecycle of customer data. These functions are designed to enhance user experience, providing both ease of use and powerful features for managing customer information. The application integrates functionalities that allow users to input, view, update, search, and logically delete customer records. It also offers a settings menu for customizing the display of customer details and controlling the background music.
a)	Customer Data Management
1.	Add Customer: This function allows users to input new customer data into the system. The input form includes fields such as Name, Age, Address, and Salary. Validation ensures that the data entered is correct before it is saved into the SQLite database.
2.	View Customer Details: Users can view detailed information for each customer, including Name, Age, Address, and Salary. Navigation controls are available to move between customers (e.g., previous, next, first, last), ensuring quick access to different records.
3.	Update Customer: This function provides users with the ability to update customer details. The app retrieves the existing information, allowing users to modify and save it back into the database. Changes are automatically reflected in the UI.
4.	Logical Deletion of Customers: Users can logically delete a customer by selecting them via a checkbox and performing the delete action. Instead of removing the record completely, the system marks the customer with a DELETE_DATE (logical deletion) for future reference. This allows recovery or auditing of deleted records.
b)	Salary Management
-	Increase Salary: The application allows users to input an amount to increase a customer's salary. After entering the increment value (e.g., 500) and pressing the "Salary Increase" button, the selected customer's salary is updated in the database.
-	Salary Notification: Every time the salary is increased, the system generates a notification with the message: “Already increased salary with amount <amount> at <time>,” where the time is displayed in the format DD/MM/YYYY. This notification helps keep track of salary changes for customers.
c)	Search Functionality
-	Search by Name or Address: The application allows users to search for customers based on either their Name or Address. Users can choose the search criterion and input the relevant information to quickly filter the list of customers. The search function dynamically updates the list of customers shown on the screen to match the search results.
d)	Settings
-	Toggle Visibility of Customer Details: The settings menu includes switch buttons that allow users to show or hide specific customer details, including: Age, Address, Salary.
-	When a user toggles these options, the display of customer information is updated accordingly throughout the app. These preferences are saved using Shared Preferences and persist between sessions.
-	Background Music Control: The settings menu also includes a switch button to control the background music in the app. Users can enable or disable continuous background music that plays while the app is in use. The system remembers the user's preference for playing music, even when the app is reopened..
e)	Notifications
-	Salary Notifications: Whenever the user increases a customer’s salary, a notification is generated and displayed in the Android notification bar. This notification includes details such as the amount of salary increased and the timestamp of the update. Notifications are implemented using Android’s Notification Manager and ensure that salary changes are properly recorded and communicated to the user.
f)	Orientation Adaptability
-	Responsive Layouts: The application is designed to adapt to both portrait and landscape orientations. The layouts adjust automatically to fit different screen sizes and orientations, ensuring an optimal user experience on any device. This is achieved through the use of Android’s Constraint Layout and Relative Layout for flexible and responsive designs.
g)	Task Management
-	Task Scheduling: The application includes a basic task management system that allows users to keep track of scheduled tasks related to customer management. This feature helps users organize their work, ensuring that customer records are maintained and updated efficiently.
V.	Use Case Diagram
1.	
Admin (Actor):
Add Customer: Allows the admin to input and save customer data such as ID, Name, Age, Address, and Salary.
Update Customer: Admin can update existing customer details like Age, Address, or Salary.
Delete Customer: Perform logical deletion by setting a DELETE_DATE without physically removing the data.
View Customer Details: Admin can view the details of individual customers and navigate between records (Previous, Next, First, Last).
Add Customer: Allows the admin to input and save customer data such as ID, Name, Age, Address, and Salary.
Update Customer: Admin can update existing customer details like Age, Address, or Salary.

2.	
Admin/User (Actor):

Search Customer: Search for customers based on criteria such as Name or Address.
Salary Increase: Input the amount of salary increase, apply the update to the customer record, and trigger a notification indicating the increase along with a timestamp.
3.	

Settings (Actor):

Manage Settings:
Toggle Information Display: Hide or Show fields such as Age, Address, and Salary when displaying customer details or adding/updating customer information.

Play/Stop Background Music: Option to play continuous background music or stop it based on app state.


1)	Add Customer Test Case
Test Case ID: TC001
Title: Add New Customer
Description: Verify the functionality of adding a new customer with all required information.
Pre-conditions: The "Add Customer" screen should display the correct interface.
Test Steps:
Open the application and navigate to the "Add Customer" screen.
Enter customer details: ID, Name, Age, Address, Salary.
Click the "Save" or "Add" button.
Expected Result:
The new customer is added to the database and displayed on the customer list screen.
A Snack bar message appears: "Customer added successfully."




 
Picture  1:  Page  Home
 
Picture 2: Page Add Customer



 
Picture 3: Fill In Information

 
		Picture 4: Customer added to list
 
Picture 5: Show notification screen


2)	 Update Customer Information Test Case
Test Case ID: TC002
Title: Update Customer Information
Description: Verify the functionality of updating an existing customer’s information.
Pre-conditions: The customer already exists in the database.
Test Steps:
Search and select a customer from the list.
Modify the customer information (e.g., name or address).
Click the "Update" button.
Expected Result:
The customer information is updated in the database and displayed correctly.
A Snack bar message appears: "Customer updated successfully."
 
Picture 1: Page Home 


 
Picture 2: Page Update

 
Picture 3: Edit new customer in list

 
Picture 4: Show notification screen

3)	 Delete Customer (Logical Deletion) Test Case
Test Case ID: TC003
Title: Delete Customer (Logical Deletion)
Description: Verify the functionality of logically deleting a customer.
Pre-conditions: The customer exists in the database.
Test Steps:
Select a customer from the list.
Click on the "Delete" option.
Confirm the deletion.
Expected Result:
The DELETE_DATE is set in the database instead of a physical deletion.
The customer is marked as "Deleted" but can be restored later.
 
Picture 1: Home Page

 
Picture 2: Check the Check Ports

 
Picture 3: Show notification screen

 
Picture 4: Show notification screen


4)	Search Customer by Name or Address Test Case
Test Case ID: TC004
Title: Search Customer by Name or Address
Description: Verify the functionality of searching customers by name or address.
Pre-conditions: The database contains customers.
Test Steps:
Go to the "Search Customer" screen.
Enter a name or address in the search bar.
Click the "Search" button.
Expected Result:
The search results display the correct customers matching the entered information.
The search completes within 2 seconds.  
Picture 1: Home Page
 
Picture 2: Search Name

 
Picture 3:  Search Address

5)	Salary Increase Notification Test Case
Test Case ID: TC005
Title: Salary Increase and Notification
Description: Verify the functionality of increasing a customer’s salary and generating a notification.
Pre-conditions: The customer exists in the database.
Test Steps:
Select a customer whose salary needs to be increased.
Enter the new salary amount.
Click the "Update Salary" button.
Expected Result:
The customer's salary is updated in the database.
A notification is generated, showing the increased amount and update time.
 
Picture 1: Home Page


 
Picture 2: Page Detail Customer


 
Picture 3: Enter the increase amount


 
Picture 4: Show notification screen


6)	 Customizable Display Settings Test Case
Test Case ID: TC006
Title: Customizable Display Settings
Description: Verify the functionality of hiding/showing customer information such as age, address, and salary.
Pre-conditions: There is at least one customer in the database.
Test Steps:
Go to the "Settings" screen.
Toggle the display settings for age, address, or salary.
Return to the customer detail screen.
Expected Result:
The customer information is shown or hidden based on the selected settings.
Changes are applied immediately.
 
Picture 1: Home Page


 
Picture 2: Click Settings








 
Picture 3: Information you want to hide
VI.	Interface ( GUI )
The Customer Management Application interface is designed to ensure user-friendly navigation and efficient data handling. The interface is structured into several key screens, each dedicated to a specific functionality of the application. The design focuses on clarity, ease of use, and providing all necessary tools to the user with minimal effort.

1.	Home Screen
-	The Home Screen serves as the main dashboard for the user, providing an overview of the available options and navigation to other parts of the application.
-	Key navigation options include:
	Add Customer
	Search Customer
	View Customer List
	Settings
	Salary Increase
-	A Bottom Navigation Bar is present for quickly accessing the home and settings screens.
2.	 Add Customer Screen
-	This screen allows the user to input new customer data, including fields for:
	Customer Name (Text Input)
	Age (Number Input)
	Address (Text Input)
	Salary (Number Input)
-	A Save Button is present to submit the form and store the new customer data in the database.
3.	 View Customer Details Screen
-	Displays all relevant details of a selected customer.
-	Includes options to navigate between records:
	First, Previous, Next, and Last buttons.
-	A button for Updating Customer details is also provided, leading to the edit screen.
4.	 Update Customer Screen
-	Similar to the Add Customer screen but pre-populated with the current customer data for editing.
-	Once the user modifies the information, they can hit the Update Button to save the changes.
5.	 Search Customer Screen
-	Provides a search bar allowing the user to search by Name or Address.
-	Results are displayed in a list format, showing basic customer information.
-	The user can click on any customer from the list to view full details.
6.	 Salary Increase Screen
-	This screen allows the user to input an amount to increase the salary of a specific customer.
-	After applying the salary increase, a Notification is generated, informing the user of the amount added and the timestamp.
7.	 Settings Screen
-	The settings screen provides toggle switches to hide or show the following fields when displaying or editing customer information:
	Age
	Address
	Salary
-	A Music Switch is available to enable or disable background music, which plays continuously unless the user opens another app.
8.	Responsive Layout
-	The app supports both horizontal and vertical orientations, allowing users to switch seamlessly between landscape and portrait modes without affecting the usability of the interface.
-	The interface design ensures a smooth workflow, enabling users to perform actions quickly and efficiently. The focus on clarity, navigation, and accessibility helps in managing customer data effectively while offering essential customization options in the settings.


VII.	Application Features Development and Deployment.
1.	Introductions
In this section, we will present the key features of the customer management application, as well as the development and deployment processes for these features. The application is built using Java for the Android platform and follows the MVVM (Model-View-ViewModel) architecture to ensure maintainability and extensibility.
1.	Key Features:
a)	Customer Management:
	Add Customer: Users can add new customer information through a simple user interface.
-	Search Customers: The search feature allows users to easily find customers by name or address. The search Customers method in Main Activity handles this through Customer View Model.
b)	User Interface:
-	Navigation Drawer and Bottom Navigation: The application uses a Navigation Drawer for main functionalities like Home, Info, and Settings, along with Bottom Navigation for quick access to frequently used features.
-	Snack bar Feedback: When users click the "Add Customer" button, a Snack bar will display a message indicating that the action has been performed.
c)	Background Music Management:
-	Play Background Music: The application has the ability to play background music through Background Music Service. Users can toggle this music on or off via a Switch, and this state is stored in Shared Preferences.
d)	Customizable Settings:
-	Hide/Show Information: Users can customize the display of customer information (such as age, address, salary) using Switches in the settings interface. These changes will be updated immediately and send notifications to the UI via a Broadcast Receiver.
2.	Development
-	The development process includes the following steps:

	Architectural Design: Using the MVVM model to separate business logic and user interface, making maintenance easier.
	User Interface Implementation: Utilizing Fragments and ViewModels to create screens such as InfoFragment, ReflowFragment, and SettingsFragment, ensuring a smooth and intuitive user experience.
	Data Management: Using SharedPreferences to store user settings and SQLite to manage customer data. The code in CustomerViewModel handles loading and processing data from the database.
3.	Deployment
-	Testing and Deployment: The application underwent thorough testing to ensure all features work as expected. Once tested, the application will be deployed on the Google Play Store for users to download and use.

-	Updates and Maintenance: After deployment, the application will be regularly updated based on user feedback and new requirements. The use of the MVVM architecture will make it easier to add new features or change existing ones.
4.	Conclusion
-	The features of the customer management application not only help users manage customer information easily but also provide a good user experience through intuitive interfaces and personalization options. The development of the application followed a rigorous process from design to deployment, ensuring that the final product meets user needs.
VIII.	Results and Evaluation
This section presents the results obtained from the development and testing of the customer management application, as well as an evaluation of its performance, usability, and overall effectiveness.
1.	Results
1.1	Functionality Testing: 
-	All key features of the application, including customer addition, searching, and settings management, have been implemented successfully.
-	The application performs well across various devices, ensuring consistent functionality. The search Customers feature provides accurate and fast results, enhancing user experience.
1.2	 User Interface and Experience:
-	The user interface (UI) has been designed with user-friendliness in mind, incorporating navigation drawers and bottom navigation for easy access to features.
-	Users have reported a positive experience while navigating through different sections of the app. The Snack bar notifications effectively communicate actions and feedback.
1.3	 Performance Metrics: 
-	The application exhibits smooth performance, with minimal load times when accessing customer data or switching between fragments.
-	Background music management has been implemented successfully, allowing users to enjoy a seamless audio experience.
2.	 Evaluation:
a)	User Feedback:
-	User feedback collected during testing phases indicates high satisfaction with the app's design and functionality. Many users appreciated the customization options available in the settings, such as hiding specific customer information.
b. Usability Testing.
-	Usability tests were conducted with a sample group of users who interacted with the application for various tasks. Results showed a high completion rate, indicating that users could efficiently navigate and utilize the app’s features.
-	The incorporation of Switches for customizable settings allowed users to tailor the app to their preferences, further improving the overall experience.
c. Areas for Improvement:
-	Although the application is functioning well, some users suggested enhancements for future versions, including:
-	Enhanced Search Filters: Adding more advanced search options to improve the customer search experience.
-	User Profile Management: Allowing users to create and manage their profiles for a more personalized experience.
3.	Conclusion
-	The results and evaluations of the customer management application indicate that it successfully meets its intended objectives. The positive user feedback and high performance metrics confirm the application's effectiveness in managing customer information. Continuous improvement based on user suggestions will be essential for future updates, ensuring that the application remains relevant and user-friendly.
IX.	Conclude
In conclusion, the development of the customer management application has proven to be a successful endeavor that addresses the needs of users seeking an efficient and user-friendly solution for managing customer information. Throughout the development process, we focused on implementing key features such as customer addition, data search functionality, and customizable settings, which have all been achieved to the satisfaction of our user base.

The application’s architecture leverages modern design principles and technologies, resulting in a robust, scalable, and maintainable product. The feedback received during usability testing highlights the app's effectiveness in providing a seamless user experience, reinforcing our commitment to delivering quality software solutions.

As we move forward, it is essential to continuously gather user feedback and monitor performance metrics to identify areas for enhancement. This will allow us to implement new features and improvements, ensuring that the application evolves in alignment with user expectations and industry standards.

Ultimately, this project not only serves as a practical tool for managing customer data but also as a valuable learning experience for our development team. The skills and knowledge gained throughout this process will undoubtedly contribute to our future endeavors in software development.





