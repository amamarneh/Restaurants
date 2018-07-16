package com.am.restauarnts.task;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LiveTask<T> extends LiveData<LiveResponse<T>> {
    public void success(T data){
        setValue(LiveResponse.success(data));
    }
    public void error(String msg){
        setValue(LiveResponse.error(msg,null));
    }
}
