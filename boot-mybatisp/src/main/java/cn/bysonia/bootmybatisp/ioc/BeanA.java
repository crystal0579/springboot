package cn.bysonia.bootmybatisp.ioc;

//这里没有 Component的注解
public class BeanA {

    public void sayName(){
        System.out.println("this is from bean A:" + this);
    }
}
