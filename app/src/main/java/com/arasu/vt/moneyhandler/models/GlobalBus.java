package com.arasu.vt.moneyhandler.models;

import com.squareup.otto.Bus;

/**
 * Created by kyros on 21-09-2017.
 */

public class GlobalBus {
    private static Bus sBus;
    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }

}
