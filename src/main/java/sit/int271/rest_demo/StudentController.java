package sit.int271.rest_demo;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
//    @GetMapping("")
//    public List<Student> getAllStudents() {
//        // ใช้ findAll() ของ JPA ดึงข้อมูลทั้งหมดในบรรทัดเดียว
//        return studentRepository.findAll();
//    }
    @GetMapping("")
    public Page<StudentDto> getStudents(Pageable pageable) {
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(student -> modelMapper.map(student, StudentDto.class));
    }
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student id " + id + " หาไม่เจอ !!! "));

        return modelMapper.map(student, StudentDto.class);
    }
    @PostMapping("")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto newStudentDto) {
        // หากข้อมูลผ่านการ Validation แล้ว ถึงจะวิ่งเข้ามาทำงานในนี้
        // แปลง DTO เป็น Entity เพื่อบันทึก (สมมติจำลองการเซฟ)
        Student student = modelMapper.map(newStudentDto, Student.class);
        Student savedStudent = studentRepository.save(student);

        // ส่ง DTO กลับไปหาหน้าบ้านพร้อม HTTP Status 201 Created
        StudentDto responseDto = modelMapper.map(savedStudent, StudentDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @PutMapping("/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto updatedStudentDto) {
        // 1. ค้นหาข้อมูลเดิมใน Database ก่อน ถ้าไม่มีให้โยน StudentNotFoundException ที่เราทำไว้
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student id " + id + " หาไม่เจอ ไม่สามารถอัปเดตได้ !!!"));

        // 2. อัปเดตฟิลด์ข้อมูลจาก DTO ลงไปใน Entity เดิม
        existingStudent.setName(updatedStudentDto.getName());
        existingStudent.setGpax(updatedStudentDto.getGpax());

        // 3. บันทึกกลับลง Database
        Student savedStudent = studentRepository.save(existingStudent);

        // 4. แปลงร่างกลับเป็น DTO ส่งคืนให้หน้าบ้าน
        return modelMapper.map(savedStudent, StudentDto.class);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        // 1. เช็คว่ามีตัวตนอยู่จริงไหม
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student id " + id + " หาไม่เจอ ไม่สามารถลบได้ !!!"));

        // 2. สั่งลบออกจาก Repository
        studentRepository.delete(student);

        // 3. ส่ง HTTP Status 204 No Content กลับไปบอกว่าลบสำเร็จเรียบร้อย
        return ResponseEntity.noContent().build();
    }
}