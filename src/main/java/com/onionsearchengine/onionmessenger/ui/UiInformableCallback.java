package com.onionsearchengine.onionmessenger.ui;

public interface UiInformableCallback<T> extends UiCallback<T> {
    void inform(String text);
}
