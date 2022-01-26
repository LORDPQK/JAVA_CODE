package pqk.com.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsLogs {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendDaye;
    private String longCode;
    private String mobile;
    private String corpName;
    private String smsContent;
    private Integer state;
    private Integer operatorId;
    private String province;
    private String ipAddr;
    private Integer replyTotal;
    private Integer fee;
}
