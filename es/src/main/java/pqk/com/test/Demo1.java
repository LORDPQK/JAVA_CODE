package pqk.com.test;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;
import pqk.com.utils.ESClient;

import java.io.IOException;


//对ES索引的增删


public class Demo1 {

    RestHighLevelClient client = ESClient.getClient();
    //定义索引名
    String index = "sms";
    //定义索引的Type
    String type = "type1";

    @Test
    public void createIndex() throws Exception{
        //1.准备关于索引的settings 指定分片数为3，备份为1
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards",3)
                .put("number_of_replicas",1);


        //2.准备关于索引的结构mappings
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")

                .startObject("createDate")
                .field("type","date")
                .field("format","yyyy-MM-dd")
                .endObject()

                .startObject("sendDate")
                .field("type","date")
                .field("format","yyyy-MM-dd")
                .endObject()

                .startObject("longCode")
                .field("type","text")
                .endObject()

                .startObject("moblie")
                .field("type","text")
                .endObject()

                .startObject("corpName")
                .field("type","text")
                .endObject()

                .startObject("smsContent")
                .field("type","text")
                .endObject()

                .startObject("state")
                .field("type","integer")
                .endObject()

                .startObject("operatorld")
                .field("type","integer")
                .endObject()

                .startObject("province")
                .field("type","text")
                .endObject()

                .startObject("ipAddr")
                .field("type","text")
                .endObject()

                .startObject("replyTotal")
                .field("type","integer")
                .endObject()

                .startObject("fee")
                .field("type","integer")
                .endObject()


                    .endObject()
                .endObject();



        //3、将settings和mappings封装到一个对象中
        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type,mappings);

        //4.通过client对象取连接ES并执行创建索引
        CreateIndexResponse resp = client.indices().create(request, RequestOptions.DEFAULT);

        //5.输出
        System.out.println("resp"+resp.toString());
    }

    @Test
    public void exists() throws IOException {
        //1.准备request对象
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);

        //2.通过request去操作
        boolean exists = client.indices().exists(request,RequestOptions.DEFAULT);

        //3.输出
        System.out.println(exists);
    }

    @Test
    public void delete() throws IOException {
        //1.准备request对象
        DeleteIndexRequest request = new DeleteIndexRequest();
        request.indices(index);

        //2.通过client对象去操作
        AcknowledgedResponse delete = client.indices().delete(request,RequestOptions.DEFAULT);

        //3.输出
        System.out.println(delete.isAcknowledged());
    }

}
