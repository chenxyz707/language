package com.chenxyz.language.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lambda & Stream
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-03-20
 */
public class Lambda {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("orange", "apple", "pear", "banana");
        print(list);
        //普通方法
        /*list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });*/

        //使用lambda表达式，如果表达式不需要参数也是需要写圆括号的
        list.sort((o1, o2) -> o1.length()-o2.length());

        print(list);

        //方法引用(Method Reference)
        List<Integer> numList = Arrays.asList(2, 34, 5, 67, 85);
        //引用静态方法
        numList.sort(Integer::compare);
        //引用某个特定对象的实例方法
        list.sort(String.CASE_INSENSITIVE_ORDER::compare);//实例方法
        numList.forEach(System.out::print);
        //引用某个类的实例方法
//        list.stream().map(a -> a.length());//lambda
//        list.stream().map(String::length);//方法引用
        //引用构造函数
//        list.stream().map(a -> new StringBuilder(a));//lambda
//        list.stream().map(StringBuilder::new);//方法引用

        //集合流式操作
        //使用sorted方法进行排序,流只能被消费一次！
        System.out.println("\nstream operations");
        System.out.print("sort : ");
        numList.stream().sorted().forEach(a -> {
            System.out.print(a + "  ");
        });

        //转换为list
        List _list = numList.stream().sorted().collect(Collectors.toList());
        print(_list);

        //使用filter过滤
        Predicate<Integer> predicate = a -> a>10;
        Stream<Integer> filterStream = numList.stream().filter(predicate);
        System.out.print("\nfilter count is :" + filterStream.count());
        filterStream = numList.stream().filter(predicate);
        System.out.print("\nfilter : ");
        filterStream.forEach(a->System.out.print(a + " "));

        //map操作对每个元素进行改变
        System.out.print("\nmap : ");
        numList.stream().map(x->x*2).forEach(a->System.out.print(a + " "));

    }

    public static void print(List list){
        System.out.println();
        list.forEach(a -> System.out.print(a + "  "));
        System.out.println();
    }
}
