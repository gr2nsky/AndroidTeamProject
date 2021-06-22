package com.example.mogastyle.Bean;

public class PaymentBeanStack {

    public static PaymentBeanStack stack = new PaymentBeanStack();
    TempDesignerBean designerBean = null;
    TempShopBean shopBean = null;
    TempStyleBean styleBean = null;
    ResDateData resDateData = null;
    int resTime = -1;

    public PaymentBeanStack() {
    }

    public void init(){
        designerBean = null;
        shopBean = null;
        styleBean = null;
        resDateData = null;
        resTime = -1;
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

    public static PaymentBeanStack getStack() {
        return stack;
    }

    public static void setStack(PaymentBeanStack stack) {
        PaymentBeanStack.stack = stack;
    }

    public ResDateData getResDateData() {
        return resDateData;
    }

    public void setResDateData(ResDateData resDateData) {
        this.resDateData = resDateData;
    }

    public int getResTime() {
        return resTime;
    }

    public void setResTime(int resTime) {
        this.resTime = resTime;
    }
}
