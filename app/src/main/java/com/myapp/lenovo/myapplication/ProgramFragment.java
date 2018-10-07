package com.myapp.lenovo.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.LinkedList;

public class ProgramFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener{
    BigInteger resInDec;
    Integer notation = 10;
    boolean isGetResult = false;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnDel, btnDiv, btnMult, btnMinus, btnPlus, btnResult, btnLBr, btnRBr;
    Button btnA, btnB, btnC, btnD, btnE, btnF;

//    int arrayBIN[] = {R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
//            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnA, R.id.btnB,
//            R.id.btnC, R.id.btnD, R.id.btnE, R.id.btnF};

    EditText editText, editTextBin, editTextOct, editTextDec, editTextHex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_prog, container, false);

        int rb[] = {R.id.radio_bin, R.id.radio_oct,R.id.radio_dec,R.id.radio_hex};

        for (int i=0; i<rb.length; i++){
            RadioButton radioButton = (RadioButton) view.findViewById(rb[i]);
            radioButton.setOnClickListener(radioButtonClickListener);
        }

        editText = (EditText) view.findViewById(R.id.editText);
        editTextBin = (EditText) view.findViewById(R.id.editTextBin);
        editTextOct = (EditText) view.findViewById(R.id.editTextOct);
        editTextDec = (EditText) view.findViewById(R.id.editTextDec);
        editTextHex = (EditText) view.findViewById(R.id.editTextHex);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        btn9 = (Button) view.findViewById(R.id.btn9);
        btn0 = (Button) view.findViewById(R.id.btn0);
        btnDel = (Button) view.findViewById(R.id.btnDel);
        btnDiv = (Button) view.findViewById(R.id.btnDiv);
        btnMult = (Button) view.findViewById(R.id.btnMult);
        btnMinus = (Button) view.findViewById(R.id.btnMinus);
        btnPlus = (Button) view.findViewById(R.id.btnPlus);
        btnResult = (Button) view.findViewById(R.id.btnResult);
        btnLBr = (Button)view.findViewById(R.id.btnLbracket);
        btnRBr = (Button)view.findViewById(R.id.btnRbracket);
        btnA = (Button)view.findViewById(R.id.btnA);
        btnB = (Button)view.findViewById(R.id.btnB);
        btnC = (Button) view.findViewById(R.id.btnC);
        btnD = (Button) view.findViewById(R.id.btnD);
        btnE = (Button) view.findViewById(R.id.btnE);
        btnF = (Button) view.findViewById(R.id.btnF);

        btn1.setOnClickListener(this);btn2.setOnClickListener(this);btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);btn5.setOnClickListener(this);btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);btn8.setOnClickListener(this);btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);btnDel.setOnClickListener(this);btnDel.setOnLongClickListener(this);
        btnPlus.setOnClickListener(this);btnMinus.setOnClickListener(this);btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);btnResult.setOnClickListener(this);
        btnLBr.setOnClickListener(this);btnRBr.setOnClickListener(this);
        btnA.setOnClickListener(this);btnB.setOnClickListener(this);btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);btnE.setOnClickListener(this);btnF.setOnClickListener(this);
        return view;
    }

    void writeText(String text) {
        editText.setText(text);
        editText.setSelection(editText.getText().length());
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.radio_bin:
                    notation = 2;
                    RadioGrClear();
                    btnA.setEnabled(false);btnB.setEnabled(false);btnC.setEnabled(false);
                    btnD.setEnabled(false);btnE.setEnabled(false);btnF.setEnabled(false);
                    btn9.setEnabled(false);btn8.setEnabled(false);btn7.setEnabled(false);
                    btn6.setEnabled(false);btn5.setEnabled(false);btn4.setEnabled(false);
                    btn3.setEnabled(false);btn2.setEnabled(false);
                    break;
                case R.id.radio_oct:
                    notation = 8;
                    RadioGrClear();
                    btnA.setEnabled(false);btnB.setEnabled(false);btnC.setEnabled(false);
                    btnD.setEnabled(false);btnE.setEnabled(false);btnF.setEnabled(false);
                    btn9.setEnabled(false);btn8.setEnabled(false);btn7.setEnabled(true);
                    btn6.setEnabled(true);btn5.setEnabled(true);btn4.setEnabled(true);
                    btn3.setEnabled(true);btn2.setEnabled(true);
                    break;
                case R.id.radio_dec:
                    notation = 10;
                    RadioGrClear();
                    btnA.setEnabled(false);btnB.setEnabled(false);btnC.setEnabled(false);
                    btnD.setEnabled(false);btnE.setEnabled(false);btnF.setEnabled(false);
                    btn9.setEnabled(true);btn8.setEnabled(true);btn7.setEnabled(true);
                    btn6.setEnabled(true);btn5.setEnabled(true);btn4.setEnabled(true);
                    btn3.setEnabled(true);btn2.setEnabled(true);
                    break;
                case R.id.radio_hex:
                    notation = 16;
                    RadioGrClear();
                    btnA.setEnabled(true);btnB.setEnabled(true);btnC.setEnabled(true);
                    btnD.setEnabled(true);btnE.setEnabled(true);btnF.setEnabled(true);
                    btn9.setEnabled(true);btn8.setEnabled(true);btn7.setEnabled(true);
                    btn6.setEnabled(true);btn5.setEnabled(true);btn4.setEnabled(true);
                    btn3.setEnabled(true);btn2.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        switch (v.getId()) {
            case R.id.btnLbracket: {
                canClear();
                if(isTextEmpty()){
                    writeText(editText.getText().toString() + "(");
                    }else if(!isOperator(text.charAt(text.length() - 1))){
                        writeText(editText.getText().toString() + "*(");
                        }else {
                            writeText(editText.getText().toString() + "(");
                            }
                isGetResult = false;
                break;
            }case R.id.btnRbracket: {
                canClear();
                if(isTextEmpty() || isOperator(text.charAt(text.length() - 1)) || !checkCloseBrackets(text)){
                    return;
                }
                writeText(editText.getText().toString() + ")");
                isGetResult = false;
                break;
            }case R.id.btnA: {
                addCharacter('a');
                break;
            }case R.id.btnB: {
                addCharacter('b');
                break;
            }case R.id.btnC: {
                addCharacter('c');
                break;
            }case R.id.btnD: {
                addCharacter('d');
                break;
            }case R.id.btnE: {
                addCharacter('e');
                break;
            }case R.id.btnF: {
                addCharacter('f');
                break;
            }case R.id.btn1: {
                addCharacter('1');
                break;
            }case R.id.btn2: {
                addCharacter('2');
                break;
            }case R.id.btn3: {
                addCharacter('3');
                break;
            }case R.id.btn4: {
                addCharacter('4');
                break;
            }case R.id.btn5: {
                addCharacter('5');
                break;
            }case R.id.btn6: {
                addCharacter('6');
                break;
            }case R.id.btn7: {
                addCharacter('7');
                break;
            }case R.id.btn8: {
                addCharacter('8');
                break;
            }case R.id.btn9: {
                addCharacter('9');
                break;
            }case R.id.btn0: {
                if (text.equals("0")) {
                    return;
                    }else{
                        addCharacter('0');
                        }
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
            }case R.id.btnDel: {
                if (isTextEmpty()){
                    return;
                    }else {
                        writeText(text.substring(0, text.length() - 1));
                        isGetResult = false;
                        }
                break;
            }case R.id.btnResult: {
                if(isGetResult || isTextEmpty() || editText.getText().toString().equals("0")){
                    return;
                }
                if (isOperator(text.charAt(text.length() - 1)) || checkBrackets(text)){
                    Toast.makeText(getContext(), "Неверное выражение", Toast.LENGTH_LONG).show();
                    return;
                }
                if ((text.charAt(text.length() - 1) == '0') && (text.charAt(text.length() - 2) =='/')){
                    writeText("∞");
                    break;
                }
                switch(notation){
                    case 2:
                        resInDec = getResInDec(text, 2);
                        convertTo();
                        break;
                    case 8:
                        resInDec = getResInDec(text, 8);
                        convertTo();
                        break;
                    case 10:
                        resInDec = getResInDec(text, 10);
                        convertTo();
                        break;
                    case 16:
                        resInDec = getResInDec(text, 16);
                        convertTo();
                        break;
                }
            }
        }
    }

    void convertTo(){
        writeText(resInDec.toString(notation));
        isGetResult = true;

        editTextBin.setText(resInDec.toString(2));
        editTextOct.setText(resInDec.toString(8));
        editTextDec.setText(resInDec.toString(10));
        editTextHex.setText(resInDec.toString(16));
    }

    void addCharacter (char c){
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
    }

    public static boolean checkZero(String str) {
        boolean bool = true;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length()-1; i++) {
            if ((arr[i] == '/' || arr[i] == '*' || arr[i] == '-' || arr[i] == '+' || arr[i] == '(' || arr[i] == ')') && arr[i+1] == '0'){
                bool = true;
            }

            else bool = false;
        }
        return bool;
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(';
    }

    boolean isTextEmpty() {
        return editText.getText().toString().equals("");
    }

    int priority(char operand) {
        if (operand == '*' || operand == '/') {
            return 1;
        }
        else if (operand == '+' || operand == '-') {
            return 0;
        }
        else {
            return -1;
        }
    }

    boolean isCalculate(LinkedList<BigInteger> st, char operand) {
        BigInteger One = st.removeLast();
        BigInteger Two = st.removeLast();

        switch (operand) {
            case '+':
                st.add(Two.add(One));
                return true;
            case '-':
                st.add(Two.subtract(One));
                return true;
            case '*':
                st.add(Two.multiply(One));
                return true;
            case '/':
                try{
                    st.add(Two.divide(One));
                    return true;
                }catch(ArithmeticException ex){
                    return false;
                }
            default:
                System.out.println("NaN");
        }
        return true;
    }

    BigInteger getResInDec(String s, int baseFrom) {
        LinkedList<BigInteger> Numbers = new LinkedList<>();
        LinkedList<Character> Operands = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                Operands.add('(');
            }
            else if (c == ')') {
                while (Operands.getLast() != '(') {
                    boolean isGood = isCalculate(Numbers, Operands.removeLast());
                    if (!isGood) {
                        return new BigInteger("0");
                    }
                }
                Operands.removeLast();
            }
            else if (isOperator(c)) {
                while (!Operands.isEmpty() &&
                        priority(Operands.getLast()) >= priority(c)) {
                    boolean isGood = isCalculate(Numbers, Operands.removeLast());
                    if (!isGood) {
                        return new BigInteger("0");
                    }
                }
                Operands.add(c);
            }
            else if (Character.isDigit(c) || Character.isLetter(s.charAt(i))) {
                String operand = "";

                while ((i < s.length() && Character.isDigit(s.charAt(i))) || Character.isLetter(s.charAt(i))) {
                    operand += s.charAt(i++);
                    if (i == s.length()) {
                        break;
                    }
                }
                --i;
                Numbers.add(new BigInteger(operand, baseFrom));
            }
        }

        while (!Operands.isEmpty()) {
            boolean isGood = isCalculate(Numbers, Operands.removeLast());
            if (!isGood) {
                return new BigInteger("0");
            }
        }

        return Numbers.get(0);
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
            if(editTextBin != null){
                editTextBin.setText("");
            }
            if(editTextOct != null){
                editTextOct.setText("");
            }
            if(editTextDec != null){
                editTextDec.setText("");
            }
            if(editTextHex != null){
                editTextHex.setText("");
            }
        }
    }

    void RadioGrClear(){
            editText.setText("");
            if(editTextBin != null){
                editTextBin.setText("");
            }
            if(editTextOct != null){
                editTextOct.setText("");
            }
            if(editTextDec != null){
                editTextDec.setText("");
            }
            if(editTextHex != null){
                editTextHex.setText("");
            }
    }

    @Override
    public boolean onLongClick(View v) {
        isGetResult = false;
        editText.setText("");
        if(editTextBin != null){
            editTextBin.setText("");
        }
        if(editTextOct != null){
            editTextOct.setText("");
        }
        if(editTextDec != null){
            editTextDec.setText("");
        }
        if(editTextHex != null){
            editTextHex.setText("");
        }
        return true;
    }
}
