package DAO;

import br.inatel.cdg.Avatar;

import java.sql.SQLException;
import java.util.ArrayList;


public class AvatarDAO extends ConectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    public boolean inserirAvatar(Avatar p) {
        connectToDB();
        String sql = "INSERT INTO Avatar (idAvatar,nome,raca,classe,forca,destreza,inteligencia,Player_idPlayer) values(?,?,?,?,?,   ?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.idAvatar);
            pst.setString(2, p.nome);
            pst.setString(3, p.raca);
            pst.setString(4, p.classe);
            pst.setInt(5, p.forca);
            pst.setInt(6, p.destreza);
            pst.setInt(7, p.inteligencia);
            pst.setInt(8, p.Player_idPlayer);
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

    public boolean atualizarAvatar(int id, Avatar p) {
        connectToDB();
        String sql = "UPDATE Avatar SET nome=?,raca=?,classe=?,forca=?,destreza=?,inteligencia=?,Player_idPlayer=? where idAvatar=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, p.nome);
            pst.setString(2, p.raca);
            pst.setString(3, p.classe);
            pst.setInt(4, p.forca);
            pst.setInt(5, p.destreza);
            pst.setInt(6, p.inteligencia);
            pst.setInt(7, p.Player_idPlayer);
            pst.setInt(8, id);
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
    public boolean deletarAvatar(int id) {
        connectToDB();
        String sql = "DELETE FROM Avatar where idAvatar=?";
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
    public ArrayList<Avatar> buscarAvatarSemFiltro() {
        ArrayList<Avatar> listaDeAvatares = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Avatar";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Avatares: ");
            while (rs.next()) {
                Avatar avatarAux = new Avatar();
                avatarAux.idAvatar = rs.getInt("idAvatar");
                avatarAux.nome = rs.getString("nome");
                avatarAux.raca = rs.getString("raca");
                avatarAux.classe = rs.getString("classe");
                avatarAux.forca = rs.getInt("forca");
                avatarAux.inteligencia = rs.getInt("inteligencia");
                avatarAux.destreza = rs.getInt("destreza");
                avatarAux.Player_idPlayer = rs.getInt("Player_idPlayer");
                System.out.println("idPersonagem = " + avatarAux.idAvatar);
                System.out.println("nome = " + avatarAux.nome);
                System.out.println("raça = " + avatarAux.raca);
                System.out.println("Força = " + avatarAux.forca);
                System.out.println("Destreza = " + avatarAux.destreza);
                System.out.println("Inteligencia = " + avatarAux.inteligencia);
                System.out.println("classe = " + avatarAux.classe);
                System.out.println("Player_idPlayer = " + avatarAux.Player_idPlayer);
                System.out.println("--------------------------------");
                listaDeAvatares.add(avatarAux);
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
        return listaDeAvatares;
    }

}
