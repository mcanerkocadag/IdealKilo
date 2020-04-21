package com.lyadirga.observer;

    public interface Subject {
        void registerObserver(ObserverKanal observerKanal);
        void removeObserver(ObserverKanal observerKanal);
        void notifyObservers();
    }

