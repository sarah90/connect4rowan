/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bp2070
 */
public class MiniMax {
    final int NUM_COLS = 7;

    //returns minimum value of children
    private double expandMin(int currentNode){
        double min = Double.MAX_VALUE;
        /* check base cases
         * depth
         * column full
         * win
         * leaf
         */ 
        
        for(int i = 0; i < NUM_COLS; i++){
            
            double temp = expandMax(i);
            if (temp < min)
                min = temp;
            
        }
        
        return min;
        
    }
    
    //returns maximum value of children
    private double expandMax(int currentNode){
        double max = -1;
        /* check base cases
         * depth
         * column full
         * win
         * leaf
         */
        
        for(int i = 0; i < NUM_COLS; i++){
            double temp = expandMin(i);
            if( temp > max)
                max = temp;
        }
        
        return max;
        
    }
}
