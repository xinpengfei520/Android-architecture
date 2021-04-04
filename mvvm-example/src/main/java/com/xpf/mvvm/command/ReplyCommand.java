package com.xpf.mvvm.command;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable;

/**
 * Created by x-sir on 3/26/21 :)
 * Function:
 */
public class ReplyCommand<T> {

    private Action action;
    private Consumer<T> consumer;
    private ObservableFromCallable<Boolean> observableFromCallable;

    public ReplyCommand(Action action) {
        this.action = action;
    }

    public ReplyCommand(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    /**
     * @param action     callback for event
     * @param callable if this function return true the action action would be invoked! otherwise would't invoked!
     */
    public ReplyCommand(Action action, ObservableFromCallable<Boolean> callable) {
        this.action = action;
        this.observableFromCallable = callable;
    }

    /**
     * @param consumer     callback for event,this callback need a params
     * @param callable if this function return true the action consumer would be invoked! otherwise would't invoked!
     */
    public ReplyCommand(Consumer<T> consumer, ObservableFromCallable<Boolean> callable) {
        this.consumer = consumer;
        this.observableFromCallable = callable;
    }


    public void execute() {
        if (action != null && canExecute0()) {
            try {
                action.run();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private boolean canExecute0() {
        if (observableFromCallable == null) {
            return true;
        }
        // TODO: 3/26/21 check it right?
        try {
            return observableFromCallable.get();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return false;
    }


    public void execute(T parameter) {
        if (consumer != null && canExecute0()) {
            try {
                consumer.accept(parameter);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
