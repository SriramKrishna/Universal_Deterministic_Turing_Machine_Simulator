/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import components.SquareContent;
import java.util.stream.Collectors;

/**
 *
 * @author usr001
 */
public class State {

    private final String id;
    private final List<Transition> transitions = new ArrayList<>();


    State(String id) {
        this.id = Objects.requireNonNull(id, "id");
    }
    
    /**
     * Determine if the current state is a Halting State
     * @return True if there is no transitions
     */
    public boolean isHaltingState(){
        return transitions.isEmpty();
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }


    /**
     * Add a transition to the state
     * @param transition
     */
    public void addTransition(Transition transition){
        transitions.add(transition);
    }
    
    Transition getTransition(SquareContent input){
        Optional<Transition> next = transitions.stream().filter(t->input.equals(t.getInput())).findFirst();
        if (next.isPresent())
            return next.get();
        throw new IllegalStateException(String.format("State %s has no transition for input %s",this.id,input));
    }

    @Override
    public String toString() {
        return transitions.stream().map(t->id+","+t.toString()).collect(Collectors.joining("\n"));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
