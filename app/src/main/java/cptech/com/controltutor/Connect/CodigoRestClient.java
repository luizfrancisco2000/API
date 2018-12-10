package cptech.com.controltutor.Connect;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import cptech.com.controltutor.Controle.API.SingleProcessRequest;
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
        url = BASE_URL + "cadastrar/"+codigo.getDiscente().getId();
        try {
            HashMap<String, Object> valuesDiscente = new HashMap<>();
            SingleProcessRequest singleProcessRequest = new SingleProcessRequest(codigo.getResolucao());
            Log.i("Erro",codigo.getAssunto());
            valuesDiscente.put("assunto", codigo.getAssunto());
            valuesDiscente.put("enunciado", codigo.getEnunciado());
            valuesDiscente.put("avaliacao", codigo.getAvaliacao());
            valuesDiscente.put("discente", codigo.getDiscente().toString());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            codigo.setAvaliacao(0);
            ObjectMapper om = new ObjectMapper();
            om.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            jsonObject.put("assunto", codigo.getAssunto());
            jsonObject.put("enunciado", codigo.getEnunciado());
            jsonObject.put("resolucao", singleProcessRequest.getUrl());
            jsonObject.put("avaliacao", codigo.getAvaliacao());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
            Log.d("Deu","Deu");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public List listAllCodigosByDiscente(Long idDiscente){
        url = BASE_URL + "ProcuraAluno/"+idDiscente;
        try{
            List<Codigo> codigos = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Codigo>>() {}).getBody();
            if(codigos==null){
                Log.d("NAO FOI POSSIVEL","EAE");
                return null;
            }else{
                return codigos;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Discente> listAllCodigosByTutorDiscente(Long idTutor){
        url = BASE_URL + "ProcuraAluno/"+idTutor;
        try{
            List<Discente> discentes = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Discente>>() {}).getBody();
            if(discentes==null){
                Log.d("NAO FOI POSSIVEL","EAE");
                return null;
            }else{
                return discentes;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
