package com.free.basemvp.event;



public class BusProvider {

    private static IBus bus;

    public static IBus getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = new RxBusImpl();
                }
            }
        }
        return bus;
    }

}
