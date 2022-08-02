package observer;

public interface ShapesSubject {
	public void registerObserver(ShapesObserver o);
    public void unregisterObserver(ShapesObserver o);
    public void notifyObservers();
}
