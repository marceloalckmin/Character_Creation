package DAO;

import br.inatel.cdg.Player;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerDAO extends ConectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    public boolean inserirPersonagem(Player p) {
        connectToDB();
        String sql = "INSERT INTO Player (nome,senha,idPlayer) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, p.nome);
            pst.setInt(2, p.senha);
            pst.setInt(3, p.idPlayer);
            pst.execute();
            sucesso = true;
        } catch(SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean atualizarPersonagem(int id, Player p) {
        connectToDB();
            String sql = "UPDATE Player SET nome=?, senha=?, idPlayer=? where idPlayer=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, p.nome);
            pst.setInt(2, p.senha);
            pst.setInt(3, p.idPlayer);
            pst.setInt(4,id);
            pst.execute();
            sucesso = true;
        } catch(SQLException ex) {
            System.out.println("Erro = " +  ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    public boolean deletarPersonagem(int id) {
        connectToDB();
        String sql = "DELETE FROM Player where idPlayer=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } catch(SQLException ex) {
            System.out.println("Erro = " +  ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    public ArrayList<Player> buscarPlayerSemFiltro() {
        ArrayList<Player> listaDePlayers = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Player";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Player: ");
            while (rs.next()) {
                Player playerAux = new Player();
                playerAux.idPlayer = rs.getInt("idPlayer");
                playerAux.nome = rs.getString("nome");
                playerAux.senha = rs.getInt("Senha");
                System.out.println("idPlayer = " + playerAux.idPlayer);
                System.out.println("nome = " + playerAux.nome);
                System.out.println("Senha = " + playerAux.senha);

                System.out.println("--------------------------------");
                listaDePlayers.add(playerAux);
            }
            sucesso = true;
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return listaDePlayers;
    }

}
