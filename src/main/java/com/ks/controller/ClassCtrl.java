package com.ks.controller;

import com.ks.exception.DataNotFoundException;
import com.ks.exception.InvalidInputException;
import com.ks.service.ClassService;
import com.ks.validator.ClassValidator;
import com.ks.vo.CourseClassVO;
import com.ks.vo.CourseVO;
import com.ks.vto.ClassRequest;
import com.ks.vto.GenericResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@RestController
public class ClassCtrl {

    @Autowired
    private ClassService classService;

    @Autowired
    private ClassValidator classValidator;

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/class/create")
    public ResponseEntity<GenericResponse> create(@RequestBody ClassRequest request, HttpSession session) {
        GenericResponse response = new GenericResponse();
        try {
            String errorMsg = classValidator.validateRequest(request);
            if (StringUtils.isBlank(errorMsg)) {
                CourseClassVO clazz = classService.save(request);
                response.setContent(clazz);
                return new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
            }
            response.setErrorMsg(errorMsg);
            return new ResponseEntity<GenericResponse>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setErrorMsg(e.getMessage());
            return new ResponseEntity<GenericResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/class")
    public ResponseEntity<CourseClassVO> getCourse(@Param("id") Long id) throws InvalidInputException, DataNotFoundException {
        CourseClassVO clazz = null;
        try {
            if (id != null) {
                clazz = classService.getClassById(id);

            } else {
                throw new InvalidInputException("Class Id is required");
            }
        } catch (EntityNotFoundException e) {
            throw new DataNotFoundException("Clas with id=" + id + " not found");
        }
        return new ResponseEntity<CourseClassVO>(clazz, HttpStatus.OK);

    }

    @DeleteMapping("/class")
    public ResponseEntity<CourseVO> deleteClass(@Param("id") Long id) throws InvalidInputException, DataNotFoundException {
        try {
            if (id != null) {
                classService.deleteClassbyId(id);

            } else {
                throw new InvalidInputException("Class Id is required");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Class with id=" + id + " not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
