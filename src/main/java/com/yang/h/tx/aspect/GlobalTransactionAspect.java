package com.yang.h.tx.aspect;

import com.yang.h.tx.GlobalTransaction;
import com.yang.h.tx.GlobalTransactionManager;
import com.yang.h.tx.MyTransaction;
import com.yang.h.tx.enums.TransactionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class GlobalTransactionAspect implements Ordered {

    @Around("@annotation(com.yang.h.tx.GlobalTransaction)")
    public void invoke(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        GlobalTransaction globalTransaction = method.getAnnotation(GlobalTransaction.class);

        if (globalTransaction.isStart()) {
            // 创建事务组
            String groupId = GlobalTransactionManager.createGroup();

            // 创建分支事务
            MyTransaction transaction = GlobalTransactionManager.createBranchTransaction(groupId);

            try {
                point.proceed();
                transaction.setTransactionType(TransactionType.commit);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                transaction.setTransactionType(TransactionType.rollback);
            }

            // 注册
            GlobalTransactionManager.registryBranchTransaction(transaction);

            // 提交全局分布式事务
            GlobalTransactionManager.commitGlobalTransaction(groupId);
        } else {
            try {
                point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
