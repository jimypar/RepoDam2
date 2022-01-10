package com.example.geoprueba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static com.example.geoprueba.util.Util.solicitarPermiso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {
    Button boton,botonCamara;

    TextView latitud, longitud;
    LocationManager gestor;
    int SOLICITUD_PERMISO_LOCALIZACION = 3;
    int SOLICITUD_PERMISO_EXTERNO=4;
    int RESULTADO_FOTO=37;
    Uri uri;
    Location localizacion;

    @Override
    protected void onResume() {
        super.onResume();
        activarProveedor();

         }

         private void activarProveedor(){
             if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                gestor.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000*20,5,this);
             }
             else{
                 solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                         "Sin este permiso no hay GPS",
                         SOLICITUD_PERMISO_LOCALIZACION,this);
             }


         }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gestor.removeUpdates(this);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestor = (LocationManager) getSystemService(LOCATION_SERVICE);

        boton = findViewById(R.id.btn_mapa);
        boton.setOnClickListener(this);
        botonCamara=findViewById(R.id.btn_camera);
        botonCamara.setOnClickListener(this);
        latitud = findViewById(R.id.txt_latitud);
        longitud = findViewById(R.id.txt_longitud);
        obtenerLocalizacion();



    }

    @Override
    public void onClick(View v) {
switch (v.getId()){

    case R.id.btn_mapa:
        obtenerLocalizacion();
        uri=Uri.parse("geo:"+localizacion.getLatitude()+","+localizacion.getLongitude()+"?z="+20);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);

        break;
    case R.id.btn_camera:
        Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uri=Uri.fromFile(new File(Environment.getExternalStorageDirectory()
                + File.separator+"img_"+(System.currentTimeMillis()/1000)+".jpg"));
       intent1.putExtra(MediaStore.EXTRA_OUTPUT,uri);
       startActivityForResult(intent1,RESULTADO_FOTO);

        break;
}
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==SOLICITUD_PERMISO_LOCALIZACION){
            if(grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                obtenerLocalizacion();
            }
        }
        else if(requestCode==SOLICITUD_PERMISO_EXTERNO){
            if(grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                ImageView iv=findViewById(R.id.iv_foto);
                iv.setImageURI(uri);
            }
        }else{
            Toast.makeText(this,"Pues tú te lo pierdes",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
obtenerLocalizacion();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void obtenerLocalizacion(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            if(gestor.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                localizacion= gestor.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (localizacion==null){
                    latitud.setText("" + 0.0);
                    longitud.setText("" + 0.0);


                }else{
                latitud.setText("" + localizacion.getLatitude());
                longitud.setText("" + localizacion.getLongitude());}

            }
        }
        else{
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Sin este permiso no hay GPS",
                    SOLICITUD_PERMISO_LOCALIZACION,this);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULTADO_FOTO && resultCode== Activity.RESULT_OK){
            //lo que quiera hacer con la foto
            ImageView iv=findViewById(R.id.iv_foto);
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


                iv.setImageURI(uri);}
            else{
                solicitarPermiso(Manifest.permission.READ_EXTERNAL_STORAGE,
                        "Necesito leer la foto",
                        SOLICITUD_PERMISO_EXTERNO,this);

            }

        }
        else{
            Toast.makeText(this,"Ups, algo ha ido mal con la foto",Toast.LENGTH_LONG).show();
        }
    }
}
