/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import farmacia.Cartao_de_Cadastro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bruno
 */
public class RelatorioCR {

     public Relatorio getRelatorios(Cartao_de_Cadastro paciente) {
         
        String consulta;
    
           consulta = "SELECT p.nome, p.endereco, p.numero, p.bairro, p.rg, p.orgemissor, "
                        + "p.telefone, p.nomedamae, p.nomedopai, p.datadenascimento, p.datadeadmissao, "
                        + "p.nomedoresponsavel, p.enderecodoresponsavel, p.numerodoresponsavel, "
                        + "p.bairrodoresponsavel, p.rgdoresponsavel, p.orgemissordoresponsavel, "
                        + "p.telefonedoresponsavel, p.datadenascimentodoresponsavel, r.dataretono "
                        + "FROM  cartao_de_cadastro as p, addretorno as r "
                        + "WHERE p.nome = ('" + paciente.getNome() + "') and p.id = r.cartaoaddretorno_id";
           
           System.err.println(consulta);
           
           Relatorio relatorio = new Relatorio();
            
           Connection conn = null;
           PreparedStatement pstm = null;
           ResultSet rset = null;

            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(consulta);
                rset = pstm.executeQuery();

                // Enquanto existir dados no banco de dados, faça
                while (rset.next()) {
                    
                    // Recupera o id do banco e atribui ele ao objeto
                    relatorio.setNomePaciente(rset.getString(1));
                    relatorio.setEnderecoPaciente(rset.getString(2));
                    relatorio.setNumeroPaciente(rset.getLong(3));
                    relatorio.setBairroPaciente(rset.getString(4));
                    relatorio.setRgPaciente(rset.getString(5));
                    relatorio.setOrgemissorPaciente(rset.getString(6));
                    relatorio.setTelefonePaciente(rset.getString(7));
                    relatorio.setNomemaePaciente(rset.getString(8));
                    relatorio.setNomepaiPaciente(rset.getString(9));
                    relatorio.setDatanascimentoPaciente(rset.getDate(10));
                    relatorio.setDataadmissaoPaciente(rset.getDate(11));
                
                    relatorio.setNomeresponsavel(rset.getString(12));
                    relatorio.setEnderecoresponsavel(rset.getString(13));
                    relatorio.setNumeroresponsavel(rset.getLong(14));
                    relatorio.setBairroresponsavel(rset.getString(15));
                    relatorio.setRgresponsavel(rset.getString(16));
                    relatorio.setOrgemissorresponsavel(rset.getString(17));
                    relatorio.setTelefoneresponsavel(rset.getString(18));
                    relatorio.setDatanascimentoresponsavel(rset.getDate(19));
                    relatorio.setDataRetorno(rset.getDate(20));
                    // Recupera o nome do banco e atribui ele ao objeto
                
                    // Adiciono o contato recuperado, a lista de contatos
                    
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
        return relatorio;

     }
}
