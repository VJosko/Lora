package com.example.lorablok.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorablok.R;
import com.example.lorablok.activities.MainActivity;
import com.example.lorablok.activities.NovaIgraActivity;
import com.example.lorablok.adapters.recAdapterIgre;
import com.example.lorablok.adapters.recAdapterOdabraneIgre;
import com.example.lorablok.classes.Igra;
import com.example.lorablok.classes.OdabraneItemTouchHelper;
import com.example.lorablok.interfaces.INovaIgra;
import com.example.lorablok.interfaces.ItemTouchHelperAdapterOdabrane;
import com.example.lorablok.room.IgreRepository;
import com.example.lorablok.room.OdabraneIgreRepository;

import java.util.ArrayList;
import java.util.List;

public class FragmentOdabraneIgre extends Fragment implements
        recAdapterOdabraneIgre.OnIgraListener,
        OdabraneItemTouchHelper.onMove {

    private INovaIgra mINovaIgra;

    private ItemTouchHelperAdapterOdabrane itemTouchHelperAdapterOdabrane;

    private ImageButton btnDodajIgru;

    private OdabraneIgreRepository mOdabraneIgreRepository;
    private IgreRepository mIgreRepository;
    private ArrayList<Igra> mIgre;
    private ArrayList<Igra> mIgreKontrola;

    private RecyclerView recyclerView;
    private recAdapterOdabraneIgre mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_odabrane_igre, container, false);

        mINovaIgra.setNaslov("Odabrane igre");

        mOdabraneIgreRepository = new OdabraneIgreRepository(getContext());
        mIgreRepository = new IgreRepository(getContext());
        mIgre = new ArrayList<>();
        mIgreKontrola = new ArrayList<>();

        retrieveIgre();


        btnDodajIgru = view.findViewById(R.id.btn_dodaj_igru);
        btnDodajIgru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINovaIgra.inflateFragment(2, "");
            }
        });

        //------------Recycler-view------------------
        recyclerView = view.findViewById(R.id.rec_igre);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new recAdapterOdabraneIgre(mIgre, this);


        ItemTouchHelper.Callback callback = new OdabraneItemTouchHelper(mAdapter, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mINovaIgra = (INovaIgra)getActivity();
    }

    private void retrieveIgre(){
        mOdabraneIgreRepository.retrieveIgreTask().observe(this, new Observer<List<Igra>>() {
            private List<Igra> lIgre;
            int i = 100;
            int pozicija;
            @Override
            public void onChanged(List<Igra> igras) {
                if(mIgre.size() > 0){
                    mIgre.clear();
                }
                if(igras != null){
                    lIgre = igras;
                    int size = lIgre.size();
                    for(int l = 0; l < size; l++) {
                        for (int j = 0; j < lIgre.size(); j++) {
                            if (lIgre.get(j).red <= i) {
                                i = lIgre.get(j).red;
                                pozicija = j;
                            }
                        }
                        mIgreKontrola.add(lIgre.get(pozicija));
                        mIgre.add(lIgre.get(pozicija));
                        lIgre.remove(pozicija);
                        i = 100;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onIgraClick(int position) {
    }

    @Override
    public void onOpisClick(int position) {
        mIgre.get(position).expanded = !mIgre.get(position).expanded;
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void changeList() {
        ArrayList<Igra> igre = mIgre;
        for(int i = 0; i < igre.size(); i++){
            igre.get(i).red = i;
        }
        Igra lIgre[] = igre.toArray(new Igra[0]);
        mOdabraneIgreRepository.updateIgra(lIgre);
    }

    @Override
    public void removeFromList() {
        int obrisan = mIgre.size();
        for (int j = 0; j < mIgre.size(); j++) {
            if (mIgre.get(j).id != mIgreKontrola.get(j).id) {
                obrisan = j;
                j = mIgre.size() + 1;
            }
        }
        Igra igra = mIgreKontrola.get(obrisan);
        mIgreKontrola.remove(obrisan);
        mIgreRepository.insertIgraTask(igra);
        mOdabraneIgreRepository.deleteIgra(igra);
    }


}
