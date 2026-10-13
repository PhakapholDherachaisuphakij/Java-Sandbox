package sit.int271.rest_demo;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private Long id;
    private String name;
    private Double gpax;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // --- เพิ่มสองตัวนี้เข้าไปครับ ---
    public Double getGpax() { return gpax; }
    public void setGpax(Double gpax) { this.gpax = gpax; }
}