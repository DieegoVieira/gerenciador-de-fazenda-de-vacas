package Fazenda;

public class Producao {
    private Vaca vaca;
    private String data;
    private Double quantidade;
    
    public Producao(Vaca cow, String date, Double quantity){
        vaca = cow;
        data = date;
        quantidade = quantity;
    }
    
    public Producao(){}
    
    // SETs e GETs
    public Vaca getVaca() {
        return vaca;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    
}