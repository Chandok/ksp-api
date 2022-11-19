package com.ks.service;

import com.ks.dao.CourseDao;
import com.ks.dto.Course;
import com.ks.dto.CourseClass;
import com.ks.vo.CourseClassVO;
import com.ks.vo.CourseVO;
import com.ks.vto.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public Course save(CourseRequest request) {
        Course course = getCourse(request);
        courseDao.save(course);
        return course;
    }

    private Course getCourse(CourseRequest request) {
        Course course = new Course();
        course.setId(request.getId());
        course.setName(request.getName());
        course.setEnrollmentStartDate(request.getEnrollmentStartDate());
        course.setEnrollmentEndDate(request.getEnrollmentEndDate());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        course.setCost(request.getCost());
        return course;
    }

    public void deleteCourseById(Long id) {
        courseDao.deleteById(id);
    }

    public List<CourseVO> getCourses() {
        List<Course> courses = courseDao.findAll();
        List<CourseVO> courseVOList = new ArrayList<>();
        for (Course course : courses) {
            courseVOList.add(convertCourse(course));
        }
        return courseVOList;
    }

    private CourseVO convertCourse(Course course) {
        CourseVO courseVO = new CourseVO();
        courseVO.setId(course.getId());
        courseVO.setName(course.getName());
        courseVO.setStartDate(course.getStartDate());
        courseVO.setEndDate(course.getEndDate());
        courseVO.setEnrollmentStartDate(course.getEnrollmentStartDate());
        courseVO.setEnrollmentEndDate(course.getEnrollmentEndDate());
        courseVO.setCost(course.getCost());
        List<CourseClass> classes = course.getCourseClasses();
        List<CourseClassVO> voClasses = new ArrayList<>();
        for (CourseClass clazz : classes) {
            CourseClassVO classVO = convertCourseClass(clazz);
            voClasses.add(classVO);
        }
        courseVO.setClasses(voClasses);
        return courseVO;
    }

    private CourseClassVO convertCourseClass(CourseClass clazz) {
        CourseClassVO vo = new CourseClassVO();
        vo.setId(clazz.getId());
        vo.setName(clazz.getName());
        return vo;
    }

    public CourseVO getCourseById(Long id) {
        return convertCourse(courseDao.getById(id));
    }
}
