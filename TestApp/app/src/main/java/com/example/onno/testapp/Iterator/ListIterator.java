/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.onno.testapp.Iterator;

import java.util.List;

public class ListIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index = -1;

    public ListIterator(List<T> list) {
        this.list = list;
    }

    public T getNext() {
    	index++;
    	if (index < list.size()) 
    		return list.get(index);     		   	
    	else    	
           return null; 
    }
    
    public Boolean hasNext() {
    	if (index + 1>= list.size()) {
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
