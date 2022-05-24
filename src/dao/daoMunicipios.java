
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Municipios;

public class daoMunicipios {

    private DataSource dataSource;
    

    public daoMunicipios(DataSource dataSource){
        this.dataSource = dataSource;
    }
            

    public ArrayList<Municipios> readAll(){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MUNICIPIOS";

            ps = dataSource.getConnection().prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            ArrayList<Municipios> lista = new ArrayList<Municipios>();
            
 
            while(rs.next()){

                Municipios cidade = new Municipios();

                cidade.setIdMunicipios(rs.getInt("idMunicipios"));
                cidade.setCidade(rs.getString("cidade"));
                cidade.setUf(rs.getString("uf"));
                
                lista.add(cidade);
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


    public ArrayList<Municipios> readOne(String descricao){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM MUNICIPIOS WHERE (cidade LIKE ?)";


            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+descricao+"%");
            

            ResultSet rs = ps.executeQuery();
            
 
            ArrayList<Municipios> lista = new ArrayList<Municipios>();
            
            while(rs.next()){
                Municipios cidade = new Municipios();

                cidade.setIdMunicipios(rs.getInt("idMunicipios"));
                cidade.setCidade(rs.getString("cidade"));
                cidade.setUf(rs.getString("uf"));
                
                lista.add(cidade);
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

    public void create(Municipios m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO MUNICIPIOS (cidade,uf) VALUES (?,?)";

            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getCidade());
            ps.setString(2,m.getUf());

            ps.executeUpdate();
            ps.close();

        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao salvar!\n"+ex);
        }
        
        /*
        finally{
            //ps.close();
            dataSource.closeDataSource();
        }
        */
        
    }

    public void alterar(Municipios m){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "UPDATE MUNICIPIOS SET cidade=?, uf=? WHERE idMunicipios=?";
            
            ps = con.prepareStatement(SQL);
            ps.setString(1,m.getCidade());
            ps.setString(2,m.getUf());
            ps.setInt(3,m.getIdMunicipios());

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
            String SQL = "DELETE FROM MUNICIPIOS WHERE (idMunicipios = ?)";
            
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
