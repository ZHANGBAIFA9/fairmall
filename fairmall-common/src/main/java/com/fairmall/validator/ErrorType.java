package com.fairmall.validator;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/10 17:48
 * @Description:
 */
public interface ErrorType {
    /**
     * 返回code
     * @return
     */
    String getCode();
    /**
     * 返回mesg
     * @return
     */
    String getMesg();
}
