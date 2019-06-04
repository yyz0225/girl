package com.yyz.girl;

import com.yyz.girl.entity.User;
import org.junit.Test;

import java.util.*;

/**
 * @Author: yyz
 * @Date: 2019/4/17 9:28
 */
public class HashMapTest {

    /**
     * 对HashMap进行排序
     * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现对HashMap 的排序功能，
     * 该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，要求对 HashMap 中的 User 的 age 倒序进行排序。
     * 排序时 key=value 键值对不得拆散。
     */
    @Test
    public void main() {
        /*HashMap<Integer,User> users=new HashMap<>();
        users.put(1,new User("张三",18));
        users.put(2,new User("李四",22));
        users.put(3,new User("王五",16));
        System.out.println(users);

        HashMap<Integer,User> sortedUsers=sortHashMap(users);
        System.out.println(sortedUsers);*/

        List<String> list1 =new ArrayList<String>();
        list1.add("A");
        list1.add("B");

         List<String> list2 =new ArrayList<String>();
        list2.add("A");
        list2.add("B");

        list1.removeAll(list2);
        System.out.println(list1);
    }

    /**
     * 对HashMap排序方法
     * @param map
     * @return
     */
    private HashMap<Integer,User> sortHashMap(HashMap<Integer, User> map) {
        //排序后的结果集
        LinkedHashMap<Integer,User> linkedHashMap=new LinkedHashMap<>();

        //key=value键值对实体
        Set<Map.Entry<Integer,User>> entrySet= map.entrySet();
        //key=value键值对实体转为list集合
        List<Map.Entry<Integer,User>> entryList=new ArrayList<>(entrySet);
        //对list集合进行排序,user的age倒序排列
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge()-o1.getValue().getAge();
            }
        });

        /*java8 使用lambda表达式实现*/
        Collections.sort(entryList,(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) -> o2.getValue().getAge()-o1.getValue().getAge());

        /*java8 使用lambda表达式实现 简易写法*/
        entryList.sort((Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) -> o2.getValue().getAge()-o1.getValue().getAge());

        //遍历entryList集合,把排序后的结果集放入linkedHashMap中
        for(Map.Entry<Integer,User> entry : entryList){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
        return linkedHashMap;
    }
}
