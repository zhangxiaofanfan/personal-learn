package com.zhangxiaofanfan.creation;

/**
 * 工厂模式, 之所以需要引入工厂模式，是因为我们往往需要使用两个或两个以上的工厂;
 *
 * @date 2023-07-10 09:16:32
 * @author zhangxiaofanfan
 */
public class Factory {
    public static void main(String[] args) {
        // 使用 AmericanFoodFactory 生产食物并调用打印方法
        AmericanFoodFactory americanFoodFactory = new AmericanFoodFactory();
        americanFoodFactory.makeFood("A").userEat();
        americanFoodFactory.makeFood("B").userEat();

        // 使用 ChineseFoodFactory 生产食物并调用打印方法
        ChineseFoodFactory chineseFoodFactory = new ChineseFoodFactory();
        chineseFoodFactory.makeFood("A").userEat();
        chineseFoodFactory.makeFood("B").userEat();
    }


    interface FoodFactory {
        /**
         * 声明创建食物的接口规范, 后续工厂类使用此接口实现来创建用户需要的对象
         *
         * @param name  需要创建的名称
         * @return      返回对应名称的 Food 对象
         */
        Food makeFood(String name);
    }

    @FunctionalInterface
    interface Food {
        void userEat();
    }

    static class ChineseFoodFactory implements FoodFactory {

        @Override
        public Food makeFood(String name) {
            if ("A".equals(name)) {
                return () -> System.out.println("ChineseFoodA can be eat!");
            }
            return () -> System.out.println("ChineseFoodB can be eat!");
        }
    }

    static class AmericanFoodFactory implements FoodFactory {

        @Override
        public Food makeFood(String name) {
            if ("A".equals(name)) {
                return () -> System.out.println("AmericanFoodA can be eat!");
            }
            return () -> System.out.println("AmericanFoodB can be eat!");
        }
    }
}


