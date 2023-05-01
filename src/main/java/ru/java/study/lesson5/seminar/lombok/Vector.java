package ru.java.study.lesson5.seminar.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vector {
    private Integer x;
    private Integer y;
    private Integer z;

}
