package com.gutierrez_carlos.store.repositories;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.DataLoadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository{

    private static final String COMMA_DELIMITER = ",";
    private List<ArticleDTO> articleDB = new ArrayList<>();


    public ArticleRepositoryImp() throws DataLoadException {
        try {
            this.loadDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataLoadException("Error al cargar datos de articulos");
        }
    }

    public  void loadDatabase() throws IOException {
        List<List<String>> records = new ArrayList<>();
        InputStream resource = new ClassPathResource(
                "dbProductos.csv").getInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < records.size(); i++) {
            articleDB.add(new ArticleDTO(records.get(i)));
        }
    }


    public List<ArticleDTO> listArticles(){
        return this.articleDB;
    }






}
