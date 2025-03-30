# 😎 PasswordHolder Application

## 📌 Overview
This Java Swing-based desktop application provides a simple user interface for storing and managing email/phone and password combinations. It features a dark mode toggle, data persistence in a table, and user-friendly alerts for invalid inputs.

## ✨ Features
- ✅ **Add Credentials**: Users can enter an email/phone and password, which will be saved in a table.
- 🗑️ **Remove Credentials**: Selected rows can be deleted from the table.
- 🌙 **Dark Mode**: A toggle button switches the UI between light and dark modes.
- ⚠️ **Validation Alerts**: Displays a warning dialog if the user attempts to add empty fields.

## 🛠️ Installation
1. Clone this repository:
   ```sh
   git clone https://github.com/DoritosLover1/PasswordHolder.git
   ```
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans).
3. Ensure you have Java (JDK 8 or later) installed.
4. ▶️ Run `Application.java` to launch the application.

## 🎯 Usage
1. 📑 Select a text file or application, gonna create it for you named as "password.txt"
2. ✏️ Enter an email/phone number and password in the respective fields.
3. 💾 Click the **Add** button to add the entry to the table.
4. 🗑️ Select an entry in the table and click **Remove** to delete it.
5. 🌙 Click the **Dark Mode** toggle to switch themes.
6. 🔒 When you are going to **Save**, you must to **note** your secret key anywhere.
7. ‼️ If you did not press **Save** and then closed the application, that would save but not encrypt.

## 📂 Code Structure
- 📜 **`Application.java`**: Implements the main UI logic and event handling.
- ⚠️ **`WarningDialog.java`**: Creates a warning dialog for empty input fields.
- 🔧 **`UtilityFunctions.java`**: Defines utility functions like the dark mode toggle.

## 🖼️ Screenshots
![image](https://github.com/user-attachments/assets/5122bd0e-0b39-4d53-8104-7d7251e2fe74)
![image](https://github.com/user-attachments/assets/015a2fa5-b623-4cbb-a155-6c7b5f7d25dc)

## 🚀 Upcoming Updates
1. Documentation.
2. Improved UI/UX designs.

## 📜 License
There is no license about this project.

## 🤝 Contributing
Pull requests are welcome!
