package controller;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class ProcController {

    public void propriedades() {
        Properties prop = System.getProperties();
        Set<Object> lista = prop.keySet();
        for (Object key : lista) {
            System.out.print(key);
            System.out.print(" : ");
            String atributo = System.getProperty(key.toString());
            System.out.println(atributo);
        }
    }

    public String so() {
        String so = System.getProperty("os.name");
        return so;
    }

    public void lerProcesso(String caminho) {

        try {
            Process proc = Runtime.getRuntime().exec(caminho);
            InputStream fluxo = proc.getInputStream();
            InputStreamReader leFluxo = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leFluxo);
            String linha = buffer.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = buffer.readLine();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void criaProcesso(String caminho) {
        try {
            Runtime.getRuntime().exec(caminho);
        } catch (IOException e) {
            // Elevando a administrador.
            String erro = e.getMessage();
            if (erro.contains("740")) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("cmd /e ");
                buffer.append(caminho);
                try {
                    Runtime.getRuntime().exec(buffer.toString());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }
    }

    public void mataProcesso(String processo) {
        String cmdNome = "taskkill /im ";
        String cmdPid = "taskkill /pid ";
        int pid = 0;
        StringBuffer buffer = new StringBuffer();
        try {
            pid = Integer.parseInt(processo);
            buffer.append(cmdPid);
            buffer.append(pid);
        } catch (NumberFormatException erro) {
            buffer.append(cmdNome);
            buffer.append(processo);
        }
        criaProcesso(buffer.toString());
    }
}
