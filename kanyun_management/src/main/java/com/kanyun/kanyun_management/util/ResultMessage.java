package com.kanyun.kanyun_management.util;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultMessage<T> {
    private  String message;
    private  int state;
    private T temp;
}
