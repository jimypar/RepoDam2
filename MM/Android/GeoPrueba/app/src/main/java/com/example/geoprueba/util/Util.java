package com.example.geoprueba.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class Util {

    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
        return bos.toByteArray();
    }

    public static Bitmap getBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);

    }

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

             }else{ActivityCompat.requestPermissions(actividad,new String[]{permiso},resquestCode);
        }

    }

    public static Bitmap reduceBitmap(Context contexto, String uri, int maxAncho, int maxAlto){
        try {
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
            BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),null,options);
       options.inSampleSize=(int)Math.max(Math.ceil(options.outWidth/maxAncho),Math.ceil(options.outHeight/maxAlto));
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),null,options);
        } catch (FileNotFoundException e) {
            Toast.makeText(contexto,"Recurso no encontrado",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        }
    }
}
