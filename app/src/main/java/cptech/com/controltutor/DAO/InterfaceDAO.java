package cptech.com.controltutor.DAO;

/**
 * Created by Aluno on 13/08/2018.
 */

public interface InterfaceDAO<T> {
    public String adicionar(T classe);
    public String excluir(T classe);
    public String alterar(T classe);
    public T buscarByID(int id);

}
