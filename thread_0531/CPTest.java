package exercise.thread_0531;

/*
一个生产者，一个消费者
 */
/*
class Goods{
    private String goodname;
    private int count;

    Goods(String goodname, int count) {
        this.goodname = goodname;
        this.count = count;
    }
    public synchronized void get()  {
        if(this.count == 0){
            System.out.println("已经没有库存了，等待生产者生产");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count --;
        System.out.println(toString());
        //消费完毕唤醒生产者
        notify();
    }
    public synchronized void set() {
        if(this.count > 0){
            System.out.println("还有库存哦，等待消费者消费");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count ++;
        System.out.println(toString());
        //生产完毕唤醒消费者
        notify();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodname='" + goodname + '\'' +
                ", count=" + count +
                '}'+Thread.currentThread().getName();
    }
}
class Producer implements Runnable{
    private Goods goods;

    public Producer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        this.goods.set();
    }
}

class Consumer implements Runnable{
    private Goods goods;

    public Consumer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        this.goods.get();
    }
}

public class CPTest {
    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods("小黑瓶",0);
        Producer producer = new Producer(goods);
        Consumer consumer = new Consumer(goods);
        Thread produceThread = new Thread(producer,"生产者1");
        Thread consumseThread = new Thread(consumer,"消费者1");
        produceThread.start();
        Thread.sleep(1000);
        consumseThread.start();
    }
}
*/

/*
多个消费者
多个生产者
 */
class Goods{
    private String goodname;
    private int count;

    Goods(String goodname, int count) {
        this.goodname = goodname;
        this.count = count;
    }
    public synchronized void get()  {
        while(this.count == 0){
            System.out.println("已经没有库存了，等待生产者生产");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count --;
        System.out.println(toString());
        notifyAll();
    }
    public synchronized void set() {
        while(this.count > 0){
            System.out.println("还有库存哦，等待消费者消费");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.count ++;
        System.out.println(toString());
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodname='" + goodname + '\'' +
                ", count=" + count +
                '}'+Thread.currentThread().getName();
    }
}
class Producer implements Runnable{
    private Goods goods;

    public Producer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true) {
            this.goods.set();
        }
    }
}

class Consumer implements Runnable{
    private Goods goods;

    public Consumer(Goods goods) {
        super();
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true){
            this.goods.get();
        }
    }
}

public class CPTest {
    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods("小黑瓶",0);
        Thread produceThread1 = new Thread(new Producer(goods),"生产者1");
        Thread consumseThread1 = new Thread(new Consumer(goods),"消费者1");
        Thread produceThread2 = new Thread(new Producer(goods),"生产者2");
        Thread consumseThread2 = new Thread(new Consumer(goods),"消费者2");
        Thread produceThread3 = new Thread(new Producer(goods),"生产者3");
        Thread consumseThread3 = new Thread(new Consumer(goods),"消费者3");
        produceThread1.start();
        produceThread2.start();
        produceThread3.start();
        consumseThread1.start();
        consumseThread2.start();
        consumseThread3.start();
    }
}
