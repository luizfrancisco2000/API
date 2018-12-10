package cptech.com.controltutor.Connect;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import cptech.com.controltutor.Controle.Notificacao;

public class NotificacaoRestClient extends RestClient {
    private final String BASE_URL = BASE + "notificacao/";
    private RestTemplate restTemplate;
    private String url;

    public NotificacaoRestClient() {
        restTemplate = new RestTemplate();
    }

    public boolean insertNotificacao(Notificacao notificacao) {
        url = BASE_URL + "cadastrar/" + notificacao.getUsuario().getId();
        JSONObject jsonObject = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper om = new ObjectMapper();
        om.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            jsonObject.put("id_solicitante", notificacao.getId_solicitante());
            jsonObject.put("visto", notificacao.isVisto());
            jsonObject.put("mensagem", notificacao.getMensagem());
            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            restTemplate.postForEntity(url, entity, null);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Notificacao> pequisaNotificacao(Long aLong) {
        url = BASE_URL + "user/"+aLong;
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Notificacao>>() {}).getBody();
    }
}
