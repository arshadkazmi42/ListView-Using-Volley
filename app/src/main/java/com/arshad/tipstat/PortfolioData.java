package com.arshad.tipstat;

public class PortfolioData {

    private int imageRes;
    private String name;
    private String url;

    public PortfolioData(int res, String n, String url) {
        this.imageRes = res;
        this.name = n;
        this.url = url;
    }

    public void setImageRes(int res) {
        this.imageRes = res;
    }

    public int getImageRes () {
        return this.imageRes;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
