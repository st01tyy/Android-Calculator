package com.example.calculator.表达式计算;

public enum Type
{
    /*
     * minus = -
     *op1 = +
     *op2 = ×, ÷
     *op3 = ^
     *op4 = log, sin, cos, tan
     */

    left, right, number, dot, minus, op1, op2, op3, op4;

    public static Type getType(char ch)
    {
        if(ch == '(')
            return Type.left;
        else if(ch == ')')
            return Type.right;
        else if(ch >= '0' && ch <= '9')
            return Type.number;
        else if(ch == '.')
            return Type.dot;
        else if(ch == '-')
            return Type.minus;
        else if(ch == '+')
            return Type.op1;
        else if(ch == '*' || ch == '×' || ch == '/' || ch == '÷')
            return Type.op2;
        else if(ch == '^')
            return Type.op3;
        else
            return null;
    }

    public static Type getType(String str)
    {
        if(str.length() == 1)
            return getType(str.charAt(0));
        if(str.equals("mod"))
            return Type.op2;
        else if(str.equals("sin") || str.equals("cos") || str.equals("tan") || str.equals("log"))
            return Type.op4;
        else
            return null;
    }
}
