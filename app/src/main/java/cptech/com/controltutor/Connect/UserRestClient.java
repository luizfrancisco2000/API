package cptech.com.controltutor.Connect;

import android.util.Log;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cptech.com.controltutor.Controle.Discente;
import cptech.com.controltutor.Controle.Usuario;

public class UserRestClient {
    //http://10.100.37.192:8000
    //http://10.100.45.241:8000
    private final String BASE_URL = "http://10.100.45.241:8000/api/usuario/";
    private RestTemplate restTemplate;
    private String url;
    private DiscenteRestClient discenteRestClient;
    public static char tipo;
    public UserRestClient() {
        restTemplate = new RestTemplate();
    }

    public int procurarDados(long aLong) {
        Log.w("2: Id", String.valueOf(aLong));
        url = BASE_URL + "procurar/"+aLong;
        Usuario user;
        try{
            if(tipo=='A'){
                Discente discente = Discente.getInstance();
                ResponseEntity<Discente> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Discente>() {});
                discente = response.getBody();
                if(discente.getTipo()==tipo){
                    Discente.setInstance(discente);
                    return 1;
                }
            }else if(tipo=='P'){

            }else if(tipo=='T'){

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
