package lib.vary.basic;

import java.util.Iterator;

import lib.std.StdRandom;

/**
 * 随机背包，难点在于快速生成一份随机索引
 */
public class RandomBag<Item> implements Iterable<Item> {

    private int pointer;
    private Item[] contents;
    private int len;

    public RandomBag() {
        this.contents = (Item[]) new Object[1];
        this.pointer = 0;
        this.len = 1;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private int i;
        private int[] index;
        
        public RandomIterator() {
            this.i = pointer;
            this.index = getRandomIndex();
        }

        private int[] getRandomIndex() {
            int range = this.i;
            int[] index = new int[range];
            for (int i = 0; i < range; i++) {
                index[i] = i;
            }

            var rand = new int[range];
            for (int i = 0; i < range; i++) {

                var temp = StdRandom.uniformInt(range - i);
                rand[i] = index[temp];

                for (int j = temp; j < range - i - 1; j++) {
                    index[j] = index[j+1];
                }
            }

            return rand;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return contents[index[--i]];
        }

    }

    public void add(Item item) {
        if (pointer == len) {
            resize(len * 2);
        }
        contents[pointer] = item;
        pointer++;
    }

    public boolean isEmpty() {
        return this.pointer == 0;
    }

    public int size() {
        return this.pointer;
    }

    /**
     * 返回 package 的最大容量
     * @return
     */
    public int supremeSize() {
        return this.len;
    }

    /**
     * 当 package 容量达到某个程度时，修改 package 的大小
     * @param cap
     */
    private void resize(int cap) {
        var temp = (Item[]) new Object[cap];
        int index = 0;
        for (index = 0; index < pointer; index++) {
            temp[index] = contents[index]; 
        }

        contents = temp;
        len = cap;
    }

    public static void main(String[] args) {

        // 确认包能够如期构建
        var stats = new RandomBag<Integer>();
        System.out.println("初始包容量: " + stats.supremeSize());
        System.out.println("是否为空: " + stats.isEmpty());
        System.out.println("当前包大小为: " + stats.size());

        // 容量自动增长测试
        for (int i = 0; i < 100; i++) {
            stats.add(i);
        }
        
        System.out.println("第二次添加后包容量: " + stats.supremeSize());
        System.out.println("当前包大小为: " + stats.size());

        // 迭代器测试
        for (Integer i : stats) {
            System.out.println(i);
        }



    }
}
