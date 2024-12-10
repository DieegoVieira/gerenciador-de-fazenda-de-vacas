package Fazenda;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Vaca {
    @BsonProperty(value="brinco")
    private String brinco;
    @BsonProperty(value="nome")
    private String nome;
    @BsonProperty(value="raca")
    private String raca;
    
    public Vaca(String earrings, String name, String breed){
        brinco = earrings;
        nome = name;
        raca = breed;
    }
    
    public Vaca(){
        
    }
    
    
    // GETs e SETs
    public String getBrinco(){
        return brinco;
    }
    public String getNome(){
        return nome;
    }
    public String getRaca(){
        return raca;
    }
    
    public void setBrinco(String earrings){
        brinco = earrings;
    }
    public void setNome(String name){
        nome = name;
    }
    public void setRaca(String breed){
        raca = breed;
    }
}
