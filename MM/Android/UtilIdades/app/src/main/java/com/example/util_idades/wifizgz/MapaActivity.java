package com.example.util_idades.wifizgz;

import static com.example.util_idades.wifizgz.Util.solicitarPermiso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util_idades.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
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
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapaActivity extends AppCompatActivity implements LocationListener, MapboxMap.OnMarkerClickListener {

    double latitud, longitud;
    LocationManager gestor;
    int SOLICITUD_PERMISO_LOCALIZACION = 3;
    private MapView mapa;
    ArrayList<Punto> puntos;
    Location miUbicacion;
    TextView distancia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,"pk.eyJ1IjoiamlteXBhciIsImEiOiJja3ZtYnN0d3AwdHlhMnVvNXF0cDM2Z2d6In0.nwgPnAMA0VNbWFQRB4_lNg");
        setContentView(R.layout.activity_mapa);
        gestor = (LocationManager) getSystemService(LOCATION_SERVICE);
        puntos=new ArrayList<Punto>();
        mapa=findViewById(R.id.mapView);
        mapa.onCreate(savedInstanceState);
        distancia=findViewById(R.id.tDistancia);
        obtenerLocalizacion();

    }


    private void pintarPuntos(){


        mapa.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                if (latitud!=0 && longitud!=0) {
                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitud, longitud))
                            .title("Tu ubicacion")
                            .icon(IconFactory.getInstance(MapaActivity.this).fromResource(R.drawable.puntoazul)));
                }

                for(Punto punto:puntos){
                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(punto.getLatitud(),punto.getLongitud()))
                            .title(punto.getNombre())
                            .snippet(punto.getEstado()));

                }
                CameraPosition posicion=new CameraPosition.Builder()
                        .target(new LatLng(latitud,
                                longitud))
                        .zoom(17)
                        .tilt(30)
                        .build();

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(posicion),7000);
                mapboxMap.setOnMarkerClickListener(MapaActivity.this);
            }
        });

    }

    private void activarProveedor(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gestor.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000*20,5,this);
        }
        else{
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Sin este permiso no hay GPS",
                    SOLICITUD_PERMISO_LOCALIZACION,this);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SOLICITUD_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerLocalizacion();
            } else {
                finish();
            }
        } else {
            Toast.makeText(this, "Pues tú te lo pierdes", Toast.LENGTH_LONG).show();
        }
    }

    private void obtenerLocalizacion(){

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if(gestor.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                miUbicacion= gestor.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (miUbicacion!=null){
                    latitud=miUbicacion.getLatitude();
                    longitud=miUbicacion.getLongitude();
                }
            }
        }
        else{
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "No hay permiso GPS",
                    SOLICITUD_PERMISO_LOCALIZACION,this);
        }


    }




    @Override
    protected void onStart() {
        super.onStart();
        mapa.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            mapa.setStyleUrl(Style.DARK);
        }else {
            mapa.setStyleUrl(Style.LIGHT);
        }

        activarProveedor();
        Tarea tarea=new Tarea();
        tarea.execute(Constantes.URL);
        mapa.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapa.onPause();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gestor.removeUpdates(this);
        }
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

    @Override
    public void onLocationChanged(Location location) {

        if (miUbicacion!=null){
            latitud=miUbicacion.getLatitude();
            longitud=miUbicacion.getLongitude();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        distancia.setText("Distancia al WIFI: "+distancia(latitud,longitud,marker.getPosition().getLatitude(),marker.getPosition().getLongitude()));
        return false;
    }


    public class Tarea extends AsyncTask<String, Void, Void> {
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
                String estado;
                String coordenadas;
                Punto punto;
                for(int i=0;i<jsonArray.length();i++){
                    titulo=jsonArray.getJSONObject(i).getJSONObject("properties").getString("title");
                    estado=jsonArray.getJSONObject(i).getJSONObject("properties").getString("estado");
                    coordenadas=jsonArray.getJSONObject(i).getJSONObject("geometry").getString("coordinates");
                    coordenadas=coordenadas.substring(1,coordenadas.length()-1);
                    String latlong[]=coordenadas.split(",");

                    punto=new Punto();
                    punto.setNombre(titulo);
                    punto.setEstado(estado);
                    punto.setLatitud(Double.parseDouble(latlong[1]));
                    punto.setLongitud(Double.parseDouble(latlong[0]));
                    puntos.add(punto);
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



            pintarPuntos();
        }
    }

    private static String distancia(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return "";
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            dist = dist * 1.609344;

            ;

            return (convertir(dist));
        }
    }

    private static String convertir(double distancia) {
        DecimalFormat df;
        if (distancia>=1){
            df = new DecimalFormat("0.0");
            return df.format(distancia)+"Km";
        } else {
            df = new DecimalFormat("0");
            return df.format(distancia*1000)+"m";
        }

    }


}