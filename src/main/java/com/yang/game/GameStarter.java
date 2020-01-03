package com.yang.game;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Map;
import java.util.Scanner;

public class GameStarter {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Map<Integer, Condition> conditionMap = ConditionCollections.CONDITION_MAP;

        Condition condition = conditionMap.get(1);
        System.out.println(condition.getContent());
        condition.getChoices().forEach((key, value) -> System.out.println(value.getContent()));
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("请输入选择项数字，然后按回车键：");
            int i = SCANNER.nextInt();
            Choice choice = condition.getChoices().get(i);
            condition = conditionMap.get(choice.getSkipConditionId());
            if (BooleanUtils.isTrue(condition.getEnded())) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(condition.getContent());
                System.out.println("=================游戏结局=================");
                Ending ending = condition.getEnding();
                System.out.println(ending.getName());
                System.out.println(ending.getContent());
                gameOver = true;
            } else {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(condition.getContent());
                condition.getChoices().forEach((key, value) -> System.out.println(value.getContent()));
            }
        }
    }
}
