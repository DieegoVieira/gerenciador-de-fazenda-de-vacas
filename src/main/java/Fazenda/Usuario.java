package Fazenda;
public class Usuario {
    private String login;
    private String nome;
    private String senha1;
    private String senha2;
    
    public Usuario(String nome, String login, String senha1, String senha2){
        this.nome = nome;
        this.login = login;
        this.senha1 = senha1;
        this.senha2 = senha2;
    }
    
    public Usuario(){}
    
    //SETs e GETs
    public String getLogin(){
        return login;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getSenha1(){
        return senha1;
    }
    
    public String getSenha2(){
        return senha2;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public void setNome(String name){
        nome = name;
    }
    public void setSenha1(String password){
        senha1 = password;
    }
    public void setSenha2(String password){
        senha2 = password;
    }
}
