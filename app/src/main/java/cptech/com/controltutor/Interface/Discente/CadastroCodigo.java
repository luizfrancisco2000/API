package cptech.com.controltutor.Interface.Discente;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.vision.text.TextRecognizer;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import cptech.com.controltutor.Connect.CodigoRestClient;
import cptech.com.controltutor.Controle.API.OCR.OCRClass;
import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.R;

public class CadastroCodigo extends AppCompatActivity {
    private Spinner assuntos;
    private AlertDialog alert;
    private ImageButton foto;
    private EditText enunciadoText, codigoText;
    private ImageView verFoto;
    private FloatingActionButton finalizar;
    private Codigo codigo;

    private CodigoRestClient codigoRestClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_codigo);
        enunciadoText = findViewById(R.id.enunciadoCadID);
        codigoText = findViewById(R.id.codigoCadID);
        assuntos = findViewById(R.id.assuntos);
        foto = findViewById(R.id.fotoCadID);
        verFoto = findViewById(R.id.fotoVisID);
        finalizar = findViewById(R.id.salveBottonID);
        messagemInicial();
        validarPermissao();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurarFoto();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CadastroCodigo.this,R.array.lp_assuntos,R.layout.padrao_spinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        assuntos.setAdapter(adapter);
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void cadastrar() {
       File aux =  transformarTextToC();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(aux);
            //System.out.println(file.exists() + "!!");
            //InputStream in = resource.openStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    System.out.println("read " + readNum + " bytes,");
                }
            } catch (IOException ex) {
                ex.getMessage();
            }
            codigo.setResolucao(bos.toByteArray());
            codigo.setAssunto(assuntos.getSelectedItem().toString());
            codigo.setEnunciado(enunciadoText.getText().toString());
            try{
                if(new HttpCadastrarCodigo().execute(codigo).get()){
                    Toast.makeText(this, "Funcionou", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Deu coco", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Deu coco", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private File transformarTextToC() {
        File arq = null;
        byte[] dados;
        try
        {

            arq = new File(Environment.getExternalStorageDirectory(), "programa: "+ codigo.getDiscente().getUsuario()+".c");
            FileOutputStream fos;
            FileInputStream fis;
            dados = codigoText.getText().toString().getBytes();
            fos = new FileOutputStream(arq);
            fos.write(dados);
            fos.flush();
            fos.close();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return arq;
    }

    public void messagemInicial(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção").setMessage("Os codigos serão salvos em formato .c");
        alerta.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onStop();
                System.exit(0);
            }
        });
        alert = alerta.create();
        alert.show();

        codigo = new Codigo();
        codigo.setDiscente(Discente.getInstance());
    }
    public void procurarFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bmp;
        if(resultCode==RESULT_OK && requestCode==1){
            Uri selectedImage = data.getData();
            verFoto.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,filePath,null,null,null);
            c.moveToFirst();
            String picturePath = c.getString(c.getColumnIndex(filePath[0]));
            c.close();
            verFoto.setDrawingCacheEnabled(true);
            verFoto.buildDrawingCache();
            bmp = (BitmapFactory.decodeFile(picturePath));
            verFoto.setImageBitmap(bmp);
            OCRClass ocr = new OCRClass(verFoto.getContext(),bmp);
            codigoText.setText(ocr.readToImage());
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bmp, 300, 300, true);
            verFoto.setImageBitmap(bitmapReduzido);
        }
    }
    public void validarPermissao(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},2);
            }
        }

    }
    private class HttpCadastrarCodigo extends AsyncTask<Codigo, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Codigo... codigos) {
            codigoRestClient = new CodigoRestClient();
            return codigoRestClient.insertCodigo(codigos[0]);
        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
}
