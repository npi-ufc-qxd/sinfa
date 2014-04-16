/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import farmacia.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author
 */
public class RelatorioMI {
     
    /*public static void main(String args[]){
        RelatorioMI rpf = new RelatorioMI();
        ArrayList al = (ArrayList)rpf.getRelatorios(new Date(2012, 10, 10), new Date(2014, 10, 10), null);
        Iterator it = al.iterator();
        while(it.hasNext()){
            Object o = it.next();
            if(o!= null) System.out.println("Tem dados");
            
        }
        
        System.out.println("Aqui");
    }*/

    public List<Relatorio> getRelatorios(Date dataInicio, Date dataFim, Produto produto) {

       
        Format d = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal=Calendar.getInstance();  
//        cal.setTime(data);  
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String data_fim = d.format(dataFim);                            
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
        String data_ini = d.format(dataInicio);
        String consulta;
        if(produto==null)
        consulta = "select p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada from entradas as e, produto as p "
                    + "where E.DATAENTRADA BETWEEN  DATE('" + data_ini + "') and  DATE('" + data_fim + "')and "
                    + "statusproduto = 2 and PRODUTOENTRADA_IDPRODUTO = P.IDPRODUTO";
        else {
          consulta = "select p.nomeproduto, e.loteentrada, e.datavalidade, e.dataentrada from entradas as e, produto as p "
                    + "where E.DATAENTRADA BETWEEN  DATE('" + data_ini + "') AND  DATE('" + data_fim + "')and "
                    + "statusproduto = 2 and PRODUTOENTRADA_IDPRODUTO = P.IDPRODUTO and "
                    + "p.nomeproduto='"+produto.getNomeProduto()+"'";
        }
        
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
                //relatorio.setNomeFornecedor(rset.getString("NOMEFANTASIAFORNECEDOR"));
                relatorio.setNomeProduto(rset.getString("NOMEPRODUTO"));
                relatorio.setNumeroLote(rset.getString("LOTEENTRADA"));
                relatorio.setDataValidade(rset.getDate("DATAVALIDADE"));
                //relatorio.setValorUnitario(rset.getLong("VALORUNITARIO"));
                //relatorio.setValorTotal(rset.getLong("VALORTOTAL"));
                relatorio.setDataEntrada(rset.getDate("DATAENTRADA"));

                // Recupera o nome do banco e atribui ele ao objeto
                //relatorio.setCodigo(rset.getLong("CODIGOPROCEDIMENTO"));
                
                //relatorio.setUnidadeDeSaude(rset.getString("NOMEUNIDADEDESAUDE"));

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
         
         
 //        for(Relatorio  r : relatorios){              
 //            r.setQuantidade(getQuantidade(r, relatorios));
 //        }
//         System.out.println("Gerando Relatorio");
        return removeRepedidos(relatorios);
    }
    
   
    
    private static boolean podeInserirRelatorio(Relatorio r, List<Relatorio> relatorios){
   
    for(Relatorio rela : relatorios){
        if((rela.getNumeroLote().equals(r.getNumeroLote()))){
            return false;
        }                
    }
    return true;
    }
    
    
    
    private static List<Relatorio> removeRepedidos(List<Relatorio> relatorios){
    
    List<Relatorio> relas = new ArrayList<Relatorio>();
    
    
    for(Relatorio r: relatorios){ 
        if(podeInserirRelatorio(r, relas)){
            relas.add(r);
        }                
    }        
    return relas;    
        
        
    }
 
}