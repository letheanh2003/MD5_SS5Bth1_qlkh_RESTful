package the.anh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.anh.model.Student;
import the.anh.service.IStudentService;

import java.util.List;
import java.util.Optional;


@RestController // requestbody + controller
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        List<Student> list = (List<Student>) studentService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student s = studentService.save(student);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<Student> update(@PathVariable("id") Long id,@RequestBody Student student){
        Optional<Student> studentOptional = studentService.findById(id);
        if(studentOptional.isPresent()){
            student.setId(id);
            studentService.save(student);
            return new ResponseEntity<>(student,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id){
        Optional<Student> studentOptional = studentService.findById(id);
        if(studentOptional.isPresent()){
            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Long id){
        Optional<Student> studentOptional= studentService.findById(id);
        if (studentOptional.isPresent()){
            return new ResponseEntity<>(studentOptional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByAge")
    public ResponseEntity<List<Student>> findByAge(@RequestParam("age") String age){
        List<Student> list = (List<Student>) studentService.findStudentMoreAge(age);
        if (list.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
}
