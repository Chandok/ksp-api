package com.ks.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseVO {
    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrollmentStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrollmentEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Double cost;

    private List<CourseClassVO> classes;
}
