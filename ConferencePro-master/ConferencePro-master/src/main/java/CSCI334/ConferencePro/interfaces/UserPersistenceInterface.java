package CSCI334.ConferencePro.interfaces;

import java.util.List;
import java.util.Optional;


public interface UserPersistenceInterface<T> {
    public Optional<T> create(T user);
    public List<T> getAll();
    public Optional<T> get(T user);
    public Optional<T> update(T user);
    public Optional<String> delete(T user);
}
