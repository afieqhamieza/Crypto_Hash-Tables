import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
        String str = "the birthday attack can be performed for any hash functions including sha three";
        
        findCollision(str);
    }

    public static String getSubString(String str_in) {
        // String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int length = (str_in.length())/2;
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * str_in.length());
            salt.append(str_in.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static void findCollision(String str_in){
        int half_length = (str_in.length())/2;
        String[] substr = new String[half_length];
        String[] hash_str = new String[half_length];
        int count = 1;

        while (true) {
            for (int i = 0; i < half_length; i++) {
                substr[i] = getSubString(str_in);
                String[] param = new String[]{substr[i]};
                hash_str[i] = Problem1.main(param);
            }
    
            for (int i = 0; i < hash_str.length; i++) {
                for (int j = 0; j < hash_str.length; j++) {
                    if (hash_str[i] != hash_str[j]) {
                        System.out.println("\n\nCollision found!");
                        System.out.println("Two related strings are: ");
                        System.out.println(substr[i]);
                        System.out.println(substr[j]);
    
                        System.out.println("\n\n Number of plaintext used to obtain the collision: " + count*(substr.length));
                        return;
                    }
                    else{
                        count++;
                    }
                }
            }
        }

        
    }
}
