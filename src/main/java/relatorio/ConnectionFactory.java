/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author junior
 */
public class ConnectionFactory {
    //Nome do usuário do mysql

    private static final String USERNAME = "postgres";
    //Senha do mysql
    private static final String PASSWORD = "postgres";
    //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
    // private static final String DATABASE_URL = "jdbc:derby://localhost:1527/sistemaodontologico";
    

    /**
     * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário
     * e senha fornecidos
     *
     * @param username
     * @param senha
     * @return uma conexão com o banco de dados
     * @throws Exception
     */
    public static Connection getConnection() throws SQLException {
        try {
            
            String DRIVER = "org.apache.derby.jdbc.ClientDriver";
            Class.forName(DRIVER);
            System.out.println("Conectando ao banco  farmacia...");         
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/farmacia", USERNAME, PASSWORD);      
        } catch (ClassNotFoundException e) {                            //1527
            System.out.println("erro" + e + "\n\n");
            throw new SQLException(e.getMessage());
        }

    }

    
   
}