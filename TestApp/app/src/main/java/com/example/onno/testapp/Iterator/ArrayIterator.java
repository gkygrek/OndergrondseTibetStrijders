/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onno.testapp.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int index = -1;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    public T getNext() {
    	index++;
    	if (index < array.length) 
    		return array[index];     		   	
    	else    	
           return null;     	    
    }
    
    public Boolean hasNext() {    
    	if (index + 1 >= array.length) {
            return false;
        }
    	return true;
    }

    public Integer getIndex() {
        return index;
    }
    
    public void reset() {
    	index = -1;
    }
}
