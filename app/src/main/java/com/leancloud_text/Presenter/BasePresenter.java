package com.leancloud_text.Presenter;

import com.leancloud_text.View.maView;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends maView> {

    private CompositeDisposable disposable;

    private V view;

    public BasePresenter(V view) {
        this.view = view;
        setView(view);
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        disposable = new CompositeDisposable();
        this.view = view;
    }

    public void destroy() {
        try {
            disposable.dispose();
        } catch (Exception e) { }

        this.view = null;
    }
}