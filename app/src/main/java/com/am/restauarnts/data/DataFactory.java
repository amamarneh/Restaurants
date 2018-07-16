package com.am.restauarnts.data;

import com.am.restauarnts.data.network.AppWebHelper;
import com.am.restauarnts.data.network.WebHelper;

public class DataFactory {
    public static WebHelper getWebHelper(){
        return new AppWebHelper();
    }
}
