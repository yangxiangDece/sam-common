package com.yang.springannotation.service.impl;

import com.yang.springannotation.service.MathCalculatorService;
import org.springframework.stereotype.Service;

@Service("mathCalculatorService")
public class MathCalculatorServiceImpl implements MathCalculatorService {

    @Override
    public double div(int a, int b) {
        return a/b;
    }
}
