package com.android.plugins;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Button;
import android.view.ViewGroup;

/**
 * This class echoes a string called from JavaScript.
 */
public class realwearforcerefresh extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("forceRefresh")) {
            this.forceRefresh(callbackContext);
            return true;
        }
        return false;
    }

    private void forceRefresh(CallbackContext callbackContext) {
        try {
            Context context = this.cordova.getActivity().getApplicationContext();
            Button button = new Button(context);
            ViewGroup viewGroup = (ViewGroup)((ViewGroup)this.cordova.getActivity().findViewById(android.R.id.content)).getChildAt(0);

            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    viewGroup.addView(button);
                    viewGroup.removeView(button);
                }
            });

            if (true) {
                callbackContext.success();
            } else {
                callbackContext.error("Unknown error");
            }
        }
        catch (Exception e) {
            callbackContext.error(e.toString());
        }
    }
}
