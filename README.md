# 策略模式

## 一、什么是策略模式

策略模式（Strategy Pattern）又叫政策模式（Policy Pattern），它是将定义的算法家族分别封装起来，让它们之间可以互相替换，从而让算法的变化不会影响到使用算法的用户。属于行为型模式。

策略模式使用的就是面向对象的继承和多态机制，从而实现同一行为在不同场景下具备不同实现。



### 1、策略模式应用场景

策略模式在生活中应用也非常多。比如一个人的交税比率与他的工资有关，不同工资对应不同的税率。再比如互联网移动支付，每次下单后付款都需要选择支付方式。

策略模式可以解决在有多种算法相似的情况下，使用if-else或者switch-case所带来的复杂性和臃肿性，策略模式通常适用于以下场景：

- 针对同一类型问题，有多种处理方式，每一种都能独立解决问题；
- 一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现，可将每个条件分支移入它们各自的策略类中以代替这些条件语句；
- 多个类只区别在表现行为不同，可以使用策略模式，在运行时动态选择具体要执行的行为；
- 算法需要自由切换的场景；
- 需要屏蔽算法规则的场景；



### 2、状态模式与策略模式的区别

状态模式和策略模式的UML类图架构几乎完全一样，但他们的应用场景是不一样的。策略模式多种算法行为择其一都能满足，彼此之间是独立的，用户可自行更换策略算法；而状态模式各个状态间是存在相互关系的，彼此之间在一定条件下存在自动切换状态效果，且用户无法指定状态，只能设置初始状态。



### 3、策略模式优缺点

优点：

- 策略类之间可以自由切换：由于策略类都实现同一个接口，所以使它们之间可以自由切换。

- 易于扩展：增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合“开闭原则“
- 避免使用多重条件选择语句（if else），充分体现面向对象设计思想。

缺点：

- 客户端必须知道所有的策略类，并自行决定使用哪一个策略类。
- 策略模式将造成产生很多策略类，可以通过使用享元模式在一定程度上减少对象的数量。



### 4、策略模式的三大角色

![在这里插入图片描述](E:/BaiduSyncdisk/文档/我的笔记/Typora版本/设计模式/img/64f670828e654619b6ac437bbbfe9370.png)

策略模式的主要角色如下：

- 抽象策略（Strategy）类：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
- 具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现或行为。
- 环境（Context）类：用来操作策略的上下文环境，屏蔽高层模块（客户端）对策略、算法的直接访问，封装可能存在的变化。



> 注：策略模式中的上下文环境（Context），其职责本来是隔离客户端与策略类的耦合，让客户端完全与上下文环境沟通，
>
> 无需关心具体策略





## 二、实例

### 1、策略模式的一般写法

```java
//抽象策略类 Strategy
public interface IStrategy {
    void algorithm();
}
//具体策略类 ConcreteStrategy
public class ConcreteStrategyA implements IStrategy {
    public void algorithm() {
        System.out.println("Strategy A");
    }
}
//具体策略类 ConcreteStrategy
public class ConcreteStrategyB implements IStrategy {
    public void algorithm() {
        System.out.println("Strategy B");
    }
}
```

```java
//上下文环境
public class Context {
    private IStrategy mStrategy;

    public Context(IStrategy strategy) {
        this.mStrategy = strategy;
    }

    public void algorithm() {
        this.mStrategy.algorithm();
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        //选择一个具体策略
        IStrategy strategy = new ConcreteStrategyA();
        //来一个上下文环境
        Context context = new Context(strategy);
        //客户端直接让上下文环境执行算法
        context.algorithm();
    }
}
```

### 2、促销活动案例

一家百货公司在定年度的促销活动。针对不同的节日（春节、中秋节、圣诞节）推出不同的促销活动，由促销员将促销活动展示给客户。类图如下：

![在这里插入图片描述](E:/BaiduSyncdisk/文档/我的笔记/Typora版本/设计模式/img/ffb78e3c87704134a232b473e0d1db79.png)

```java
// 定义百货公司所有促销活动的共同接口
public interface Strategy {
	void show();
}
// 定义具体策略角色（Concrete Strategy）：每个节日具体的促销活动
//为春节准备的促销活动A
public class StrategyA implements Strategy {
	public void show() {
		System.out.println("买一送一");
	}
}
//为中秋准备的促销活动B
public class StrategyB implements Strategy {
	public void show() {
		System.out.println("满200元减50元");
	}
}
//为圣诞准备的促销活动C
public class StrategyC implements Strategy {
	public void show() {
		System.out.println("满1000元加一元换购任意200元以下商品");
	}
}
```

```java
// 定义环境角色（Context）：用于连接上下文，即把促销活动推销给客户，这里可以理解为销售员
public class SalesMan {
	//持有抽象策略角色的引用
	private Strategy strategy;
	public SalesMan(Strategy strategy) {
		this.strategy = strategy;
	}
	//向客户展示促销活动
	public void salesManShow(){
		strategy.show();
	}
}
```

```java
// 测试类
public class Client {
    public static void main(String[] args) {
        //春节来了，使用春节促销活动
        SalesMan salesMan = new SalesMan(new StrategyA());
        //展示促销活动
        salesMan.salesManShow();

        System.out.println("==============");
        //中秋节到了，使用中秋节的促销活动
        salesMan.setStrategy(new StrategyB());
        //展示促销活动
        salesMan.salesManShow();

        System.out.println("==============");
        //圣诞节到了，使用圣诞节的促销活动
        salesMan.setStrategy(new StrategyC());
        //展示促销活动
        salesMan.salesManShow();
    }
}
```

此时，我们发现，上面的测试代码放到实际业务场景其实并不实用，因为我们做活动时往往是要根据不同的需求对促销策略进行动态选择的，并不会一次性执行多种优惠，所以我们代码通常会这样写：

```java
public class Client {
    public static void main(String[] args) {
        SalesMan salesMan = null;
        String saleKey = "A";
        
        if(saleKey.equals("A")){
            //春节来了，使用春节促销活动
            salesMan = new SalesMan(new StrategyA());
        } else if (saleKey.equals("B")) {
            //中秋节到了，使用中秋节的促销活动
            salesMan = new SalesMan(new StrategyB());
        } // ...

        //展示促销活动
        salesMan.salesManShow();
    }
}
```

这样改造之后，满足了业务需求，客户可以根据自己的需求选择不同的优惠策略了。但是这里的if-else随着促销活动的增多会越来越复杂，我们可以使用单例模式和工厂模式进行优化：

```java
public class SalesMan {

    public static final String SaleKeyA = "A";
    public static final String SaleKeyB = "B";
    public static final String SaleKeyC = "C";

    private static Map<String, Strategy> sales = new HashMap<String, Strategy>();

    static {
        sales.put(SaleKeyA, new StrategyA());
        sales.put(SaleKeyB, new StrategyB());
        sales.put(SaleKeyC, new StrategyC());
    }

    public Strategy getStrategy(String key) {
        Strategy strategy = sales.get(key);
        if(strategy == null){
            throw new RuntimeException("策略有误");
        }
        return strategy;
    }
}
```

```java
public class Client {
    public static void main(String[] args) {
        SalesMan salesMan = new SalesMan();
        String saleKey = "A";

        Strategy strategy = salesMan.getStrategy(saleKey);

        //展示促销活动
        strategy.show();
    }
}
```

### 3、网购订单支付案例

我们在网购下单时，会提示选择支付方式，通常会有支付宝、微信、银联等等支付方式，如果没选择，系统也会使用默认的支付方式，我们使用策略模式来模拟此场景：

```java
// 支付状态包装类
public class MsgResult {
    private int code;
    private Object data;
    private String msg;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MsgResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
```

```java
// 定义支付逻辑，具体支付交由子类实现
public abstract class Payment {

    public abstract String getName();

    //通用逻辑放到抽象类里面实现
    public MsgResult pay(String uid, double amount){
        //余额是否足够
        if(queryBalance(uid) < amount){
            return new MsgResult(500,"支付失败","余额不足");
        }
        return new MsgResult(200,"支付成功","支付金额" + amount);
    }

    protected abstract double queryBalance(String uid);
}
```

```java
// 定义具体支付方式
public class AliPay extends Payment {
    public String getName() {
        return "支付宝";
    }

    protected double queryBalance(String uid) {
        return 900;
    }
}
public class JDPay extends Payment {
    public String getName() {
        return "京东白条";
    }

    protected double queryBalance(String uid) {
        return 500;
    }
}
public class UnionPay extends Payment {
    public String getName() {
        return "银联支付";
    }

    protected double queryBalance(String uid) {
        return 120;
    }
}
public class WechatPay extends Payment {
    public String getName() {
        return "微信支付";
    }

    protected double queryBalance(String uid) {
        return 263;
    }
}
```

```java
// 策略管理类
public class PayStrategy {
    public static  final String ALI_PAY = "AliPay";
    public static  final String JD_PAY = "JdPay";
    public static  final String WECHAT_PAY = "WechatPay";
    public static  final String UNION_PAY = "UnionPay";
    public static  final String DEFAULT_PAY = ALI_PAY;

    private static Map<String,Payment> strategy = new HashMap<String,Payment>();

    static {
        strategy.put(ALI_PAY,new AliPay());
        strategy.put(JD_PAY,new JDPay());
        strategy.put(WECHAT_PAY,new WechatPay());
        strategy.put(UNION_PAY,new UnionPay());
    }

    public static Payment get(String payKey){
        if(!strategy.containsKey(payKey)){
            return strategy.get(DEFAULT_PAY);
        }
        return strategy.get(payKey);
    }
}
```

```java
// 订单类
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public MsgResult pay(){
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey){
        Payment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为" + amount + "，开始扣款");
        return payment.pay(uid,amount);
    }
}
```

```java
// 测试类
public class Test {
    public static void main(String[] args) {
        Order order = new Order("1","orderid",324.5);
        System.out.println(order.pay(PayStrategy.UNION_PAY));
    }
}
```