/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import entities.annotations.View;
import entities.annotations.Views;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import config.Configuracao;
import relatorio.GeradorRelatorio;
import relatorio.Relatorio;
import relatorio.RelatorioEB;;

// @author wanrly
 
@Entity
@Views({
    @View(name = "GerarEB", title = "Gerar Relatório Estoque Baixo",
     members = "[gerarRelatorioEstoqueBaixo()]",
     roles = "Administrador,Famaceutico,Atendente",
     template = "@PAGER", namedQuery="select new farmacia.GerarEB()")
})

public class GerarEB implements Serializable{
    @Id
    private Long id;

    public File gerarRelatorioEstoqueBaixo(){      
        RelatorioEB dao = new RelatorioEB();
        List<Relatorio> results = dao.getRelatorios();
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarEB((ArrayList<Relatorio>) results);
        
        File file = new File(Configuracao.CLASSPATH + "relatorios/relatorioEstoqueBaixo.pdf");
        return file;

    }
    
}

