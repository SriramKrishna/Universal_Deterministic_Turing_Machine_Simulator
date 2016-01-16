/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateMachine;

import java.util.Objects;
import components.SquareContent;

/**
 *
 * @author usr001
 */
public class Transition implements TransitionResult {

    private final SquareContent input;
    private final State nextState;
    private final SquareContent output;
    private final String action;

    public SquareContent getInput() {
        return input;
    }

    public State getNextState() {
        return nextState;
    }

    @Override
    public SquareContent getOutput() {
        return output;
    }

    @Override
    public String getAction() {
        return action;
    }

    /**
     * Add a transition:
     * 
     * @param input input token
     * @param nextState next state
     * @param output write token
     * @param action Action
     */
    public Transition(SquareContent input, State nextState, SquareContent output, String action) {
        
        this.input = Objects.requireNonNull(input, INPUT);
        this.nextState = Objects.requireNonNull(nextState, NEXT_STATE);
        this.output = Objects.requireNonNull(output, OUTPUT);
        this.action = Objects.requireNonNull(action, ACTION);
        
        if (action.length()!=1 || ACTIONS_ID.indexOf(action.charAt(0))==-1)
            throw new IllegalArgumentException(ACTION);
    }

    @Override
    public String toString() {
        return  input + "->" + nextState.getId() + "," + output + "," + action;
    }
    
    
    
    private static final String ACTIONS_ID = "LR";
    private static final String ACTION = "action";
    private static final String OUTPUT = "output";
    private static final String NEXT_STATE = "nextState";
    private static final String INPUT = "input";
}
