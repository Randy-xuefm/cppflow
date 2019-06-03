package cpp.flow.util;

/**
 * Created by fenming.xue on 2019/6/3.
 */
public class StringUtils {

    public static boolean isBlank(String str){
        if(null == str){
            return true;
        }
        return str.isEmpty();
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }
}
