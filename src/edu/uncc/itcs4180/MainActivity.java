/*
 * James Keller
 * ITCS 4180 - 091
 * 2/11/14
 * HW2 - Discount Calculator
 */

package edu.uncc.itcs4180;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//String constant for log messages
	final static String TAG = "demo";
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Seekbar, discount calculator, and textview instantiations
        final SeekBar sb = (SeekBar) findViewById(R.id.seekBar1);
        final DiscountCalculator dc = new DiscountCalculator();
        final TextView tv2 = (TextView) findViewById(R.id.textView5);
        final TextView tv3 = (TextView) findViewById(R.id.textView6);
        
        //Creation of an alert dialog to be used during exception handling
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("An Error Has Occurred!")
        .setMessage("The aplication will now close.")
        .setCancelable(false)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d(TAG, "An Error Occurred!");
				finish();
				System.exit(0);
			}
		});
        
        //Alert dialog instantiation
        final AlertDialog simpleAlert = builder.create();
        
        //Allows the text display the seekbar's value as a percentage
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        final TextView tv1 = (TextView) findViewById(R.id.textView8);
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tv1.setText(progress + "%");
			}
		});
        
        //Creates and sets up an edit text field that produces a toast alert if a 
        //value isn't entered in the field. Exceptions are handled by an alert dialog
        final EditText et = (EditText) findViewById(R.id.editText1);
        et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			//Displays a toast message for when the edit text field is empty, displays 
			//default values, and recalculates the discount based on edit text field changes
			@Override
			public void afterTextChanged(Editable s) {
				
				try {
					if (et.getText().toString().length() == 0) {
						tv2.setText("$0.00");
						tv3.setText("$0.00");
						et.setError("Enter List Price");
					} else {
						dc.setUserInput(Double.parseDouble(s.toString()));
						tv2.setText(dc.calcSaved());
						tv3.setText(dc.calcPay());
					}
				} catch (Exception e) {
							simpleAlert.show();
						}
				}
		});
        
        //Creates a radio group and uses a switch to determine the exclusive choice of 
        //the user. Based on the radio button clicked, the proper discount is calculated and 
        //displayed via the appropriate textview. Exception are handled with an alert dialog
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) { 
				try {
					switch (checkedId) {
					case R.id.radio0:
						dc.setDiscount(.10);
						tv2.setText(dc.calcSaved());
						tv3.setText(dc.calcPay());
						break;
					case R.id.radio1:
						dc.setDiscount(.25);
						tv2.setText(dc.calcSaved());
						tv3.setText(dc.calcPay());
						break;
					case R.id.radio2:
						dc.setDiscount(.50);
						tv2.setText(dc.calcSaved());
						tv3.setText(dc.calcPay());
						break;
					case R.id.radioButton1:
						double dValue = (double)(sb.getProgress()/100.00);
						dc.setDiscount(dValue);
						tv2.setText(dc.calcSaved());
						tv3.setText(dc.calcPay());
						
						//Another seekbar is created to handled the discount calculations in real-time
						//as the progress bar values are changed
						final SeekBar sb2 = (SeekBar) findViewById(R.id.seekBar1);
						sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					        final TextView tv4 = (TextView) findViewById(R.id.textView8);
								@Override
								public void onStopTrackingTouch(SeekBar seekBar) {
								}
								
								@Override
								public void onStartTrackingTouch(SeekBar seekBar) {
								}
								
								@Override
								public void onProgressChanged(SeekBar seekBar, int progress,
										boolean fromUser) {
									tv4.setText(progress + "%");
									double dValue = (double)(sb.getProgress()/100.00);
									dc.setDiscount(dValue);
									tv2.setText(dc.calcSaved());
									tv3.setText(dc.calcPay());
								}
							});
						break;
					}
				} catch (Exception e) {
					simpleAlert.show();
				}
			}
		});
		
        //Exit button which ends the application
        Button exit = (Button) findViewById(R.id.button1);
        exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				System.exit(0);	
			}
		}); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
