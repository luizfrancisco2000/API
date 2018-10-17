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
import java.util.List;

import cptech.com.controltutor.Controle.Professor;

public class ProfessorRestClient {
    //http://10.100.37.192:8000
    //http://10.100.38.128:8000
    //http://10.100.45.241:8000
    private final String BASE_URL = "http://10.100.45.241:8000/api/professor/";
    private RestTemplate restTemplate;
    private String url;

    public ProfessorRestClient() {
        restTemplate = new RestTemplate();
    }

    public boolean insertProfessor(Professor professor) {
        url = BASE_URL + "cadastrar";
        try {
            HashMap<String, Object> valuesProfessor = new HashMap<>();
            valuesProfessor.put("usuario", professor.getUsuario());
            valuesProfessor.put("nome", professor.getNome());
            valuesProfessor.put("senha", professor.getSenha());
            valuesProfessor.put("tipo", professor.getTipo());
            valuesProfessor.put("discentes",professor.getDiscentes());
            valuesProfessor.put("listas",professor.getListas());
            valuesProfessor.put("tutores",professor.getTutores());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("usuario", professor.getUsuario());
            jsonObject.put("nome", professor.getNome());
            jsonObject.put("senha", professor.getSenha());
            jsonObject.put("tipo", professor.getTipo());
            jsonObject.put("discentes",professor.getDiscentes());
            jsonObject.put("listas",professor.getListas());
            jsonObject.put("tutores",professor.getTutores());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
            Log.d("Deu","Deu");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public Professor loginProfessor(String user, String senha){

        Log.d("user e senha", "user: "+user+" senha: "+senha);
        url = BASE_URL + "executar_login/"+user+"/"+senha;
        Professor professor = Professor.getInstance();
        try{

            ResponseEntity<Professor> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Professor>() {});
            professor = response.getBody();
            /*Professor = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Professor>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Professor>() {}).getBody();*/

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return professor;
    }

    public boolean listar(String user) {
        url = BASE_URL+"procuraUsuario/"+user;
        Professor professor = Professor.getInstance();
        try{

            ResponseEntity<Professor> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Professor>() {});
            professor = response.getBody();
            if(professor!=null){
                return true;
            }
            else{
                return false;
            }
            /*Professor = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Professor>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Professor>() {}).getBody();*/

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
