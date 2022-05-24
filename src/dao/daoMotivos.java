
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Motivos;

public class daoMotivos {

    private DataSource dataSource;

    public daoMotivos(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Motivos> readAll(){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MOTIVOS";
            
            ps = dataSource.getConnection().prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            ArrayList<Motivos> lista = new ArrayList<Motivos>();

            while(rs.next()){

                Motivos m = new Motivos();

                m.setIdMotivo(rs.getInt("idMotivo"));
                m.setDsMotivo(rs.getString("dsMotivo"));
                m.setCategorias(rs.getString("categorias"));

                lista.add(m);
            }

            ps.close();
            dataSource.closeDataSource();

            return lista;
        }
        catch (SQLException ex){
            System.err.println("Erro ao recuperar dados "+ex.getMessage());
        }
        catch (Exception ex){
            System.err.println("Erro geral "+ex.getMessage());
        }

        dataSource.closeDataSource();

        return null;
    }

        public ArrayList<Motivos> readOne(String descricao){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MOTIVOS WHERE (categorias LIKE ?)";

            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+descricao+"%");

            ResultSet rs = ps.executeQuery();

            ArrayList<Motivos> lista = new ArrayList<Motivos>();
            
            while(rs.next()){

                Motivos m = new Motivos();
                
                m.setIdMotivo(rs.getInt("idMotivo"));
                m.setDsMotivo(rs.getString("dsMotivo"));
                m.setCategorias(rs.getString("categorias"));
                lista.add(m);
            }
            

            ps.close();
            dataSource.closeDataSource();

            return lista;
        }
        catch (SQLException ex){
            System.err.println("Erro ao recuperar dados "+ex.getMessage());
        }
        catch (Exception ex){
            System.err.println("Erro geral "+ex.getMessage());
        }

        dataSource.closeDataSource();

        return null;
    }
    public void create(Motivos m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO MOTIVOS (dsMotivo, categorias ) VALUES (?,?)";

            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getDsMotivo());
            ps.setString(2,m.getCategorias());

            // executa a inserção no banco
            ps.executeUpdate();
            ps.close();
            
        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao salvar!\n"+ex);
        }
        finally{
            //ps.close();
            dataSource.closeDataSource();
        }
    }

        public void alterar(Motivos m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{

            String SQL = "UPDATE MOTIVOS SET dsMotivo=?, categorias=? WHERE idMotivo=? ";
              
            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getDsMotivo());
            ps.setString(2,m.getCategorias());
            ps.setInt(3,m.getIdMotivo());

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao alterar!\n"+ex);
        }
        finally{

            dataSource.closeDataSource();
        }
    }
        
        public void remover(int codigo){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "DELETE FROM MOTIVOS WHERE (idMotivo = ?)";
            
            ps = con.prepareStatement(SQL);
            ps.setInt(1,codigo);

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao excluir!\n"+ex);
        }
        finally{
            dataSource.closeDataSource();
        }
    }
}
