package Fazenda;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Control_Cadastro_Vacas {
    @FXML
    TextField campoBrinco;
    @FXML
    TextField campoNome;
    @FXML
    TextField campoRaca;
    
    Dao <Vaca> daoVaca = new Dao<>(Vaca.class);
    
    @FXML
    public void InserirVaca() {
        String brinco = campoBrinco.getText();
        String nome = campoNome.getText();
        String raca = campoRaca.getText();
        if(brinco.equals("") || nome.equals("") || raca.equals("")){
            CustomAlert.showError("Preencha todas as informações!");
            return;
        }
        Vaca v = new Vaca(brinco, nome, raca);
        List <Vaca> vacas = daoVaca.listarTodos();
        for(Vaca cow : vacas){
            if(cow.getNome().equals(nome)){
                CustomAlert.showError("Nome de vaca já existente. Mude o nome da vaca!");
                return;
            }
            else if(cow.getBrinco().equals(brinco)){
                CustomAlert.showError("Brinco já existente. Altere o código do brinco!");
                return;
            }
        }
        Dao<Vaca> dao = new Dao(Vaca.class);
        dao.inserir(v);
        CustomAlert.showConfirmation("Vaca cadastrada com sucesso!");
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
