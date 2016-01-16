/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Objects;

/**
 *
 * @author usr001
 */
public class SquareContent {

    private static final String SCAPE_SECUENCE_PREFIX = "\\";
    private static final String BLANK_CHARACTER = "B";
    private static SquareContent blank;

    private static SquareContent IllegalArgumentException(String tapeCharacter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void setBlank(String emptySimbol) {
         blank = new SquareContent(emptySimbol);
    }

    /**
     * @return the blank
     */
    public static SquareContent getBLANK() {
        return blank;
    }

    private String content;

    private SquareContent() {
    }

    SquareContent(String symbol) {
        content = Objects.requireNonNull(symbol);
    }

    /**
     * Get the content of content
     *
     * @return the content of content
     */
    public String getValue() {
        return content;
    }

    /**
     * Set the content of content
     *
     * @param value new content of content
     */
    public void setValue(String value) {
        this.content = Objects.requireNonNull(value);
    }

    @Override
    public String toString() {
        return content;
    }

    /**
     * Convert Scape input string to a tape alfable character
     * @param tapeCharacter
     * @param empty
     * @return 
     */
    public static SquareContent instanceOf(String tapeCharacter,String empty) {
        Objects.requireNonNull(tapeCharacter, "tapeCharacter");
        Objects.requireNonNull(empty, "empty");
        if (tapeCharacter.equals(empty))
            return getBLANK();
        String character = tapeCharacter.substring(0, 1);
        return new SquareContent(character);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this==obj)
            return true;
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SquareContent other = (SquareContent) obj;
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }
    
    
    
}
