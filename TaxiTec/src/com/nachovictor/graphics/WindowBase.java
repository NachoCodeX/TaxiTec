package com.nachovictor.graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class WindowBase extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel mainPanel;
	protected final int W=400,H=400;
	
	
	
	protected abstract void init();
	protected abstract void setWindow();
	protected abstract void addComponents();
	
}
