package Fazenda;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Control_Gerenciar_Usuarios {
    @FXML
    ComboBox<String> campoUsuario;
    @FXML
    TextField campoEmail;
    @FXML
    PasswordField campoSenha1;
    @FXML
    PasswordField campoSenha2;
    @FXML
    TableView tabela;
    @FXML
    TableColumn<Usuario, String> colunaUsuario;
    @FXML
    TableColumn<Usuario, String> colunaEmail;
    
    Dao<Usuario> daoUsuario;
    @FXML
    public void initialize() {
        // Inicializa o DAO para a classe Usuario
        daoUsuario = new Dao<>(Usuario.class);

        // Preenche o ComboBox com os brincos das vacas cadastradas
        carregaInfos();
        carregaUsuarios();
    }

    
    public void carregaUsuarios(){
        List<Usuario> usuarios = daoUsuario.listarTodos();
        for (Usuario users : usuarios) {
            campoUsuario.getItems().add(users.getNome());
        }
    }
    
    @FXML
    public void carregaDadosUsuario(){
        String usuario = campoUsuario.getValue();
        List <Usuario> listaUsuarios = daoUsuario.listarTodos();
        for(Usuario user : listaUsuarios){
            if(user.getNome().equals(usuario)){
                campoEmail.setText(user.getLogin());
                campoSenha1.setText(user.getSenha1());
                campoSenha2.setText(user.getSenha2());
                break;
            }
        }
    }
    
    
    private void carregaInfos() {
        List<Usuario> usuarios = daoUsuario.listarTodos();

        // Configura as colunas do TableView para exibirem os atributos do objeto Usuario
        colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("login"));

        // Adiciona os usuários ao TableView
        tabela.getItems().setAll(usuarios);
    }
    
    public void alterar() {
    String nome = (String) campoUsuario.getValue();
    boolean usuarioExiste = false;
    
    if(campoEmail.getText().equals("") || campoSenha1.getText().equals("") || campoSenha2.getText().equals("")){
        CustomAlert.showError("Selecione um usuário!");
    }

    List<Usuario> usuarios = daoUsuario.listarTodos();

    for (Usuario user : usuarios) {
        if (user.getNome().equals(nome)) {
            usuarioExiste = true;

            if (campoSenha1.getText().equals(campoSenha2.getText())) {
                Usuario novo = new Usuario(
                    nome,
                    campoEmail.getText(), // Novo email
                    campoSenha1.getText(), // Nova senha
                    campoSenha2.getText()  // Confirmação da senha
                );

                daoUsuario.alterar("nome", nome, novo);

                // Exibe uma mensagem de sucesso
                CustomAlert.showConfirmation("Dados alterados com sucesso!");
                break;
            } else {
                // Caso as senhas não coincidam
                CustomAlert.showError("Coloque senhas iguais");
                break;
            }
        }
    }

    if (!usuarioExiste) {
        CustomAlert.showError("Usuário não existente!");
    }
}
    
    // MÉTODOS PARA ABRIR TELAS
    
    @FXML
    public void abrirCadastroVacas() throws IOException{
        App.setRoot("CadastroVacas");
    }
    
    @FXML
    public void abrirCadastroUsuario() throws IOException{
        App.setRoot("RegistroUsuario");
    }
    
    @FXML
    public void abrirRegistroProducao() throws IOException{
        App.setRoot("RegistroProducao");
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
