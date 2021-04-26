package com.example.storage_practice.utils;
import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.item.Item;
import com.mercadolibre.kvsclient.kvsapi.KvsapiConfiguration;
import com.mercadolibre.kvsclient.kvsapi.KvsapiLowLevelClient;

public class KVSUtils {
    import java.util.Map;

    private final ContainerKvsLowLevelClient kvs;
    String containerName = System.getenv("KEY_VALUE_STORE_MY_CONTAINER_CONTAINER_NAME");

    KvsapiConfiguration config = KvsapiConfiguration.builder()
            .withSocketTimeout(150) //all of this are default values
            .withMaxWait(100)
            .withConnectionTimeout(100)
            .withMaxConnections(30)
            .withMaxConnectionsPerRoute(30)
            .withMaxRetries(1)
            .withRetryDelay(30)
            .build();

    kvs = new ContainerKvsLowLevelClient(new KvsapiLowLevelClient(config), containerName);

    //This is how you get data from your kvsclient
    Item item = kvs.get("my_key");
      return item != null ? (Map<String,Object>) item.getValue() : null;

    //This is how you set data in your kvsclient
    Item item = new Item();
      item.setKey("key");
      item.setValue(value);
      item.setTtl(10);    //TTL in seconds, optional

      kvs.save(item);

    //This is how you delete data from your kvsclient
      kvs.delete("my_key");
}
