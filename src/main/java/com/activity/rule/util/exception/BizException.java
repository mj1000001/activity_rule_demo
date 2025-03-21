package com.activity.rule.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;


/**
 * 业务异常：因不符合业务规则导致的异常
 *
 * @author ZhangPeng
 * @create 2021-07-04 9:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class BizException extends Exception {
    private static final long serialVersionUID = -3972788344776467724L;

    private Integer exceptionCode;

    public BizException(String message) {
        super(message);
        log.error(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.exceptionCode = code;
        log.error(message);
    }
}
