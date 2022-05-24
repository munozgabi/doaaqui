
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuarios;
import java.sql.Date;

public class daoUsuario {
    

    private DataSource dataSource;
    

    public daoUsuario(DataSource dataSource){
        this.dataSource = dataSource;
    }
            

    public ArrayList<Usuarios> readAll(){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM USUARIOS";
            

            ps = dataSource.getConnection().prepareStatement(SQL);

     
            ResultSet rs = ps.executeQuery();
            
      
            ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
            

            while(rs.next()){

                Usuarios u = new Usuarios();

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setCpf(rs.getString("cpf"));
                u.setRg(rs.getString("rg"));
                u.setData_nascimento(rs.getString("data_nascimento"));


                lista.add(u);
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
    
    public ArrayList<Usuarios> ValidaUser(String email, String senha){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM USUARIOS WHERE (email=?) and (senha=?)";
            
            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,email);
            ps.setString(2,senha);
     
            ResultSet rs = ps.executeQuery();
      
            ArrayList<Usuarios> lista = new ArrayList<Usuarios>();

            while(rs.next()){

                Usuarios u = new Usuarios();

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setCpf(rs.getString("cpf"));
                u.setRg(rs.getString("rg"));
                u.setData_nascimento(rs.getString("data_nascimento"));
        
                lista.add(u);
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
    
  
    
    public void create(Usuarios u){
         
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO USUARIOS (nome, email, senha, cpf, rg, data_nascimento) VALUES (?,?,?,?,?,?)";
            

            ps = con.prepareStatement(SQL);
            ps.setString(1,u.getNome());
            ps.setString(2,u.getEmail());
            ps.setString(3,u.getSenha());
            ps.setString(4,u.getCpf());
            ps.setString(5,u.getRg());
            ps.setString(6,u.getData_nascimento());


            ps.executeUpdate();
            ps.close();
            
        }
        catch (SQLException ex){

            JOptionPane.showMessageDialog(null,"Erro ao salvar!\n"+ex);
        }
        finally{
            //ps.close();
            dataSource.closeDataSource();
        }
    }
    
        public void alterar(Usuarios u){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{

            String SQL = "UPDATE USUARIOS SET nome=?, email=?, senha=?, cpf=?, rg=?, data_nascimento=? WHERE idUsuario=? ";

            ps = con.prepareStatement(SQL);
            ps.setString(1,u.getNome());
            ps.setString(2,u.getEmail());
            ps.setString(3,u.getSenha());
            ps.setString(4,u.getCpf());
            ps.setString(5,u.getRg());
            ps.setString(6,u.getData_nascimento());
            ps.setInt(7,u.getIdUsuario());


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
        // excluir registro
        public void remover(int codigo){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "DELETE FROM USUARIOS WHERE (idUsuario = ?)";
            

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



        

