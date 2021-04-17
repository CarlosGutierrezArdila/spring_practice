package com.example.quality_challenge.Exceptions;
import static java.util.Map.entry;

import java.util.Map;

public final class ExceptionFactory {
     public static final Map<String, ApiException> exceptions =
            Map.ofEntries(
                    entry("F001", new ApiException("F001","There is no flight matching the provided filters.",200)),
                    entry("F002", new ApiException("F002","Invalid date, format must be dd/mm/aaaa.",400)),
                    entry("F002", new ApiException("F002","Invalid date, end date must be after start date.",400)),
                    entry("F003", new ApiException("F003","The specified origin doesn't exist.",400)),
                    entry("F004", new ApiException("F004","The specified destination doesn't exist.",400)),
                    entry("F005", new ApiException("F005","The filter for flights must contain \"dateFrom\",\"dateTo\",\"origin\"and \"destination\" parameters.",400))
            );

}
