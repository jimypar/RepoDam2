package com.example.monumentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.monumentos.Util.DeUMTSaLatLng;

public class MainActivity extends AppCompatActivity {

        private MapView mapa;
        ArrayList<Punto> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,"pk.eyJ1IjoiamlteXBhciIsImEiOiJja3ZtYnN0d3AwdHlhMnVvNXF0cDM2Z2d6In0.nwgPnAMA0VNbWFQRB4_lNg");
        setContentView(R.layout.activity_main);
        puntos=new ArrayList<Punto>();
        mapa=findViewById(R.id.mapView);
        mapa.onCreate(savedInstanceState);

    }
    private void pintarPuntos(){
        mapa.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                for(Punto punto:puntos){
                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(punto.getLatitud(),punto.getLongitud()))
                            .title(punto.getNombre())
                            .snippet(punto.getLink()));

                }
                Punto ultimopunto=puntos.get(puntos.size()-1);
                CameraPosition posicion=new CameraPosition.Builder()
                        .target(new LatLng(ultimopunto.getLatitud(),
                                ultimopunto.getLongitud()))
                        .zoom(17)
                        .tilt(30)
                        .build();

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(posicion),7000);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tarea tarea=new Tarea();
        tarea.execute(Constantes.URL);
        mapa.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapa.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapa.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapa.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapa.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapa.onSaveInstanceState(outState);
    }

    public class Tarea extends AsyncTask<String, Void, Void>{
    private boolean error=false;

        @Override
        protected Void doInBackground(String... strings) {
            String cadena;
            JSONObject json;
            JSONArray jsonArray;

            try{
                URL url=new URL(Constantes.URL);
                HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
                BufferedReader br=new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb= new StringBuilder();
                String linea=null;
                while((linea=br.readLine())!=null){
                    sb.append(linea+"\n");
                }
                conexion.disconnect();
                br.close();
                cadena=sb.toString();

                json=new JSONObject(cadena);
                jsonArray=json.getJSONArray("features");

                String titulo;
                String link;
                String coordenadas;
                Punto punto;
                for(int i=0;i<jsonArray.length();i++){
                    titulo=jsonArray.getJSONObject(i).getJSONObject("properties").getString("title");
                    link=jsonArray.getJSONObject(i).getJSONObject("properties").getString("link");
                    coordenadas=jsonArray.getJSONObject(i).getJSONObject("geometry").getString("coordinates");
                    coordenadas=coordenadas.substring(1,coordenadas.length()-1);
                    String latlong[]=coordenadas.split(",");

                    punto=new Punto();
                    punto.setNombre(titulo);
                    punto.setLink(link);
                    punto.setLatitud(DeUMTSaLatLng(Double.parseDouble(latlong[0]),
                            Double.parseDouble(latlong[1])).getLat());
                    punto.setLongitud(DeUMTSaLatLng(Double.parseDouble(latlong[0]),
                            Double.parseDouble(latlong[1])).getLng());
                    puntos.add(punto);

                    System.out.println(punto.getNombre());
                }


            }catch(IOException ioe){
                ioe.printStackTrace();
                error=true;
            }catch(JSONException jse){
                jse.printStackTrace();
                error=true;
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(error){
                Toast.makeText(getApplicationContext(), "Error!!",Toast.LENGTH_LONG).show();
                return;
            }

            pintarPuntos();
        }
    }


}
