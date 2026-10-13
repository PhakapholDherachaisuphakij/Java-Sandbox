package sit.int271.rest_demo;

import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // กำหนดให้เมื่อโยน Exception นี้ จะส่ง HTTP 404 กลับไปอัตโนมัติ
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}