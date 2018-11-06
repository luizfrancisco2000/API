package cptech.com.controltutor.Connect;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Discente;

public class CodigoRestClient extends RestClient{
    //http://10.100.37.192:8000
    //10.100;38.128
    //http://10.100.45.241:8000
    //10.100.38.136
    private final String BASE_URL = BASE+ "codigo/";
    private RestTemplate restTemplate;
    private String url;


    public CodigoRestClient() {
        restTemplate = new RestTemplate();
    }

    public boolean insertCodigo(Codigo codigo) {
        url = BASE_URL + "cadastrar";
        try {
            HashMap<String, Object> valuesDiscente = new HashMap<>();
            valuesDiscente.put("assunto", codigo.getAssunto());
            valuesDiscente.put("enunciado", codigo.getEnunciado());
            valuesDiscente.put("resolucao", codigo.getResolucao());
            valuesDiscente.put("discente", codigo.getDiscente());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("assunto", codigo.getAssunto());
            jsonObject.put("enunciado", codigo.getEnunciado());
            jsonObject.put("resolucao", codigo.getResolucao());
            jsonObject.put("discente",  codigo.getDiscente());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
            Log.d("Deu","Deu");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public List listAllCodigosByDiscente(){
        url = BASE_URL + "ProcuraAluno";
        try{

            return null;
            /*discente = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Discente>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Discente>() {}).getBody();*/

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
