package com.example.calculator.转换器;

public class SystemConverter
{
    private String src;
    private NumberSystem srcSystem;
    private String result;
    private NumberSystem resSystem;

    public SystemConverter()
    {
        this.src = new String("0");
        this.srcSystem = NumberSystem.ten;
        this.result = new String("0");
        this.resSystem = NumberSystem.ten;
    }

    public void calculate()
    {
        try
        {
            Integer temp;
            if(srcSystem == NumberSystem.two)
                temp = Integer.parseInt(src, 2);
            else if(srcSystem == NumberSystem.eight)
                temp = Integer.parseInt(src, 8);
            else if(srcSystem == NumberSystem.ten)
                temp = Integer.valueOf(src);
            else
                temp = Integer.parseInt(src, 16);
            if(resSystem == NumberSystem.two)
                result = Integer.toBinaryString(temp.intValue());
            else if(resSystem == NumberSystem.eight)
                result = Integer.toOctalString(temp.intValue());
            else if(resSystem == NumberSystem.ten)
                result = Integer.toString(temp.intValue());
            else
                result = Integer.toHexString(temp.intValue());
        }
        catch(Exception e)
        {
            src = new String("0");
            result = new String("错误");
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public NumberSystem getSrcSystem() {
        return srcSystem;
    }

    public void setSrcSystem(NumberSystem srcSystem) {
        this.srcSystem = srcSystem;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public NumberSystem getResSystem() {
        return resSystem;
    }

    public void setResSystem(NumberSystem resSystem) {
        this.resSystem = resSystem;
    }
}
