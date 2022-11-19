package com.ks.service;

import com.ks.dao.ClassDao;
import com.ks.dto.Course;
import com.ks.dto.CourseClass;
import com.ks.vo.CourseClassVO;
import com.ks.vto.ClassRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    private ClassDao classDao;

    public CourseClassVO save(ClassRequest request) {

        CourseClass courseClass = getClazz(request);
        classDao.save(courseClass);
        CourseClassVO vo = convertClass(courseClass);
        return vo;
    }

    public CourseClassVO getClassById(Long id) {
        CourseClass clazz = classDao.getById(id);
        return convertClass(clazz);
    }

    private CourseClassVO convertClass(CourseClass courseClass) {
        CourseClassVO vo = new CourseClassVO();
        vo.setId(courseClass.getId());
        vo.setCourseId(courseClass.getCourse().getId());
        vo.setName(courseClass.getName());
        return vo;
    }

    private CourseClass getClazz(ClassRequest request) {
        CourseClass courseClass = new CourseClass();
        courseClass.setId(request.getId());
        courseClass.setName(request.getName());
        Course course = new Course();
        course.setId(request.getCourseId());
        courseClass.setCourse(course);
        return courseClass;
    }

    public void deleteClassbyId(Long id) {
        classDao.deleteById(id);
    }
}
