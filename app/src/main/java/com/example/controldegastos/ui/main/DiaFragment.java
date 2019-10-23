package com.example.controldegastos.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.controldegastos.R;
import com.example.controldegastos.Tabla;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DiaFragment extends Fragment {
    private  TableLayout tableLayout;
    private EditText txtGasto;
    private  EditText txtValor;
    private Context context;
    View v;
    RequestQueue requestQueue;

    public DiaFragment(Context context) {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_dia, container, false);
        buscarProducto("http://172.25.8.68:8080/gastoshormiga/Buscar_Producto.php?categoria=transporte");
        return v;
    }
    private void buscarProducto(String URL){

            final   Tabla tabla = new Tabla(getActivity(), (TableLayout)v.findViewById(R.id.tabla));
            tabla.agregarCabecera(R.array.cabecera_tabla);

            for (int i = 0; i < 5; i++) {

                final ArrayList<String> elementos = new ArrayList<String>();

                elementos.add("hjolaaa");
                elementos.add("melossss");
                tabla.agregarFilaTabla(elementos);

            }
        }
    }


