/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import components.SquareContent;
import java.util.stream.Collectors;

/**
 *
 * @author usr001
 */
public class FiniteStateMachine {

    private final Map<String, State> states = new HashMap<>();
    private State q;
    private final State q0;
    private final State qn;

    public FiniteStateMachine(State q0, State qn) {
        this.q0 = this.q = Objects.requireNonNull(q0, "start state");
        this.qn = Objects.requireNonNull(qn, "accepting state");
    }

    public void addState(State qi) {
        Objects.requireNonNull(qi);
        if (!states.containsKey(qi.getId())) {
            states.put(qi.getId(), qi);
        }
    }

    public State getCurrentState() {
        return q;
    }

    public boolean isAccepted() {
        return qn.equals(q);
    }

    public TransitionResult transit(SquareContent input) {
        State qi = q;
        Transition t = q.getTransition(Objects.requireNonNull(input, "input"));
        q = t.getNextState();
        //System.out.println("   " + qi.getId() + "," + input + "->" + q.getId());
        return t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Start state: ").append(q0.getId()).append("\n");
        sb.append(states.keySet().stream().map(k -> states.get(k).toString()).collect(Collectors.joining("\n")));
        sb.append("\nAccepting state:").append(qn.getId()).append("\n");
        return sb.toString();
    }

    public State getQ0() {
        return q0;
    }
    
    

}
