/*
 * FaceMaker Activity
 * @author Emily Hoppe
 *
 * 9/30/20
 * CS301
 *
 */
package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.Spinner;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;
import static android.graphics.Color.rgb;

/*
 * Face class
 *
 * Contains variables to manage skin, eye, and hair color,
 * hair style, and widget updates
 *
 *  Contains face view data and drawing
 *  Manages user-set and randomized values
 *  Manages widget value updates
 *
 */
class Face extends SurfaceView{

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    private Paint skinPaint;
    private Paint eyePaint;
    private Paint hairPaint;

    private SeekBar redSB;
    private SeekBar greenSB;
    private SeekBar blueSB;
    private Spinner hairSS;

    private int radSelect = 1; //RadioGroup starts with "Hair" selected

    /**
     * constructs face object by randomizing starting values
     * using a call to the randomize method
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        randomize();
        setWillNotDraw(false);
    }

    /**
     * randomizes values for color variables of face
     * colors randomize with RBG aspects from 0 to 255
     * hair style randomizes from 1 to 3
     */
    public void randomize() {
        this.skinColor = RGBtoColor((int) (Math.random()*256), (int) (Math.random()*256),(int) (Math.random()*256));
        this.eyeColor = RGBtoColor((int) (Math.random()*256), (int) (Math.random()*256),(int) (Math.random()*256));
        this.hairColor = RGBtoColor((int) (Math.random()*256), (int) (Math.random()*256),(int) (Math.random()*256));
        this.hairStyle = (int) (Math.random()*3 + 1);
    }

    /**
     * pulls 3 seekbars and 1 spinner from mainActivity to update their values
     *
     * @param rsb this contains the red seek bar
     * @param gsb this contains the green seek bar
     * @param bsb this contains the blue seek bar
     * @param hairSS this contains the hair style spinner
     */
    public void setWidgets(SeekBar rsb, SeekBar gsb, SeekBar bsb, Spinner hairSS){

        //Assigns private variables to the external widgets
        this.redSB = rsb;
        this.greenSB = gsb;
        this.blueSB = bsb;
        this.hairSS = hairSS;
    }

    /**
    External Citation
    Date: 10/3/2020
    Problem: I was struggling with how to properly use paints and drawing
    Resource: Grace Penunuri
    Solution: Grace helped me understand the proportions for draw methods and how
     to set the paints and drawing*/
    /**
     * draws the face's skin, hair, and eyes
     * contains call to updateWidgets to manage randomized values
     *
     * @param canvas the space on which to draw
     */
    public void onDraw(Canvas canvas) {
        setBackgroundColor(this.skinColor);
        drawHair(canvas);
        drawEyes(canvas);
        updateWidgets();
    }

    /**
     * draws 1 of three hairstyles
     *
     * @param canvas the space on which to draw, received from onDraw
     */
    public void drawHair(Canvas canvas) {
        hairPaint = new Paint();
        hairPaint.setColor(hairColor);

        //Manage which hairstyle is drawn
        switch (hairStyle) {
            case 1: //Bald
                canvas.drawRect(0,0,50,100,hairPaint);
                canvas.drawRect(280,0,350,100,hairPaint);
                break;
            case 2: //Short Hair
                canvas.drawRect(0,0,350,50,hairPaint);
                canvas.drawRect(0,0,50,100,hairPaint);
                canvas.drawRect(280,0,350,100,hairPaint);
                break;
            case 3: //Long Hair
                canvas.drawRect(0,0,350,50,hairPaint);
                canvas.drawRect(0,0,50,500,hairPaint);
                canvas.drawRect(280,0,350,500,hairPaint);
                break;
        }


    }

    /**
     * draws the eyes on the face
     *
     * @param canvas the space on which to draw, received from onDraw
     */
    public void drawEyes(Canvas canvas){

        //Draw whites of the eyes
        Paint sclera = new Paint();
        sclera.setColor(0xffffffff);
        RectF rect = new RectF(70,100,150,150);
        RectF rect2 = new RectF(170,100,250,150);
        canvas.drawOval(rect,sclera);
        canvas.drawOval(rect2,sclera);

        //Draws colored iris
        eyePaint = new Paint();
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(130,125,20,eyePaint);
        canvas.drawCircle(230,125,20,eyePaint);


    }

    /**
     * Updates widgets to express values as set by randomizer or previousy by user
     */
    public void updateWidgets(){

        //Determine spinner selection
        int[] rgb = {0,0,0};
        if(radSelect == 1) { //Hair
            rgb = colorToRGB(this.hairColor);
        }
        else if(radSelect == 2){ //Eye
            rgb = colorToRGB(this.eyeColor);
        }
        else if(radSelect == 3){ //Skin
            rgb = colorToRGB(this.skinColor);
        }

        //Update seekBars to contain current RGB values
        this.redSB.setProgress(rgb[0]);
        this.greenSB.setProgress(rgb[1]);
        this.blueSB.setProgress(rgb[2]);

        //Update spinner to contain current hairstyle value
        this.hairSS.setSelection((hairStyle-1));
    }


    /* Setters and getters for color and style ints*/

    public void setSkin(int skin){ this.skinColor = skin; }
    public int getSkin(){ return skinColor; }

    public void setEye(int eye) { this.eyeColor = eye; }
    public int getEye(){ return eyeColor; }

    public void sethairC(int hairC) { this.hairColor = hairC; }
    public int getHairC(){ return hairColor; }

    public void sethairS(int hairS) { this.hairStyle = hairS; }
    public int gethairS(){ return hairStyle; }

    public void setRadSelect(int r){ this.radSelect = r;}
    public int getRadSelect(){return this.radSelect;}

    /**
     External Citation
     Date: 10/4/2020
     Problem: needed a way to convert RGB values to hex colors and vice versa
     Resource:
     https://developer.android.com/reference/kotlin/android/graphics/Color
     Solution: I found a few methods in the Color class that manage these conversions */

    /* Assistant methods to manage color conversions between RGB ints and color hex values*/

    public int[] colorToRGB(int color){
        int[] rgb = {red(color), green(color), blue(color)};
        return rgb;
    }

    public int RGBtoColor(int r, int g, int bl){
        int color = rgb(r,g,bl);
        return color;
    }

}

