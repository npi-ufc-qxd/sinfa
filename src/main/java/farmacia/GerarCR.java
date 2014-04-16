/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.View;
import entities.annotations.Views;
import java.io.File;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import relatorio.GeradorRelatorio;
import relatorio.Relatorio;
import relatorio.RelatorioCR;

/**
 *
 * @author Bruno
 */
@Entity
@Views({
    @View(name = "GerarCR", title = "Imprimir_Cartao",
    members = "[#nome; fazerBusca()]",
    roles = "Administrador,Famaceutico,Atendente",
    template = "@PAGER", namedQuery="select new farmacia.GerarCR()" )
})public class GerarCR implements Serializable{
    @Id
    private Long id;
    
    @ManyToOne
    private Cartao_de_Cadastro nome;
    
    public File fazerBusca(){
        RelatorioCR dao = new RelatorioCR();
         Relatorio results = dao.getRelatorios(nome);
         GeradorRelatorio gerador = new GeradorRelatorio();
         gerador.gerarCR(results);
         
         File file = new File("C:/Relatorios/Cartao de cadastro paciente.pdf");
         return file;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cartao_de_Cadastro getNome() {
        return nome;
    }

    public void setNome(Cartao_de_Cadastro nome) {
        this.nome = nome;
    }

    
}

