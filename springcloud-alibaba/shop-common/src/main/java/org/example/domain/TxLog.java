package org.example.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

//消息事务状态记录
@Entity(name = "shop_txlog")
@Data
@Accessors(chain = true)
public class TxLog {
    @Id
    private String txId;
    private Date date;
}
