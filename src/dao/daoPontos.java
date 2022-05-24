package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Pontos;
import model.Municipios;

public class daoPontos {
    
    private DataSource dataSource;

    public daoPontos(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Pontos> readAll(){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM PONTO_COLETA";

            ps = dataSource.getConnection().prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            ArrayList<Pontos> lista = new ArrayList<Pontos>();
            
            while(rs.next()){
                Pontos p = new Pontos();

                p.setIdColeta(rs.getInt("idColeta"));
                p.setDsPonto(rs.getString("dsPonto"));
                p.setCnpj(rs.getString("cnpj"));
                p.setTelefone(rs.getString("telefone"));
                p.setCep(rs.getString("cep"));
                p.setRua(rs.getString("rua"));
                p.setBairro(rs.getString("bairro"));
                p.setNumero(rs.getInt("numero"));
                p.setEmail(rs.getString("email"));
                p.setContato(rs.getString("contato"));
                p.setWhatsapp(rs.getString("whatsapp"));

                lista.add(p);
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
    
        public ArrayList<Pontos> readOne(String descricao){

        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM PONTO_COLETA WHERE (dsPonto LIKE ?)";

            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+descricao+"%");
            
            ResultSet rs = ps.executeQuery();

            ArrayList<Pontos> lista = new ArrayList<Pontos>();
            
            while(rs.next()){
                Pontos ponto = new Pontos();
                
                ponto.setIdColeta(rs.getInt("idColeta"));
                ponto.setDsPonto(rs.getString("dsPonto"));
                ponto.setCnpj(rs.getString("cnpj"));
                ponto.setTelefone(rs.getString("telefone"));
                ponto.setCep(rs.getString("cep"));
                ponto.setRua(rs.getString("rua"));
                ponto.setBairro(rs.getString("bairro"));
                ponto.setNumero(rs.getInt("numero"));
                ponto.setEmail(rs.getString("email"));
                ponto.setContato(rs.getString("contato"));
                ponto.setWhatsapp(rs.getString("whatsapp"));

                lista.add(ponto);
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

    public void create(Pontos p){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO PONTO_COLETA (dsPonto, cnpj, telefone, cep, rua, bairro, "
                    + "numero, email, whatsapp, contato ) VALUES (?,?,?,?,?,?,?,?,?,?)";

            ps = con.prepareStatement(SQL);
            ps.setString(1,p.getDsPonto());
            ps.setString(2,p.getCnpj());
            ps.setString(3,p.getTelefone());
            ps.setString(4,p.getCep());
            ps.setString(5,p.getRua());
            ps.setString(6,p.getBairro());
            ps.setInt(7,p.getNumero());
            ps.setString(8,p.getEmail());
            ps.setString(9,p.getWhatsapp());
            ps.setString(10,p.getContato());

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
    
        public void alterar(Pontos p){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "UPDATE PONTO_COLETA SET dsPonto=? , cnpj=?, telefone=?, cep=?, rua=?, bairro=?, "
                    + "numero=?, email=?, whatsapp=?, contato=? WHERE idColeta=? ";
              

            ps = con.prepareStatement(SQL);
            ps.setString(1,p.getDsPonto());
            ps.setString(2,p.getCnpj());
            ps.setString(3,p.getTelefone());
            ps.setString(4,p.getCep());
            ps.setString(5,p.getRua());
            ps.setString(6,p.getBairro());
            ps.setInt(7,p.getNumero());
            ps.setString(8,p.getEmail());
            ps.setString(9,p.getWhatsapp());
            ps.setString(10,p.getContato());
            ps.setInt(11,p.getIdColeta());

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
            String SQL = "DELETE FROM PONTO_COLETA WHERE (idColeta = ?)";
            
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
