package cptech.com.controltutor.Controle.API.OCR;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;


public class OCRClass {
   /* Frame frame;
    TextRecognizer ocr;
    public OCRClass(Context context, Bitmap imagem){
        ocr = new TextRecognizer.Builder(context).build();
        frame = new Frame.Builder().setBitmap(imagem).build();
    }

    public StringBuilder readToImage(){
        StringBuilder text = new StringBuilder();
        if(ocr.isOperational()){
            SparseArray<TextBlock> itens = ocr.detect(frame);
            for (int i = 0; i < itens.size();++i) {
                TextBlock item = itens.valueAt(i);
                text.append(item.getValue());
                text.append("\n");
            }
        }else{
            Log.d("Erro","Erro");
            return text.append("erro");
        }
        return text;
    }*/
}
