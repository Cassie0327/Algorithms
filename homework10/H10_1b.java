package homework10;


public class H10_1b {
    public abstract class AbstractArrayBasedHeap<E> {
        protected E[] heap = null;
        protected static final int MIN_CAPACITY = 15;//min # elements in heap
        protected int capacity = MIN_CAPACITY;
        protected int heapSize = 0;//# of elements stored in heap


        protected E[] getHeap() {
            if (heap == null) {
                Object[] o = new Object[MIN_CAPACITY];
                heap = (E[]) o;//need to cast since you can't initialize generic arrays
            }
            return heap;
        }

        protected void setHeap(E[] heap) {
            this.heap = heap;
        }

        public int getHeapSize() {
            return heapSize;
        }

        public void setHeapSize(int heapSize) {
            this.heapSize = heapSize;
        }

        public int getCapacity() {
            return capacity;
        }
        protected void updateCapacity() {
            double percentFilled = (double) heapSize / capacity;
            if (percentFilled > .75) {
                increaseCapacity();
            } else if (percentFilled < .25 && capacity > 2 * MIN_CAPACITY) {
                decreaseCapacity();
            }
        }

        /**
         * Creates a new array that is double the capacity of the current heap array, copies the
         * existing array's values into it, and then sets the new array as the heap.
         */
        protected void increaseCapacity() {
            Object[] o = new Object[getCapacity() * 2];
            E[] newHeap = (E[]) o;
            for (int i = 0; i < getHeapSize(); i++) {
                newHeap[i] = getHeap()[i];
            }
            setHeap(newHeap);
        }



        protected void decreaseCapacity() {
            Object[] o = new Object[getCapacity() / 2];
            E[] newHeap = (E[]) o;
            for (int i = 0; i < getHeapSize(); i++) {
                newHeap[i] = getHeap()[i];
            }
            setHeap(newHeap);
        }

        protected int calculateParentIndex(int childIndex) {
            int parentIndex = -1; //assumes childIndex is the root
            if (childIndex > 0) {
                parentIndex = (childIndex - 1) / 2;
            }
            return parentIndex;
        }

        /**
         * Calculates the left child index for the parent index passed in. This is done
         * by multiplying the parent index by 2 and adding 1.
         *
         * @return
         */
        protected int calculateLeftChildIndex(int parentIndex) {
            return parentIndex * 2 + 1;
        }

        /**
         * Calculates the left child index for the parent index passed in. This is done
         * by multiplying the parent index by 2 and adding 2.
         *
         * @return
         */
        protected int calculateRightChildIndex(int parentIndex) {
            return parentIndex * 2 + 2;
        }

        /**
         * Swaps the parent and child elements in the heap array.
         *
         * @param parentIndex
         * @param childIndex
         */
        protected void swap(int parentIndex, int childIndex) {
            E temp = getHeap()[parentIndex];
            getHeap()[parentIndex] = getHeap()[childIndex];
            getHeap()[childIndex] = temp;
        }

    }

    public interface MinHeapInterface<E> {
        /**
         * Removes the maximum element from the heap and returns it.
         *
         * @return
         */
        public E removeMin();

        /**
         * Adds the element passed in to the heap and puts it in its proper location in
         * the heap.
         *
         * @param element
         */
        public void addElement(E element);

        /**
         * Swaps the element at the index position passed in with the greater of its two
         * children if one of the children have a greater value.
         *
         * @param index
         */
        public void downMinHeapify(int index);

        /**
         * Swaps the element at the index position passed in with its parent if the value
         * of the element stored at the index is larger than its parent.
         *
         * @param index
         */
        public void upMaxHeapify(int index);
    }

    public class MaxHeap<E> extends AbstractArrayBasedHeap<E> implements MinHeapInterface<E> {
        @Override

        public E removeMin() {
            E element = null;
            if (getHeapSize() > 0) {
                element = getHeap()[0];
                getHeap()[0] = getHeap()[getHeapSize() - 1];//bring last elemetn to top

                downMinHeapify(0);
                setHeapSize(getHeapSize() - 1);
            }
            return element;
        }

        @Override
        public void addElement(E element) {
            updateCapacity();
            int nextIndex = getHeapSize();
            getHeap()[nextIndex] = element;
            upMaxHeapify(nextIndex);
            setHeapSize(getHeapSize() + 1);
        }

        @Override
        public void downMinHeapify(int index) {
            int leftChildIndex = calculateLeftChildIndex(index);
            int rightChildIndex = calculateRightChildIndex(index);
            Comparable<E> leftChild = null;
            Comparable<E> rightChild = null;
            Comparable<E> node = (Comparable<E>) getHeap()[index];
            if (leftChildIndex < getHeapSize()) {

                leftChild = (Comparable<E>) getHeap()[leftChildIndex];

            }
            if (rightChildIndex < getHeapSize()) {
                rightChild = (Comparable<E>) getHeap()[rightChildIndex];

            }
            int testIndex = index;
            if (leftChild != null && rightChild != null) {
                if (leftChild.compareTo(getHeap()[rightChildIndex]) > 0) {
                    testIndex = rightChildIndex;
                } else {
                    testIndex = leftChildIndex;
                }
            } else if (leftChild != null) {
                testIndex = leftChildIndex;
            } else if (rightChild != null) {
                testIndex = rightChildIndex;
            }
            if (testIndex != index) {
                swap(index, testIndex);
                downMinHeapify(testIndex);
            }
        }

        @Override
        public void upMaxHeapify(int index) {
            if (index > 0) {
                int parentIndex = calculateParentIndex(index);
                Comparable<E> parentValue = (Comparable<E>) (getHeap()[parentIndex]);
                E childValue = getHeap()[index];
                if (parentValue.compareTo(childValue) > 0) {
                    swap(parentIndex, index);
                    upMaxHeapify(parentIndex);
                }
            }
        }

        @Override
        public String toString() {
            StringBuffer buf = new StringBuffer();
            buf.append("[");
            for (int i = 0; i < getHeapSize(); i++) {
                buf.append(getHeap()[i]);
                if (i < getHeapSize() - 1) {
                    buf.append(",");
                }
            }
            buf.append("]");
            return buf.toString();
        }
    }

    public void runTests(){
        MaxHeap<String> heap = new MaxHeap<String>();
        heap.addElement("John");
        heap.addElement("Paul");
        heap.addElement("George");
        heap.addElement("Ringo");
        heap.addElement("Elmer");
        heap.addElement("Zorro");
        System.out.println(heap);
        heap.removeMin();
        System.out.println("------------------Remove min--------------");
        System.out.println(heap);
    }


    public static void main(String[] args) {
        H10_1b h=new H10_1b();
        h.runTests();

    }

}