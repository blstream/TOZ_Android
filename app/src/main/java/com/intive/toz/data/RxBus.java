package com.intive.toz.data;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * The type Rx bus.
 */
public class RxBus {

    private final Relay<Object> bus = PublishRelay.create().toSerialized();

    /**
     * Send.
     *
     * @param o the o
     */
    public void send(final Object o) {
        bus.accept(o);
    }

    /**
     * As flowable flowable.
     *
     * @return the flowable
     */
    public Flowable<Object> asFlowable() {
        return bus.toFlowable(BackpressureStrategy.LATEST);
    }

    /**
     * Has observers boolean.
     *
     * @return the boolean
     */
    public boolean hasObservers() {
        return bus.hasObservers();
    }
}