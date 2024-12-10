package Fazenda;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Control_Busca_ProducaoPeriodo {
    @FXML
    TextField campoProducaoTotal;
    @FXML
    DatePicker campoDataInicial;
    @FXML
    DatePicker campoDataFinal;
    
    Dao<Producao> daoProducao;
    
    @FXML
    public void initialize() {
        campoDataInicial.setValue(LocalDate.now());
        campoDataFinal.setValue(LocalDate.now());
        daoProducao = new Dao<>(Producao.class);
    }

    public void Buscar() {
        List<Producao> producoes = daoProducao.listarTodos();
        double total = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicial = LocalDate.parse(campoDataInicial.getValue().toString(), formatter);
        LocalDate dataFinal = LocalDate.parse(campoDataFinal.getValue().toString(), formatter);


        for (Producao production : producoes) {
            LocalDate dataProducao = LocalDate.parse(production.getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (!dataProducao.isBefore(dataInicial) && !dataProducao.isAfter(dataFinal)) {
                total += production.getQuantidade();
            }
        }

        campoProducaoTotal.setText(total + " litros");
    }

    
    

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
    public void abrirConsultarProducaoVaca() throws IOException{
        App.setRoot("ProducaoVaca");
    }
    
    
}
