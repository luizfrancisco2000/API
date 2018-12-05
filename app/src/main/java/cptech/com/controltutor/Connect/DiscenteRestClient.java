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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Discente;

public class DiscenteRestClient extends RestClient {
    //http://10.100.37.192:8000
    //http://10.100.38.128:8000
    //http://10.100.45.241:8000
    //10.100.38.136
    private final String BASE_URL = BASE + "discente/";
    private RestTemplate restTemplate;
    private String url;
    private JSONObject jsonObject = new JSONObject();
    private HttpHeaders headers = new HttpHeaders();
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
            valuesDiscente.put("tutor", discente.getTutor());
            valuesDiscente.put("professor", discente.getProfessor());
            valuesDiscente.put("codigos", discente.getCodigos());
            valuesDiscente.put("notas", discente.getNotas());
            valuesDiscente.put("ativo", discente.isAtivo());
            valuesDiscente.put("firstAcess", discente.isFirstAcess());
            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("id", discente.getId());
            jsonObject.put("usuario", discente.getUsuario());
            jsonObject.put("nome", discente.getNome());
            jsonObject.put("senha", discente.getSenha());
            jsonObject.put("turma", discente.getTurma());
            jsonObject.put("tipo", discente.getTipo());
            jsonObject.put("tutor", discente.getTutor());
            jsonObject.put("professor", discente.getProfessor());
            jsonObject.put("codigos", discente.getCodigos());
            jsonObject.put("notas", discente.getNotas());
            jsonObject.put("ativo", discente.isAtivo());
            jsonObject.put("firstAcess", discente.isFirstAcess());
            Log.d("Deu", "Deu");
            try{
                HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
                restTemplate.postForEntity(url, entity, null);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public void save(){
        try{
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Discente updateAlunoFromTheCod(Codigo codigo) {
        url = BASE_URL + "atualizar";
        try {
            Discente discente = codigo.getDiscente();
            HashMap<String, Object> valuesDiscente = new HashMap<>();
            valuesDiscente.put("usuario", discente.getUsuario());
            valuesDiscente.put("nome", discente.getNome());
            valuesDiscente.put("senha", discente.getSenha());
            valuesDiscente.put("turma", discente.getTurma());
            valuesDiscente.put("tipo", discente.getTipo());
            valuesDiscente.put("tutor", discente.getTutor());
            valuesDiscente.put("professor", discente.getProfessor());
            valuesDiscente.put("codigos", JSONObject.wrap(discente.getCodigos()));
            valuesDiscente.put("notas", JSONObject.wrap(discente.getNotas()));
            valuesDiscente.put("ativo", discente.isAtivo());
            valuesDiscente.put("firstAcess", discente.isFirstAcess());
            headers.setContentType(MediaType.APPLICATION_JSON);
            jsonObject.put("usuario", discente.getUsuario());
            Log.d("NOME", discente.getNome());
            jsonObject.put("id", discente.getId());
            jsonObject.put("usuario", discente.getUsuario());
            jsonObject.put("nome", discente.getNome());
            jsonObject.put("senha", discente.getSenha());
            jsonObject.put("turma", discente.getTurma());
            jsonObject.put("tipo", discente.getTipo());
            jsonObject.put("tutor", discente.getTutor());
            jsonObject.put("professor", discente.getProfessor());
            jsonObject.put("codigos", JSONObject.wrap(discente.getCodigos()));
            jsonObject.put("notas", JSONObject.wrap(discente.getNotas()));
            jsonObject.put("ativo", discente.isAtivo());
            jsonObject.put("firstAcess", discente.isFirstAcess());
            Log.d("Deu", "Deu");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Discente loginDiscente(String user, String senha) {

        Log.d("user e senha", "user: " + user + " senha: " + senha);
        url = BASE_URL + "executar_login/" + user + "/" + senha;
        Discente discente = Discente.getInstance();
        try {

            ResponseEntity<Discente> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Discente>() {
            });
            discente = response.getBody();
            /*discente = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Discente>(){}).getBody();*/
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Discente>() {}).getBody();*/

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return discente;
    }

    public Discente procurarId(Long id) {

        url = BASE_URL + String.valueOf(id);
        Log.d("ID", String.valueOf(id));
        Discente discente = Discente.getInstance();
        try {

            ResponseEntity<Discente> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Discente>() {
            });
            discente = response.getBody();
            return discente;
            /*discente = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Discente>(){}).getBody();
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Discente>() {}).getBody();
            return true;*/
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<Discente> procurarIdd(Long id) {

        url = BASE_URL + String.valueOf(id);
        Log.d("ID", String.valueOf(id));
        Discente discente = Discente.getInstance();
        try {

            ResponseEntity<Discente> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Discente>() {
            });
            return response;

            /*discente = restTemplate.exchange(url,HttpMethod.GET,null,
                    new ParameterizedTypeReference<Discente>(){}).getBody();
           /* restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Discente>() {}).getBody();
            return true;*/
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
