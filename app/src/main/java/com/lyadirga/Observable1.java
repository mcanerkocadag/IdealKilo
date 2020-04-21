package com.lyadirga;

public abstract class Observable1 {
    public Observable1() {
    }
    public synchronized void addObserver(Observer1 o){
    }

    public synchronized void deleteObserver(Observer1 o) {
    }

    protected synchronized void setChanged() {
    }
    public void notifyObservers(Object arg) {
    }
}
