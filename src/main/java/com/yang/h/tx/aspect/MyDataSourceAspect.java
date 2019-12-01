package com.yang.h.tx.aspect;

import com.yang.h.tx.MyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class MyDataSourceAspect {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint point) throws Throwable {
        Connection connection = (Connection) point.proceed();
        return new MyConnection(connection);
    }
}
