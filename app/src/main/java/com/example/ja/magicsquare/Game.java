package com.example.ja.magicsquare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Game extends AppCompatActivity {

    View k;
    Chronometer chrono;
    TextView mText;
    EditText mEdit;
    Button mButton;
    Random rnd = new Random();
    int[] tab = {1,2,3,4,5,6,7,8,9};
    int[] edittextid = {R.id.number00,R.id.number02,R.id.number04,
            R.id.number20, R.id.number22, R.id.number24,
            R.id.number40, R.id.number42, R.id.number44};
    ArrayList<Integer> list = new ArrayList<>();
    int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mButton = (Button)findViewById(R.id.NewGame);
        mButton.performClick();
    }

    public void ShuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    public void help(View v) {
        System.out.println(v);
        EditText m[] = new EditText[9];
        for (int k = 0; k < 9; k++) {
            m[k] = (EditText) findViewById(edittextid[k]);
        }

        int i = 0;
        while(m[0].isEnabled() || m[1].isEnabled() || m[2].isEnabled() || m[3].isEnabled() || m[4].isEnabled() || m[5].isEnabled() || m[6].isEnabled() || m[7].isEnabled() || m[8].isEnabled()) {
            i = rnd.nextInt(9);
            if(!list.contains(i)){
                break;
            }
        }
        list.add(i);
        mEdit = (EditText)findViewById(edittextid[i]);

        if (edittextid[i] == R.id.number00 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[0]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number02 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[1]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number04 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[2]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number20 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[3]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number22 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[4]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number24 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[5]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number40 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[6]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number42 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[7]));
            mEdit.setEnabled(false);
        }
        else if (edittextid[i] == R.id.number44 && mEdit.isEnabled()) {
            mEdit.setText(String.valueOf(tab[8]));
            mEdit.setEnabled(false);
        }

        if (!m[0].isEnabled() && !m[1].isEnabled() && !m[2].isEnabled() && !m[3].isEnabled() && !m[4].isEnabled() && !m[5].isEnabled() && !m[6].isEnabled() && !m[7].isEnabled() && !m[8].isEnabled()) {
            mButton = (Button) findViewById(R.id.help);
            mButton.setEnabled(false);
        }
    }




    public void initialize(View v){
        setContentView(R.layout.activity_game);
        chrono = (Chronometer) findViewById(R.id.chronometer1);
        chrono.clearFocus();
        chrono.start();
        list.clear();
        ShuffleArray(tab);

        int summ06 = tab[0] + tab[1] + tab[2];
        int summ26 = tab[3] + tab[4] + tab[5];
        int summ46 = tab[6] + tab[7] + tab[8];
        int summ60 = tab[0] + tab[3] + tab[6];
        int summ62 = tab[1] + tab[4] + tab[7];
        int summ64 = tab[2] + tab[5] + tab[8];

        mEdit = (EditText)findViewById(R.id.sum06);
        mEdit.setText(String.valueOf(summ06));
        mEdit = (EditText)findViewById(R.id.sum26);
        mEdit.setText(String.valueOf(summ26));
        mEdit = (EditText)findViewById(R.id.sum46);
        mEdit.setText(String.valueOf(summ46));
        mEdit = (EditText)findViewById(R.id.sum60);
        mEdit.setText(String.valueOf(summ60));
        mEdit = (EditText)findViewById(R.id.sum62);
        mEdit.setText(String.valueOf(summ62));
        mEdit = (EditText)findViewById(R.id.sum64);
        mEdit.setText(String.valueOf(summ64));

        mButton = (Button) findViewById(R.id.NewGame);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.conti);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.submit);
        mButton.setEnabled(true);

        mButton = (Button) findViewById(R.id.help);
        for (int i = 0; i < count; i++)
            mButton.performClick();
    }

    public void exit(View v) {
        finish();
    }

    public int convert(int n){
        int number = 0;
        mEdit = (EditText)findViewById(n);
        try {
            number = Integer.parseInt(mEdit.getText().toString());
        }
        catch (NumberFormatException nfe){
            System.out.println("Could not parse " + nfe);
        }
        return number;
    }

    public boolean areValuesUnique( int[] values )
    {
        for( int i = 0; i < values.length; ++i )
        {
            for( int j = i + 1; j < values.length; ++j )
            {
                if( values[i] == values[j] )
                    return false;
            }
        }
        return true;
    }

    public void resume(View v){
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.conti);
        mButton.setEnabled(false);
        mButton = (Button) findViewById(R.id.submit);
        mButton.setEnabled(true);
        mButton = (Button)findViewById(R.id.help);
        mButton.setEnabled(true);
    }

    public void submit(View v) {
        int[] tab = new int[9];
        tab[0] = convert(R.id.number00);
        tab[1] = convert(R.id.number02);
        tab[2] = convert(R.id.number04);

        tab[3] = convert(R.id.number20);
        tab[4] = convert(R.id.number22);
        tab[5] = convert(R.id.number24);

        tab[6] = convert(R.id.number40);
        tab[7] = convert(R.id.number42);
        tab[8] = convert(R.id.number44);

        int[] tab2 = new int[6];
        tab2[0] = convert(R.id.sum06);
        tab2[1] = convert(R.id.sum26);
        tab2[2] = convert(R.id.sum46);

        tab2[3] = convert(R.id.sum60);
        tab2[4] = convert(R.id.sum62);
        tab2[5] = convert(R.id.sum64);

        int value1 = tab[0] + tab[1] + tab[2];
        int value2 = tab[3] + tab[4] + tab[5];
        int value3 = tab[6] + tab[7] + tab[8];

        int value4 = tab[0] + tab[3] + tab[6];
        int value5 = tab[1] + tab[4] + tab[7];
        int value6 = tab[2] + tab[5] + tab[8];

        if (areValuesUnique(tab) == true) {
            if (tab2[0] != value1 || tab2[1] != value2 || tab2[2] != value3 || tab2[3] != value4 || tab2[4] != value5 || tab2[5] != value6) {
                mText = (TextView) findViewById(R.id.textView);
                mText.setText("WRONG ANSWER");
                mButton = (Button) findViewById(R.id.submit);
                mButton.setEnabled(false);
                mButton = (Button) findViewById(R.id.NewGame);
                mButton.setEnabled(true);
                mButton = (Button) findViewById(R.id.conti);
                mButton.setEnabled(true);
                mButton = (Button)findViewById(R.id.help);
                mButton.setEnabled(false);


            } else {
                mText = (TextView) findViewById(R.id.textView);
                mText.setText("SUCCESS");
                mButton = (Button) findViewById(R.id.NewGame);
                mButton.setEnabled(true);
                mButton = (Button) findViewById(R.id.conti);
                mButton.setEnabled(false);
                mButton = (Button) findViewById(R.id.submit);
                mButton.setEnabled(false);
                mButton = (Button)findViewById(R.id.help);
                mButton.setEnabled(false);
                chrono = (Chronometer) findViewById(R.id.chronometer1);
                chrono.stop();
            }
        }
        else{
            mText = (TextView) findViewById(R.id.textView);
            mText.setText("WRONG ANSWER");
            mButton = (Button) findViewById(R.id.submit);
            mButton.setEnabled(false);
            mButton = (Button) findViewById(R.id.NewGame);
            mButton.setEnabled(true);
            mButton = (Button) findViewById(R.id.conti);
            mButton.setEnabled(true);
            mButton = (Button)findViewById(R.id.help);
            mButton.setEnabled(false);
        }

    }

    public void levels(View v){
        setContentView(R.layout.levels);
    }

    public void level1(View v){
        setContentView(R.layout.activity_game);
        count = 8;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level2(View v){
        setContentView(R.layout.activity_game);
        count = 7;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level3(View v){
        setContentView(R.layout.activity_game);
        count = 6;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level4(View v){
        setContentView(R.layout.activity_game);
        count = 5;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level5(View v){
        setContentView(R.layout.activity_game);
        count = 4;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level6(View v){
        setContentView(R.layout.activity_game);
        count = 3;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level7(View v){
        setContentView(R.layout.activity_game);
        count = 2;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level8(View v){
        setContentView(R.layout.activity_game);
        count = 1;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();

    }

    public void level9(View v){
        setContentView(R.layout.activity_game);
        count = 0;
        mButton = (Button) findViewById(R.id.NewGame);
        mButton.performClick();
    }


}


