package com.arasu.vt.moneyhandler.swipegestures;

/**
 * Created by kyros on 22-09-2017.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
