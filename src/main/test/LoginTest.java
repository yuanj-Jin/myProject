import java.util.*;

public class LoginTest {
    public static void main(String args[]){
        //1、处理输入数据
        Scanner sc =new Scanner(System.in);
        String inputStr=sc.nextLine();
        String[] inputStrs=inputStr.split("#");
        List<String> handleList=new ArrayList<String>();
        for (int i = 0; i < inputStrs.length ; i++) {
            char[] strChar=inputStrs[i].toCharArray();
//            System.out.println(strChar[0]=='-');
            for (int j = 0; j < strChar.length; j++) {
                if ('-'==strChar[j]){
                    strChar[j]='0';
                }else if ('.'==strChar[j]){
                    strChar[j]='1';
                }
            }
            String string=new String(strChar);
            Integer orgInt=Integer.parseInt(string); //011
            //将二进制转换为十进制
            String lastInt=Integer.parseInt(orgInt+"",2)+""; //3
            Integer a=Integer.parseInt(lastInt);
            System.out.println(a);
            if (a>40 &&a<0){
                System.out.println("ERROR");
                break;
            }
            handleList.add(lastInt);
        }

        //2、处理映射关系
        String abc[]={"G","R","S","T","L","M","N","O","P","Q","W","X","Y","Z","U","A","G","H","I","J","K","B","C","D","E","i","m","n","o","p","i","j","k","f","g","h","a","b","c","d","e","q","r","w","x","y","z","s","t","u","v"};
        Map map=new HashMap();
        map.put("0","F");
        for (int i = 1; i <=abc.length; i++) {
            //System.out.println(abc.length);
            map.put(i+"",abc[i-1]);
        }

        //3、输出结果
        for (int i = 0; i < handleList.size(); i++) {
            //System.out.print(handleList.get(i));
            System.out.print(map.get(handleList.get(i)));
        }

    }


}
