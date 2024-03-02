package org.example.springbeexample.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ExchangeRate {

    @Id
    private String shortName;

    private String validFrom;

    private String name;

    private String country;

    private double move;

    private int amount;

    private double valBuy;

    private double valSell;

    private double valMid;

    private double currBuy;

    private double currSell;

    private double currMid;

    private int version;

    private double cnbMid;

    private double ecbMid;
}
