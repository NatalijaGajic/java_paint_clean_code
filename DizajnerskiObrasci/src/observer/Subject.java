package observer;

public interface Subject {

	public void registerObserver(SelectedShapesObserver o);
    public void unregisterObserver(SelectedShapesObserver o);
    public void notifyObservers();
}
