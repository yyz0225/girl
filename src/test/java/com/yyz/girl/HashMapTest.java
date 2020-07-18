package com.yyz.girl;

import com.yyz.girl.entity.User;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @Author: yyz
 * @Date: 2019/4/17 9:28
 */
public class HashMapTest implements Testa {

    private static int frood;

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

        //list1.removeAll(list2);
        System.out.println(list1);
        String[] list1Array = list1.toArray(new String[list1.size()]);
        System.out.println("List转数组:"+list1Array);
        List<String> arrayList = Arrays.asList(list1Array);
        System.out.println("数组转List:"+arrayList);
        //List转数组获取到的是一个只读的List,不能添加和删除元素,如果添加和删除操作,会报UnsupportedOperationException异常
        List<String> arrayList2 = new ArrayList<>(arrayList);
        arrayList2.add("C");
        System.out.println("数组添加元素:"+arrayList2);
        System.out.println(++frood);
        String string= "hello word **";
        System.out.println(allRotate(string));
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
       /* Collections.sort(entryList,(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) -> o2.getValue().getAge()-o1.getValue().getAge());

        *//*java8 使用lambda表达式实现 简易写法*//*
        entryList.sort((Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) -> o2.getValue().getAge()-o1.getValue().getAge());
*/
        //遍历entryList集合,把排序后的结果集放入linkedHashMap中
        for(Map.Entry<Integer,User> entry : entryList){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
        return linkedHashMap;
    }

    @Test
    public void d() throws UnsupportedEncodingException {
        // 原始的字符串
        String wholeWord = "oneofthecentralresultsofairesearchinthe1970swasthattoachievegoodperformanceaisystemsmusthavelargeamountsofknowledgeknowledgeispowertheslogangoeshumansclearlyusevastamountsofknowledgeandifaiistoachieveitslongtermgoalsaisystemsmustalsousevastamountssincehandcodinglargeamountsofknowledgeintoasystemisslowtediousanderrorpronemachinelearningtechniqueshavebeendevelopedtoautomaticallyacquireknowledgeoftenintheformofifthenrulesproductionsunfortunatelythishasoftenledtoautilityproblemminton1988bthelearninghascausedanoverallslowdowninthesystemforexampleinmanysystemslearnedrulesareusedtoreducethenumberofbasicstepsthesystemtakesinordertosolveproblemsbypruningthesystemssearchspaceforinstancebutinordertodetermineateachstepwhichrulesareapplicablethesystemmustmatchthemagainstitscurrentsituationusingcurrenttechniquesthematcherslowsdownasmoreandmorerulesareacquiredsoeachsteptakeslongerandlongerthisectcanoutweighthereductioninthenumberofstepstakensothatthenetresultisaslowdownthishasbeenobservedinseveralrecentsystemsminton1988aetzioni1990tambeetal1990cohen1990ofcoursetheproblemofslowdownfromincreasingmatchcostisnotrestrictedtosystemsinwhichthepurposeofrulesistoreducethenumberofproblemsolvingstepsasystemacquiringnewrulesforanypurposecanslowdowniftherulessignicantlyincreasethematchcostandintuitivelyoneexpectsthatthemoreproductionsthereareinasystemthehigherthetotalmatchcostwillbethethesisofthisresearchisthatwecansolvethisprobleminabroadclassofsystemsbyimprovingthematchalgorithmtheyuseinessenceouraimistoenablethescalingupofthenumberofrulesinproductionsystemsweadvancethestateoftheartinproductionmatchalgorithmsdevelopinganimprovedmatchalgorithmwhoseperformancescaleswellonasignicantlybroaderclassofsystemsthanexistingalgorithmsfurthermorewedemonstratethatbyusingthisimprovedmatchalgorithmwecanreduceoravoidtheutilityprobleminalargeclassofmachinelearningsystems";
        // 已经统计过的词组
        Set<String> countedWord = new HashSet<String>();
       /* for (int i = 0; i < wholeWord.length() - 1; i++) {
            String aWord = String.valueOf(wholeWord.charAt(i))
                    + String.valueOf(wholeWord.charAt(i + 1));
            // 忽略空格
            if (aWord.contains(" ")) {
                continue;
            }
            // 忽略已统计的词组
            if (countedWord.contains(aWord)) {
                continue;
            }
            String copyOfWholeWord = new String(wholeWord);
            // 出现的次数
            int repeatedTime = 0;
            while (copyOfWholeWord.indexOf(aWord) != -1) {
                repeatedTime++;
                copyOfWholeWord = copyOfWholeWord.substring(copyOfWholeWord.indexOf(aWord) + aWord.length());
            }
            if (repeatedTime > 1) {
                System.out.println("词组[" + aWord + "]出现的次数为：" + repeatedTime);
            }
            countedWord.add(aWord);*/
    }
    /**
     * 给予字符串是类似于网址：www.baidu.com    www.sina.com.cn
     * 将给与的字符串按照规则反转 com.baidu.www    cn.com.sina.www
     * @param str
     * @return
     */
    public static String allRotate(String str){
        int strLength = str.length();
        char[] chars = str.toCharArray();
        int left = 0;
        // 先局部反转
        for (int i = 0; i < strLength; i++){
            if (chars[i] == ' '){
                Rotate(chars, left, i-1);
                left = i + 1;
            }
        }
        Rotate(chars, left, strLength-1);  // 最后一小段反转
        Rotate(chars,0,strLength-1);  // 整体反转
        return new String(chars);
    }
    public static void Rotate(char[] chars, int start, int end){
        while (start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 自定义文件名,实现文件复制
     *
     * @param src  源文件的路径
     * @param desc 目标文或文件夹的路径
     * @throws FileNotFoundException
     */
    public static void copyfile(File src, File desc) throws FileNotFoundException {
        String str;
        FileReader fr;
        FileWriter fw;
        BufferedReader br = null;
        BufferedWriter bw = null;
        //判断文件路径是否正确是否是文件和文件夹
        if (src == null || desc == null || src.isDirectory() || desc.isFile()) {
            throw new FileNotFoundException("文件参数错误!");//抛出异常
        } else {
            if (!desc.exists()) desc.mkdirs();//如果目标地址没有文件夹则创建文件夹
            File file = new File(desc.getPath() + File.separator + src.getName());//创建目标文件路径和文件名的对象
            try {
                file.createNewFile();//创建文件
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                fr = new FileReader(src);
                fw = new FileWriter(desc);
                br = new BufferedReader(fr);
                bw = new BufferedWriter(fw);
                while ((str = br.readLine()) != null) {
                    bw.write(str);
                    bw.newLine();
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {
                try {
                    //首先关闭的是bw.flush()
                    bw.flush();
                    br.close();
                    bw.close();
                } catch (IOException e) {
                }

            }

        }
    }

}

 interface Testa{
    int frood=42;
}
