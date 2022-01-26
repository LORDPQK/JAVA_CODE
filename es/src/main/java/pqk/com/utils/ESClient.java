package pqk.com.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    public static RestHighLevelClient getClient(){

        //创建HttpHost对象        RestClientBuilder构造方法需要一个这个对象
        HttpHost httpHost =new HttpHost("192.168.2.27",9200);

        //创建RestClientBuilder  RestHighLevelClient对象构造方法需要一个这个对象
        RestClientBuilder clientBuilder = RestClient.builder(httpHost);

        //创建出RestHighLevelClient  之后操作ES都是通过这个对象
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);

        //返回
        return client;
    }
}
