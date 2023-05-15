# Database for a human resource management application.

Description: Human resource management application is used to manage employee related activities of an organization. The relational database plays a vital role in maintaining personnel information, recruitment management, attendance and absence data, salaries and employee performance.

Database structure:

1. "Employees" Table: This table contains basic employee information, such as unique ID, first name, last name, date of birth, 1. address, phone number, email address, role, department, and associated manager.
2. "Hires" table: This table records employee hire information, including unique ID, date hired, position, starting salary, 1. contract type (full-time, part-time, temporary contract), and other related information .
3. "Attendance" table: This table keeps track of employee attendance and absences. Includes unique ID, associated employee ID, 1. date, time of entry, time of exit, and type of absence (vacation, sickness, leave).
4. "Wages" Table: This table maintains employee payroll data, such as Unique ID, Associated Employee ID, Payment Date, Salary 1. Amount, Taxes, and Deductions Applied.
5. "Performance Evaluations" table: This table records employee performance evaluations. Includes unique ID, associated employee  ID, assessment date, assessment results, comments and feedback.
6. "Training" table: This table tracks employee training and development. Includes Unique ID, Associated Employee ID, Training 1. Type, Start Date, End Date, Description, and Training Results.

Benefits of relational database for human resource management application:

1. Centralized storage of employee data: The database allows you to keep all employee information in one place, simplifying 1. information management and retrieval.
2. Monitoring of attendance and absences: With the "Attendance" table, it is possible to keep track of the attendance and 1. absences of employees, allowing for accurate management of working time and leaves.
3. Payroll and Tax Management: The database allows you to accurately calculate and maintain employee payroll data, facilitating reporting and tax compliance.
4. Performance Evaluations and Employee Development: The "Performance Evaluations" table and the "Training" table allow you to 1. monitor employee performance and plan the appropriate training