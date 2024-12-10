package Fazenda;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Control_TelaInicial {
    @FXML
    TextField campoLogin;
    @FXML
    TextField campoSenha;
    
    Dao <Usuario> usuarioDao = new Dao<>(Usuario.class);
    
    @FXML
    public void Entrar() throws IOException {
        String login = campoLogin.getText();
        String senha = campoSenha.getText();
        
        List <Usuario> usuarios = usuarioDao.listarTodos();
        boolean liberado = false;
        for(Usuario user : usuarios){
            if(user.getLogin().equals(login) && user.getSenha1().equals(senha)){
                liberado = true;
                break;
            }
        }
        if(liberado){
            abrirRegistroProducao();
        }
        else{
            CustomAlert.showError("Usuario não encontrado!");
        }
    }
    
    public void abrirRegistroProducao() throws IOException{
        App.setRoot("RegistroProducao");
    }
}
    