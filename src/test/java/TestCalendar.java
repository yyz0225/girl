import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @Author: yyz
 * @Date: 2020/10/15 21:35
 * 控制台输出可视化日历程序
 */
public class TestCalendar {
    public static void main(String[] args) throws ParseException{

        System.out.println("请输入日期格式(2020/09/15):");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse(str);
        System.out.println(date);
        System.out.println("---------------分隔符-----------");
        System.out.println();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        System.out.println("111111:"+calendar.get(Calendar.DAY_OF_WEEK));

        showCalendar(date);


    }

    /**
     * 可视化日历打印
     * @param date
     */
    private static void showCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        System.out.println("日\t一\t二\t三\t四\t五\t六");

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH,1);

        for (int i = 0; i <calendar.get(Calendar.DAY_OF_WEEK)-1 ; i++) {
            System.out.print("\t");
        }
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <=maxDays ; i++) {

            if (day == calendar.get(Calendar.DAY_OF_MONTH)){
                System.out.print(calendar.get(Calendar.DAY_OF_MONTH)+"*\t");
            }else {
                System.out.print(calendar.get(Calendar.DAY_OF_MONTH)+"\t");
            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                System.out.println();
            }
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
    }
}
