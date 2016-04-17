package com.example.marcio.myphonebookone;

/**
 * Created by marcio on 17/04/2016.
 */
import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowManager;

public class myphonebookone extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        FlowManager.init(this);
    }
}
