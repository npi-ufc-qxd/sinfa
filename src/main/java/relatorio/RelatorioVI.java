/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import farmacia.Fornecedor;
import farmacia.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author junior
 */
public class RelatorioVI {
  public List<Relatorio> getRelatorios(Date dataInicio, Date dataFim, Produto produto, Fornecedor fornecedor) {

       
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal=Calendar.getInstance();  
 
        String data_fim = d.format(dataFim);                            
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
        String data_ini = d.format(dataInicio);
                                        
       String consulta;
        if(produto==null){
        consulta = "select p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada from entradas as e, produto as p "
                    + "where E.DATAENTRADA BETWEEN  DATE('"+data_ini+"') AND  DATE('"+data_fim+"')and "
                    + " e.statusproduto = 3 and PRODUTOENTRADA_IDPRODUTO = P.IDPRODUTO";        
        }else{     
            consulta = "select p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada from entradas as e, produto as p "
                    + "where E.DATAENTRADA BETWEEN  DATE('"+data_ini+"') AND  DATE('"+data_fim+"')and "
                    + " e.statusproduto = 3 and PRODUTOENTRADA_IDPRODUTO = P.IDPRODUTO and "
                    + "p.nomeproduto='"+produto.getNomeProduto()+"'";
        }

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

            // Enquanto existir dados no banco de dados, faça
            while (rset.next()) {

                Relatorio relatorio = new Relatorio();

                // Recupera o id do banco e atribui ele ao objeto
                relatorio.setMedicamento(rset.getString("NOMEPRODUTO"));

                // Recupera o nome do banco e atribui ele ao objeto
                relatorio.setNumeroLote(rset.getString("LOTEENTRADA"));
                
                relatorio.setQuantidade(rset.getLong("QUATIDADETOTAL"));
                
                relatorio.setDataValidade(rset.getDate("DATAVALIDADE"));

                
                // Recupera a idade do banco e atribui ele ao objeto

                // Adiciono o contato recuperado, a lista de contatos
                relatorios.add(relatorio);
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
        
         
//         System.out.println("Gerando Relatorio");
//           System.out.println(relatorios.size());
    
        return removeRepedidos(relatorios);
    }
    
   
    
    private static boolean podeInserirRelatorio(Relatorio r, List<Relatorio> relatorios){
   
    for(Relatorio rela : relatorios){
        if((rela.getNumeroLote().equals(r.getNumeroLote()))){
//            rela.setIdade(getIdadeDoPaciente(rela));
        return false;
        }                
    }
    return true;
    }
    
    
    
    private static List<Relatorio> removeRepedidos(List<Relatorio> relatorios){
    
    List<Relatorio> relas = new ArrayList<Relatorio>();
    
    
    for(Relatorio r: relatorios){ 
        if(podeInserirRelatorio(r, relas)){
//            r.setIdade(getIdadeDoPaciente(r));
        relas.add(r);
        }                
    }        
    return relas;    
        
        
    }
        
     
}