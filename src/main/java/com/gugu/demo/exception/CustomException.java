package com.gugu.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gugu
 * @Classname CustomException
 * @Description TODO
 * @Date 2022/7/2 10:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception {
    public Integer code;
    public String message;
    public Throwable throwable;
}
