/*
 * FaceMaker Activity
 * @author Emily Hoppe
 *
 * 9/30/20
 * CS301
 *
 */
package com.example.facemaker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;


public class FaceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,  AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener{

    private Face face;
    private int radioSelection = 1; //RadioGroup starts with "Hair" selected

    public FaceController(Face face) {
        this.face = face;
    }

    /* Constructor */

    @Override
    public void onClick(View view) {
        this.face.randomize();
        this.face.invalidate();
    }


    /**
     * Takes color and value of seekBar and updates the appropriate aspect
     * of face's color to match
     *
     * @param seekBar the seekBar that has had the value changes
     * @param prog the value along the seekBar
     * @param b true if progress changed by user
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int prog, boolean b) {

        // Which seekBar is being listened to
        int id = seekBar.getId();
        int r,g,bl;
        int color = 0;
        int [] rgb;

        //Managing the color (R,G,B) and aspect (hair,eye,skin) that is changing
        switch (id) {
            case 2131230984: //Red seekBar

                //set red aspect to progress
                r = prog;

                //Pull current color
                if(radioSelection == 1) { color = face.getHairC(); } //Hair
                else if(radioSelection == 2){ color = face.getEye(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkin(); } //Skin

                //Pull current aspects
                rgb = face.colorToRGB(color);
                g = rgb[1];
                bl = rgb[2];
                break;

            case 2131230889: //Green seekBar

                //set green aspect to progress
                g = prog;

                //Pull current color
                if(radioSelection == 1) { color = face.getHairC(); } //Hair
                else if(radioSelection == 2){ color = face.getEye(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkin(); } //Skin

                //Pull current aspects
                rgb = face.colorToRGB(color);
                r = rgb[0];
                bl = rgb[2];
                break;

            case 2131230806: //Blue seekBar

                //set blue aspect to progress
                bl = prog;

                //Pull current color
                if(radioSelection == 1) { color = face.getHairC(); } //Hair
                else if(radioSelection == 2){ color = face.getEye(); } //Eyes
                else if (radioSelection == 3) { color = face.getSkin(); } //Skin

                //Pull current aspects
                rgb = face.colorToRGB(color);
                r = rgb[0];
                g = rgb[1];
                break;
            default:
                return;
        }

        //put all color aspects back together
        color = face.RGBtoColor(r,g,bl);

        //Set updated color in face
        if(radioSelection == 1) { face.sethairC(color); } //Hair
        else if(radioSelection == 2){ face.setEye(color); } //Eyes
        else if (radioSelection == 3) {face.setSkin(color); } //Skin

        face.invalidate();
    }


    /**
     External Citation
     Date: 9/30/2020
     Problem: Needed to identify listener for spinner selection event
     Resource:
     https://stackoverflow.com/questions/42507295/listener-on-a-spinner
     Solution: I learned how to set up a spinner listener*/

    /**
     * manages which item has been selected from the spinner and conveys
     * that info to the face object
     *
     * @param adapterView the spinner that is being listened to
     * @param view the view within the adapter that was clicked
     * @param pos position selected
     * @param l row id of the selected item
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

        //communicate update to face
        switch (pos) {
            case 0: //"Bald" selected
                this.face.sethairS(1);
                break;
            case 1: //"Short" selected
                this.face.sethairS(2);
                break;
            case 2: //"long" selected
                this.face.sethairS(3);
                break;
        }

        this.face.invalidate();
    }


    /**
     External Citation
     Date: 9/30/2020
     Problem: Needed to identify listener for radioGroup selection event
     Resource:
     https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
     Solution: I learned how to set up a radioGroup listener*/

    /**
     * manages radioGroup selection and communcated selection to face object
     *
     * @param radioGroup radioGroup where selection occured
     * @param i id of selected radiobutton
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        //Which button was selected
        if(i == R.id.hairRadioButton) { //Hair
            radioSelection = 1; //updates selection status in faceController
            this.face.setRadSelect(1); //updates selection status in face
        }
        else if ( i == R.id.eyesRadioButton) { //Eyes
            radioSelection = 2;
            this.face.setRadSelect(2);
        }
        else if(i == R.id.skinRadioButton) { //Skin
            radioSelection = 3;
            this.face.setRadSelect(3);
        }

        //Updates widget display
        this.face.updateWidgets();
    }



    /* Unused methods required from interfaces*/

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not used
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not used
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //not used
    }
}
