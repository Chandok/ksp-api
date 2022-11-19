package com.ks.controller;

import com.ks.dto.Course;
import com.ks.exception.DataNotFoundException;
import com.ks.exception.InvalidInputException;
import com.ks.service.CourseService;
import com.ks.validator.CourseValidator;
import com.ks.vo.CourseVO;
import com.ks.vto.CourseRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseCtrl {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    @PostMapping("/course/create")
    public ResponseEntity<Course> create(@RequestBody CourseRequest request, HttpSession session) throws InvalidInputException {

        String errorMsg = courseValidator.validateCourseRequest(request);
        if (StringUtils.isBlank(errorMsg)) {
            Course course = courseService.save(request);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } else {
            throw new InvalidInputException(errorMsg);
        }

    }

    @GetMapping("/course")
    public ResponseEntity<CourseVO> getCourse(@Param("id") Long id) throws InvalidInputException, DataNotFoundException {
        CourseVO course = null;
        try {
            if (id != null) {
                course = courseService.getCourseById(id);

            } else {
                throw new InvalidInputException("Course Id is required");
            }
        } catch (EntityNotFoundException e) {
            throw new DataNotFoundException("Course with id=" + id + " not found");
        }
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @GetMapping("/course/list")
    public ResponseEntity<List<CourseVO>> getCourses() {
        List<CourseVO> courses = new ArrayList<>();
        courses = courseService.getCourses();
        return new ResponseEntity<List<CourseVO>>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/course")
    public ResponseEntity<CourseVO> deleteCourse(@Param("id") Long id) throws InvalidInputException, DataNotFoundException {
        try {
            if (id != null) {
                courseService.deleteCourseById(id);

            } else {
                throw new InvalidInputException("Course Id is required");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Course with id=" + id + " not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
