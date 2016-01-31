package org.myfarm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by jakeglass on 1/31/16.
*/
public class InputToCalculatorActivity extends Fragment{

    public Plot plot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        plot = new Plot();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plot_edit_popover, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
    }

    public  void saveButtonClicked(){
        Log.d("AAH","TRIANGLESS AVES");
        plot.plotName = ((EditText)getActivity().findViewById(R.id.plot_name_field)).getText().toString();

        //get values from radio selectors
        int cropRadioId = ((RadioGroup)getActivity().findViewById(R.id.crop_radio_group)).getCheckedRadioButtonId();
        int fertilizerRadioId = ((RadioGroup)getActivity().findViewById(R.id.fertilizer_radio_group)).getCheckedRadioButtonId();

        String cropChoice = ((RadioButton)(getActivity().findViewById(cropRadioId))).getText().toString();
        String fertilizerChoice = ((RadioButton)(getActivity().findViewById(fertilizerRadioId))).getText().toString();

        plot.crop = cropChoice;
        plot.fertilizerType = fertilizerChoice;

        //DO CALCULATIONS & show the view plot stats winder'
        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = layoutInflater.inflate(R.layout.view_plot_stats, null, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        PopupWindow popWindow = new PopupWindow(inflatedView, size.x - 50,size.y - 500, true);

        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));        //popWindow.setAnimationStyle(android.R.anim.an); // call this before showing the popup

        //((TextView)findViewById(R.id.plot_name_display)).setText(plot.plotName);
        Crop crop;
        switch (plot.crop){
            case "Corn":
                crop = Crop.CORN;
                break;
            case "Grain":
                crop = Crop.GRAIN;
                break;
            case "Soybean":
                crop = Crop.SOYBEAN;
                break;
            case "Rice":
                crop = Crop.RICE;
                break;
            default:
                crop = Crop.SOYBEAN;
                break;
        }
        Fertilizer fertilizer;
        switch (plot.fertilizerType){
            case "Urea":
                fertilizer = Fertilizer.UREA;
                break;
            case "Swine":
                fertilizer = Fertilizer.SWINE;
                break;
            case "Chicken":
                fertilizer = Fertilizer.CHICKEN;
                break;
            case "Cow":
                fertilizer = Fertilizer.COW;
                break;
            case "Sheep":
                fertilizer = Fertilizer.SHEEP;
                break;
            default:
                fertilizer = Fertilizer.CHICKEN;
                break;
        }
        //run the calculator
        OptimalValuesCalculator calculator = new OptimalValuesCalculator(plot.plotArea,crop,fertilizer);

        //set all of the views to display the output
        Log.d("TAGGGGGGG", (((Double) calculator.getSeedAmount()).toString()) + "SEED FERT" + (((Double) calculator.getFertilizerAmount()).toString()));
        ((TextView)getActivity().findViewById(R.id.seed_amount_display)).setText(((Double) calculator.getSeedAmount()).toString());
        ((TextView)getActivity().findViewById(R.id.fertilizer_amount_display)).setText(((Double) calculator.getFertilizerAmount()).toString());
        ((TextView)getActivity().findViewById(R.id.water_amount_display)).setText(((Double)calculator.getWaterAmount()).toString());
        ((TextView)getActivity().findViewById(R.id.land_area_display)).setText(((Float) plot.plotArea).toString());
        ((TextView)getActivity().findViewById(R.id.crop_type_display)).setText(cropChoice);
        ((TextView)getActivity().findViewById(R.id.fertilizer_type_display)).setText(fertilizerChoice);
        ((TextView)getActivity().findViewById(R.id.plot_recommendations_field)).setText("Recommendations for "+plot.plotName);

        popWindow.showAtLocation(getActivity().findViewById(R.id.main_bar_view), Gravity.BOTTOM, 0, 150);  // 0 - X postion and 150 - Y position

    }

}
