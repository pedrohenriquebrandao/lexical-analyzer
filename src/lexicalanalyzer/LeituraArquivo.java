/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Victor Souza e Pedro Brandão
 */
public class LeituraArquivo {
    
    private String localFile;
    private int num;
    
    /**
     * Encontra o caminho de todos os arquivos que estão no diretório
     * @return 
     */
    public ArrayList<String> leitura(){
    
        //cria uma lista de string
        ArrayList<String> code = new ArrayList<>();
        //cria o acesso para o arquivo com localização especificada
        File access = new File("test/input/");
        
        //cria uma lista com os caminhos dos arquivos que estão no diretório específicado
      
        for(File aux : access.listFiles()){
            if(aux.getName().contains("entrada")){ // Verificação do nome do arquivo
                code.add(aux.getName());
            }
            else{
               System.out.println("Nome do arquivo fora do padrão: " + aux.getName());
               System.out.println("");
            }
        }
    
        return code;
    }
    /**
     * Cria uma lista com todas as linhas de um arquivo
     * @param localFile local de um arquivo
     * @return uma lista com todas as linhas do arquivo
     * @throws FileNotFoundException 
     */
    public ArrayList<String> lerArquivo(String localFile) throws FileNotFoundException{
    
        ArrayList<String> code;
        try (Scanner scanner = new Scanner(new FileReader("test/input/" + localFile))) {
            this.localFile = localFile;
            this.num= num+1;
            code = new ArrayList<>();
            //percorre um arquivo e adiciona cada linha em uma lista
            while(scanner.hasNextLine()){
                String aux = scanner.nextLine();
                if(aux.length() != 0){
                    code.add(aux);
                }
            }
        }
        return code;
        
    }
    /**
     * Escreve em um arquivo a linha, o lexema e o tipo do token.
     * @param tokens uma lista com todos os tokens encontrados em um arquivo
     * @param erros os tipos de erros encontrados nos tokens, caso ocorreram
     * @throws IOException 
     */
    public void escreverArquivo(ArrayList<Token> tokens, ArrayList<String> erros) throws IOException{
        try (FileWriter file = new FileWriter("test/exit/" + "saida" + this.num + ".txt", false)) {
            PrintWriter gravar = new PrintWriter(file);
            
            //grava no arquivo o número da linha, lexema e o tipo
            tokens.forEach((token) -> {
                gravar.println("Linha: " + token.getLinha() + " | " + "Lexema: " + token.getLexema() + " | " + "Tipo: " + token.getTipo());
            });
            //verifica se existem erros, se não existir uma mensagem é adicionada caso contrário cada erro é escrito
            if(erros.isEmpty())
                gravar.println("\n Nao existem erros léxicos");
            else{
                erros.forEach((erro) -> {
                    gravar.println("ERRO: " + erro);
                });
            }
        }
    
    }
    /**
     * Retorna o local do arquivo.
     * @return a variável que contém o local do arquivo
     */
    public String getLocalFile() {
        return localFile;
    }
    
    
}
