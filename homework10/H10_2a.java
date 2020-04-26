package homework10;

public class H10_2a {
    public void search(String text, String pattern){

        int lengthOfText = text.length();
        int lengthOfPattern = pattern.length();

        for( int i = 0; i <= lengthOfText - lengthOfPattern ; i++){

            int j;

            for( j=0;j<lengthOfPattern;j++){
                if( text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }

            if( j == lengthOfPattern ) System.out.println("Pattern found at index "+i);
        }


    }
    public static void main(String[] args)
    {
        H10_2a a=new H10_2a();
        String text="ABCADCBABABCDABCDABDE";
        String pattern="BCD";
        System.out.println("---The result of Brute-Force substring search algorithm---" );
         a.search(text,pattern);
    }
}
