package com.example.mogastyle.Common;

import com.example.mogastyle.Bean.Designer;
import com.example.mogastyle.Bean.Shop;
import com.example.mogastyle.Bean.Styling;

public class BeanStackForRes {

    public static BeanStackForRes beanStackForRes = null;
    Shop shop = null;
    Designer designer = null;
    Styling styling = null;

    private BeanStackForRes(){}

    public static BeanStackForRes callStack(){
        if (beanStackForRes == null){
            beanStackForRes = new BeanStackForRes();
        }
        return beanStackForRes;
    }

    public void initAll(){
        Shop shop = null;
        Designer designer = null;
        Styling styling = null;
    }
    public void initShop(){
        Shop shop = null;
    }
    public void initDesigner(){
        Shop designer = null;
    }
    public void initStyling(){
        Shop styling = null;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public Styling getStyling() {
        return styling;
    }

    public void setStyling(Styling styling) {
        this.styling = styling;
    }
}
