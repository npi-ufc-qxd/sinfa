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
import relatorio.RelatorioRE;

/**
 *
 * @author pedro
 */
@Entity
@Views({
    @View(name = "GerarRE", title = "Gerar Relatório Estoque",
     members = "[gerarRelatorioEstoque()]",
     roles = "Administrador,Famaceutico,Atendente",
     template = "@PAGER", namedQuery="select new farmacia.GerarRE()")
})

public class GerarRE implements Serializable{
    @Id
    private Long id;

    public File gerarRelatorioEstoque(){      
        RelatorioRE dao = new RelatorioRE();
        List<Relatorio> results = dao.getRelatorios();
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.gerarRE((ArrayList<Relatorio>) results);
        
        File file = new File(Configuracao.CLASSPATH + "relatorios/relatorioEstoque.pdf");
        return file;

    }
    
}
