package com.xpf.mvvm.messenger;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * Created by x-sir on 3/26/21 :)
 * Function:
 */
public class WeakAction<T> {

    private Action action;
    private Consumer<T> consumer;
    private boolean isLive;
    private Object target;
    private WeakReference reference;

    public WeakAction(Object target, Action action) {
        reference = new WeakReference(target);
        this.action = action;
    }

    public WeakAction(Object target, Consumer<T> action1) {
        reference = new WeakReference(target);
        this.consumer = action1;
    }

    public void execute() {
        if (action != null && isLive()) {
            try {
                action.run();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void execute(T parameter) {
        if (consumer != null
                && isLive()) {
            try {
                consumer.accept(parameter);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void markForDeletion() {
        reference.clear();
        reference = null;
        action = null;
        consumer = null;
    }

    public Action getAction() {
        return action;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public boolean isLive() {
        if (reference == null) {
            return false;
        }
        return reference.get() != null;
    }


    public Object getTarget() {
        if (reference != null) {
            return reference.get();
        }
        return null;
    }
}
