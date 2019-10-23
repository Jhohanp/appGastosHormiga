package com.example.controldegastos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cz.msebera.android.httpclient.Header;

import com.example.controldegastos.ui.main.GsonMetodo;
import com.example.controldegastos.ui.main.Utilities;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.*;
import com.example.controldegastos.ui.main.Gasto;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class AnadirGasto extends AppCompatActivity {

    Spinner Cat, mPago;
    EditText Monto, Fecha, Hora, Frec, Desc;
    Button buttonAgregar;
    Utilities U;
    String[] arraymetodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadirgasto);

        Cat =(Spinner)findViewById(R.id.spinnerCat);
        Frec =(EditText)findViewById(R.id.InFrec);
        mPago = (Spinner)findViewById(R.id.spinnerMetodo);
        Monto = (EditText)findViewById(R.id.InMonto);
        Fecha = (EditText)findViewById(R.id.InFecha);
        Hora = (EditText)findViewById(R.id.InHora);
        Desc = (EditText)findViewById(R.id.InDescripcion);
        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);
        U = new Utilities();
        AsyncHttpClient clientGet;
        clientGet = new AsyncHttpClient();
        RequestParams param= new RequestParams();
        param.put("metodosGasto","metodos");
        clientGet.get(U.url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                Toast.makeText(getApplicationContext(), "Cargando metodos de pago", Toast.LENGTH_LONG).show();
                String s = new String(responseBody);
                System.out.println("METODOS"+s);
                Type listType = new TypeToken<ArrayList<MetodoPago>>() {}.getType();
                ArrayList<MetodoPago> arregloConvert = gson.fromJson(s, listType);
                System.out.println(arregloConvert.get(1).Nombre);
                arraymetodos=new String[arregloConvert.size()];
                for (int i=0; i<arregloConvert.size();i++){
                    arraymetodos[i]=arregloConvert.get(i).Nombre+"   "+arregloConvert.get(i).Digitos;
                }
                System.out.println(arraymetodos[1]);
               crearSpinnerMetodos(arraymetodos);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


        //ArrayAdapter<String> adapterPago = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraymetodos);
        //mPago.setAdapter(adapterPago);

        String [] CategoriasArray = {"Hogar", "Salud", "Transporte", "Ropa y calzado", "Cuentas y pagos", "Seguros", "Estetica", "Diversion", "Otros gastos"};
        String [] MetodosArray;

        ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CategoriasArray);
        Cat.setAdapter(adapterCat);

        buttonAgregar = (Button)findViewById(R.id.buttonAgregar);
        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Gasto gasto = new Gasto("0",(String)Cat.getSelectedItem(),Fecha.getText().toString().toString(),Hora.getText().toString(),(String) mPago.getSelectedItem(),Desc.getText().toString(),Integer.parseInt(Monto.getText().toString()),Integer.parseInt(Frec.getText().toString()));
                GsonMetodo<Gasto> gson = new GsonMetodo<Gasto>();
                String json = gson.convertToJson(gasto);
                RequestParams params= new RequestParams();
                params.put("gasto",json);
                AsyncHttpClient client;
                client = new AsyncHttpClient();

                client . post ( U.url, params , new AsyncHttpResponseHandler ()
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

    private void crearSpinnerMetodos(String[] arraymetodos) {
        ArrayAdapter<String> adapterPago = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraymetodos);
        mPago.setAdapter(adapterPago);
    }

}
