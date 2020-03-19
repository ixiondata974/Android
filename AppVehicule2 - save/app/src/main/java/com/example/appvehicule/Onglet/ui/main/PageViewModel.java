package com.example.appvehicule.Onglet.ui.main;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.appvehicule.Onglet.Liste_entret.obj.EssRdHerit;
import com.example.appvehicule.Onglet.Liste_entret.obj.essence;
import com.example.appvehicule.Onglet.Liste_entret.obj.rendez_vous;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

            //.......................................
            //.......................................
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello from section: " + input;
        }
    });

    private LiveData<List<EssRdHerit>> lHerit = Transformations.map(mIndex, new Function<Integer, List<EssRdHerit>>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public List<EssRdHerit> apply(Integer input) {
            List<EssRdHerit> mHerit = new ArrayList<>();

            if (input == 1){
                LocalDate date = LocalDate.of(2019,05,06);
                LocalDate date2 = LocalDate.of(2019,05,06);
                LocalDate date3 = LocalDate.of(2019,03,06);

                mHerit.add(new rendez_vous(date,"Moteur"));
                mHerit.add(new rendez_vous(date2,"Carburant"));
                mHerit.add(new rendez_vous(date3,"Batteri"));
            }else if (input == 2){

                LocalDate date = LocalDate.of(2019,05,06);
                LocalDate date2 = LocalDate.of(2018,05,06);
                LocalDate date3 = LocalDate.of(2017,05,06);

                mHerit.add(new essence(date,"Colgate","Electricité", 25,56));
                mHerit.add(new essence(date2,"Carglass","Electricité", 25,56));
                mHerit.add(new essence(date3,"jjojo","Electricité", 25,56));
            }
            return mHerit;
        }
    });

    //public**************************************************************************************

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public int getIndex() {
        return mIndex.getValue();
    }
    public LiveData<List<EssRdHerit>> getlHerit(){
        return lHerit;
    }
    public LiveData<String> getText() {
        return mText;
    }
}