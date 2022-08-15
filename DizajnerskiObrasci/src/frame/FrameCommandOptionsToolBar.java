package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controllers.ButtonsController;
import controllers.CommandController;
import observer.CommandsHandlerObserver;
import observer.LogReaderObserver;

public class FrameCommandOptionsToolBar extends JToolBar implements CommandsHandlerObserver, LogReaderObserver{

	private static final long serialVersionUID = 1L;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnExecuteLog;
	private CommandController commandController;
	private ButtonsController buttonsController;
	
	public FrameCommandOptionsToolBar() {
		setOrientation(SwingConstants.VERTICAL);
		createButtons();
		addRedoButtonListener();
		addUndoButtonListener();
		addExecuteLogButtonListener();
		addButtonsToToolBar();
		disableButtons();
	}
	
	private void createButtons() {
		btnUndo = new JButton("          Undo          ");
		btnRedo = new JButton("          Redo          ");
		btnExecuteLog = new JButton("   Execute Log   ");
	}
	
	private void addButtonsToToolBar() {
		add(btnUndo);
		add(btnRedo);
		add(btnExecuteLog);
	}
	
	public void disableButtons() {
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnExecuteLog.setEnabled(false);
	}
	
	private void addRedoButtonListener() {
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandController.redoCommand();
			}
		});
	}
	
	private void addUndoButtonListener() {
		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandController.undoCommand();
			}
		});
	}
	
	public void addExecuteLogButtonListener() {
		btnExecuteLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandController.executeLog();
			}
		});
	}
	
	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnExecuteLog() {
		return btnExecuteLog;
	}
	
	public boolean isBtnRedoEnabled() {
		return btnRedo.isEnabled();
	}
	
	public boolean isBtnUndoEnabled() {
		return btnUndo.isEnabled();
	}
	
	public boolean isBtnExecuteLogEnabled() {
		return btnExecuteLog.isEnabled();
	}
	
	public void setCommandController(CommandController commandController) {
		this.commandController = commandController;
	}
	
	public void setButtonsController(ButtonsController buttonsController) {
		this.buttonsController = buttonsController;
	}
	
	@Override
	public void updateCommandsHandlerObserver(int numberOfExecutedCommands, int numberOfUnexecutedCommands) {
		buttonsController.updateObservableUndoRedoButtons(numberOfExecutedCommands, numberOfUnexecutedCommands);
	}

	@Override
	public void updateLogReaderObserver(int numberOfCommandsToBeExecuted) {
		buttonsController.updateLogReaderObserverButtons(numberOfCommandsToBeExecuted);
		
	}
	
}
