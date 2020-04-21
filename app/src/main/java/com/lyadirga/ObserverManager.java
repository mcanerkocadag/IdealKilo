package com.lyadirga;

import java.util.Observer;

public class ObserverManager extends Observable1 {
    private static ObserverManager instance;

    private ObserverManager() {
    }

    public static ObserverManager getInstance() {
        if (instance == null)
            instance = new ObserverManager();
        return instance;
    }

    @Override
    public synchronized void addObserver(Observer1 o) {
        synchronized (this) {
            deleteObserver(o);
            super.addObserver(o);
        }
    }


    public void updateValue(Object data) {
        synchronized (this) {
            setChanged();
            notifyObservers(data);
        }
    }
}

