package com.example.lorablok.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lorablok.R;
import com.example.lorablok.classes.Igra;
import com.example.lorablok.room.IgreDatabase;
import com.example.lorablok.dialogs.ImenaDialog;
import com.example.lorablok.room.IgreRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImenaDialog.ImenaInterface {

    private ImageButton ibtnPostavke;
    private Button btnNovaIgra, btnNastavi, btnPovijest;
    private List<Igra> mIgre;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private IgreRepository mIgreRepository;

    //Opisi
    String opisVise, opisManje, opisDecki, opisBabe, opisKralj, opisSrca,
            opisSljepac, opisProzivka, opisZir, opisLora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIgreRepository = new IgreRepository(this);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        //mEditor.putString("prviInit", "0");
        //mEditor.commit();
        if(mPreferences.getString("prviInit", "0").equals("0")){
            getOpise();
            initIgre();
        }

        //retrieveIgre();

        //Postavke onclick
        ibtnPostavke = findViewById(R.id.ibtn_postavke);
        ibtnPostavke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Nova igra onclick
        btnNovaIgra = findViewById(R.id.btn_nova_igra);
        btnNovaIgra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImenaDialog imenaDialog = new ImenaDialog();
                imenaDialog.show(getSupportFragmentManager(), "ImenaDialog");
            }
        });

        //Nastavi onclick
        btnNastavi = findViewById(R.id.btn_nastavi);
        btnNastavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Povijest onclick
        btnPovijest = findViewById(R.id.btn_povijest);
        btnPovijest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void sendImena(String igrac1, String igrac2, String igrac3, String igrac4) {
        startActivity(new Intent(MainActivity.this, NovaIgraActivity.class));
    }

    private void retrieveIgre(){
        mIgreRepository.retrieveIgreTask().observe(this, new Observer<List<Igra>>() {
            @Override
            public void onChanged(List<Igra> igras) {
                if(mIgre.size() > 0){
                    mIgre.clear();
                }
                if(igras != null){
                    mIgre.addAll(igras);
                }
            }
        });
    }

    private void initIgre(){
        Igra StoVise = new Igra("Što više",opisVise,0, 0);
        mIgreRepository.insertIgraTask(StoVise);

        Igra StoManje = new Igra("Što manje", opisManje,1, 1);
        mIgreRepository.insertIgraTask(StoManje);

        Igra Decki = new Igra("Dečki","opis decki",2, 2);
        mIgreRepository.insertIgraTask(Decki);

        Igra Babe = new Igra("Babe","opis babe",3, 3);
        mIgreRepository.insertIgraTask(Babe);

        Igra SrceKralje = new Igra("Srce kralj i zadnji štih","opis srce kralj",4, 4);
        mIgreRepository.insertIgraTask(SrceKralje);

        Igra Srca = new Igra("Srca","opis srca",-1,5);
        mIgreRepository.insertIgraTask(Srca);

        Igra Sljepac = new Igra("Sljepac","opis sljepac",0,6);
        mIgreRepository.insertIgraTask(Sljepac);

        Igra Prozivka = new Igra("Prozivka","opis prozivka",0,7);
        mIgreRepository.insertIgraTask(Prozivka);

        Igra ZirDecko = new Igra("Žir dečko","opis zir decko",5,8);
        mIgreRepository.insertIgraTask(ZirDecko);

        Igra Lora = new Igra("Lora","opis lora jedan dva tri cetiri pet sest sdam osam devet deset",-1,9);
        mIgreRepository.insertIgraTask(Lora);

        Toast.makeText(this, "Upisano je", Toast.LENGTH_SHORT).show();

        mEditor.putString("prviInit", "1");
        mEditor.commit();
    }

    void getOpise(){
        opisVise = "Potrebno je sakupiti Što više štihova, a svaki štih vrijedi -1 bod.\n(negativni bodovi su dobri)";
        opisManje = "Potrebno je sakupiti što manje štihova, a svaki štih vrijedi +1 bod.\n(pozitivni bodovi su loši)";
    }
}
