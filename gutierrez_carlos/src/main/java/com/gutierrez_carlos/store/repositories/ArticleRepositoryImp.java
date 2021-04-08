package com.gutierrez_carlos.store.repositories;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.DataLoadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ArticleRepositoryImp implements ArticleRepository{

    private static final String COMMA_DELIMITER = ",";
    private List<ArticleDTO> articleDB = new ArrayList<>();
    private AtomicLong productId = new AtomicLong();

    /**
     * Article DB is currently read Once
     * @throws DataLoadException
     */
    public ArticleRepositoryImp() throws DataLoadException {
        try {
            this.loadDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataLoadException("Error al cargar datos de articulos");
        }
    }

    /**
     * Load Articles list from source (currently csv file)
     * @throws IOException
     */
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
            articleDB.add(new ArticleDTO((int) productId.addAndGet(1),records.get(i)));
        }
    }

    /**
     * returns complete list or articles from db
     * @return
     */
    public List<ArticleDTO> listArticles(){
        return this.articleDB;
    }

    /**
     * filter articles db and gets single article
     * @param id
     * @return
     */
    @Override
    public ArticleDTO getArticleById(Integer id) {
        List<ArticleDTO> match = this.articleDB.stream()
                .filter(articleDTO -> articleDTO.getProductId().equals(id))
                .collect(Collectors.toList());
        if(match.size()==0)
            throw new ArticleNotFoundException("El producto con id "+id+" no existe", HttpStatus.BAD_REQUEST);
        return match.get(0);
    }


}
