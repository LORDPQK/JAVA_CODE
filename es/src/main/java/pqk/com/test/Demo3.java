package pqk.com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import pqk.com.entity.Person;
import pqk.com.utils.ESClient;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo3 {

    ObjectMapper mapper = new ObjectMapper();
    RestHighLevelClient client = ESClient.getClient();
    //定义索引名
    String index = "sms-logs-index";
    //定义索引的Type
    String type = "sms-logs-type";

    //添加文档
    @Test
    public void createDoc() throws IOException {
        //1.准备一个JSON
        Person person = new Person(1,"张三",23,new Date());
        String JSON = mapper.writeValueAsString(person);

        //2.准备一个request对象（手动指定id）
        IndexRequest request = new IndexRequest(index,type,person.getId()+"");
        request.source(JSON, XContentType.JSON);

        //3，通过client对象执行添加
        IndexResponse resp = client.index(request, RequestOptions.DEFAULT);


        //4.输出
        System.out.println(resp.getResult().toString());
    }



    //修改document
    @Test
    public void updateDoc() throws IOException{
        //1.创建一个Map,指定需要修改的内容
        Map<String,Object> doc = new HashMap<>();
        doc.put("name","钟离");
        String docID = "1";

        //2.创建rquest对象，封装数据
        UpdateRequest request = new UpdateRequest(index,type,docID);
        request.doc(doc);

        //3.通过client对象执行
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);

        //4、输出
        System.out.println(update.getResult().toString());
    }

}
