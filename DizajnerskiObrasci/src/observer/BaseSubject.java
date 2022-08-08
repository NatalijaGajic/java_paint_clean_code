package observer;

public interface BaseSubject<T> {
	public void registerObserver(T o);
    public void unregisterObserver(T o);
    public void notifyObservers();
}