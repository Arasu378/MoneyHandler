package com.arasu.vt.moneyhandler.models;

/**
 * Created by kyros on 21-09-2017.
 */

public class Events {
    public static class FragmentAdapterMessage {

        private String message;

        public FragmentAdapterMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class AdapterFragmentMessage {

        private String message;

        public AdapterFragmentMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
