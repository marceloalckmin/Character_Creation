import DAO.AvatarDAO;
import DAO.PlayerDAO;
import DAO.ServerDAO;
import DAO.TokenDAO;
import br.inatel.cdg.Avatar;
import br.inatel.cdg.Player;
import br.inatel.cdg.Servers;
import br.inatel.cdg.Token;


import java.sql.SQLOutput;
import java.util.Scanner;
// Kayky Peres 177 e Marcelo Alckmin 119
@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        Avatar a1 = new Avatar();
        Avatar a2 = new Avatar();
        Avatar a3 = new Avatar();
        Avatar a4 = new Avatar();
        Avatar a6 = new Avatar();
        AvatarDAO aDao = new AvatarDAO();
        Player p1 = new Player();
        Player p2 =new Player();
        PlayerDAO pDao = new PlayerDAO();
        ServerDAO sDao = new ServerDAO();
        Servers s1 = new Servers();
        Servers s2 = new Servers();

        //Criando personagens, usuarios, tokens e servidores para não começar o banco do zero, como se já tivessem jogadores no jogo
        //player1
        p1.nome = "Kayky";
        p1.senha=1109;
        p1.idPlayer = 11;
        //player2
        p2.nome="Marcelo";
        p2.senha = 2469;
        p2.idPlayer = 420;
        pDao.inserirPersonagem(p1);
        pDao.inserirPersonagem(p2);
        a1.nome= "Legolas";
        a1.raca= "Elfo";
        a1.forca = 10;
        a1.destreza = 15;
        a1.inteligencia = 0;
        a1.classe= "Ladino";
        a1.Player_idPlayer = p1.idPlayer;
        aDao.inserirAvatar(a1);
        a2.idAvatar = 1;
        a2.nome= "Arthur";
        a2.raca= "Humano";
        a2.forca = 20;
        a2.destreza = 10;
        a2.inteligencia = 0;
        a2.classe= "Paladino";
        a2.Player_idPlayer = p1.idPlayer;
        aDao.inserirAvatar(a2);
        a3.idAvatar = 2;
        a3.nome= "Pinha";
        a3.raca= "Bloco";
        a3.forca = 10;
        a3.destreza = 0;
        a3.inteligencia = 20;
        a3.classe= "Paralelepipedo";
        a3.Player_idPlayer = p2.idPlayer;
        aDao.inserirAvatar(a3);
        a4.idAvatar = 3;
        a4.nome= "Hubbao";
        a4.raca= "Rato";
        a4.forca = 0;
        a4.destreza = 30;
        a4.inteligencia = 0;
        a4.classe= "Ratazana";
        a4.Player_idPlayer = p2.idPlayer;
        aDao.inserirAvatar(a4);
        a6.idAvatar = 4;
        a6.nome= "Robert Deniro";
        a6.raca= "DarkElf";
        a6.forca = 0;
        a6.destreza = 20;
        a6.inteligencia = 10;
        a6.classe= "Arqueiro";
        a6.Player_idPlayer = p2.idPlayer;
        aDao.inserirAvatar(a6);
        s1.regiao = "Brasil";
        sDao.inserirSevers(s1);
        s2.idServers = 1;
        s2.regiao = "USA";
        sDao.inserirSevers(s2);

        Scanner entrada = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("============================================================");
            System.out.println(" SEJA BEM-VINDO AO MENU DE CRIAÇÃO DE PERSONAGENS! ");
            System.out.println(" CASO NÃO SEJA REGISTRADO CRIE SEU USUARIO, SENHA E ID" );
            System.out.println(" PARA PODER CRIAR SEU AVATAR! ");
            System.out.println(" CASO QUEIRA PROTEGER SUA CONTA CRIE UM TOKEN! ");
            System.out.println("  Essas são as opções disponíveis:  ");
            System.out.println("1 - Criação de Jogadores");
            System.out.println("2 - Criação de Avatares");
            System.out.println("3 - Criação de Token");
            System.out.println("4 - Criação de Server");
            System.out.println("5 - Deletar Avatar");
            System.out.println("6 - Informações dos Jogadores ");
            System.out.println("7 - Informações dos Avatares ");
            System.out.println("8 - Atualizar dados de um Jogador");
            System.out.println("9 - Atualizar dados de um Avatar");
            System.out.println("10 - Informações dos Servers");
            System.out.println("11 - Atualizar dados de um Server");
            System.out.println("============================================================");

            int op= entrada.nextInt();
            switch (op){
                //Criação de Jogadores
                case 1:
                    Player pAux = new Player();
                    System.out.println("Entre com o ID do jogador: ");
                    // Este id é usado para reconhecer a que jogador pertence um avatar quando ele é inserido no banco de dados, já que um jogador
                    // pode possuir mais de um avatar
                    pAux.idPlayer = entrada.nextInt();
                    System.out.print("Entre agora com o nome de usuario: ");
                    entrada.nextLine();
                    pAux.nome=entrada.nextLine();
                    System.out.print("Entre agora com sua senha (só números): ");
                    pAux.senha=entrada.nextInt();
                    PlayerDAO pd = new PlayerDAO();
                    pd.inserirPersonagem(pAux);
                    break;
                //Criação de Avatares
                case 2:
                    Avatar aAux = new Avatar();
                    System.out.println("Entre com as informações do avatar a ser criado:");
                    System.out.print("Nome: ");
                    entrada.nextLine();
                    aAux.nome = entrada.nextLine();
                    System.out.print("Raça: ");
                    aAux.raca = entrada.nextLine();
                    System.out.print("Classe: ");
                    aAux.classe = entrada.nextLine();

                    /*
                    Como é um sistema de criação de personagem e só existem 3 atributos base, o máximo de pontos de atributo será 30
                    caso passe de 30, é pedido para que o usuario entre novamente com os atributos
                     */
                    boolean boolAcha = true;
                    while(boolAcha) {
                        int pontos = 30;
                        System.out.print("Força: ");
                        aAux.forca = entrada.nextInt();
                        pontos = pontos - aAux.forca;
                        System.out.print("Destreza: ");
                        aAux.destreza = entrada.nextInt();
                        pontos = pontos - aAux.destreza;
                        System.out.print("Inteligência: ");
                        aAux.inteligencia = entrada.nextInt();
                        pontos = pontos - aAux.inteligencia;
                        if (pontos < 0) {
                            System.out.println("Numero de pontos inválido, por favor insira os atributos novamente.");
                        }
                        else boolAcha = false;
                    }
                    entrada.nextLine();
                    System.out.print("Id do avatar: ");
                    aAux.idAvatar = entrada.nextInt();
                    System.out.print("ID do player dono do avatar: ");
                    aAux.Player_idPlayer=entrada.nextInt();

                    //inserção do avatar criado no banco de dados
                    AvatarDAO ad = new AvatarDAO();
                    ad.inserirAvatar(aAux);
                    break;
                //Criação de Token
                case 3:
                    TokenDAO tDao = new TokenDAO();
                    Token tAux = new Token();
                    System.out.println("================== IMPORTANTE ==================");
                    System.out.println(" Anote o seu token em um lugar seguro ");
                    System.out.println( "Caso seja necessário um moderador lhe pedirá este código");
                    System.out.println(" e se o número dado ao moderador condizer com um numero presente no banco de dados");
                    System.out.println(" ele realizará a ação necessária para a recuperação de sua conta");
                    System.out.print(" Entre com o token escolhido: ");
                    tAux.token= entrada.nextInt();
                    System.out.print("Digite o ID do jogador no qual o token ficará vinculado à conta:");
                    tAux.Player_idPlayer = entrada.nextInt();
                    //id do token será registrado como id do proprio jogador para não haver 2 tokens com o mesmo id
                    //já que só haverá 1 token por jogador
                    tAux.idToken = tAux.Player_idPlayer;
                    tDao.inserirToken(tAux);
                    break;
                //Criação de Server
                case 4:
                    Servers sAux = new Servers();
                    System.out.print("Digite o ID do server a ser adicionado: ");
                    sAux.idServers = entrada.nextInt();
                    System.out.print("Digite o nome da região do servidor: ");
                    entrada.nextLine();
                    sAux.regiao = entrada.nextLine();
                    sDao.inserirSevers(sAux);
                    break;
                //Deletar Avatar
                case 5:
                    int id2;
                    System.out.println("Digite o ID do personagem a deletar: ");
                    id2 = entrada.nextInt();
                    aDao.deletarAvatar(id2);
                    break;
                //Informações dos Jogadores
                case 6:
                    pDao.buscarPlayerSemFiltro();
                    break;
                //Informações dos Avatares
                case 7:
                    aDao.buscarAvatarSemFiltro();
                    break;
                //Atualizar dados de um Jogador
                case 8:
                    Player p3 = new Player();
                    System.out.print("Digite o ID do player a ser atualizado: ");
                    int id = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Nome: ");
                    p3.nome = entrada.nextLine();
                    System.out.print("Senha: ");
                    p3.senha = entrada.nextInt();
                    p3.idPlayer = id;
                    pDao.atualizarPersonagem(id, p3);
                    break;
                //Atualizar dados de um Avatar
                case 9:
                    Avatar a7 = new Avatar();
                    System.out.print("Digite o ID do jogador que possui o avatar a ser atualizado: ");
                    a7.Player_idPlayer = entrada.nextInt();
                    System.out.print("Agora, o ID do avatar que deseja atualizar: ");
                    int idAv = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Agora, entre novamente com os dados do avatar :) ");
                    System.out.print("Nome: ");
                    a7.nome = entrada.nextLine();
                    System.out.print("Raça: ");
                    a7.raca= entrada.nextLine();
                    System.out.print("Classe: ");
                    a7.classe= entrada.nextLine();
                    System.out.print("Força: ");
                    a7.forca = entrada.nextInt();
                    System.out.print("Destreza: ");
                    a7.destreza = entrada.nextInt();
                    System.out.print("Inteligência: ");
                    a7.inteligencia = entrada.nextInt();
                    System.out.println("Pronto, avatar atualizado!");
                    aDao.atualizarAvatar(idAv,a7);

                    break;
                //Informações dos Servers
                case 10:
                    //lista todos os servidores existentes
                    sDao.buscarServersSemFiltro();
                    break;
                //Atualizar dados de um Server
                case 11:
                    Servers s3 = new Servers();
                    //id do server a ser atualizado
                    System.out.println("Digite o ID do server a ser atualizado: ");
                    int idsv = entrada.nextInt();
                    //regiao a ser atualizada no server
                    System.out.println("Qual a região correta desse servidor?");
                    entrada.nextLine();
                    s3.regiao = entrada.nextLine();
                    sDao.atualizarServers(idsv,s3);
                    break;
                default:
                    break;
            }

        }
    }
    }

