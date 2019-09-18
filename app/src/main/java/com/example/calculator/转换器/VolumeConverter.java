package com.example.calculator.转换器;

public class VolumeConverter
{
    private String src;
    private VolumeUnit srcUnit;
    private String result;
    private VolumeUnit resUnit;

    public VolumeConverter()
    {
        src = new String("0");
        srcUnit = VolumeUnit.cm3;
        result = new String("0");
        resUnit = VolumeUnit.cm3;
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
            result = format(Double.toString(a));
            return;
        }
        if(srcUnit == VolumeUnit.cm3)
            temp = a;
        else if(srcUnit == VolumeUnit.m3)
            temp = a * Math.pow(10, 6);
        else if(srcUnit == VolumeUnit.ml)
            temp = a;
        else
            temp = a * 1000;
        if(resUnit == VolumeUnit.cm3)
            b = temp;
        else if(resUnit == VolumeUnit.m3)
            b = temp * Math.pow(10, -6);
        else if(resUnit == VolumeUnit.ml)
            b = temp;
        else
            b = temp / 1000;
        result = format(Double.toString(b));
    }

    private String format(String str)
    {
        boolean judge = false;
        for(int a = 0; a < str.length(); a++)
        {
            if(str.charAt(a) == '.')
            {
                judge = true;
                break;
            }
        }
        if(!judge)
            return str;
        int a;
        for(a = str.length() - 1; a >=0 && str.charAt(a) == '0'; a--);
        if(str.charAt(a) == '.')
            a--;
        return str.substring(0, a + 1);
    }


    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public VolumeUnit getSrcUnit()
    {
        return srcUnit;
    }

    public void setSrcUnit(VolumeUnit srcUnit)
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

    public VolumeUnit getResUnit()
    {
        return resUnit;
    }

    public void setResUnit(VolumeUnit resUnit)
    {
        this.resUnit = resUnit;
    }
}
