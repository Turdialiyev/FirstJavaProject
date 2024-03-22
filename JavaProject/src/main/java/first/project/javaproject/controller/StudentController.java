package first.project.javaproject.controller;

import first.project.javaproject.model.Student;
import first.project.javaproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
   //CRUD
    @Autowired
    StudentRepository studentRepository;
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudent()
    {
        List<Student> students = studentRepository.findAll();
        return  students;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student)
    {
        studentRepository.save(student);
        return "Student is added";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Integer id)
    {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent())
        {
            Student data = optionalStudent.get();
            return data;
        }
        else
        {
            return new Student();
        }
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String delateStudent(@PathVariable int id)
    {
        studentRepository.deleteById(id);

        return "student is deleted";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable int id, @RequestBody Student student)
    {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent())
        {
            Student data = optionalStudent.get();
            data.setFirstName(student.getFirstName());
            data.setLastName(student.getLastName());
            data.setPhoneNumber(student.getPhoneNumber());

            studentRepository.save(data);

            return data;
        }
        else
        {
            return new Student();
        }
    }


}
