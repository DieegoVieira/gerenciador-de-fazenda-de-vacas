package Fazenda;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Control_Registro_Producao {
    @FXML
    ComboBox<String> campoBrinco;
    @FXML
    ComboBox<String> campoNome;
    @FXML
    ComboBox<String> campoRaca;
    @FXML
    TextField campoLitros;
    @FXML
    DatePicker campoData;
    
    Dao<Vaca> daoVaca;
    @FXML
    public void initialize() {
        // Inicializa o DAO para a classe Vaca
        daoVaca = new Dao<>(Vaca.class);
        
        campoData.setValue(LocalDate.now());

        // Preenche o ComboBox com os brincos das vacas cadastradas
        carregaInfos();
    }

    private void carregaInfos() {
        List<Vaca> vacas = daoVaca.listarTodos(); // Busca todas as vacas no banco de dados
        for (Vaca vaca : vacas) {
            campoBrinco.getItems().add(vaca.getBrinco()); // Adiciona o brinco de cada vaca no ComboBox
            campoNome.getItems().add(vaca.getNome());
            campoRaca.getItems().add(vaca.getRaca());
        }
    }
    
    @FXML
    public void carregaNomeRaca(){
        String brinco = (String) campoBrinco.getValue();
        List<Vaca> vacas = daoVaca.listarTodos();
        for (Vaca vaca : vacas){
            if(vaca.getBrinco().equals(brinco)){
                campoNome.setValue(vaca.getNome());
                campoRaca.setValue(vaca.getRaca());
            }
        }
    }
    
    @FXML
    public void carregaBrincoRaca(){
        String nome = (String) campoNome.getValue();
        List<Vaca> vacas = daoVaca.listarTodos();
        for (Vaca vaca : vacas){
            if(vaca.getNome().equals(nome)){
                campoBrinco.setValue(vaca.getBrinco());
                campoRaca.setValue(vaca.getRaca());
            }
        }
    }
    
    @FXML
    public void RegistraProducao(){
        if(campoLitros.getText().equals("")){
            CustomAlert.showError("Insira a litragem da produção");
            return;
        }
        Vaca cow = new Vaca(campoBrinco.getValue(), campoNome.getValue(), campoRaca.getValue());
        Producao p = new Producao(cow, campoData.getValue().toString(), Double.valueOf(campoLitros.getText()));
        Dao<Producao> dao = new Dao(Producao.class);
        dao.inserir(p);
        CustomAlert.showConfirmation("Produção registrada com sucesso!");
        limparTudo();
    }
    
    public void limparTudo(){
        campoNome.setValue("");
        campoBrinco.setValue("");
        campoRaca.setValue("");
        campoLitros.setText("");
        campoData.setValue(LocalDate.now());
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
