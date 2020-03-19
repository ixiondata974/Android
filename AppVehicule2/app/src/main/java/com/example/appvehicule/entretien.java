package com.example.appvehicule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appvehicule.Onglet.List_onglets;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

public class entretien extends AppCompatActivity {

    private TextView entr;
    private TextView chiff;
    private Button prendre_photo;
    private Button liste;
    private Button envoy;
    private SurfaceView analyse;
    private CameraSource sourceImg;
    private ImageView image_analyse;
    private String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    private EditText prix;
    private EditText tot;
    private EditText quant;

    private static final int Request_image = 1;
    private final int permission = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entretien);

        image_analyse = (ImageView)findViewById(R.id.image_Set);
        prendre_photo = (Button)findViewById(R.id.button2);
        liste = (Button)findViewById(R.id.Liste);
        envoy = (Button)findViewById(R.id.envoi);
        entr = (TextView)findViewById(R.id.textView4);
        chiff = (TextView)findViewById(R.id.TestChiffre);

        prix = (EditText)findViewById(R.id.prix);
        tot = (EditText)findViewById(R.id.total);
        quant = (EditText)findViewById(R.id.litre);

        prix.addTextChangedListener(mText);
        tot.addTextChangedListener(mText);
        quant.addTextChangedListener(mText);

        final Intent inten = new Intent(this, List_onglets.class);
        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inten);
            }
        });
        prendre_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                affiche_photo();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==Request_image && resultCode == RESULT_OK){
            File file = new File(getFilesDir(), "img");
            String fullImagePath = file.getPath();
            Bitmap image = BitmapFactory.decodeFile(fullImagePath);

            image_analyse.setImageBitmap(image);
        }
        get_textFrom_Image();
    }

    //Mes méthodes****************************************************************************

    private TextWatcher mText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String price = prix.getText().toString().trim();
            String quanti = quant.getText().toString().trim();
            String total = tot.getText().toString().trim();

            envoy.setEnabled(!price.isEmpty() && !quanti.isEmpty() && !total.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void affiche_photo() {
        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File im = new File(getFilesDir(), "img");
        String auth = this.getApplicationContext().getPackageName();
        Uri giveURI = FileProvider.getUriForFile(this,auth+".provider",im);

        in.putExtra(MediaStore.EXTRA_OUTPUT,giveURI);

        if (in.resolveActivity(getPackageManager()) != null){
            startActivityForResult(in, Request_image);
        }
    }

    private void orientation(Bitmap ori){
        Matrix mtrix = new Matrix();
    }

    private void get_textFrom_Image(){
        Bitmap bit = ((BitmapDrawable) image_analyse.getDrawable()).getBitmap();

        TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if(recognizer.isOperational()){

            StringBuilder sb = new StringBuilder();
            StringBuilder sb_Int = new StringBuilder();
            try{
                Frame fram = new Frame.Builder().setBitmap(bit).build();
                SparseArray<TextBlock> items = recognizer.detect(fram);

                for (int i = 0; i<items.size(); i++){
                    TextBlock tb = items.get(i);

                    try{
                        sb_Int.append(convert_Int(tb));
                    }catch (Exception e){
                        sb.append(tb.getValue());
                        sb.append(" ");
                    }

                }
            }catch (Exception e){
                sb.append("Exeption : ");
                sb.append(e);
            }
            entr.setText(sb);
            chiff.setText(sb_Int);
        }
    }

    private static float convert_Int(TextBlock tb){
        int[] nombre = {0,1,2,3,4,5,6,7,8,9};
        int get = Integer.parseInt(tb.getValue());
        int result = 0;
        /*try {
            get = Integer.parseInt(tb.getValue());
            return get;
        }catch (Exception e){
            return 0;
        }*/

        for (int i = 0; i<nombre.length; i++){
            if (get == nombre[i]){
                result += get;
            }
        }
        return result;
    }
}