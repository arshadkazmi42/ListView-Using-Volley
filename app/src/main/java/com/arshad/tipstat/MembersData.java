package com.arshad.tipstat;

public class MembersData {

    private int id;
    private String dob;
    private String status;
    private int ethnicity;
    private int weight;
    private int height;
    private int isVeg;
    private int drink;
    private String image;

    public MembersData() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEthinicity(int e) {
        this.ethnicity = e;
    }


    public void setWeight(int w) {
        this.weight = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void setVeg(int veg) {
        this.isVeg = veg;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public void setImage(String img) {
        this.image = img;
    }

    //Getter Methods
    public int getId() {
        return this.id;
    }

    public String getDob(){
        return this.dob;
    }

    public String getStatus() {
        return this.status;
    }

    public int getEthinicity() {
        return this.ethnicity;
    }

    public String getEthinicityString() {
        switch (this.ethnicity) {
            case 0: return "Asian";
            case 1: return "Indian";
            case 2: return "African Americans";
            case 3: return "Asian Americans";
            case 4: return "Europian";
            case 5: return "British";
            case 6: return "Jewish";
            case 7: return "Latino";
            case 8: return "Native American";
            case 9: return "Arabic";
            default: return "NA";
        }
    }

    public String getWeight() {
        return (this.weight/1000)+"";
    }

    public String getHeight() {
        return (this.height)+"";
    }

    public String isVeg() {
        if(this.isVeg == 1) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public String isDrink() {
        if(this.drink == 1) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public String getImage() {
        return this.image;
    }

}
