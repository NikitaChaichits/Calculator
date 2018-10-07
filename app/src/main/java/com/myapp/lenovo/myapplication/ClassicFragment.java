package com.myapp.lenovo.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class ClassicFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener{

    Button btnDel;
    EditText editText;
    boolean isGetResult = false, isDotEnter = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_classic, container, false);
        initlayout(view);
        return view;
    }

    public void initlayout(View view){

        int buttons[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnDot, R.id.btnDel, R.id.btnDiv, R.id.btnMult,
                R.id.btnMinus, R.id.btnPlus, R.id.btnResult, R.id.btnLbracket, R.id.btnRbracket, R.id.btnPow};

        for (int i=0;i<buttons.length;i++){
            Button btn = (Button) view.findViewById(buttons[i]);
            btn.setOnClickListener(this);
        }

        btnDel = (Button) view.findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);btnDel.setOnLongClickListener(this);
        editText = (EditText) view.findViewById(R.id.editText1);
    }

    void writeText(String text){
        editText.setText(text);
        editText.setSelection(editText.getText().length());
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        switch (v.getId()) {
            case R.id.btnLbracket: {
                canClear();
                if(isTextEmpty() || editText.getText().toString().equals("0")){
                    addSymbol('(');
                    }else if (isDot(text.charAt(text.length() - 1))){
                        return;
                        }else if(!isOperator(text.charAt(text.length() - 1))){
                            writeText(editText.getText().toString() + "*(");
                            }else {
                                addSymbol('(');
                                }
                isGetResult = false;
                break;
            }case R.id.btnRbracket: {
                canClear();
                if(isTextEmpty() || isOperator(text.charAt(text.length() - 1)) || !checkCloseBrackets(text)){
                    return;
                }
                addSymbol(')');
                isGetResult = false;
                break;
            }case R.id.btn1: {
                addSymbol('1');
                break;
            }case R.id.btn2: {
                addSymbol('2');
                break;
            }case R.id.btn3: {
                addSymbol('3');
                break;
            }case R.id.btn4: {
                addSymbol('4');
                break;
            }case R.id.btn5: {
                addSymbol('5');
                break;
            }case R.id.btn6: {
                addSymbol('6');
                break;
            }case R.id.btn7: {
                addSymbol('7');
                break;
            }case R.id.btn8: {
                addSymbol('8');
                break;
            }case R.id.btn9: {
                addSymbol('9');
                break;
            }case R.id.btn0: {
                canClear();
                if (isTextEmpty()) {
                    addSymbol('0');
                }else if (text.equals("0")){
                    return;
                    }else if (isOperatorforZero(text.charAt(text.length() - 1))){
                        writeText(editText.getText().toString() + "0.");
                        isDotEnter = true;
                        }else{
                            addSymbol('0');
                        }
                isGetResult = false;
                break;
            }case R.id.btnDot: {
                if (isTextEmpty() || isDotEnter || isGetResult || isOperator(text.charAt(text.length() - 1))) {
                    return;
                    }else{
                        writeText(editText.getText().toString() + ".");
                        }
                isDotEnter = true;
                isGetResult = false;
                break;
            }case R.id.btnPlus: {
                addOperand('+');
                break;
            }case R.id.btnMinus: {
                addOperand('-');
                break;
            }case R.id.btnDiv: {
                addOperand('/');
                break;
            }case R.id.btnMult: {
                addOperand('*');
                break;
            }case R.id.btnPow: {
                addOperand('^');
                break;
            }case R.id.btnDel: {
                if (isTextEmpty()) {
                    return;
                }if (text.equals("NaN") || text.equals("Infinity")){
                    writeText("");
                }else {
                    writeText(text.substring(0, text.length() - 1));
                }
                isGetResult = false;
                break;
            }case R.id.btnResult: {
                if(isGetResult || isTextEmpty()){
                    return;
                }
                if (isOperator(text.charAt(text.length() - 1)) || checkBrackets(text)){
                    Toast.makeText(getContext(), "Неверное выражение", Toast.LENGTH_LONG).show();
                    return;
                }
                Expression expression = new Expression(text);
                String resultStr = String.valueOf(expression.calculate());
                writeText(resultStr);
                isGetResult = true;
            }
        }
    }

    public static boolean checkZero(String str) {
        boolean bool = true;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length()-1; i++) {
            if (arr[i] == '(' && arr[i+1] == '0'){
                bool = true;
            }
            else bool = false;
        }
        return bool;
    }


    public boolean checkBrackets(String str) {
        boolean bool = true;
        char[] array = str.toCharArray();
        int countOpenBrackets = 0;
        int countCloseBrackets = 0;

        for (int i = 0; i < str.length(); i++) {
            if (array[i] == '(') {
                countOpenBrackets += 1;
            }
            if (array[i] == ')') {
                countCloseBrackets += 1;
            }
            if (countOpenBrackets != countCloseBrackets){
                bool = true;
            }
            else
                bool = false;
        }
        return bool;
    }

    public boolean checkCloseBrackets(String str) {
        boolean bool = true;
        char[] array = str.toCharArray();
        int countOpenBrackets = 0;
        int countCloseBrackets = 0;

        for (int i = 0; i < str.length(); i++) {
            if (array[i] == '(') {
                countOpenBrackets += 1;
            }
            if (array[i] == ')') {
                countCloseBrackets += 1;
            }
            if (countOpenBrackets > countCloseBrackets){
                bool = true;
            }
            else
                bool = false;
        }
        return bool;
    }

    void canClear(){
        if(isGetResult){
            editText.setText("");
        }
    }

    void addSymbol (char c) {
        canClear();
        if (isTextEmpty()){
            writeText(editText.getText().toString() + c);
        }else if(editText.getText().toString().equals("0")) {
            writeText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1) + c);
        }else if (checkZero(editText.getText().toString()) && editText.getText().toString().length() > 1) {
            writeText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1) + c);
        }else if(editText.getText().toString().charAt(editText.getText().toString().length() - 1)== ')'){
            writeText(editText.getText().toString() + "*"+ c);
        }else{
            writeText(editText.getText().toString() + c);
            }
        isGetResult = false;
    }

    void addOperand (char c){
        if(isTextEmpty()){
            return;
        }
        if(!isOperator(editText.getText().toString().charAt(editText.length() - 1))){
            writeText(editText.getText().toString() + c);
        }
        isGetResult = false;
        isDotEnter = false;
    }

    @Override
    public boolean onLongClick(View v) {
        editText.setText("");
        return true;
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '.' || c == '(';
    }

    boolean isOperatorforZero(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    boolean isDot(char c) {
        return  c == '.';
    }

    boolean isTextEmpty() {
        return editText.getText().toString().equals("");
    }
}


