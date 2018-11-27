package com.yang.springannotation.service.impl;

import com.yang.springannotation.service.MathCalculatorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mathCalculatorService2")
public class MathCalculatorService2Impl implements MathCalculatorService {

    @Transactional
    @Override
    public double div(int a, int b) {
        return a/b;
    }
}
