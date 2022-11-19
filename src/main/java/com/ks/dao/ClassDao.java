package com.ks.dao;

import com.ks.dto.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao extends JpaRepository<CourseClass, Long> {

}
