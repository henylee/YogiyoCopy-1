package kr.co.tjeit.yogiyocopy.data;

import java.io.Serializable;

/**
 * Created by the on 2017-08-16.
 */

public class MenuData implements Serializable {

    public String imagePath;
    public String menuName;
    public int price;

    public MenuData() {

    }

    public MenuData(String imagePath, String menuName, int price) {
        this.imagePath = imagePath;
        this.menuName = menuName;
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
