package com.example.ethan.androidprojectfinal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class FestivalViewModel extends AndroidViewModel {

    private FestivalRepo mFestivalRepo;
    private LiveData<List<Festival>> mAllFestivals;



    public FestivalViewModel (Application application) {
        super(application);
        mFestivalRepo = new FestivalRepo(application);
        mAllFestivals = mFestivalRepo.getmAllFestivals();
    }

    public LiveData<List<Festival>> getmAllFestivals() {
        return mAllFestivals;
    }

    public void insert(Festival festival) { mFestivalRepo.insert(festival); }
}
