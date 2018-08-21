package cptech.com.controltutor.DAO;


import android.os.AsyncTask;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import cptech.com.controltutor.Controle.Codigo;
import cptech.com.controltutor.Controle.Discente;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Aluno on 13/08/2018.
 */

public class CodigoDAO {
    /**
     * Comandos para fazer a função desejada lá nos servlet
     */
    private static final String SALVAR = "salvar";
    private static final String DELETAR = "deletar";
    private static final String ATUALIZAR = "atualizar";
    private static final String FIND_BY_ID = "procurarID";
    private static final String FIND_BY_DISCENTE = "procurarByDiscente";
    private static final String LISTAR_TODOS = "Listar";

    /**
     *URL da página*/
    private static final String URL = "localhost:8084/APIAndroid/CodigoDAO";

    /**
     * Atributos para fazer o programa rodar*/

    /*
                try {
                URIBuilder builder = new URIBuilder("http://192.168.43.233/MyProjectAndroid/hello");   //.net servlet url here                builder.setParameter("Name","John Smith");//    this is parameter                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(builder.build());
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return output;
        }*/
    public String adicionar(Codigo codigo) throws URISyntaxException, IOException {
        return null;
    }

    public String excluir() {
        return null;
    }

    public String alterar() {
        return null;
    }

    public Discente buscarByID() {
        return null;
    }
}
