package the.anh.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import the.anh.model.Student;

@Repository

public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    @Query("Select s from Student  as s where s.studentName like concat('%',:age,'%')")
    Iterable<Student> findStudentMoreAge(@Param("age") String age);
}

