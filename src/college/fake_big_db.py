'''Create a fake university database'''

#!/usr/bin/env python3
#encoding: UTF-8

import random
import sqlite3
from faker import Faker

random.seed(440)

DEPT_LST = []
FAC_LST = [Faker().name() for _ in range(100)]
GRADES = ['A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D', 'F']
TITLES_ADV = ["Introduction to", "Mastering", "Developing", "Advanced", "Hacking", "Understanding", "Diving into"]
TITLES_ADJ = ["Java", "C++", "Python", "Secure", "Computer", "Hardware", "Database", "Web", "Game", "Operating"]
TITLES_NOUN = ["Basics", "Fundamentals", "Concepts", "Systems", "Security", "Problems", "Networks", "Principles"]


def title():
    '''Generate the course title'''
    if random.random() > 0.5:
        return random.choice(TITLES_ADV) + ' ' + random.choice(TITLES_ADJ) + ' ' + random.choice(TITLES_NOUN)
    else:
        return random.choice(TITLES_ADJ) + ' ' + random.choice(TITLES_NOUN)

def read_files():
    '''Read data from files'''
    with open('src/college/majors.txt', 'r') as input_file:
        for line in input_file:
            DEPT_LST.append(line.strip())

def create_db(conn):
    '''Create a database'''
    cursor = conn.cursor()
    # Create table STUDENT
    cursor.execute("DROP TABLE IF EXISTS student;")
    cursor.execute('''CREATE TABLE student (
                            SId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            SName TEXT,
                            GradYear INTEGER,
                            MajorId INTEGER);''')
    # Create table DEPT
    cursor.execute("DROP TABLE IF EXISTS dept;")
    cursor.execute('''CREATE TABLE dept (
                            DId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            DName TEXT);''')
    # Create table COURSE
    cursor.execute("DROP TABLE IF EXISTS course;")
    cursor.execute('''CREATE TABLE course (
                            CId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            Title TEXT,
                            DeptId INTEGER);''')
    # Create table SECTION
    cursor.execute("DROP TABLE IF EXISTS section;")
    cursor.execute('''CREATE TABLE section (
                            SectId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            CourseId INTEGER,
                            Prof TEXT,
                            YearOffered INTEGER);''')
    # Create table ENROLL
    cursor.execute("DROP TABLE IF EXISTS enroll;")
    cursor.execute('''CREATE TABLE enroll (
                            EId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            StudentId INTEGER,
                            SectionId INTEGER,
                            Grade TEXT);''')
    conn.commit()
                        
def populate_db(conn):
    '''Add date to the database'''
    cursor = conn.cursor()
    person = Faker()
    number_of_students = 25000
    number_of_departments = len(DEPT_LST)
    number_of_courses = 500
    number_of_sections = 2000
    number_of_enrollments = 50000

    # Populate table STUDENT
    for _ in range(25000):
        cursor.execute("INSERT INTO student(SName, GradYear, MajorId) VALUES ('{}', {}, {});".\
            format(person.name(), random.randint(1861, 2017), random.randint(1, number_of_departments)))
    conn.commit()
    # Populate table DEPT
    for dept in DEPT_LST:
        cursor.execute("INSERT INTO dept(DName) VALUES ('{}');".\
            format(dept))
    conn.commit()
    # Populate table COURSE
    for _ in range(number_of_courses):
        cursor.execute("INSERT INTO course(Title, DeptId) VALUES ('{}', {});".\
            format(title(), random.randint(1, number_of_departments)))
    conn.commit()
    # Populate table SECTION
    for _ in range(number_of_sections):
        cursor.execute("INSERT INTO section(CourseId, Prof, YearOffered) VALUES ({}, '{}', {});".\
            format(random.randint(1, number_of_courses), random.choice(FAC_LST), random.randint(1861, 2017)))
    conn.commit()
    # Populate table ENROLL
    for _ in range(number_of_enrollments):
        cursor.execute("INSERT INTO enroll(StudentId, SectionId, Grade) VALUES ({}, {}, '{}');".\
            format(random.randint(1, number_of_students), random.randint(1, number_of_sections), random.choice(GRADES)))
    conn.commit()

def main():
    '''Main function'''
    read_files()
    conn = sqlite3.connect('exercises/sql_queries/university.sqlite')
    create_db(conn)
    populate_db(conn)
    conn.close()


if __name__ == "__main__":
    main()
