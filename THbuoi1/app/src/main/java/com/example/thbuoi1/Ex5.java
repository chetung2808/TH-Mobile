package com.example.thbuoi1;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;




public class Ex5 extends AppCompatActivity {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);


        assignId(button0,R.id.bt_0);
        assignId(button1,R.id.bt_1);
        assignId(button2,R.id.bt_2);
        assignId(button3,R.id.bt_3);
        assignId(button4,R.id.bt_4);
        assignId(button5,R.id.bt_5);
        assignId(button6,R.id.bt_6);
        assignId(button7,R.id.bt_7);
        assignId(button8,R.id.bt_8);
        assignId(button9,R.id.bt_9);
        assignId(buttonAC,R.id.bt_ac);
        assignId(buttonDot,R.id.bt_dot);
        assignId(buttonC,R.id.bt_c);
        assignId(buttonBrackOpen,R.id.bt_open_bracket);
        assignId(buttonBrackClose,R.id.bt_close_bracket);
        assignId(buttonDivide,R.id.bt_divide);
        assignId(buttonMultiply,R.id.bt_multiply);
        assignId(buttonPlus,R.id.bt_plus);
        assignId(buttonMinus,R.id.bt_minus);
        assignId(buttonEquals,R.id.bt_equals);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialButton button =(MaterialButton) view;
                String buttonText = button.getText().toString();
                String dataToCalculate = solutionTv.getText().toString();

                if(buttonText.equals("AC")){
                    solutionTv.setText("");
                    resultTv.setText("0");
                    return;
                }
                if(buttonText.equals("=")){
                    solutionTv.setText(resultTv.getText());
                    return;
                }
                if(buttonText.equals("C")){
                    dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
                }else{
                    dataToCalculate = dataToCalculate+buttonText;
                }
                solutionTv.setText(dataToCalculate);

                String finalResult = getResult(dataToCalculate);

                if(!finalResult.equals("Err")){
                    resultTv.setText(finalResult);
                }
            }
        });
    }


    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}