/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;

/**
 *
 * @author danielalvarado
 */
public class Tuple<X, Y> { 
  public final X x; 
  public final Y y; 
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  }
  
  public static boolean ifLowest(List<Tuple> _tuples, int _value) {
      int lowestValue = 0;
      for (int i = 0; i < _tuples.size();i++) {
          if (i == 0) lowestValue = (int) _tuples.get(i).y;
          if ( lowestValue < (int) _tuples.get(i).y) lowestValue = (int) _tuples.get(i).y;
      }
      
    return _value < lowestValue;
  }
} 