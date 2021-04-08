package com.gutierrez_carlos.store.utils;

import com.gutierrez_carlos.store.exceptions.FilterErrorException;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ValidateUtils {

    /**
     * Checks for known exceptions and restrictions in the input of articles endpoint
     * @param query map of query params
     */
    public static void articleQueryValidator(Map<String, String> query) {
        if (query.containsKey("order"))
            if (!Arrays.asList("0", "1", "2", "3").contains(query.get("order")))
                throw new FilterErrorException("Error en filtros parametro de ordenamiento invalido");
        if (query.keySet().stream().filter(key -> !key.equals("order")).collect(Collectors.toList()).size() > 2)
            throw new FilterErrorException("No se pueden aplicar mas de 2 filtros al tiempo");
        if (query.containsKey("price"))
            if (!query.get("price").replaceAll("[^\\d]", "").matches("^[0-9]+$"))
                throw new FilterErrorException("Error en filtros, ingrese un formato de numero valido en precio");
            else
                query.replace("price", query.get("price").replaceAll("[^\\d]", ""));
        if (query.containsKey("prestige"))
            if(!query.get("prestige").matches("^[*]+$"))
                throw new FilterErrorException("El filtro de prestigio solo debe incluir el caracter *");
        for (String key:query.keySet()) {
            if (!Arrays.asList("product","category","brand","freeShipping","prestige","price","order").contains(key))
                throw new FilterErrorException("Error en filtros, parametro de filtrado invalido");
        }
    }
}
