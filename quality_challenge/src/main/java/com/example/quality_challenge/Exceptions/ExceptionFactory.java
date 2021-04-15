package com.example.quality_challenge.Exceptions;
import static java.util.Map.entry;

import java.util.Map;

public class ExceptionFactory {
     private static final Map<String, ApiException> exceptions =
            Map.ofEntries(
                    entry("E001", new ApiException("E001","There is no flight matching the provided filters",400))
            );

     public static ApiException getException(String key){
         return exceptions.get(key);
     }
}
