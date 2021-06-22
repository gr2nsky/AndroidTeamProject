package com.example.mogastyle.Bean;

public class PaymentBeanStack {

    public static PaymentBeanStack stack = new PaymentBeanStack();
    TempDesignerBean designerBean = null;
    TempShopBean shopBean = null;
    TempStyleBean styleBean = null;

    public PaymentBeanStack() {
    }

    public void init(){
        designerBean = null;
        shopBean = null;
        styleBean = null;
    }

    public TempDesignerBean getDesignerBean() {
        return designerBean;
    }

    public void setDesignerBean(TempDesignerBean designerBean) {
        this.designerBean = designerBean;
    }

    public TempShopBean getShopBean() {
        return shopBean;
    }

    public void setShopBean(TempShopBean shopBean) {
        this.shopBean = shopBean;
    }

    public TempStyleBean getStyleBean() {
        return styleBean;
    }

    public void setStyleBean(TempStyleBean styleBean) {
        this.styleBean = styleBean;
    }
}
