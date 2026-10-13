package sit.int271.rest_demo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring Data JPA เตรียม findAll(), findById(), save(), delete() ไว้ให้หมดแล้ว
    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);
    List<Student> findByNameContainsOrGpaxBetweenOrderByGpaxDesc(
            String name, double low, double high
    );

}