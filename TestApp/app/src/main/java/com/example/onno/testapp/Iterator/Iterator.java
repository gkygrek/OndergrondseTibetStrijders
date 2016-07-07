package com.example.onno.testapp.Iterator;

public interface Iterator<T> {
  T getNext();
  Boolean hasNext();
  void reset();
  Integer getIndex();
}
