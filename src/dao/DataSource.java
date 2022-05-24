package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
        

    private String hostname;
    private int    porta;
    private String database;
    private String username;
    private String password;
    
 
    private Connection connection;
    

    public DataSource(){
        try{

            hostname = "localhost";
            porta = 3306;
            database = "doacoes";
            username = "root";
            password = "123456";
            

            String url = "jdbc:mysql://"+hostname+":"+porta+"/"+database+"?useTimezone=true&serverTimezone=UTC";
            

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            

            connection = DriverManager.getConnection(url, username, password);
            
            //System.out.println("Conectou!");
        }
        catch (SQLException ex){
            System.err.println("ERRO na conexão "+ex.getMessage());
        }
        catch (Exception ex){
            System.err.println("ERRO geral "+ex.getMessage());
        }
    }
    

    public Connection getConnection(){
        return this.connection;
    }
    

    public void closeDataSource(){
        try{
            connection.close();
            //System.out.println("Conexão fechada!");
        }
        catch (Exception ex){
            System.err.println("ERRO ao desconectar "+ex.getMessage());
        }
    }    
}