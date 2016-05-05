package co.tomwalters;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Callback;

import android.widget.Toast;


public class RNDeviceDisplay extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNDeviceDisplay(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "DisplayDeviceUtil";
  }

  private boolean isTablet(Context context) {
      return (context.getResources().getConfiguration().screenLayout
              & Configuration.SCREENLAYOUT_SIZE_MASK)
              >= Configuration.SCREENLAYOUT_SIZE_LARGE;
  }
  //
  // private boolean isTablet(Context context) {
  //   return true;
  //     // boolean isTablet = false;
  //     // Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
  //     // DisplayMetrics metrics = new DisplayMetrics();
  //     // display.getMetrics(metrics);
  //     //
  //     // float widthInches = metrics.widthPixels / metrics.xdpi;
  //     // float heightInches = metrics.heightPixels / metrics.ydpi;
  //     // double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
  //     // if (diagonalInches >= 7.0) {
  //     //    isTablet = true;
  //     // }
  //     // Toast.makeText(getReactApplicationContext(), "D: " + Double.toString(diagonalInches), Toast.LENGTH_LONG).show();
  //     // return true;
  //     // return isTablet;
  // }

  /**
   * Read the 'isTablet' resource and determine if
   * the device is a phone or tablet based on size
   *
   * See http://stackoverflow.com/questions/9279111/ for a full explaination
   */
  @Override
  public Map<String, Object> getConstants() {
    // boolean isTablet = getReactApplicationContext().getResources().getBoolean(R.bool.isTablet);
    boolean isTablet = this.isTablet(getReactApplicationContext());

    final Map<String, Object> constants = new HashMap<>();
    constants.put("isPhone", !isTablet);
    constants.put("isTablet", isTablet);
    constants.put("testConst", "Hi there");
    return constants;
  }
}
