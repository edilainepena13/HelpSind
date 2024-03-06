package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Apartment;
import com.ufop.HelpSind.domain.Expense;
import com.ufop.HelpSind.domain.Statement;
import com.ufop.HelpSind.enums.ApportionmentType;
import com.ufop.HelpSind.enums.Gender;
import com.ufop.HelpSind.enums.State;
import com.ufop.HelpSind.service.StatementService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("trustee/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"condominium", "condos"};
    }

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @ModelAttribute("apartments")
    public List<Apartment> aparments() {
        return null;
    }

    @ModelAttribute("state")
    public State[] state() {
        return State.values();
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getPeople(@RequestParam("pagina") Optional<Integer> pagina,
                                  @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("statement",
                statementService.listPage(PageRequest.of(pagina.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "statementList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getStatementCadastro(@ModelAttribute("statement") Statement statement, ModelMap model) {
        model.addAttribute("content", "statementRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/{idStatement}/cadastro")
    public ModelAndView getStatementEditar(@PathVariable("idStatement") Long idStatement, ModelMap model) {
        Statement statement = statementService.read(idStatement);

        model.addAttribute("statement", statement);
        model.addAttribute("content", "statementRegister");
        return new ModelAndView("layouts/trustee", model);
    }


    @PostMapping(value = "/cadastro")
    public ModelAndView postStatement(@Valid @ModelAttribute("statement") Statement statement,
                                   BindingResult validation, ModelMap model) {
        statementService.validate(statement, validation);
        if (validation.hasErrors()) {
            statement.setIdStatement(null);
            model.addAttribute("content", "statementRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        statementService.save(statement);
        return new ModelAndView("redirect:/trustee/statement");
    }

    @PutMapping(value = "/cadastro")
    public ModelAndView putStatement(@Valid @ModelAttribute("pessoa") Statement statement, BindingResult validation, ModelMap model) {
        statementService.validate(statement, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "statementRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        statementService.update(statement);
        return new ModelAndView("redirect:/trustee/statement");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deleteStatementCadastro(@RequestParam("idObj") Long idObj) {
        statementService.delete(statementService.read(idObj));
        return new ModelAndView("redirect:/trustee/statement");
    }

//    @PostMapping(value = "/cadastro")
//    public String handleCSVUpload(@RequestParam("file") MultipartFile file) {
//        if (!file.isEmpty()) {
//            try {
//                // Lê o arquivo CSV
//                CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
//                String[] linha;
//                while ((linha = csvReader.readNext()) != null) {
//                    // Processa cada linha do arquivo CSV conforme necessário
//                    for (String coluna : linha) {
//                        System.out.print(coluna + " | ");
//                    }
//                    System.out.println();
//                }
//                csvReader.close();
//
//                // Aqui você pode fazer o que precisar com os dados lidos do CSV
//
//                // Exemplo de escrita em um arquivo CSV
//                String arquivoEscrita = "caminho/do/novo/arquivo.csv";
//                List<String[]> linhasParaEscrever = new ArrayList<>();
//                linhasParaEscrever.add(new String[]{"Nome", "Idade", "Email"});
//                linhasParaEscrever.add(new String[]{"João", "30", "joao@email.com"});
//                linhasParaEscrever.add(new String[]{"Maria", "28", "maria@email.com"});
//                escreverCSV(arquivoEscrita, linhasParaEscrever);
//
//                return "upload_sucesso";
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "upload_falhou";
//            }
//        } else {
//            return "arquivo_vazio";
//        }
//    }
//
//    public void escreverCSV(String nomeArquivo, List<String[]> linhas) {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(nomeArquivo))) {
//            writer.writeAll(linhas);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    public String extrairTextoDoPDF(@RequestParam("file") MultipartFile file) {
//        if (!file.isEmpty()) {
//            try {
//                // Carrega o documento PDF
//                PDDocument document = PDDocument.load(file.getInputStream());
//                // Instancia o stripper para extrair texto
//                PDFTextStripper pdfStripper = new PDFTextStripper();
//
//                // Obtém o texto do documento
//                String text = pdfStripper.getText(document);
//
//                // Agora você tem o texto do PDF em 'text'
//                // Faça o que for necessário com o texto, como encontrar nomes e valores
//                List<String> transacoesPIX = extrairTransacoesPIX(text);
//
//                // Aqui você pode processar ou armazenar as transações PIX conforme necessário
//
//                // Feche o documento
//                document.close();
//
//                // Redirecione para alguma página de sucesso ou faça o que for apropriado
//                return "pagina_sucesso"; // Substitua pelo nome da sua página de sucesso
//            } catch (IOException e) {
//                // Lidar com a exceção adequadamente
//                e.printStackTrace();
//            }
//        }
//        // Redirecione para alguma página de erro, se necessário
//        return "pagina_erro"; // Substitua pelo nome da sua página de erro
//    }
//
//    private List<String> extrairTransacoesPIX(String texto) {
//        List<String> transacoes = new ArrayList<>();
//
//        // Expressões regulares para identificar padrões de transações PIX
//
//        //Pattern pattern = Pattern.compile("Pix\\s*-\\s*Recebido"); // Expressão regular para "Pix - Recebido"
//
//        String patternPixRecebido = "Pix-Recebido.*?(\\d{2}/\\d{2} \\d{2}:\\d{2}).*?([A-Za-z]+[\\s\\S]+?(?=\\d{2}/\\d{2}/\\d{4}))[\\s\\S]+?(\\d+,\\d{2})";
//        Pattern regexPixRecebido = Pattern.compile(patternPixRecebido);
//        Matcher matcherPixRecebido = regexPixRecebido.matcher(texto);
//
//        List<String> nomes = new ArrayList<>();
//        List<String> datas = new ArrayList<>();
//        List<String> valores = new ArrayList<>();
//
//
//
//       // Matcher matcher = pattern.matcher(texto);
//
//        //List<String> ocorrenciasPixRecebido = new ArrayList<>();
//
//        while (matcherPixRecebido.find()) {
//            String data = matcherPixRecebido.group(1);
//            String nome = matcherPixRecebido.group(2).trim();
//            String valor = matcherPixRecebido.group(3);
//
//            nomes.add(nome);
//            datas.add(data);
//            valores.add(valor);
//        }
//
//        return transacoes;
//    }



}
