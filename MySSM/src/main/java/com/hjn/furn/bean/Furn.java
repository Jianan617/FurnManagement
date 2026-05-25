package com.hjn.furn.bean;

import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Furn {
    private Integer id;
    @NotEmpty(message = "Please enter name.")
    private String name;
    
    @NotEmpty(message = "Please enter maker.")
    private String maker;
    
    @NotNull(message = "Please enter price.")
    @Range(min = 0, message = "Price cannot less than 0.")
    private BigDecimal price;
    
    @NotNull(message = "Please enter sales.")
    @Range(min = 0, message = "Sales cannot less than 0.")
    private Integer sales;
    
    @NotNull(message = "Please enter stock.")
    @Range(min = 0, message = "Stock cannot less than 0.")
    private Integer stock;
    
    private String imgPath = "assets/images/product-image/default.jpg";
    public Integer getId() {
        return id;
    }
    
    public Furn() {
    }
    
    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
//        if(imgPath != null && !imgPath.isEmpty()){
//            this.imgPath = imgPath;
//        }
        //StringUtils.hasText(imgPath)要求imgPath不能是null, "",且不是空格
        if(StringUtils.hasText(imgPath)){
            this.imgPath = imgPath;
        }
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}