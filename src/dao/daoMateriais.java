
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Materiais;

public class daoMateriais {

    private DataSource dataSource;
    
    public daoMateriais(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Materiais> readAll(){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MATERIAIS";
            
            ps = dataSource.getConnection().prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            
            ArrayList<Materiais> lista = new ArrayList<Materiais>();

            while(rs.next()){
                
                Materiais m = new Materiais();

                m.setIdMateriais(rs.getInt("idMateriais"));
                m.setDescricao(rs.getString("descricao"));
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

        public ArrayList<Materiais> readOne(String descricao){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MATERIAIS WHERE (categorias LIKE ?)";

            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+descricao+"%");

            ResultSet rs = ps.executeQuery();

            ArrayList<Materiais> lista = new ArrayList<Materiais>();
            
            while(rs.next()){

                Materiais m = new Materiais();
                
                m.setIdMateriais(rs.getInt("idMateriais"));
                m.setDescricao(rs.getString("descricao"));
                m.setCategorias(rs.getString("materiais"));

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

    public void create(Materiais m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO MATERIAIS (descricao,categorias) VALUES (?,?)";
            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getDescricao());
            ps.setString(2,m.getCategorias());

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

        public void alterar(Materiais m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "UPDATE MATERIAIS SET descricao=?, categorias=? WHERE idMateriais=?";

            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getDescricao());
            ps.setString(2,m.getCategorias());
            ps.setInt(3,m.getIdMateriais());

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
            String SQL = "DELETE FROM MATERIAIS WHERE (idMateriais = ?)";

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
