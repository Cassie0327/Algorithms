package homework7;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 Name: Qian Cai
 * NU ID:001389278

 * Created by Qian Cai on 2019/10/25.
 */
public class H7_9c <K,V>{

    private static class SearchResult
    {
        private boolean result;
        private int index;

        public SearchResult(boolean result, int index)
        {
            this.result = result;
            this.index = index;
        }

        public boolean getResult()
        {
            return result;
        }

        public int getIndex()
        {
            return index;
        }
    }
    private static class BTreeNode
    {
        private List<Character> keys;
        private List<BTreeNode> children;
        private boolean leaf;

        public BTreeNode()
        {
            keys = new ArrayList<Character>();
            children = new ArrayList<BTreeNode>();
            leaf = false;
        }

        public boolean isLeaf()
        {
            return leaf;
        }

        public void setLeaf(boolean leaf)
        {
            this.leaf = leaf;
        }

        public int size()
        {
            return keys.size();
        }

        public SearchResult searchKey(Character key)
        {
            int l = 0;
            int h = keys.size() - 1;
            int mid = 0;
            while(l <= h)
            {
                mid = (l + h) / 2;
                if(keys.get(mid) == key)
                    break;
                else if(keys.get(mid) > key)
                    h = mid - 1;
                else
                    l = mid + 1;
            }
            boolean result = false;
            int index = 0;
            if(l <= h)
            {
                result = true;
                index = mid;
            }
            else
            {
                result = false;
                index = l;
            }
            return new SearchResult(result, index);
        }

        public void addKey(Character key)
        {
            keys.add(key);
        }


        public void removeKey(int index)
        {
            keys.remove(index);
        }

        public Character keyAt(int index)
        {
            return keys.get(index);
        }

        public void insertKey(Character key)
        {
            SearchResult result = searchKey(key);
            insertKey(key, result.getIndex());
        }

        public void insertKey(Character key, int index)
        {
            List<Character> newKeys = new ArrayList<Character>();
            int i = 0;
            for(; i < index; ++ i)
                newKeys.add(keys.get(i));
            newKeys.add(key);
            for(; i < keys.size(); ++ i)
                newKeys.add(keys.get(i));
            keys = newKeys;
        }


        public BTreeNode childAt(int index)
        {
            if(isLeaf())
                throw new UnsupportedOperationException("Leaf node doesn't have children.");
            return children.get(index);
        }
        public void addChild(BTreeNode child)
        {
            children.add(child);
        }

        public void removeChild(int index)
        {
            children.remove(index);
        }

        public void insertChild(BTreeNode child, int index)
        {
            List<BTreeNode> newChildren = new ArrayList<BTreeNode>();
            int i = 0;
            for(; i < index; ++ i)
                newChildren.add(children.get(i));
            newChildren.add(child);
            for(; i < children.size(); ++ i)
                newChildren.add(children.get(i));
            children = newChildren;
        }
    }

    private static final int DEFAULT_T = 2;

    private BTreeNode root;
    private int t = DEFAULT_T;
    private int minKeySize = t - 1;
    private int maxKeySize = 2*t - 1;

    public H7_9c()
    {
        root = new BTreeNode();
        root.setLeaf(true);
    }

    public H7_9c(int t)
    {
        this();
        this.t = t;
        minKeySize = t - 1;
        maxKeySize = 2*t - 1;
    }

    public int search(Character key)
    {
        return search(root, key);
    }

    private static int search(BTreeNode node, Character key)
    {
        SearchResult result = node.searchKey(key);
        if(result.getResult())
            return result.getIndex();
        else
        {
            if(node.isLeaf())
                return -1;
            else
                search(node.childAt(result.getIndex()), key);

        }
        return -1;
    }


    private void splitNode(BTreeNode parentNode, BTreeNode childNode, int index)
    {
        assert childNode.size() == maxKeySize;

        BTreeNode siblingNode = new BTreeNode();
        siblingNode.setLeaf(childNode.isLeaf());
        for(int i = 0; i < minKeySize; ++ i)
            siblingNode.addKey(childNode.keyAt(t + i));
        Character key = childNode.keyAt(t - 1);
        for(int i = maxKeySize - 1; i >= t - 1; -- i)
            childNode.removeKey(i);
        if(!childNode.isLeaf())
        {
            for(int i = 0; i < minKeySize + 1; ++ i)
                siblingNode.addChild(childNode.childAt(t + i));
            for(int i = maxKeySize; i >= t; -- i)
                childNode.removeChild(i);
        }
        parentNode.insertKey(key, index);
        parentNode.insertChild(siblingNode, index + 1);
    }

    private void insertNotFull(BTreeNode node, Character key)
    {
        assert node.size() < maxKeySize;

        if(node.isLeaf())
            node.insertKey(key);
        else
        {
            SearchResult result = node.searchKey(key);
            BTreeNode childNode = node.childAt(result.getIndex());
            if(childNode.size() == 2*t - 1)
            {
                splitNode(node, childNode, result.getIndex());

                if(key > node.keyAt(result.getIndex()))
                    childNode = node.childAt(result.getIndex() + 1);
            }
            insertNotFull(childNode, key);
        }
    }
    public void insert(Character key)
    {
        if(root.size() == maxKeySize)
        {
            BTreeNode newRoot = new BTreeNode();
            newRoot.setLeaf(false);
            newRoot.addChild(root);
            splitNode(newRoot, root, 0);
            root = newRoot;
        }
        insertNotFull(root, key);
    }

    public void delete(Character key)
    {
        delete(root, key);
    }


    public void delete(BTreeNode node, Character key)
    {
        assert node.size() >= t || node == root;

        SearchResult result = node.searchKey(key);

        if(result.getResult())
        {
            if(node.isLeaf())
                node.removeKey(result.getIndex());
            else
            {
                BTreeNode leftChildNode = node.childAt(result.getIndex());
                if(leftChildNode.size() >= t)
                {
                    node.removeKey(result.getIndex());
                    node.insertKey(leftChildNode.keyAt(leftChildNode.size() - 1), result.getIndex());
                    delete(leftChildNode, leftChildNode.keyAt(leftChildNode.size() - 1));
                }
                else
                {
                    BTreeNode rightChildNode = node.childAt(result.getIndex() + 1);
                    if(rightChildNode.size() >= t)
                    {
                        node.removeKey(result.getIndex());
                        node.insertKey(rightChildNode.keyAt(0), result.getIndex());
                        delete(rightChildNode, rightChildNode.keyAt(0));
                    }
                    else
                    {
                        node.removeKey(result.getIndex());
                        node.removeChild(result.getIndex() + 1);
                        leftChildNode.addKey(key);
                        for(int i = 0; i < rightChildNode.size(); ++ i)
                            leftChildNode.addKey(rightChildNode.keyAt(i));
                        if(!rightChildNode.isLeaf())
                        {
                            for(int i = 0; i <= rightChildNode.size(); ++ i)
                                leftChildNode.addChild(rightChildNode.childAt(i));
                        }
                        delete(leftChildNode, key);
                    }
                }
            }
        }
        else
        {
            if(node.isLeaf())
            {
                return;
            }
            BTreeNode childNode = node.childAt(result.getIndex());
            if(childNode.size() >= t)
                delete(childNode, key);
            else
            {
                BTreeNode siblingNode = null;
                int siblingIndex = -1;
                if(result.getIndex() < node.size())
                {
                    if(node.childAt(result.getIndex() + 1).size() >= t)
                    {
                        siblingNode = node.childAt(result.getIndex() + 1);
                        siblingIndex = result.getIndex() + 1;
                    }
                }
                if(siblingNode == null)
                {
                    if(result.getIndex() > 0)
                    {
                        if(node.childAt(result.getIndex() - 1).size() >= t)
                        {
                            siblingNode = node.childAt(result.getIndex() - 1);
                            siblingIndex = result.getIndex() - 1;
                        }
                    }
                }
                if(siblingNode != null)
                {
                    if(siblingIndex < result.getIndex())
                    {
                        childNode.insertKey(node.keyAt(siblingIndex), 0);
                        node.removeKey(siblingIndex);
                        node.insertKey(siblingNode.keyAt(siblingNode.size() - 1), siblingIndex);
                        siblingNode.removeKey(siblingNode.size() - 1);
                        if(!siblingNode.isLeaf())
                        {
                            childNode.insertChild(siblingNode.childAt(siblingNode.size()), 0);
                            siblingNode.removeChild(siblingNode.size());
                        }
                    }
                    else
                    {
                        childNode.insertKey(node.keyAt(result.getIndex()), childNode.size() - 1);
                        node.removeKey(result.getIndex());
                        node.insertKey(siblingNode.keyAt(0), result.getIndex());
                        siblingNode.removeKey(0);

                        if(!siblingNode.isLeaf())
                        {
                            childNode.addChild(siblingNode.childAt(0));
                            siblingNode.removeChild(0);
                        }
                    }
                    delete(childNode, key);
                }
                else
                {
                    if(result.getIndex() < node.size())
                    {
                        BTreeNode rightSiblingNode = node.childAt(result.getIndex() + 1);
                        childNode.addKey(node.keyAt(result.getIndex()));
                        node.removeKey(result.getIndex());
                        node.removeChild(result.getIndex() + 1);
                        for(int i = 0; i < rightSiblingNode.size(); ++ i)
                            childNode.addKey(rightSiblingNode.keyAt(i));
                        if(!rightSiblingNode.isLeaf())
                        {
                            for(int i = 0; i <= rightSiblingNode.size(); ++ i)
                                childNode.addChild(rightSiblingNode.childAt(i));
                        }
                    }
                    else
                    {
                        BTreeNode leftSiblingNode = node.childAt(result.getIndex() - 1);
                        childNode.addKey(node.keyAt(result.getIndex() - 1));
                        node.removeKey(result.getIndex() - 1);
                        node.removeChild(result.getIndex() - 1);
                        for(int i = leftSiblingNode.size() - 1; i >= 0; -- i)
                            childNode.insertKey(leftSiblingNode.keyAt(i), 0);
                        if(!leftSiblingNode.isLeaf())
                        {
                            for(int i = leftSiblingNode.size(); i >= 0; -- i)
                                childNode.insertChild(leftSiblingNode.childAt(i), 0);
                        }
                    }
                    if(node == root && node.size() == 0)
                        root = childNode;
                    delete(childNode, key);
                }
            }
        }
    }
    public void output()
    {
        Queue<BTreeNode> queue = new LinkedList<BTreeNode>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            BTreeNode node = queue.poll();
            for(int i = 0; i < node.size(); ++ i)
                System.out.print(node.keyAt(i) + " ");
            System.out.println();
            if(!node.isLeaf())
            {
                for(int i = 0; i <= node.size(); ++ i)
                    queue.offer(node.childAt(i));
            }
        }
    }


    public static void main(String[] args)
    {
        H7_9c<Character, Byte[]> btree = new H7_9c<Character, Byte[]>();
       btree.insert('A');
        btree.insert('G');
        btree.insert('F');
        btree.insert('B');
        btree.insert('K');
        btree.insert('D');
        btree.insert('H');
        btree.insert('M');
        btree.insert('J');
        btree.insert('E');
        btree.insert('S');
        btree.insert('I');
        btree.insert('R');
        btree.insert('X');
        btree.insert('C');
        btree.insert('L');
        btree.insert('N');
        btree.insert('T');
        btree.insert('U');
        btree.insert('P');
        System.out.println("----------------------");
        btree.output();


    }
}