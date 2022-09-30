package source;
import java.util.LinkedList;

public class Lista<T> {
    private LinkedList<T> data;

    public Lista() {
        this.data = new LinkedList<>();
    }

    public boolean add(T newElement) {
        return this.data.add(newElement);
    }

    public T getElement(int position){
        return this.data.get(position);
    }

    public boolean remove(T element) {
        return this.data.remove(element);
    }

    public boolean contains(T element) {
        return this.data.contains(element);
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public T[] allElements(T[] array) {
        T[] allData = this.data.toArray(array);
        return allData;
    }
}
