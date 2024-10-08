package programacionproyecto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import programacionproyecto.BaseDatos;

public class PriorityQueue implements Iterable<Tareas> {
    private List<Tareas> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    public void add(Tareas element) {
        try{
            
            if (element == null) {
                throw new IllegalArgumentException("Element cannot be null");
            }

            if (isEmpty()) {
                elements.add(element);
                return;
            }

            for (int i = 0; i < elements.size(); ++i) {
                if (element.compareTo(elements.get(i)) >= 0) {
                    elements.add(i, element);
                    return;
                }
            }
            
            elements.add(element);
            
        }finally{
            BaseDatos.getBBDD().store(elements);
            BaseDatos.getBBDD().commit();
        }
    }

    public Tareas poll() {
        if (isEmpty()) {
            return null;
        }
        return elements.remove(0);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    public boolean contains(Tareas e) {
        return elements.contains(e);
    }

    public boolean removeElement(Tareas e) {
        try{
            boolean q = elements.remove(e);
            return q;
        }finally{
            BaseDatos.getBBDD().store(elements);
            BaseDatos.getBBDD().commit();
        }
    }

    public PriorityQueue joinLists(PriorityQueue p) {
        PriorityQueue nP = new PriorityQueue();
        for (Tareas e : this) {
            nP.add(e);
        }
        for (Tareas e : p) {
            nP.add(e);
        }
        return nP;
    }
    
    public void reorder() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < elements.size() - 1; i++) {
                if (elements.get(i).compareTo(elements.get(i + 1)) < 0) {
                    Tareas temp = elements.get(i);
                    elements.set(i, elements.get(i + 1));
                    elements.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
        BaseDatos.getBBDD().store(elements);
        BaseDatos.getBBDD().commit();
    }


    @Override
    public String toString() {
        StringBuilder build = new StringBuilder("Ementos:\n");
        for (Tareas t : elements) {
            build.append("\t"+t+"\n");
        }
        return build.toString();
    }

    @Override
    public Iterator<Tareas> iterator() {
        return elements.iterator();
    }
}
