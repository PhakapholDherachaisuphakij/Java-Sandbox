package sit.int271.rest_demo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;

    @NotBlank(message = "Student name is required") // ห้ามเป็นค่าว่างหรือช่องว่างเปล่าๆ[cite: 33, 40]
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") // กำหนดความยาว[cite: 40]
    private String name;

    @Min(value = 0, message = "GPAX must be at least 0.00") // ค่าตัวเลขต่ำสุด[cite: 41]
    @Max(value = 4, message = "GPAX cannot exceed 4.00") // ค่าตัวเลขสูงสุด[cite: 41]
    private Double gpax;
}