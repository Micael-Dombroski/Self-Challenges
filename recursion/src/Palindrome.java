public class Palindrome {
    public boolean isPalindrome(String word) {
        if(word.length() <= 1)
            return true;
        char c1 = word.charAt(0);
        char c2 = word.charAt(word.length()-1);
        if(c1 == c2) {
            String substr = word.substring(1, word.length()-1);
            return isPalindrome(substr);
        }
        return false;
    }
}
