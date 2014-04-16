/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import relatorio.ConnectionFactory;
import relatorio.Relatorio;

/**
 *
 * @author Bruno
 */
public class Gerar_RRetorno {
   public List<Relatorio> getRelatorios(Date dataInicio, Date dataFim) {
    
       Format d = new SimpleDateFormat("dd-MM-yyyy");
       Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        String data_fim = d.format(dataFim);
        String data_ini = d.format(dataInicio);
        
        String consulta;
    
           consulta = "SELECT p.nome, prod.nomeproduto, r.dataretono "
                       + "FROM  cartao_de_cadastro as p, addretorno as r, AddMedicamento as m, Produto as prod "
                       + "WHERE p.id = r.cartaoaddretorno_id and addproduto_idproduto = idproduto and  r.dataretono"
                       + " BETWEEN  DATE('" + dataInicio + "') and  DATE('" + dataFim + "')";
                    
     
        List<Relatorio> relatoriobusca = new ArrayList<Relatorio>();
        Connection conn = null;
        PreparedStatement pstm = null;
        // Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(consulta);
            rset = pstm.executeQuery();

            // Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Relatorio relatorio = new Relatorio();

                // Recupera o id do banco e atribui ele ao objeto
                relatorio.setNomePaciente(rset.getString(1));
                relatorio.setNomeProduto(rset.getString(2));
                relatorio.setDataRetorno(rset.getDate(3));
                // Recupera o nome do banco e atribui ele ao objeto
                
                // Adiciono o contato recuperado, a lista de contatos
                relatoriobusca.add(relatorio);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return relatoriobusca;        
   }
}