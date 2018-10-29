package cptech.com.controltutor.Connect;

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

import cptech.com.controltutor.Controle.Tutor;

public class TutorRestClient {
    //http://10.100.37.192:8000
    //http://10.100.38.128:8000
    //http://10.100.45.241:8000
    //10.100.38.136
    private final String BASE_URL = "http://10.100.38.121:8000/api/tutor/";
    private RestTemplate restTemplate;
    private String url;

    public TutorRestClient() {
        restTemplate = new RestTemplate();
    }

    public boolean insertTutor(Tutor tutor) {
        url = BASE_URL + "cadastrar";
        try {
            HashMap<String, Object> valuesTutor = new HashMap<>();
            valuesTutor.put("usuario", tutor.getUsuario());
            valuesTutor.put("nome",tutor.getNome());
            valuesTutor.put("senha", tutor.getSenha());
            valuesTutor.put("tipo", tutor.getTipo());
            valuesTutor.put("",tutor.getDiscentes());
            valuesTutor.put("",tutor.getListas());
            valuesTutor.put("",tutor.getProfessor());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("usuario", tutor.getUsuario());
            jsonObject.put("nome", tutor.getNome());
            jsonObject.put("senha", tutor.getSenha());
            jsonObject.put("tipo", tutor.getTipo());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
            Log.d("Deu","Deu");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public Tutor loginTutor(String user, String senha){

        Log.d("user e senha", "user: "+user+" senha: "+senha);
        url = BASE_URL + "executar_login/"+user+"/"+senha;
        Tutor tutor = Tutor.getInstance();
        try{

            ResponseEntity<Tutor> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Tutor>() {});
            tutor = response.getBody();
            /*Tutor = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Tutor>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Tutor>() {}).getBody();*/

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tutor;
    }

    public boolean listar(String user) {
        url = BASE_URL+"procuraUsuario/"+user;
        Tutor tutor = Tutor.getInstance();
        try{

            ResponseEntity<Tutor> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Tutor>() {});
            tutor = response.getBody();
            if(tutor!=null){
                return true;
            }
            else{
                return false;
            }
            /*Tutor = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Tutor>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Tutor>() {}).getBody();*/

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
