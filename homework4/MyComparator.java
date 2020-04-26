package homework4;
import java.util.Comparator;

/**
 Name: Qian Cai
 * Created by Qian Cai on 2019/10/2.
 */
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {

        return x.data - y.data;
    }
}
