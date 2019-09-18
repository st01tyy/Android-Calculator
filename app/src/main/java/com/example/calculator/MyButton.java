package com.example.calculator;

import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;

import com.example.calculator.表达式计算.Jury;

public class MyButton extends AppCompatButton
{
    private EditText editText;
    private Type type;
    public MyButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    public void setOnClickFunction(EditText editText, Type type)
    {
        super.setOnClickListener(new MyOnClickListener(type, editText, this));
    }
}
class MyOnClickListener implements View.OnClickListener
{
    private Type type;
    private EditText editText;
    private MyButton myButton;
    public MyOnClickListener(Type type, EditText editText, MyButton myButton)
    {
        super();
        this.type = type;
        this.editText = editText;
        this.myButton = myButton;
    }
    @Override
    public void onClick(View view)
    {
        if(type == Type.type_1)
        {
            StringBuffer sb = new StringBuffer(editText.getText());
            if(myButton.getText().equals("xⁿ"))
                sb.append('^');
            else
                sb.append(myButton.getText());
            editText.setText(sb.toString());
        }
        else
        {
            StringBuffer sb = new StringBuffer(editText.getText());
            sb.append(myButton.getText());
            sb.append('(');
            editText.setText(sb.toString());
        }
    }
}


