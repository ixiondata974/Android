package com.example.appvehicule.Onglet.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appvehicule.Onglet.Liste_entret.Adapters.AdapterHerit;
import com.example.appvehicule.Onglet.Liste_entret.obj.EssRdHerit;
import com.example.appvehicule.R;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView.LayoutManager layoutManager;

    private AdapterHerit adapterHerit;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_onglets, container, false);

        final RecyclerView Rcv = root.findViewById(R.id.list);
        pageViewModel.getlHerit().observe(this, new Observer<List<EssRdHerit>>() {
            @Override
            public void onChanged(List<EssRdHerit> essRdHerits) {
                set_list(Rcv, essRdHerits);
            }
        });

        return root;
    }

    //Mes méthodes..........................................................................

    private void set_list(RecyclerView rcv, List<EssRdHerit> lHerit){

        layoutManager = new LinearLayoutManager(this.getContext());
        rcv.setLayoutManager(layoutManager);

        adapterHerit = new AdapterHerit(lHerit, this);
        rcv.setAdapter(adapterHerit);
    }
}