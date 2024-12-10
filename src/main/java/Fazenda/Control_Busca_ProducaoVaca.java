package Fazenda;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Control_Busca_ProducaoVaca {
    @FXML
    ComboBox<String> campoBrinco;
    @FXML
    ComboBox<String> campoNome;
    @FXML
    TextField campoRaca;
    @FXML
    TextField campoProducaoDia;
    @FXML
    TextField campoProducaoMes;
    @FXML
    TextField campoProducaoVida;
    @FXML
    DatePicker campoData;
    
    Dao<Vaca> daoVaca;
    Dao<Producao> daoProducao;
    
    @FXML
    public void initialize() {
        // Inicializa o DAO para a classe Vaca
        daoVaca = new Dao<>(Vaca.class);
        daoProducao = new Dao<>(Producao.class);
        
        campoData.setValue(LocalDate.now());

        // Preenche o ComboBox com os brincos das vacas cadastradas
        carregaInfos();
    }

    private void carregaInfos() {
        List<Vaca> vacas = daoVaca.listarTodos();
        for (Vaca vaca : vacas) {
            campoBrinco.getItems().add(vaca.getBrinco());
            campoNome.getItems().add(vaca.getNome());
        }
    }
    
    @FXML
    public void carregaNomeRaca(){
        String brinco = (String) campoBrinco.getValue();
        List<Vaca> vacas = daoVaca.listarTodos();
        for (Vaca vaca : vacas){
            if(vaca.getBrinco().equals(brinco)){
                campoNome.setValue(vaca.getNome());
                campoRaca.setText(vaca.getRaca());
                break;
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
                campoRaca.setText(vaca.getRaca());
                break;
            }
        }
    }
    
    @FXML
    public void BuscarProducao(){
        Double producaoMes = 0.0;
        Double producaoVida = 0.0;
        Double producaoDia = 0.0;
        String dataSelecionada = campoData.getValue().toString();
        Vaca cow = new Vaca(campoBrinco.getValue(), campoNome.getValue(), campoRaca.getText());
        
        List <Producao> producoes = daoProducao.listarTodos();
        
        for(Producao product : producoes){
            // Produção da vaca na data requerida
            if(product.getVaca().getBrinco().equals(cow.getBrinco()) && product.getData().equals(dataSelecionada)){
                producaoDia += product.getQuantidade();
            }
            //Produção no mês escolhido
            String AnoMes = product.getData().substring(0, 7);
            if(product.getVaca().getBrinco().equals(cow.getBrinco()) && dataSelecionada.substring(0, 7).equals(AnoMes)){
                producaoMes += product.getQuantidade();
            }
            if(product.getVaca().getBrinco().equals(cow.getBrinco())){
                producaoVida += product.getQuantidade();
            }
        }
        campoProducaoDia.setText(producaoDia.toString() + " litros");
        campoProducaoMes.setText(producaoMes.toString() + " litros");
        campoProducaoVida.setText(producaoVida.toString() + " litros");
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
    public void abrirGerenciadorUsuarios() throws IOException{
        App.setRoot("GerenciarUsuarios");
    }
    
    
    @FXML
    public void abrirConsultarProducaoPeriodo() throws IOException{
        App.setRoot("Producao-dia-mes");
    }
}