/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pedro
 */
public class RelatorioPV {
     public List<Relatorio> getRelatorios(Date dataInicio, Date dataFim){
         
        Format d = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        String data_fim = d.format(dataFim);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        String data_ini = d.format(dataInicio);

         System.out.println(data_fim + " "+ data_ini );
        
        String consulta = "select p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada "
                    + "from entradas as e, produto as p "
                    + "where E.DATAENTRADA BETWEEN  DATE('"+data_ini+"') "
                    + "AND  DATE('"+data_fim+"')and "
                    + "e.statusproduto = 2 and PRODUTOENTRADA_IDPRODUTO = P.IDPRODUTO "
                    + "group by p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada ";        
        
         System.out.println(consulta);
        
        List<Relatorio> relatorios = new ArrayList<Relatorio>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        // Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.getConnection();

            pstm = conn.prepareStatement(consulta);

            rset = pstm.executeQuery();
            
             while (rset.next()) {

                Relatorio relatorio = new Relatorio();
                
                relatorio.setNomeProduto(rset.getString(1));
                relatorio.setNumeroLote(rset.getString(2));
                relatorio.setDataValidade(rset.getDate(3));
                relatorio.setDataEntrada(rset.getDate(4));
                
                relatorios.add(relatorio);
                                
             }
        }catch (Exception e) {

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
        
        return relatorios;
     } 
}
