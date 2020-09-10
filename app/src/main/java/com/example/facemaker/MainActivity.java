/*
* FaceMaker Activity
* @author Emily Hoppe
*
* 9/8/20
* CS301
*
*/
package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String[] hairStyles = {"Bald", "Short", "Medium", "Long"};
    private Spinner hairSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hairSpinner = findViewById(R.id.styleSpinner);
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                this.hairStyles);
        hairSpinner.setAdapter(hairAdapter);

    }
}

/*
 * Face class
 *
 * Contains variables to manage skin, eye, and hair color
 * as well as hair style
 *
 * Contains 2 methods
 *      constructor - face()
 *      constructor support - randomize()
 */
class face{
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    /**
     * constructs face object by randomizing starting values
     * using a call to the randomize method
     *
     * CAVEAT: Does not connect to XML code until part B of project
     */
    public void face() {
        randomize();
    }

    /**
     * randomizes values for all variables of face
     * colors randomize from 0 to 255
     * hair style randomizes from 1 to 4
     */
    public void randomize(){
        this.skinColor = (int)Math.random()*256;
        this.eyeColor = (int)Math.random()*256;
        this.hairColor = (int)Math.random()*256;
        this.hairStyle = (int)Math.random()*4 + 1;
    }

}