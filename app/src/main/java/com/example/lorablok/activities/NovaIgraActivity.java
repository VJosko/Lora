package com.example.lorablok.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lorablok.R;
import com.example.lorablok.room.IgreDatabase;
import com.example.lorablok.fragments.FragmentOdabraneIgre;
import com.example.lorablok.fragments.FragmentOstaleIgre;
import com.example.lorablok.interfaces.INovaIgra;

public class NovaIgraActivity extends AppCompatActivity implements INovaIgra {

    private ImageButton ibtn_natrag, ibtnPostavke;
    private TextView tvNaslov;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_igra);

        tvNaslov = findViewById(R.id.tv_naslov);

        init();

        ibtn_natrag = findViewById(R.id.ibtn_natrag);
        ibtn_natrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentOdabraneIgre test = (FragmentOdabraneIgre) getSupportFragmentManager().findFragmentByTag("odabraneIgre");
                if(test != null && test.isVisible()){
                    finish();
                }
                else {
                    getSupportFragmentManager().popBackStack();
                }
            }
        });

        ibtnPostavke = findViewById(R.id.ibtn_postavke);
        ibtnPostavke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init(){
        FragmentOdabraneIgre fragmentOdabraneIgre = new FragmentOdabraneIgre();
        doFragmentTransaction(fragmentOdabraneIgre, "odabraneIgre", false, "");
    }

    private void doFragmentTransaction(Fragment fragment, String tag, boolean addToBackStack, String message){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(!message.equals("")){
            Bundle bundle = new Bundle();
            bundle.putString("intent_message", message);
            fragment.setArguments(bundle);
        }

        transaction.replace(R.id.main_container, fragment, tag);

        if(addToBackStack){
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public void inflateFragment(int nFragment, String message) {
        switch (nFragment){
            case 1:
                FragmentOdabraneIgre fragmentOdabraneIgre = new FragmentOdabraneIgre();
                doFragmentTransaction(fragmentOdabraneIgre, "OdabraneIgre", true, message);
                break;

            case 2:
                FragmentOstaleIgre fragmentOstaleIgre = new FragmentOstaleIgre();
                doFragmentTransaction(fragmentOstaleIgre, "Upisi", true, message);
                break;
        }
    }

    @Override
    public void setNaslov(String naslov) {
        tvNaslov.setText(naslov);
    }


}
