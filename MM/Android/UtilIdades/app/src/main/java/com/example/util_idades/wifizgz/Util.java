package com.example.util_idades.wifizgz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.core.app.ActivityCompat;

public class Util {

    public static void solicitarPermiso(final String permiso, String justificacion, final int resquestCode, final Activity actividad){
        if(ActivityCompat.shouldShowRequestPermissionRationale(actividad,permiso)){
            new AlertDialog.Builder(actividad).
                    setTitle("Solicitud de permiso").
                    setMessage(justificacion).
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso},resquestCode);

                        }
                    }).show();

        }else{
            ActivityCompat.requestPermissions(actividad,new String[]{permiso},resquestCode);

        }

    }
}
