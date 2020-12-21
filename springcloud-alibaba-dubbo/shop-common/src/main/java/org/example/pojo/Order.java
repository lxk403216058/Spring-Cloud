package org.example.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

//订单
@Entity(name = "shop_order")
@Data
@Accessors(chain = true)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;//订单id
    private Integer uid;//用户id
    private String username;//用户名
    private Integer pid;//商品id
    private String pname;//商品名称
    private Double pprice;//商品单价
    private Integer number;//购买数量
}
