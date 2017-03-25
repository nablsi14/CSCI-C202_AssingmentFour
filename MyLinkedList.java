/**
 * MyLinkedList.java
 * Lab6
 * Purpose: Implenemt methods for a linked list
 * @author Nathaniel Sigafoos
 * @version 1.0.1 3/21/17
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /** Create a default list */
    public MyLinkedList () {}
    /** 
     * Create a list from an array of objects
     * @param objects The objects being used to create this linked list
     */
    public MyLinkedList (E[] objects) {
        super(objects);
    }
    /** 
     * Return the head element in the list.
     * @return The first elemnt in the list. null if not defined.
     */
    public E getFirst () {
        if (size == 0)
            return null;
        return head.element;
    }
    /** 
     * Return the last element in the list
     * @return The last element in the list. null if not defined.
     */
    public E getLast () {
        if (size == 0)
            return null;
        return tail.element;
    }
    /** 
     * Add an element to the beginning of the list 
     * @param e The element being added.
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = head;
    }
    /** 
     * Add an element to the end of the list.
     * @param e The elment being added to the end of the list.
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }
    /**
     * Add a new element at the specified index in this list
     * The index of the head element is 0 
     * @param index The index the element is being inserted at.
     * @param e The element being added.
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }
    /** 
     * Remove the head node and
     *  return the object that is contained in the removed node. 
     * @return The first element in the list.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }
    /** 
     * Remove the last node and
     * return the object that is contained in the removed node. 
     * @return The last element in the list.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }
    /** 
     * Removes the element at the specified position in this list.
     * Returns the element that was removed from the list.
     * @param index The index of te element being removed.
     * @return The element that was at the given index.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }
    /** 
     * Override toString() to return elements in the list 
     * @return A string representation of this linked list.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }
    /** 
     * Clears this list 
     */
    public void clear() {
        head = tail = null;
    }
    /**
     * Returns whether or not an element is in the linked list
     * @param e The element being looked for.
     * @return Whether or not the element being looked for is in the list
     */
    @Override
    public boolean contains(E e) {
        Node<E> current = head;
        while (current == null) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }
        return false;
    }
    /**
     * Returns an element at a given index.
     * @param index The index of the element wanted.
     * @return The element at the given index. 
     *         Returns null if the index is not defined.
     */
    @Override
    public E get(int index) {
        if (index < 0) return null;
        Node<E> temp = head;
        for (int i = 0; i <= index; i++) {
            if (temp == null) return null;
            temp = temp.next;
        }
        return temp.element;
    }
    /**
     * Returns the first index of a given element in this linked list
     * @param e The element being looked for.
     * @return The index of the element given. -1 if not in list.
     */
    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i = 0; current != null; i++) {
            if (current.element.equals(e))
                return i;
            current = current.next;
        }
        return -1;
    }
    /**
     * Returns the last index of a given element in this linked list.
     * @param e The element being looked for.
     * @return The index of the element. -1 if not in list.
     */
    @Override
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int lastIndex = -1;
        for (int i = 0; current != null; i++) {
            if (current.element.equals(e))
                lastIndex = i;
            current = current.next;
        }
        return lastIndex;
    }
    /**
     * Sets the value of an element at a given position.
     * @param index The index of the element being set.
     * @param e The value the element is being set to.
     * @return The old value at the index given. 
     *          null if index is not defined on the list.
     */
    @Override
    public E set (int index, E e) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }
    /**
     * Private class used by MyLinkedList
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        /**
         * Constructor for class
         * @param element the "data" of this Node.
         */
        public Node(E element) {
            this.element = element;
        }
    }
}