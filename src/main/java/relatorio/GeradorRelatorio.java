package relatorio;

import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import config.Configuracao;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GeradorRelatorio {

    public void gerarRE(ArrayList<Relatorio> relatorio) {
        Document doc = null;
        OutputStream os = null;
        try {
            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/relatorioEstoque.pdf");

            doc = new Document();
            PdfWriter.getInstance(doc, os);
            doc.open();

            PdfPTable cabecalho = new PdfPTable(2);
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);

            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */

            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90);/*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");

            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);

            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Relatório de Estoque");
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoRE(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void gerarVI(ArrayList<Relatorio> relatorio, Date dataInicio, Date dataFim) {
        Document doc = null;
        OutputStream os = null;

        try {
            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String data_Inicio = d.format(dataInicio);
            String data_Fim = d.format(dataFim);
            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/vencidosIncinerados(" + data_Inicio + "-" + data_Fim + ").pdf");

            doc = new Document();
            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);
//	    Font fonteCabecalho = new Font(12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /*

            
             float[] widths = {0.15f, 0.85f};
             cabecalho.setWidthPercentage(90); /*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");

            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);

            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Relatório Medicamentos Vencidos e Incinerados - Período de: " + data_Inicio + " Até: " + data_Fim, fonteCabecalho);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoVI(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();



            System.out.println("Relatorio Gerado");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void gerarPF(ArrayList<Relatorio> relatorio, Date dataInicio, Date dataFim) {
        Document doc = null;
        OutputStream os = null;


        try {
            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String data_Inicio = d.format(dataInicio);
            String data_Fim = d.format(dataFim);

            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/produtosFornecedor(" + data_Inicio + "-" + data_Fim + ").pdf");

            doc = new Document();

            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);
//			Font fonteCabecalho = new Font(12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");


            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);


            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Produtos Por Fornecedor - Período de: " + data_Inicio + " Até: " + data_Fim, fonteCabecalho);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoPF(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void gerarMI(ArrayList<Relatorio> relatorio, Date dataInicio, Date dataFim, String nproduto) {
        Document doc = null;
        OutputStream os = null;

//		Atividade at = new AtividadeService().getAtividadeById(id); //pega a atividade pelo id
        try {
            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String data_Inicio = d.format(dataInicio);
            String data_Fim = d.format(dataFim);
            String produto = nproduto;
            
            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/medicamentosIncineracao("+ data_Inicio + "a"+ data_Fim +")"+ produto +".pdf");
//			os = new FileOutputStream("donwloads/Relatorio_Mensal.pdf");
//			doc = new Document(PageSize.A4, 72, 72, 72, 72);
            doc = new Document();

            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);
//			Font fonteCabecalho = new Font(12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");

//            Image img = Image.getInstance(getClass().getResource("/img/logo.png"));
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);

//             Período de: "+ data_Inicio + " Até: " + data_Fim

            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Medicamentos Icineração - Período de: " + data_Inicio + " Até: " + data_Fim, fonteCabecalho);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);
//			Paragraph p = new Paragraph("apresentar isso");
//			doc.add(p);
            try {
                doc.add(getCabecalhoMI(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
//			os.close();


    }

    public void gerarPV(ArrayList<Relatorio> relatorio, Date dataInicio, Date dataFim) {
        Document doc = null;
        OutputStream os = null;


        try {
            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String data_Inicio = d.format(dataInicio);
            String data_Fim = d.format(dataFim);

            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/produtosVencidos(" + data_Inicio + "-" + data_Fim + ").pdf");

            doc = new Document();

            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);
//			Font fonteCabecalho = new Font(12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");


            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);


            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Produtos Vencidos - Período de: " + data_Inicio + " Até: " + data_Fim, fonteCabecalho);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoPV(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void gerarRelatorioRetorno(ArrayList<Relatorio> relatorio, Date dataInicio, Date dataFim) {
        Document doc = null;
        OutputStream os = null;

        try {
            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String data_Inicio = d.format(dataInicio);
            String data_Fim = d.format(dataFim);

            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/RetornosPacientes(" + data_Inicio + "-" + data_Fim + ").pdf");

            doc = new Document();

            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);
//			Font fonteCabecalho = new Font(12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /*
             * Seta a largura da tabela com relação a página.
             */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");


            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);


            Paragraph p = new Paragraph("Prefeitura Municipal de Quixadá\n" + "Farmacia Quixada\n"
                    + "Retornos - Período de: " + data_Inicio + " Até: " + data_Fim, fonteCabecalho);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoRRetorno(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void gerarCR(Relatorio relatorio) {

        Document doc = null;
        OutputStream os = null;

        try {

            os = new FileOutputStream(Configuracao.CLASSPATH + "relatorios/Cartao de cadastro paciente.pdf");

            doc = new Document();

            PdfWriter.getInstance(doc, os);
            doc.open();
            PdfPTable cabecalho = new PdfPTable(2);

            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteDesc = new Font(); /*
             * Será usada na descrição.
             */
            float[] widths = {0.15f, 0.85f};

            cabecalho.setWidthPercentage(90);
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            Image img = Image.getInstance(Configuracao.CLASSPATH + "relatorios/logo.png");

            img.setAlignment(Element.ALIGN_TOP);
            img.scalePercent(80);
            cabecalho.addCell(img);
            //doc.add(img);
            PdfPCell texto = new PdfPCell(new Phrase("Prefeitura Municipal de Quixadá\n"
                    + "Secretaria Municipal da Saúde\n"
                    + "Cartão de cadastro", fonteCabecalho));

            texto.setBorder(PdfPCell.NO_BORDER);
            texto.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalho.addCell(texto);


            doc.add(cabecalho);

            try {
                doc.add(getCabecalhoCR(relatorio, fonteDesc));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Gerando Relatorio");
            doc.close();
            os.close();

            System.out.println("Relatorio Gerado");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public PdfPTable getCabecalhoVI(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(4);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /*
         * Seta a largura da tabela com relação a página.
         */

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Medicamento: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Lote: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Quantidade: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Data Validade: ", fonteCabecalho)); //pega o local da atividade
        for (Relatorio relatorio : a) {

            desc.addCell(new Phrase("Medicamentos: " + relatorio.getMedicamento()));
            desc.addCell(new Phrase(relatorio.getNumeroLote()));
            desc.addCell(new Phrase("" + relatorio.getQuantidade()));
            desc.addCell(new Phrase("" + relatorio.getDataValidade()));
        }

        return desc;
    }

    public PdfPTable getCabecalhoRE(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(4);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /*
         * Seta a largura da tabela com relação a página.
         */

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);

        desc.addCell(new Phrase("Lote: ", fonteCabecalho));
        desc.addCell(new Phrase("Medicamento: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Quantidade: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Validade: ", fonteCabecalho));

        for (Relatorio relatorio : a) {

            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String validade = d.format(relatorio.getDataValidade());

            desc.addCell(new Phrase(relatorio.getNumeroLote()));
            desc.addCell(new Phrase(relatorio.getNomeProduto()));
            desc.addCell(new Phrase("" + relatorio.getQuantidade()));
            desc.addCell(new Phrase(validade));

        }

        return desc;
    }

    public PdfPTable getCabecalhoPF(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(7);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90);

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Fornecedor: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Produto: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Lote: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Validade: ", fonteCabecalho));
        desc.addCell(new Phrase("Unitario: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Total: ", fonteCabecalho));
        desc.addCell(new Phrase("Entrada: ", fonteCabecalho));
        for (Relatorio relatorio : a) {

            desc.addCell(new Phrase("" + relatorio.getNomeFornecedor()));
            desc.addCell(new Phrase(relatorio.getNomeProduto()));

            desc.addCell(new Phrase("" + relatorio.getNumeroLote()));
            desc.addCell(new Phrase("" + relatorio.getDataValidade()));
            desc.addCell(new Phrase("" + relatorio.getValorUnitario()));
            desc.addCell(new Phrase("" + relatorio.getValorTotal()));
            desc.addCell(new Phrase("" + relatorio.getDataEntrada()));
        }
        return desc;
    }

    public PdfPTable getCabecalhoMI(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(4);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /*
         * Seta a largura da tabela com relação a página.
         */

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        //desc.addCell(new Phrase("Fornecedor: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Produto: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Lote: ", fonteCabecalho));
        desc.addCell(new Phrase("Validade: ", fonteCabecalho)); //pega o local da atividade
        //desc.addCell(new Phrase("Unitario: ", fonteCabecalho));
        //desc.addCell(new Phrase("Valor Total: ", fonteCabecalho));
        desc.addCell(new Phrase("Entrada: ", fonteCabecalho));
        for (Relatorio relatorio : a) {

            //desc.addCell(new Phrase("Fornecedores: " + relatorio.getNomeFornecedor()));
            desc.addCell(new Phrase("" + relatorio.getNomeProduto()));
            desc.addCell(new Phrase("" + relatorio.getNumeroLote()));
            desc.addCell(new Phrase("" + relatorio.getDataValidade()));
            //desc.addCell(new Phrase("" + relatorio.getValorUnitario()));
            //desc.addCell(new Phrase("" + relatorio.getValorTotal()));
            desc.addCell(new Phrase("" + relatorio.getDataEntrada()));

        }
        return desc;
    }

    public PdfPTable getCabecalhoPV(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(4);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90); /*
         * Seta a largura da tabela com relação a página.
         */

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);

        desc.addCell(new Phrase("Lote: ", fonteCabecalho));
        desc.addCell(new Phrase("Medicamento: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Data de entrada: ", fonteCabecalho)); //pega o local da atividade
        desc.addCell(new Phrase("Validade: ", fonteCabecalho));

        for (Relatorio relatorio : a) {

            Format d = new SimpleDateFormat("dd-MM-yyyy");
            String validade = d.format(relatorio.getDataValidade());
            String entrada = d.format(relatorio.getDataEntrada());

            desc.addCell(new Phrase(relatorio.getNumeroLote()));
            desc.addCell(new Phrase(relatorio.getNomeProduto()));
            desc.addCell(new Phrase(entrada));
            desc.addCell(new Phrase(validade));

        }

        return desc;
    }

    public PdfPTable getCabecalhoRRetorno(ArrayList<Relatorio> a, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(3);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90);

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font fonteCabecalho = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
        desc.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        desc.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        desc.addCell(new Phrase("Paciente: ", fonteCabecalho));//pega o id da atividade
        desc.addCell(new Phrase("Medicamento: ", fonteCabecalho));
        desc.addCell(new Phrase("Data Retorno: ", fonteCabecalho));

        for (Relatorio relatorio : a) {

            desc.addCell(new Phrase("" + relatorio.getNomePaciente()));
            desc.addCell(new Phrase(relatorio.getNomeProduto()));
            desc.addCell(new Phrase("" + relatorio.getDataRetorno()));

        }
        return desc;
    }

    public PdfPTable getCabecalhoCR(Relatorio result, Font fonteRodaPe) {
        PdfPTable desc = new PdfPTable(1);
        float[] widths = new float[]{0.20f, 0.20f, 0.60f};
        desc.setSpacingBefore(10f);
        desc.setWidthPercentage(90);
        /*
         * Seta a largura da tabela com relação a página.
         */

        try {
            desc.setWidths(widths);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        desc.addCell("Identificação do Paciente:\n"
                + "Nome: " + result.getNomePaciente() + "       Data de Nascimento:" + result.getDatanascimentoPaciente()
                + "\nEndereço:" + result.getEnderecoPaciente() + "       N°:" + result.getNumeroPaciente() + "       Bairro:" + result.getBairroPaciente()
                + "\nNome do Pai:" + result.getNomepaiPaciente() + "       Nome da Mae:" + result.getNomemaePaciente()
                + "\nTelefone:" + result.getTelefonePaciente() + "      RG:" + result.getRgPaciente() + "   Orgão Emissor:" + result.getOrgemissorPaciente()
                + "Data de Admissão:" + result.getDataadmissaoPaciente() + "    Assinatura:_______________________________");

        desc.addCell("");
        desc.addCell("");


        desc.addCell("Responsável pelo Paciente:\n"
                + "Nome : " + result.getNomeresponsavel() + "       Data de Nascimento :" + result.getDatanascimentoresponsavel()
                + "\nEndereço:" + result.getEnderecoresponsavel() + "       N°:" + result.getNumeroresponsavel() + "       Bairro:" + result.getBairroresponsavel()
                + "\nTelefone:" + result.getTelefoneresponsavel() + "      RG:" + result.getRgresponsavel() + "   Orgão Emissor:" + result.getOrgemissorresponsavel()
                + "Assinatura:________________________________________\n");

        desc.addCell("");
        desc.addCell("");

        desc.addCell("Medimento:______________________" + result.getMedicamento() + "Dose/Dia/Mês:_________\n"
                + "Medimento:______________________" + result.getMedicamento() + "Dose/Dia/Mês:_________\n"
                + "Medimento:______________________" + result.getMedicamento() + "Dose/Dia/Mês:_________\n"
                + "Medimento:______________________" + result.getMedicamento() + "Dose/Dia/Mês:_________\n"
                + "Medimento:______________________" + result.getMedicamento() + "Dose/Dia/Mês:_________");

        desc.addCell("");
        desc.addCell("");

        desc.addCell("Data de Retorno: " + result.getDataRetorno());

        return desc;
    }
}
