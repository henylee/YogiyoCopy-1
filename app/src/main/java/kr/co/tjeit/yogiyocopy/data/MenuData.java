package kr.co.tjeit.yogiyocopy.data;

/**
 * Created by the on 2017-08-16.
 */

public class MenuData {

    public String imagePath;
    public String menuName;
    public String menuCost;

    public MenuData() {

    }

    public MenuData(String imagePath, String menuName, String menuCost) {
        this.imagePath = imagePath;
        this.menuName = menuName;
        this.menuCost = menuCost;
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

    public String getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(String menuCost) {
        this.menuCost = menuCost;
    }
}
