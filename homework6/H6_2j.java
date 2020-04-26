package homework6;

/**
 Name: Qian Cai
 NU ID:001389278

 * Created by Qian Cai on 2019/10/12.
 */
public class H6_2j {
    class Node
    {
        int key;
        Node left, right;

        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
    Node root=null;
    public void delete_min()
    {
        deleteKey(minValue(root));

    }
    public void delete_max()
    {
        deleteKey(maxValue(root));
    }
   public int minValue(Node root)
    {
        Node minv = root;
        while (minv.left != null)
        {
            minv = minv.left;
        }
        return minv.key;
    }
    public int maxValue(Node root)
    {
        Node maxv = root;
        while (maxv.right != null)
        {
            maxv = maxv.right;
        }
        return maxv.key;
    }
    void deleteKey(int key)
    {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key)
    {
        if (root == null)  return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);


        else
        {

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    public void insertbinary(int key)
    {
        root = insertbinaryRec(root, key);
    }

    Node insertbinaryRec(Node root, int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertbinaryRec(root.left, key);
        else if (key > root.key)
            root.right = insertbinaryRec(root.right, key);
        return root;
    }
    public void inorderbinary()
    {
        inorderbinaryRec(root);
    }
    public  void inorderbinaryRec(Node root)
    {
        if (root != null)
        {
            inorderbinaryRec(root.left);
            System.out.print(root.key + " ");
            inorderbinaryRec(root.right);
        }
    }
    public  static void main(String[]args)
    {
        H6_2j h=new H6_2j();
        h.insertbinary(3);
        h.insertbinary(7);
        h.insertbinary(9);
        h.insertbinary(23);
        h.insertbinary(45);
        h.insertbinary(1);
        h.insertbinary(5);
        h.insertbinary(14);
        h.insertbinary(55);
        h.insertbinary(24);
        h.insertbinary(13);
        h.insertbinary(11);
        h.insertbinary(8);
        h.insertbinary(19);
        h.insertbinary(4);
        h.insertbinary(31);
        h.insertbinary(35);
        h.insertbinary(56);
        System.out.println("Before delete:");
        h.inorderbinary();
        System.out.println();
        System.out.println("After deletemin:");
        h.delete_min();
        h.inorderbinary();
        System.out.println();
        System.out.println("After deletemax:");
        h.delete_max();
        h.inorderbinary();



    }



}
