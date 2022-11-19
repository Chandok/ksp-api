package com.ks.vto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequest {
    private Long id;

    private String name;

    private Date enrollmentStartDate;

    private Date enrollmentEndDate;

    private Date startDate;

    private Date endDate;

    private Double cost;
}
