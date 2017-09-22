package com.arasu.vt.moneyhandler.swipegestures;

import com.arasu.vt.moneyhandler.dbconnections.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyros on 22-09-2017.
 */

public interface OnBarListChangedListner {
    void onBarListChanged(List<Income> categoryList);
}
