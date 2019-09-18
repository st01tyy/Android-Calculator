package com.example.calculator.转换器;

public class LengthConverter
{
    private String src;
    private LengthUnit srcUnit;
    private String result;
    private LengthUnit resUnit;

    public LengthConverter()
    {
        src = new String("0");
        srcUnit = LengthUnit.m;
        result = new String("0");
        resUnit = LengthUnit.m;
    }

    public void calculate()
    {
        double a,temp,b;
        if(src.charAt(src.length() - 1) == '.')
            a = Double.valueOf(src.substring(0, src.length() - 1));
        else
            a = Double.valueOf(src);
        if(srcUnit == resUnit)
        {
            result = Double.toString(a);
            return;
        }
        if(srcUnit == LengthUnit.mm)
        {
            temp = a / 1000;
        }
        else if(srcUnit == LengthUnit.cm)
        {
            temp = a / 100;
        }
        else if(srcUnit == LengthUnit.dm)
        {
            temp = a / 10;
        }
        else if(srcUnit == LengthUnit.m)
        {
            temp = a;
        }
        else if(srcUnit == LengthUnit.km)
        {
            temp = a * 1000;
        }
        else if(srcUnit == LengthUnit.in)
        {
            temp = a * 0.0254;
        }
        else
        {
            temp = a * 0.3048;
        }
        if(resUnit == LengthUnit.mm)
        {
            b = temp * 1000;
        }
        else if(resUnit == LengthUnit.cm)
        {
            b = temp * 100;
        }
        else if(resUnit == LengthUnit.dm)
        {
            b = temp * 10;
        }
        else if(resUnit == LengthUnit.m)
        {
            b = temp;
        }
        else if(resUnit == LengthUnit.km)
        {
            b = temp / 1000;
        }
        else if(resUnit == LengthUnit.in)
        {
            b = temp * 39.3700787;
        }
        else
        {
            b = temp * 3.2808399;
        }
        result = Double.toString(b);
    }


    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public LengthUnit getSrcUnit()
    {
        return srcUnit;
    }

    public void setSrcUnit(LengthUnit srcUnit)
    {
        this.srcUnit = srcUnit;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public LengthUnit getResUnit()
    {
        return resUnit;
    }

    public void setResUnit(LengthUnit resUnit)
    {
        this.resUnit = resUnit;
    }
}
