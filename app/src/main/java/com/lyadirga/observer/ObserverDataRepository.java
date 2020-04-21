package com.lyadirga.observer;

import java.util.ArrayList;

public class ObserverDataRepository implements Subject {
    private Object object;
    private static ObserverDataRepository INSTANCE = null;

    private ArrayList<ObserverKanal> mObservers;

    private ObserverDataRepository() {
        mObservers = new ArrayList<>();
    }

    // SINGELTON
    public static ObserverDataRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ObserverDataRepository();
        }
        return INSTANCE;
    }

    @Override
    public void registerObserver(ObserverKanal repositoryObserver) {
        if(!mObservers.contains(repositoryObserver)) {
            mObservers.add(repositoryObserver);
        }
    }

    @Override
    public void removeObserver(ObserverKanal repositoryObserver) {
        if(mObservers.contains(repositoryObserver)) {
            mObservers.remove(repositoryObserver);
        }
    }

    @Override
    public void notifyObservers() {
        for (ObserverKanal observer: mObservers) {
            observer.onUpdate(object);
        }
    }

    public void setUserData(Object object) {
        this.object = object;
        notifyObservers();
    }
}
