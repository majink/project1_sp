package org.example;

public interface ICRUD {
    public Object add();
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
    //데이터 한개를 조회할 때 쓰는 것.
}
