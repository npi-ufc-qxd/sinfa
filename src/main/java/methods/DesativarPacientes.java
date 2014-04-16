/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import relatorio.ConnectionFactory;

/**
 *
 * @author NPI-UFC - Luis Lima
 */
public class DesativarPacientes {
    String consulta;
    
    public String desativarPacientes(){
        
        consulta = "UPDATE cartao_de_cadastro\n" +
        "SET ativo=false\n" +
        "FROM addretorno\n" +
        "WHERE id=cartaoaddretorno_id\n" +
        "AND dataretono < CURRENT_DATE - INTERVAL '3 mons';";
        
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(consulta);
            pst.executeUpdate();
            
            
            
            return "Desativados Pacientes com mais de 3 meses inativos";
        } catch (Exception e) {
            
            return "Erro ao Desativados Pacientes com mais de 3 meses inativos";
        }
        finally {
            try {
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
