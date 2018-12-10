package cptech.com.controltutor.Controle;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luiz
 */


public class Notificacao {
    private Long id;
    private Usuario usuario;
    private boolean visto;
    private String mensagem;
    private Long id_solicitante;

    public Notificacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getId_solicitante() {
        return id_solicitante;
    }

    public void setId_solicitante(Long id_solicitante) {
        this.id_solicitante = id_solicitante;
    }


}
