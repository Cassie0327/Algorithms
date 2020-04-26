package homework6;

/**
 Name: Qian Cai
 NU ID:001389278

 * Created by Qian Cai on 2019/10/12.
 */
public class H6_4 {
    class Node
    {
        char data;
        Node left, right;

        public Node(char item)
        {
            data = item;
            left = right = null;
        }
    }
    static Node head;

    Node insert(Node node, char data) {

        if (node == null) {
            return (new Node(data));
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }

            return node;
        }
    }


    public char minvalue(Node node) {
        Node current = node;


        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public static void main(String[] args) {
        H6_4 tree = new H6_4();
        Node root = null;
        root = tree.insert(root, 'A');
        tree.insert(root, 'B');
        tree.insert(root, 'C');
        tree.insert(root, 'D');
        tree.insert(root, 'E');
        tree.insert(root, 'F');
        tree.insert(root, 'G');
        tree.insert(root, 'H');
        tree.insert(root, 'I');
        tree.insert(root, 'F');
        tree.insert(root, 'J');
        tree.insert(root, 'K');

        System.out.println("Minimum value of BST is " + tree.minvalue(root));
    }
}
