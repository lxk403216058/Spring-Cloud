package org.example.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

//消息事务状态记录
@Entity(name = "shop_txlog")
@Data
@Accessors(chain = true)
public class TxLog implements Serializable {
    @Id
    private String txId;
    private Date date;
}
