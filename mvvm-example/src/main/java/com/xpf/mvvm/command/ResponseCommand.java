package com.xpf.mvvm.command;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable;

/**
 * Created by x-sir on 3/26/21 :)
 * Function:
 */
public class ResponseCommand<T, R> {

    private ObservableFromCallable<R> observableFromCallable;
    private Function<T, R> function;
    private ObservableFromCallable<Boolean> canExecute0;

    /**
     * like {@link ReplyCommand},but ResponseCommand can return result when command has executed!
     *
     * @param execute function to execute when event occur.
     */
    public ResponseCommand(ObservableFromCallable<R> execute) {
        this.observableFromCallable = execute;
    }

    public ResponseCommand(Function<T, R> execute) {
        this.function = execute;
    }

    public ResponseCommand(ObservableFromCallable<R> execute, ObservableFromCallable<Boolean> canExecute0) {
        this.observableFromCallable = execute;
        this.canExecute0 = canExecute0;
    }

    public ResponseCommand(Function<T, R> execute, ObservableFromCallable<Boolean> canExecute0) {
        this.function = execute;
        this.canExecute0 = canExecute0;
    }

    public R execute() {
        if (observableFromCallable != null && canExecute0()) {
            try {
                return observableFromCallable.get();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    private boolean canExecute0() {
        if (canExecute0 == null) {
            return true;
        }
        try {
            return canExecute0.get();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return false;
    }

    public R execute(T parameter) {
        if (function != null && canExecute0()) {
            try {
                return function.apply(parameter);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

}
