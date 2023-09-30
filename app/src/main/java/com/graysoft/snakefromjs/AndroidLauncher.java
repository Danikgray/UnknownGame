package com.graysoft.snakefromjs;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        initialize(new MainGame(), cfg);
    }
    
    protected void onResume() {
        super.onResume();
    }

    
    protected void onPause() {
        super.onPause();
    }
}
