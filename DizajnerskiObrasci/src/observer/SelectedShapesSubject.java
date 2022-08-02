package observer;


public interface SelectedShapesSubject {

	public void registerObserver(SelectedShapesObserver o);
    public void unregisterObserver(SelectedShapesObserver o);
    public void notifyObservers();
}
