package com.classy.customers;

import android.os.Bundle;
import com.classy.common.Activity_MapParent;

public class Activity_Map extends Activity_MapParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainRL.setBackgroundResource(R.color.green);
    }
}