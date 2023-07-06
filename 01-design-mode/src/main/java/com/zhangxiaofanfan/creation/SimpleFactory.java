package com.zhangxiaofanfan.creation;

/**
 * 简单工厂设计模式
 * 
 * @date 2023-07-06 08:35:40
 * @author zhangxiaofanfan
 */
public class SimpleFactory {
    public static void main(String[] args) {
        // 模拟用户想要兰州烂面, 并加面
        String name = "noodle";
        Food noodle = FoodFactory.makeFood(name);
        ((LanZhouNoodle)noodle).addNoodle(true);
        // 模拟用户想要黄焖鸡米饭, 不加鸡肉
        name = "chicken";
        Food chicken = FoodFactory.makeFood(name);
        ((HuangMenChicken)chicken).addChicken(false);
    }
}

/**
 * 简单工厂的设计模式
 * 
 * @date 2023-07-06 08:36:22
 * @author zhangxiaofanfan
 */
class FoodFactory {
    /**
     * 简单工厂类生产食物的静态方法, 此方法通过食物名称, 生产对应的食物
     * 
     * @param name  食物名称
     * @return      生产的食物对象
     */
    public static Food makeFood(String name) {
        if ("noodle".equals(name)) {
            return new LanZhouNoodle();
        } else if ("chicken".equals(name)) {
            return new HuangMenChicken();
        }
        return new DefaultFood();
    }
}

/**
 * 定义食物的接口
 * 
 * @date 2023-07-06 08:37:28
 * @author zhangxiaofanfan
 */
interface Food {
    // 省略 Food 公用方法, 声明一个接口, 作为工厂类能生成的类型使用
}

/**
 * 食品的一种, 兰州拉面, FoodFactory 可以生产
 * 
 * @date 2023-07-06 08:42:10
 * @author zhangxiaofanfan
 */
class LanZhouNoodle implements Food {
    /**
     * 兰州拉面实现食物之余的个人方法, 加面的方法
     * 
     * @param addNoodleFlag     加面标识位
     */
    public void addNoodle(Boolean addNoodleFlag) {
        if (addNoodleFlag) {
            System.out.println("一份兰州拉面, 加面!");
        } else {
            System.out.println("一份兰州拉面, 不加面!");
        }
    }
}

/**
 * 食品的一种, 黄焖鸡米饭, FoodFactory 可以生产
 * 
 * @date 2023-07-06 09:05:07
 * @author zhangxiaofanfan
 */
class HuangMenChicken implements Food {

    /**
     * 黄焖鸡米饭实现食物之余的个人方法, 加鸡肉的方法
     * 
     * @param addChickenFlag    是否加鸡肉标识位
     */
    public void addChicken(Boolean addChickenFlag) {
        if (addChickenFlag) {
            System.out.println("一份黄焖鸡, 加鸡肉!");
        } else {
            System.out.println("一份黄焖鸡, 不加鸡肉!");
        }
    }
}

/**
 * 食品的默认实现方式, 为无法找到用户希望的事物时, 兜底返回
 * 
 * @date 2023-07-06 09:11:05
 * @author zhangxiaofanfan
 */
class DefaultFood implements Food {

}
