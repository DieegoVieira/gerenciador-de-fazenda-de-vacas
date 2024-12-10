package Fazenda;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Control_Cadastrar_Usuarios {
    @FXML
    TextField campoNome;
    @FXML
    TextField campoLogin;
    @FXML
    TextField campoSenha1;
    @FXML
    TextField campoSenha2;
    
    Dao <Usuario> usuarioDao = new Dao<>(Usuario.class);
    
    @FXML
    public void InserirUsuario() {
        String nome = campoNome.getText();
        String login = campoLogin.getText();
        String senha1 = campoSenha1.getText();
        String senha2 = campoSenha2.getText();
        if(nome.equals("") || login.equals("") || senha1.equals("") || senha2.equals("")){
            CustomAlert.showError("Preencha todas as informações!");
            return;
        }
        List<Usuario> usuarios = usuarioDao.listarTodos();
        boolean usuarioRepetido = false;
        boolean emailRepetido = false;
        for(Usuario user : usuarios){
            if(user.getNome().equals(nome)){
                usuarioRepetido = true;
            }
            if(user.getLogin().equals(login)){
                emailRepetido = true;
            }
        }
        if(usuarioRepetido && emailRepetido){
            CustomAlert.showError("Usuário já existente! Gerencie o usuário no menu Gerenciar Usuarios.");
        }
        else if(usuarioRepetido){
            CustomAlert.showError("Este nome já existe. Altere o nome para um nome inexistente.");
        }
        else if(emailRepetido){
            CustomAlert.showError("Este email já existe. Altere o email para um email inexistente.");
        }else{
            if(senha1.equals(senha2)){
                Usuario u = new Usuario(nome, login, senha1, senha2);
                Dao<Usuario> dao = new Dao(Usuario.class);
                dao.inserir(u);
                CustomAlert.showConfirmation("Usuário registrado com sucesso!");
            }
            else{
                CustomAlert.showError("Digite senhas iguais!");
            }
            
        }
           
    }
    
    @FXML
    public void abrirCadastroVacas() throws IOException{
        App.setRoot("CadastroVacas");
    }
    
    @FXML
    public void abrirRegistroProducao() throws IOException{
        App.setRoot("RegistroProducao");
    }
    
    @FXML
    public void abrirGerenciadorUsuarios() throws IOException{
        App.setRoot("GerenciarUsuarios");
    }
    
    @FXML
    public void abrirConsultarProducaoVaca() throws IOException{
        App.setRoot("ProducaoVaca");
    }
    
    @FXML
    public void abrirConsultarProducaoPeriodo() throws IOException{
        App.setRoot("Producao-dia-mes");
    }
}
    