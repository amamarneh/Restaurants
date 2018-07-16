package com.am.restauarnts.task;

public class SingleLiveTask<T> extends SingleLiveEvent<LiveResponse<T>>{
    public void success(T data){
        setValue(LiveResponse.success(data));
    }
    public void error(String msg){
        setValue(LiveResponse.error(msg,null));
    }
}
