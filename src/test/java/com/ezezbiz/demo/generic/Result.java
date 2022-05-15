package com.ezezbiz.demo.generic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Result {

    List<ResultBean> dailyList1 = new ArrayList<>();

    @BeforeAll()
    public void initData(){

        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "강원", new BigDecimal(68647480), 117));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "강원", new BigDecimal(404438190), 83));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "경기", new BigDecimal(157803300), 314));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "경기", new BigDecimal(101540580), 23));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "경남", new BigDecimal(74431790), 209));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "경남", new BigDecimal(501789824), 108));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "경북", new BigDecimal(136699120), 282));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "경북", new BigDecimal(212795810), 43));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "광주", new BigDecimal(30673130), 55));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "광주", new BigDecimal(113496290), 25));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "대구", new BigDecimal(74317300), 169));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "대구", new BigDecimal(60248630), 11));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "대전", new BigDecimal(31134790), 88));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "대전", new BigDecimal(91995475), 21));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "부산", new BigDecimal(95297210), 251));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "부산", new BigDecimal(655246112), 190));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "서울", new BigDecimal(178898973), 466));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "서울", new BigDecimal(337507109), 60));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "세종", new BigDecimal(9130390), 50));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "세종", new BigDecimal(38445190), 8));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "울산", new BigDecimal(71598080), 111));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "울산", new BigDecimal(27984800), 4));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "인천", new BigDecimal(100644820), 180));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "인천", new BigDecimal(25629310), 6));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "전남", new BigDecimal(215645330), 284));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "전남", new BigDecimal(446327460), 126));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "전북", new BigDecimal(46665570), 100));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "전북", new BigDecimal(72776820), 28));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "제주", new BigDecimal(12884060), 23));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "제주", new BigDecimal(28609240), 6));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "충남", new BigDecimal(171159790), 159));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "충남", new BigDecimal(66143220), 13));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-INFO", "충북", new BigDecimal(73003560), 161));
        dailyList1.add(new ResultBean("20220428", "ESTIMATE-REQ", "충북", new BigDecimal(230744910), 48));
    }

    @Test
    public void resultTest(){
        dailyList1.forEach(re-> System.out.println(re.toString()));
    }

}

class ResultBean {
    public String orderDate;
    public String estimateType;
    public String city;
    public BigDecimal amount;
    public int contracts;

    public ResultBean(String orderDate, String estimateType, String city, BigDecimal amount, int contracts) {
        this.orderDate = orderDate;
        this.estimateType = estimateType;
        this.city = city;
        this.amount = amount;
        this.contracts = contracts;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getEstimateType() {
        return estimateType;
    }

    public void setEstimateType(String estimateType) {
        this.estimateType = estimateType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getContracts() {
        return contracts;
    }

    public void setContracts(int contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Result{" +
                "orderDate='" + orderDate + '\'' +
                ", estimateType='" + estimateType + '\'' +
                ", city='" + city + '\'' +
                ", amount=" + amount +
                ", contracts=" + contracts +
                '}';
    }
}


