package lib.main.datastructure;

import java.util.Iterator;

import lib.api.AbstractPackage;
import lib.std.StdOut;
import lib.std.StdRandom;

/**
 * 一个基于数组结构实现的泛型不定容包
 */
public class Package<Item> implements AbstractPackage<Item> {

    private int pointer;
    private Item[] contents;
    private int len;

    public Package() {
        this.contents = (Item[]) new Object[1];
        this.pointer = 0;
        this.len = 1;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayFormIterator();
    }

    private class ReverseArrayFormIterator implements Iterator<Item> {

        private int i = pointer;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return contents[--i];    
        }

    }

    @Override
    public void add(Item item) {
        if (pointer == len) {
            resize(len * 2);
        }
        contents[pointer] = item;
        pointer++;
    }

    @Override
    public boolean isEmpty() {
        return this.pointer == 0;
    }

    @Override
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
        var stats = new Package<Integer>();
        StdOut.println("初始包容量: " + stats.supremeSize());
        StdOut.println("是否为空: " + stats.isEmpty());
        StdOut.println("当前包大小为: " + stats.size());


        for (int i = 0; i < 15; i++) {
            stats.add(StdRandom.uniformInt(1000));
        }
        
        StdOut.println("第一次添加后包容量: " + stats.supremeSize());
        StdOut.println("当前包大小为: " + stats.size());

        // 容量自动增长测试
        for (int i = 0; i < 15; i++) {
            stats.add(StdRandom.uniformInt(1000));
        }
        
        StdOut.println("第二次添加后包容量: " + stats.supremeSize());
        StdOut.println("当前包大小为: " + stats.size());

        int sum = 0;
        for (Integer i : stats) {
            sum += i;
        }
        StdOut.println("总和为: " + sum);

    }
}
