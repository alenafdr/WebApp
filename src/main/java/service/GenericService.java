package service;

import java.util.List;

public interface GenericService <T, ID, SUB>{
    public abstract boolean save(T t, SUB[] subs);
    public abstract T getById(ID id);
    public abstract List<T> getAll();
    public abstract boolean update(ID id, T t, SUB[] subs);
    public abstract boolean remove(T t);
}
