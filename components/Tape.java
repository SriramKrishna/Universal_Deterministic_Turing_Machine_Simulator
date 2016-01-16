/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author usr001
 */
public class Tape {
    private final List<SquareContent> tape;


    Tape(String initialTape) {
        tape = new LinkedList<>();
        for (int i = 0; i < initialTape.length(); i++) {
            tape.add(new SquareContent(initialTape.substring(i,i+1)));
        }
    }

    public boolean add(SquareContent e) {
        return tape.add(e);
    }

    public Stream<SquareContent> stream() {
        return tape.stream();
    }

    public void add(int index, SquareContent element) {
        tape.add(index, element);
    }

    public Tape() {
        tape = new LinkedList<>();
    }

    @Override
    public String toString() {
        return tape.stream().map(s -> s.toString()).collect(Collectors.joining());
    }

    public int size() {
        return tape.size();
    }

    public SquareContent remove(int index) {
        return tape.remove(index);
    }

    public SquareContent set(int index, SquareContent element) {
        return tape.set(index, element);
    }

    SquareContent get(int pos) {
        if (pos < 0 || pos >= tape.size()) {
            return SquareContent.getBLANK();
        } else {
            return tape.get(pos);
        }
    }

}
