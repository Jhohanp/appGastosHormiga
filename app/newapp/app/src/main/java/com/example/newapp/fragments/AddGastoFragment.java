package com.example.newapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newapp.R;

public class AddGastoFragment extends Fragment {

    Spinner Cat, mPago;
    EditText Monto, Fecha, Hora, Frec, Desc;
    Button buttonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_gasto);

        Cat =(Spinner)findViewById(R.id.spinnerCat);
        Frec =(EditText)findViewById(R.id.InFrec);
        mPago = (Spinner)findViewById(R.id.spinnerMetodo);
        Monto = (EditText)findViewById(R.id.InMonto);
        Fecha = (EditText)findViewById(R.id.InFecha);
        Hora = (EditText)findViewById(R.id.InHora);
        Desc = (EditText)findViewById(R.id.InDescripcion);
        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);

        String [] CategoriasArray = {"Hogar", "Salud", "Transporte", "Ropa y calzado", "Cuentas y pagos", "Seguros", "Estetica", "Diversion", "Otros gastos"};
        String [] MetodosArray;

        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CategoriasArray);
        Cat.setAdapter(adapterCat);

        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Gasto gasto = new Gasto("0",(String)Cat.getSelectedItem(),Fecha.getText().toString().toString(),Hora.getText().toString(),"efectivo",Desc.getText().toString(),Integer.parseInt(Monto.getText().toString()),Integer.parseInt(Frec.getText().toString()));

                GsonMetodo<Gasto> gson = new GsonMetodo<Gasto>();
                String json = gson.convertToJson(gasto);
                RequestParams params= new RequestParams();
                params.put("gasto",json);
                AsyncHttpClient client;
                client = new AsyncHttpClient();
                client . post ( "http://192.168.0.108:51414/ServerApp/Controller", params , new AsyncHttpResponseHandler ()
                {
                    public void onSuccess ( int statusCode , Header [] headers , byte [] responseBody  ) {
                        // super.onSuccess(statusCode,headers,responseBody);
                        Toast.makeText(getApplicationContext(), "Envio al servidor exitoso", Toast.LENGTH_LONG).show();
                        Monto.setText("");
                        Fecha.setText("");
                        Hora.setText("");
                        Frec.setText("");
                        Desc.setText("");
                    }
                    public void onFailure  ( int statusCode , Header [] headers , byte [] responseBody , Throwable error) {
                        // Codigo a ejecutar en caso de error .
                        Toast.makeText(getApplicationContext(), "Error en el envio al servidor", Toast.LENGTH_LONG).show();
                    }
                }) ;
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_gasto, container, false);
    }

    public interface OnFragmentInteractionListener {
    }
}
