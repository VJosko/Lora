package com.example.lorablok.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.lorablok.R;

public class ImenaDialog extends DialogFragment {

    private static final String TAG = "ImenaDialog";

    public interface ImenaInterface{
        void sendImena(String igrac1, String igrac2, String igrac3, String igrac4);
    }
    public ImenaInterface mImenaInterface;


    private Button btnOdustani, btnNastavi;
    private EditText txtIgrac1, txtIgrac2, txtIgrac3, txtIgrac4;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_imena_layout, container, false);

        txtIgrac1 = view.findViewById(R.id.txt_igrac_1);
        txtIgrac2 = view.findViewById(R.id.txt_igrac_2);
        txtIgrac3 = view.findViewById(R.id.txt_igrac_3);
        txtIgrac4 = view.findViewById(R.id.txt_igrac_4);

        btnOdustani = view.findViewById(R.id.btn_odustani);
        btnOdustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btnNastavi = view.findViewById(R.id.btn_nastavi);
        btnNastavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sIgrac1 = "";
                String sIgrac2 = "";
                String sIgrac3 = "";
                String sIgrac4 = "";

                //Čitanje imena igraca
                try {
                    sIgrac1 = txtIgrac1.getText().toString();
                }
                catch (RuntimeException ex){
                    sIgrac1 = "";
                }
                try {
                    sIgrac2 = txtIgrac2.getText().toString();
                }
                catch (RuntimeException ex){
                    sIgrac2 = "";
                }
                try {
                    sIgrac3 = txtIgrac3.getText().toString();
                }
                catch (RuntimeException ex){
                    sIgrac3 = "";
                }
                try {
                    sIgrac4 = txtIgrac4.getText().toString();
                }
                catch (RuntimeException ex){
                    sIgrac4 = "";
                }

                //Provjera imena
                if(sIgrac1.equals("") || sIgrac2.equals("") || sIgrac3.equals("") || sIgrac4.equals("")){
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.imena_toast,
                            (ViewGroup) view.findViewById(R.id.imena_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Upišite imena igrača");

                    Toast toast = new Toast(view.getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
                else
                {
                    mImenaInterface.sendImena(sIgrac1, sIgrac2, sIgrac3, sIgrac4);
                    getDialog().dismiss();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mImenaInterface = (ImenaInterface) getActivity();
    }
}
