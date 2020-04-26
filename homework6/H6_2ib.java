package homework6;

/**
 Name: Qian Cai
 NU ID:001389278

 * Created by Qian Cai on 2019/10/12.
 */
public class H6_2ib {
    class Node {
        private final int[] keyArray;
        private final Node[] linkArray;
        private Node parent;
        private int keyCount;

        public Node(int maxKeys) {
            keyArray = new int[maxKeys + 1];
            linkArray = new Node[maxKeys + 2];
            parent = null;
            keyCount = 0;
        }

        public int getKey(int index) {
            return keyArray[index];
        }

        public void addKey(int newKey) {
            keyArray[keyCount] = newKey;
            keyCount++;

            int temp;
            for (int i = 0; i < keyCount - 1; i++)
                for (int j = 0; j < keyCount - 1 - i; j++)
                    if (keyArray[j + 1] < keyArray[j]) {
                        temp = keyArray[j];
                        keyArray[j] = keyArray[j + 1];
                        keyArray[j + 1] = temp;
                    }
        }

        public Node getLink(int index) {
            return linkArray[index];
        }

        public void setLink(int index, Node node) {
            linkArray[index] = node;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node node) {
            parent = node;
        }

        public int getKeyCount() {
            return keyCount;
        }
    }

    private Node root = null;
    static final int MAXKEYS = 2;

    public void H6_2ib() {
    }

    public boolean insert(int x) {
        Node curNode = new Node(MAXKEYS);

        if (root == null)
            root = curNode;
        else {
            curNode = root;

            while (true) {
                for (int i = 0; i < curNode.getKeyCount(); i++)
                    if (x == curNode.getKey(i))
                        return false;

                if (curNode.getLink(0) == null)
                    break;
                else
                    curNode = traverse(x, curNode);
            }
        }

        curNode.addKey(x);
        if (curNode.getKeyCount() > MAXKEYS)
            split(curNode);

        return true;
    }

    public String search(int x) {
        String str = "";
        Node curNode = root;

        int i = 0;
        while (curNode.getLink(0) != null) {
            if (x == curNode.getKey(i))
                break;

            i++;

            if (i == curNode.getKeyCount()) {
                curNode = traverse(x, curNode);
                i = 0;
            }
        }

        for (int j = 0; j < curNode.getKeyCount(); j++) {
            str = str + curNode.getKey(j);

            if (j < curNode.getKeyCount() - 1)
                str = str + " ";
        }

        return str;
    }

    private Node traverse(int x, Node curNode) {
        int i = 0;

        while (i < curNode.getKeyCount())
            if (x < curNode.getKey(i))
                break;
            else
                i++;

        return curNode.getLink(i);
    }

    private void split(Node curNode) {
        int median = curNode.getKey(curNode.getKeyCount() / 2);
        Node newLeftNode = new Node(MAXKEYS);
        Node newRightNode = new Node(MAXKEYS);

        // Current node is root; else current node is inner node or leaf
        if (curNode == root) {
            Node newRootNode = new Node(MAXKEYS);
            newRootNode.addKey(median);
            root = newRootNode;

            newLeftNode.setParent(newRootNode);
            newRightNode.setParent(newRootNode);

            newRootNode.setLink(0, newLeftNode);
            newRootNode.setLink(1, newRightNode);

            newNodeSetup(curNode, newLeftNode, newRightNode);
        } else {
            curNode.getParent().addKey(median);
            newLeftNode.setParent(curNode.getParent());
            newRightNode.setParent(curNode.getParent());

            newNodeSetup(curNode, newLeftNode, newRightNode);

            // Move children right of median to the right
            int i = MAXKEYS;
            while (i >= 0) {
                curNode.getParent().setLink(i + 1, curNode.getParent().getLink(i));

                if (median == curNode.getParent().getKey(i)) {
                    curNode.getParent().setLink(i, newLeftNode);
                    curNode.getParent().setLink(i + 1, newRightNode);
                    break;
                }

                i--;
            }

            if (newLeftNode.getParent().getKeyCount() > MAXKEYS)
                split(newLeftNode.getParent());
        }

        // Wipe current node for garbage collection
        curNode = new Node(MAXKEYS);
    }

    private void newNodeSetup(Node curNode, Node newLeftNode, Node newRightNode) {
        int i;
        int j = 0;
        for (i = 0; i < curNode.getKeyCount(); i++) {
            if (i < curNode.getKeyCount() / 2) {
                newLeftNode.addKey(curNode.getKey(i));
                newLeftNode.setLink(i, curNode.getLink(i));
                if (newLeftNode.getLink(i) != null)
                    newLeftNode.getLink(i).setParent(newLeftNode);
            } else if (i > curNode.getKeyCount() / 2) {
                newRightNode.addKey(curNode.getKey(i));
                newRightNode.setLink(j, curNode.getLink(i));
                if (newRightNode.getLink(j) != null)
                    newRightNode.getLink(j).setParent(newRightNode);
                j++;
            } else {
                newLeftNode.setLink(i, curNode.getLink(i));
                if (newLeftNode.getLink(i) != null)
                    newLeftNode.getLink(i).setParent(newLeftNode);
            }
        }

        newRightNode.setLink(j, curNode.getLink(i));
        if (newRightNode.getLink(j) != null)
            newRightNode.getLink(j).setParent(newRightNode);
    }

    public static void main(String[] args)
{}
}


