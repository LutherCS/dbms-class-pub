'''Create a fake university database'''

#!/usr/bin/env python3

import random
import sqlite3
from faker import Faker

random.seed(440)

STUDENT_TBL_SIZE = 25000
DEPT_TBL_SIZE = 50  # Number of majors in the majors.txt
COURSE_TBL_SIZE = 500
SECTION_TBL_SIZE = 2000
ENROLL_TBL_SIZE = 500000
FACULTY_TBL_SIZE = 1000

DEPT_LST = []
GRADES = ['A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D', 'F']
TITLES_ADV = ['Advanced', 'Building', 'Designing', 'Developing',
              'Hacking', 'Introduction to', 'Mastering', 'Understanding']
TITLES_ADJ = ['Android', 'C++', 'Computer', 'Database',
              'Game', 'Java', 'Hardware', 'Network',
              'Python', 'Secure', 'Software', 'Web']
TITLES_NOUN = ['Basics', 'Concepts', 'Design', 'Development',
               'Fundamentals', 'Principles', 'Problems', 'Security']


def get_course_title():
    '''Generate the course title'''
    if random.random() > 0.5:
        return random.choice(TITLES_ADV) + ' '\
               + random.choice(TITLES_ADJ) + ' '\
               + random.choice(TITLES_NOUN)
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
    cursor.execute('DROP TABLE IF EXISTS student;')
    cursor.execute('''CREATE TABLE student (
                            SId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            SName TEXT NOT NULL,
                            GradYear INTEGER NOT NULL,
                            MajorId INTEGER NOT NULL,
                            FOREIGN KEY (MajorId) REFERENCES dept(DId)
                    );''')
    # Create table DEPT
    cursor.execute('DROP TABLE IF EXISTS dept;')
    cursor.execute('''CREATE TABLE dept (
                            DId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            DName TEXT NOT NULL
                    );''')
    # Create table FACULTY
    cursor.execute('DROP TABLE IF EXISTS faculty;')
    cursor.execute('''CREATE TABLE faculty (
                            FId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            FName TEXT NOT NULL,
                            DeptId INTEGER NOT NULL,
                            YearFrom INTEGER NOT NULL,
                            YearTo INTEGER,
                            FOREIGN KEY (DeptId) REFERENCES dept(DId)
                    );''')
    # Create table COURSE
    cursor.execute('DROP TABLE IF EXISTS course;')
    cursor.execute('''CREATE TABLE course (
                            CId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            Title TEXT NOT NULL,
                            DeptId INTEGER NOT NULL,
                            FOREIGN KEY (DeptId) REFERENCES dept(DId)
                    );''')
    # Create table SECTION
    cursor.execute('DROP TABLE IF EXISTS section;')
    cursor.execute('''CREATE TABLE section (
                            SectId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            CourseId INTEGER NOT NULL,
                            Prof TEXT NOT NULL,
                            YearOffered INTEGER NOT NULL,
                            FOREIGN KEY (CourseId) REFERENCES course(CId)
                    );''')
    # Create table ENROLL
    cursor.execute('DROP TABLE IF EXISTS enroll;')
    cursor.execute('''CREATE TABLE enroll (
                            EId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            StudentId INTEGER NOT NULL,
                            SectionId INTEGER NOT NULL,
                            Grade TEXT NOT NULL,
                            FOREIGN KEY (StudentId) REFERENCES student(SId),
                            FOREIGN KEY (SectionId) REFERENCES section(SectId)
                    );''')
    conn.commit()


def populate_db(conn):
    '''Add date to the database'''
    cursor = conn.cursor()
    person = Faker()
    cursor.execute("PRAGMA foreign_keys = ON;")
    # Populate table DEPT
    for dept in DEPT_LST:
        cursor.execute("INSERT INTO dept(DName) VALUES ('{}');".
                       format(dept))
    conn.commit()
    # Populate table STUDENT
    for _ in range(STUDENT_TBL_SIZE):
        cursor.execute("INSERT INTO student(SName, GradYear, MajorId) VALUES ('{}', {}, {});".
                       format(person.name(), random.randint(1866, 2017), random.randint(1, DEPT_TBL_SIZE)))
    conn.commit()
    # Populate table FACULTY
    for _ in range(FACULTY_TBL_SIZE):
        start_year = random.randint(1861, 2017)
        retirement_year = start_year + random.randint(1, 40)
        cursor.execute("INSERT INTO faculty(FName, DeptId, YearFrom, YearTo) VALUES ('{}', {}, {}, {});".
                       format(person.name(), random.randint(1, DEPT_TBL_SIZE), start_year, retirement_year))
    cursor.execute("UPDATE faculty SET YearTo=NULL WHERE YearTo>2017;")
    conn.commit()
    # Populate table COURSE
    # Course can be offered once by a department
    courses = set()
    while len(courses) < COURSE_TBL_SIZE:
        course_title = get_course_title()
        dept_id = random.randint(1, DEPT_TBL_SIZE)
        if (course_title, dept_id) not in courses:
            cursor.execute("INSERT INTO course(Title, DeptId) VALUES ('{}', {});".
                           format(course_title, dept_id))
        courses.add((course_title, dept_id))
    conn.commit()
    # Populate table SECTION
    for _ in range(SECTION_TBL_SIZE):
        course_id = random.randint(1, COURSE_TBL_SIZE)
        cursor.execute("SELECT DeptId from course where " + str(course_id) + "=course.CID;")
        dept_offering = cursor.fetchone()
        cursor.execute("SELECT FName, YearFrom, YearTo from faculty where faculty.DeptId=" + str(dept_offering[0]) + ";")
        dept_fac_lst = [f for f in cursor.fetchall()]
        prof = random.choice(dept_fac_lst)
        if prof[2]:
            year_offered = random.randint(prof[1], prof[2])
        else:
            year_offered = random.randint(prof[1], 2017)
        cursor.execute("INSERT INTO section(CourseId, Prof, YearOffered) VALUES ({}, '{}', {});".
                       format(course_id, prof[0], year_offered))
    conn.commit()
    # Populate table ENROLL
    # A student can take a class once
    enrollments = set()
    while len(enrollments) < ENROLL_TBL_SIZE:
        student_id = random.randint(1, STUDENT_TBL_SIZE)
        section_id = random.randint(1, SECTION_TBL_SIZE)
        if (student_id, section_id) not in enrollments:
            cursor.execute("INSERT INTO enroll(StudentId, SectionId, Grade) VALUES ({}, {}, '{}');".
                           format(student_id, section_id, random.choice(GRADES)))
        enrollments.add((student_id, section_id))
    conn.commit()


def main():
    '''Main function'''
    read_files()
    conn = sqlite3.connect('exercises/sql_queries/university.sqlite')
    create_db(conn)
    populate_db(conn)
    conn.close()

if __name__ == '__main__':
    main()
