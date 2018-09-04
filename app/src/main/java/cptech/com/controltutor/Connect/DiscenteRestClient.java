package cptech.com.controltutor.Connect;

import android.util.Log;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import cptech.com.controltutor.Controle.Discente;

public class DiscenteRestClient {
    private final String BASE_URL = "http://10.100.45.241:8000/discente/";
    private RestTemplate restTemplate;
    private String url;

    public DiscenteRestClient() {
        restTemplate = new RestTemplate();
    }

    public boolean insertDiscente(Discente discente) {
        url = BASE_URL + "cadastrar";
        try {
            HashMap<String, Object> valuesDiscente = new HashMap<>();
            valuesDiscente.put("usuario", discente.getUsuario());
            valuesDiscente.put("nome", discente.getNome());
            valuesDiscente.put("senha", discente.getSenha());
            valuesDiscente.put("turma", discente.getTurma());
            valuesDiscente.put("tipo", discente.getTipo());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("usuario", discente.getUsuario());
            jsonObject.put("nome", discente.getNome());
            jsonObject.put("senha", discente.getSenha());
            jsonObject.put("turma", discente.getTurma());
            jsonObject.put("tipo", discente.getTipo());
            jsonObject.put("tutor", discente.getTutor());
            jsonObject.put("professor", discente.getProfessor());
            jsonObject.put("codigos", discente.getCodigos());
            jsonObject.put("notas", discente.getNotas());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public Discente loginDiscente(String user, String senha){

        Log.d("user e senha", "user: "+user+" senha: "+senha);
        url = BASE_URL + "executar/"+user+"/"+senha;
        try{
            Discente discente = Discente.getInstance();
                    discente = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Discente>() {}).getBody();
            Log.d("nome", discente.getNome());
            return discente;
        }catch (Exception e){
            return null;
        }
    }
}
