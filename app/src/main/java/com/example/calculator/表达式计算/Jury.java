package com.example.calculator.表达式计算;

import java.util.HashMap;
import java.util.Map;

public class Jury
{
    /*
     * 用以判断表达式是否合法的实体类
     */

    private String src;

    private boolean result;

    private Map<Type, Integer> judgeMap;

    public Jury(String src)
    {
        this.src = src;
        judgeMap = new HashMap<Type, Integer>();
        judge();
    }

    private void resetJudgeMap()
    {
        judgeMap.put(Type.dot, 1);
        judgeMap.put(Type.left, 0);
        judgeMap.put(Type.minus, 0);
        judgeMap.put(Type.number, 0);
        judgeMap.put(Type.op1, 1);
        judgeMap.put(Type.op2, 1);
        judgeMap.put(Type.op3, 1);
        judgeMap.put(Type.op4, 0);
        judgeMap.put(Type.right, 1);
    }

    private void judge()
    {
        resetJudgeMap();
        char[] arr = src.toCharArray();
        int weight = 0;
        for(int a = 0; a < arr.length; a++)
        {
            Type type;
            if(arr[a] >='a' && arr[a] <= 'z')
            {
                StringBuffer sb = new StringBuffer("");
                while(a < arr.length && arr[a] >= 'a' && arr[a] <= 'z')
                {
                    sb.append(arr[a]);
                    a++;
                }
                a--;
                type = Type.getType(sb.toString());
            }
            else
                type = Type.getType(arr[a]);
            if(judgeMap.get(type) != 0)
            {
                result = false;
                return;
            }
            else
            {
                if(type == Type.dot)
                {
                    judgeMap.put(Type.dot, 2);
                    judgeMap.put(Type.left, 1);
                    judgeMap.put(Type.minus, 1);
                    judgeMap.put(Type.number, 0);
                    judgeMap.put(Type.op1, 1);
                    judgeMap.put(Type.op2, 1);
                    judgeMap.put(Type.op3, 1);
                    judgeMap.put(Type.op4, 1);
                    judgeMap.put(Type.right, 1);
                }
                else if(type == Type.left)
                {
                    weight++;
                    judgeMap.put(Type.dot, 1);
                    judgeMap.put(Type.left, 0);
                    judgeMap.put(Type.minus, 0);
                    judgeMap.put(Type.number, 0);
                    judgeMap.put(Type.op1, 1);
                    judgeMap.put(Type.op2, 1);
                    judgeMap.put(Type.op3, 1);
                    judgeMap.put(Type.op4, 0);
                    judgeMap.put(Type.right, 0);
                }
                else if(type == Type.right)
                {
                    weight--;
                    judgeMap.put(Type.dot, 1);
                    judgeMap.put(Type.left, 1);
                    judgeMap.put(Type.minus, 0);
                    judgeMap.put(Type.number, 1);
                    judgeMap.put(Type.op1, 0);
                    judgeMap.put(Type.op2, 0);
                    judgeMap.put(Type.op3, 0);
                    judgeMap.put(Type.op4, 1);
                    judgeMap.put(Type.right, 1);
                    if(weight > 0)
                        judgeMap.put(Type.right, 0);
                }
                else if(type == Type.minus || type == Type.op1 || type == Type.op2 || type == Type.op3)
                {
                    judgeMap.put(Type.dot, 1);
                    judgeMap.put(Type.left, 0);
                    judgeMap.put(Type.minus, 1);
                    judgeMap.put(Type.number, 0);
                    judgeMap.put(Type.op1, 1);
                    judgeMap.put(Type.op2, 1);
                    judgeMap.put(Type.op3, 1);
                    judgeMap.put(Type.op4, 0);
                    judgeMap.put(Type.right, 1);
                }
                else if(type == Type.number)
                {
                    if(judgeMap.get(Type.dot) == 1)
                        judgeMap.put(Type.dot, 0);
                    else if(judgeMap.get(Type.dot) == 2)
                        judgeMap.put(Type.dot, 2);
                    else
                        judgeMap.put(Type.dot, 0);
                    judgeMap.put(Type.left, 1);
                    judgeMap.put(Type.minus, 0);
                    judgeMap.put(Type.number, 0);
                    judgeMap.put(Type.op1, 0);
                    judgeMap.put(Type.op2, 0);
                    judgeMap.put(Type.op3, 0);
                    judgeMap.put(Type.op4, 1);
                    judgeMap.put(Type.right, 1);
                    if(weight > 0)
                        judgeMap.put(Type.right, 0);
                }
                else if(type == Type.op4)
                {
                    judgeMap.put(Type.dot, 1);
                    judgeMap.put(Type.left, 0);
                    judgeMap.put(Type.minus, 1);
                    judgeMap.put(Type.number, 1);
                    judgeMap.put(Type.op1, 1);
                    judgeMap.put(Type.op2, 1);
                    judgeMap.put(Type.op3, 1);
                    judgeMap.put(Type.op4, 1);
                    judgeMap.put(Type.right, 1);
                }
            }
        }
        if(weight == 0)
            result = true;
        else
            result = false;
    }

    public boolean getResult()
    {
        return result;
    }
}
