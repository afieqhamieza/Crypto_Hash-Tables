import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        String plaintext = "the birthday attack can be performed for any hash functions including sha three";
        String key = "aaaaa";

        String pt_key = concat(key, plaintext);
        String[] param = new String[]{pt_key};

        String result = Problem1.main(param);

        pt_key = concat(key, result);
        param[0] = pt_key;

        result = Problem1.main(param);

        System.out.println("\nResult: " + result);

    }

    public static String concat(String str1_in, String str2_in){
        return str1_in + str2_in;
    }
}
