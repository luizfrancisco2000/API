package cptech.com.controltutor.Connect;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import cptech.com.controltutor.Controle.Discente;

public class DiscenteRestClient {
    private final String BASE_URL = "http://localhost:8000/api/discente/";
    private RestTemplate restTemplate;
    private String url;
    public DiscenteRestClient(){
        restTemplate = new RestTemplate();
    }
    public String insertDiscente(Discente discente){
        url = BASE_URL+"cadastrar";
        HashMap<String, Object> valuesDiscente = new HashMap<>();

        return null;
    }
}
