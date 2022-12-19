package kanta;

/**
 * @author Onni
 * @version 19.12.2022
 *
 */
public interface Tietue {

    /**
     * @return Kent�t
     */
    public abstract int getKenttia();
    
    
    
    /**
     * @return ensimm�inen kentt�
     */
    public abstract int ekaKentta();
    
    
    
    /**
     * @param k a 
     * @return b
     */
    public abstract String getKysymys(int k);
    
    
    
    
    /**
     * @param k a 
     * @return b
     */
    public abstract String anna(int k);
    
    
    
    
    /**
     * @param k a 
     * @param s b 
     * @return c
     */
    public abstract String aseta(int k, String s);
    
    
    
    /**
     * @return a 
     * @throws CloneNotSupportedException b
     */
    public abstract Tietue clone() throws CloneNotSupportedException;
}
