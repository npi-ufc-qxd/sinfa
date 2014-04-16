/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.ActionDescriptor;
import entities.annotations.Editor;
import entities.annotations.Param;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import relatorio.ConnectionFactory;

/**
 *
 * @author NPI-UFC - Dalmo e Luis Lima
 */
@Entity
@Views({
    @View(name = "Saida",
            title = "Saida",
            filters = "[dataSaida, Ctrl.DAO.filter()]",
            members = "[Dados da saida["
            + "lote;"
            + "dataSaida;"
            + "quantidadeSaida:2;"
            + "tipoSetor,"
            + "setor;"
            + "tipoPac,"
            + "paciente]"
            //+ "addSaida();"
            //+ "removerSaida();"
            + "]",
            params = {
        @Param(name = "tipoPac", value = "#{dataItem.tipoPac}"),
        @Param(name = "tipoSetor", value = "#{dataItem.tipoSetor}")},
            roles = "Administrador,Famaceutico,Atendente",
            template = "@CRUD+@PAGER",
            rows = 5)
})
public class SaidaProduto implements Serializable {

    @Id
    @PropertyDescriptor(hidden = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLote;
    
    @ManyToOne
    private Entrada_Produto lote;
    
    @NotNull(message = "Informe a Data da Saida")
    @PropertyDescriptor(displayName = "Data da Saida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSaida;
    
    @NotNull(message = "Informe a Quantidade da Saida")
    @PropertyDescriptor(displayName = "Quantidade da Saida")
    private Long quantidadeSaida;
    
    @Editor(inputComponentProperties =
            @Param(name = "reRender", value = "setor"))
    @ManyToOne
    private TipoSaida tipoSetor;
    
    @Editor(inputComponentProperties =
            @Param(name = "reRender", value = "paciente"))
    @PropertyDescriptor(displayName = "Tipo do Paciente")
    @ManyToOne
    private TipoSaida tipoPac;
    
    @Editor(namedQuery = "From Cartao_de_Cadastro where tipo = :tipoPac")
    @ManyToOne
    private Cartao_de_Cadastro paciente;
    
    @Editor(namedQuery = "From SSaida where tipo = :tipoSetor")
    @ManyToOne
    private SSaida setor;

    //<editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public SSaida getSetor() {
        return setor;
    }

    public void setSetor(SSaida setor) {
        this.setor = setor;
    }

    public TipoSaida getTipoPac() {
        return tipoPac;
    }

    public void setTipoPac(TipoSaida tipoPac) {
        this.tipoPac = tipoPac;
    }

    public TipoSaida getTipoSetor() {
        return tipoSetor;
    }

    public void setTipoSetor(TipoSaida tipoSetor) {
        this.tipoSetor = tipoSetor;
    }

    public Cartao_de_Cadastro getPaciente() {
        return paciente;
    }

    public void setPaciente(Cartao_de_Cadastro paciente) {
        this.paciente = paciente;
    }
    //</editor-fold>

    @ActionDescriptor(hidden=false)
    public String addSaida() {
        if (quantidadeSaida == null || quantidadeSaida == 0) {
            return "Informe a Quantidadede Saida!!";
        }
        if ((lote.getQuantidadeTotalEntrada() - lote.getQuantidadeTotalSaida()) >= quantidadeSaida) {
            Long q = lote.getQuantidadeTotalSaida() + quantidadeSaida;
            String consulta = "UPDATE entradas\n"
                    + "SET quantidadetotalsaida=" + q + "\n"
                    + "WHERE identradas=" + lote.getIdEntradas();

            Connection con = null;
            PreparedStatement pst = null;

            try {
                con = ConnectionFactory.getConnection();
                pst = con.prepareStatement(consulta);
                pst.executeUpdate();
                
                
                
                return "Atualizado estoque saida de produto";
            } catch (Exception e) {
                return "Erro ao atualizar estoque saida de produto";
            } finally {
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

            //atualiza o novo valor do produto no estoque
            //Long quantidade = lote.getQuantidadeTotalSaida();
            //quantidade = quantidade - quantidadeSaida;
            //String s = String.valueOf(quantidade);
            //lote.setQuantidadeTotalSaida(quantidade);

            //String d = "Quantidade Modificada. Quantidade Restante = ";
            //System.out.println(d + s);
            //Long a = lote.getQuantidadeTotalSaida();
            //String b = String.valueOf(a);
            //System.out.println(d + s + b);
            //return d + s;


            //Atualizar 
        } else {
            String s = "Quantidade não Disponivel";
            return s;
        }
    }
    /*    public String addSaida(){
     if((lote.getQuantidadeTotalSaida())>= quantidadeSaida){
     //atualiza o novo valor do produto no estoque
     Long quantidade = lote.getQuantidadeTotalSaida();
     quantidade = quantidade - quantidadeSaida;
     lote.setQuantidadeTotalSaida(quantidade);
     String sms = "Quantidade Modificada";
     return sms;
     }
     else{
     String sms = "Quantidade de Saida Invalido";
     return sms;
     }
     }
     */

    public void removerSaida() {
        Long quantidade = lote.getQuantidadeTotalSaida();
        quantidade = quantidade + quantidadeSaida;
        lote.setQuantidadeTotalSaida(quantidade);
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public Entrada_Produto getLote() {
        return lote;
    }

    public void setLote(Entrada_Produto lote) {
        this.lote = lote;
    }

    public Long getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Long quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaidaProduto other = (SaidaProduto) obj;
        if (this.idLote != other.idLote && (this.idLote == null || !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idLote != null ? this.idLote.hashCode() : 0);
        return hash;
    }
}
