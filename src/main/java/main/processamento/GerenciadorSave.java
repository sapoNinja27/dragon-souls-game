package main.processamento;

import com.google.gson.Gson;
import main.DadosGame;
import main.SaveGameDto;
import main.utils.EncriptacaoService;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class GerenciadorSave {

    public SaveGameDto carregarJogo(File file, int encode) {
        SaveGameDto saveGameDto = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String primeiraLinha = br.readLine();
            StringBuilder key = new StringBuilder();
            String[] keyArray = primeiraLinha.split("\\|");
            for (String s : keyArray) {
                char c = (char) (Integer.parseInt(s));
                key.append((char) (c - encode));
            }
            String decripted = key.toString();
            Gson gson = new Gson();
            saveGameDto = (gson.fromJson(decripted, SaveGameDto.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveGameDto;
    }

    public void saveGame(DadosGame dadosGame, int encode) {
        SaveGameDto save = dadosGame.createSave();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(save);
            StringBuilder key = new StringBuilder();
            char[] keyArray = json.toCharArray();
            for (char c : keyArray) {
                key.append(c + encode).append("|");
            }
            String encoded = key.toString();
            BufferedWriter write = new BufferedWriter(new FileWriter("saves/Save slot (9).txt"));
            write.write(encoded);
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
