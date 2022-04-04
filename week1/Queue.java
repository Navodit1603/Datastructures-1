import java.util.Iterator;

class Queue<T> implements Iterable<T>{
    LinkedList<T> head, tail;

    public void add(T data){
        LinkedList<T> t = new LinkedList<>(data, null);
        if(this.head == null){
            this.head = this.tail = t ; //for the first number in the queue. Changing it to the head
        }
        else{
            this.tail.setNextNode(t);
            t.setPrevNode(this.tail); //setting last node as tail
            this.tail = t;
        }
    }

    public void delete() {
        LinkedList<T> n = this.head.getNext();
        if (n != null) {
            n.setPrevNode(null); // deleting the queue
            this.head = n;
        }else{
            this.head= this.tail= null;
        }
    }

    public LinkedList<T> getHead()
    {
        return this.head;
    }

    public LinkedList<T> getTail()
    {
        return this.tail;
    }

    /**
     *  Returns the iterator object.
     *
     * @return  this, instance of object
     */
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator<>(this);
    }

    public static void main(String[] args){
        // Create iterable Queue of Words
        Object[] words = new String[] { "seven", "slimy", "snakes", "sallying", "slowly", "slithered", "southward"};
        QueueManager qWords = new QueueManager("Words", words );
        qWords.printQueue();
        qWords.deleteList();
        System.out.println("");



    }

}