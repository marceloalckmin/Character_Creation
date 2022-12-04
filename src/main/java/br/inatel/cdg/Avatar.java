package br.inatel.cdg;

public class Avatar {

    public int idAvatar;
    public String nome;
    public String raca;
    public int forca;
    public int destreza;
    public int inteligencia;
    public String classe;
    public int Player_idPlayer;

    public void mostraInfo(){
        System.out.println(this.idAvatar);
        System.out.println(this.nome);
        System.out.println(this.raca);
        System.out.println(this.forca);
        System.out.println(this.destreza);
        System.out.println(this.inteligencia);
        System.out.println(this.classe);
        System.out.println(this.Player_idPlayer);
    }
}
