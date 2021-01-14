# InventoryApp
An Inventory application I developed using Java & Android Studio. This application handle basic CRUD operations from a SQLite database. The application uses these operation to allow the user to view, add, remove, and update items in the inventory. This inventory app also requires permission from the user to send SMS notifications when a product in their inventory is low or out of stock. The login and create account process for this application is simple. After the user enters their username and password, they can click the "Create Account" button to create a new account. Then the user can click the "Login" button to access their inventory.

### Login Screen
![Login](https://i.imgur.com/jNd5FJ8.jpg)

### Inventory Screen
![Inventory](https://i.imgur.com/1edoV4O.jpg)

### Notification Screen
![Notification](https://i.imgur.com/mlUYge0.jpg)

# Journal
Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

This mobile app is an inventory application that allows the user to manage the products in their inventory by adding, removing, and updating items. This inventory app also sends alerts to the user when their product inventory is low or out of stock. In order to send these alerts, the application requires permission from the user to send SMS messages. The user can create an account by simply entering their credentials on the login screen, which they can use to securely login in and out of the inventory app.

What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?

The screens and features that were necessary to support the user's needs and produce a user-centered UI for the app are the login screen, the inventory screen and the inventory alarms screen. The user can interact with the login screen by entering their credentials into their respective text fields. Then, they can create a new account by selecting the "CREATE ACCOUNT" button which will add their credentials to the database or login by selecting the "LOGIN" button which will verify their credentials against the data in the database. On the inventory screen, the user can view all of the items they have in their inventory. They can add an item to the inventory by entering the item name, item price, and item quantity into their respective text fields and selecting the "ADD" button. They can remove an item form the inventory by entering the item name and selecting the "DELETE" button. The user can also update the count for an existing item by entering the name of the item they want to update and the count in the available text fields and selecting the "UPDATE" button. Selecting the "ALERTS" button will bring the user to the inventory alerts page, where the user can activate or deactivate the SMS notifications for the app.

How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?

When developing the functionality for the application, I would refer to the requirements specified by the user and analyze how the data would flow through the system. I would first create data model classes with their required variables, constructor method, and getters and setters. I then worked on the database functionality and created the required CRUD functions to handle the data contained within the data models that I had created. After implementing the data models, and database functionality. I created instances of the data model classes as objects for each of the UI elements and then passed the information from the data model object to the database functionality. Finally, I implemented the remaining features specified in the requirement such as the notification system. After doing this, I perform a final review of the user requirements against the running application in order to ensure that I have met all of the requirements specified by the user.

How did you test to ensure your code was functional? Why is this process important and what did it reveal?

To test the functionality of my code I emulated a mobile device using the AVD Manager in Android Studio. I implemented a single component at a time and tested each of them throughout the development process by using the implemented functionality in the emulator to see if it generated the expected result. After reflecting on this, I now realize that this was an inefficient way to develop the app. I should have taken a Test-Driven Development approach during the development process by implementing automated JUnit tests to thoroughly test each feature prior to implementing them.

Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?

From the initial planning to the finalization of the app, the challenges that I faced were translating my intimal designs to match the desired functionality of the application. To overcome this challenge, I innovated by redesigning the UI of the app to better match the functionality that I had implemented in the code. I rearranged the layout of the text fields and buttons on the inventory screen in a way that would be natural for the user and still provide them with the functionality they required.  

In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

The components that demonstrated my knowledge, skills, and experience successfully are the CRUD functions I implemented for the database and their respective front end UI components. I think that my construction of these components demonstrates my understanding of the principles related to software engineering and design, databases, and algorithms and data structures. Implementing all of the required functionality for this application was challenging at first but with the help of the Android Studio documentation I was able to figure out how to connect all of the functions from the database class to the front-end components in order to achieve the desired functionality based on the specified user requirements.

