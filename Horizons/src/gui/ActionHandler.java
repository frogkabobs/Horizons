package gui;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.Action;
import javax.swing.JComponent;

import misc.Util;

public class ActionHandler {
	JComponent component;
	ArrayList<Action> actions;
	
	public ActionHandler(JComponent com) {
		component = com;
		actions = new ArrayList<>();
	}
	public void disable() {
		for(Action a : actions) a.setEnabled(false);
	}

	public void enable() {
		for(Action a : actions) a.setEnabled(true);
	}
	
	public void addKeyBind(int i, String s, Consumer<ActionEvent> c, boolean b) {
		Action a = Util.actionMaker(c);
		actions.add(a);
		Util.keybind(component, i, s, a, b);
	}
	
}
