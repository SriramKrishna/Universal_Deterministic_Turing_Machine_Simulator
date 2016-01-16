/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import stateMachine.FiniteStateMachine;
import stateMachine.State;
import stateMachine.StateFactory;
import stateMachine.Transition;

/**
 *
 * @author usr001
 */
public class MachineBuilder {

    public static Machine build(InputStream is, String initialTape ) throws IOException {
        Scanner scn = new Scanner(is);

        // skip al comment lines
        while (scn.hasNext(COMMENT)) {
            scn.nextLine();
        }

        // Read valid states
        List<String> validStates = Arrays.asList(scn.nextLine().trim().split(","));
        List<String> alphabetInput = Arrays.asList(scn.nextLine().trim().split(","));
        List<String> alphabetTape = Arrays.asList(scn.nextLine().trim().split(","));
        String emptySimbol = scn.nextLine().trim();
        String aceptingState = scn.nextLine().trim();
        String startState = scn.nextLine().trim();
        // Read the initial tape and initial possition
        int initialTapePosition = Integer.valueOf(scn.nextLine())-1;
        
        ReadWriteHead rwHead = new ReadWriteHead(new Tape(initialTape), initialTapePosition);
        
        final State initialState = StateFactory.getInstance(startState);
        final State endingState = StateFactory.getInstance(aceptingState);
        FiniteStateMachine stm = new FiniteStateMachine(initialState,endingState);
        SquareContent.setBlank(emptySimbol);
        // read all states
        // id,input,nextState
        while (scn.hasNext()) {
            final String nextLine = scn.nextLine().replaceAll("[()]", "").replaceAll("->", ",");
            if (nextLine.isEmpty() || nextLine.startsWith(COMMENT))
                continue;
            // State Scanner
            final Scanner sScn = new Scanner(nextLine);
            sScn.useDelimiter(DELIMITER);
            String sq0 = sScn.next();
            if (!validStates.contains(sq0)){
                throw new RuntimeException(nextLine+":"+sq0+ "is not a valid state");
            }
            String a = sScn.next();
            if (!alphabetTape.contains(a)){
                throw new RuntimeException(nextLine+":"+a+ "is not a input alphabet input");
            }
            String sq1 = sScn.next();
            if (!validStates.contains(sq1)){
                throw new RuntimeException(nextLine+":"+sq1+ "is not a valid state");
            }
            String b = sScn.next();
            if (!alphabetTape.contains(b)){
                throw new RuntimeException(nextLine+":"+b+ "is not a input alphabet tape");
            }
            String sA = sScn.next();
            if (!"LR".contains(sA)){
                throw new RuntimeException(nextLine+":"+sA+ "is not a valid action");
            }
            

            State q0 = StateFactory.getInstance(sq0);
            State q1 = StateFactory.getInstance(sq1);

            q0.addTransition(new Transition(SquareContent.instanceOf(a,emptySimbol), q1, SquareContent.instanceOf(b,emptySimbol), sA));
            stm.addState(q0);
        }
        
        
        return new Machine(stm,rwHead);
    }

    private static final String COMMENT = "#";
    private static final String DELIMITER = ",";
}
