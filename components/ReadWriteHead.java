/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author usr001
 */
public class ReadWriteHead {

    public static final String ACTION_RIGHT = "R";
    public static final String ACTION_LEFT = "L";

    private Tape tape;
    private int head = 0;
    private SquareContent currentContent = null;
    private final int initialTapePosition;
    private final String initialTape;

    /**
     * Construct a Tape containing an initial imput stream.
     *
     * @param tape the value of tape
     * @param initialTapePosition zero based position. if &lt; 0 fill left with
     * BLANK
     */
    ReadWriteHead(Tape tape, int initialTapePosition) {

        this.initialTape = tape.toString();
        this.tape = tape;
        this.head =this.initialTapePosition= initialTapePosition;
        take();
        for (int i = 0; i < initialTapePosition; i++) {
            move(ACTION_RIGHT);
        }
    }

    /**
     * Move the read one possition to the right
     */
    private void right() {
        head++;
    }

    private void left() {
        head--;
    }

    /**
     * Move the head
     *
     * @param action "&lt;" or "&gt;"
     */
    public void move(String action) {
        if (action.equals(ACTION_RIGHT)) {
            right();
        } else if (action.equals(ACTION_LEFT)) {
            left();
        } else {
            throw new IllegalArgumentException("action");
        }
        take();
    }

    /**
     * Write a character from input alphabet to the tape
     *
     * @param symbol
     */
    public void write(SquareContent symbol) {
        if (symbol != SquareContent.getBLANK()) {
            //Fill the left tape if nescesary
            for (; head < 0; head++) {
                tape.add(0, components.SquareContent.getBLANK());
            }
            //Fill the right tape if nescesary
            while (head >= tape.size()) {
                tape.add(components.SquareContent.getBLANK());
            }
            tape.set(head, symbol);
        } else if (head == 0) {
            tape.remove(0);
        } else if (head == tape.size() - 1) {
            tape.remove(--head);
        }
        take();
        if (false) {
            System.out.print(' ');
            System.out.println(tape);
            for (int i = -1; i < head; i++) {
                System.out.print(' ');
            }
            System.out.println('^');
        }
    }

    /**
     * Read the tape and return Tape Alfapeth
     *
     * @return
     */
    public SquareContent read() {
        take();
        return currentContent;
    }

    private void take() {
        currentContent = tape.get(head);
    }

    Tape getTape() {
        return tape;
    }

    public int getInitialTapePosition() {
        return initialTapePosition;
    }

    public String getInitialTape() {
        return initialTape;
    }

}
