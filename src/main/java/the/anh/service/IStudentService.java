package the.anh.service;

import the.anh.model.Student;

public interface IStudentService extends IGenericService<Student,Long> {
    Iterable<Student> findStudentMoreAge(String age);
}
