/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.cubrid.common.ui.query.control.jface.text;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;


/**
 * Abstract class for a reusable information control creators.
 *
 * @since 3.3
 */
public abstract class AbstractReusableInformationControlCreator implements IInformationControlCreator, IInformationControlCreatorExtension, DisposeListener {

	private Map fInformationControls= new HashMap();

	/**
	 * Creates the control.
	 *
	 * @param parent the parent shell
	 * @return the created information control
	 */
	protected abstract IInformationControl doCreateInformationControl(Shell parent);

	/*
	 * @see org.eclipse.jface.text.IInformationControlCreator#createInformationControl(org.eclipse.swt.widgets.Shell)
	 */
	public IInformationControl createInformationControl(Shell parent) {
		IInformationControl control= (IInformationControl)fInformationControls.get(parent);
		if (control == null) {
			control= doCreateInformationControl(parent);
			control.addDisposeListener(this);
			fInformationControls.put(parent, control);
		}
		return control;
	}

	/*
	 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
	 */
	public void widgetDisposed(DisposeEvent e) {
		Composite parent= null;
		if (e.widget instanceof Shell)
			parent= ((Shell)e.widget).getParent();
		if (parent instanceof Shell)
			fInformationControls.remove(parent);
	}


	/*
	 * @see org.eclipse.jface.text.IInformationControlCreatorExtension#canReuse(org.eclipse.jface.text.IInformationControl)
	 */
	public boolean canReuse(IInformationControl control) {
		return fInformationControls.containsValue(control);
	}

	/*
	 * @see org.eclipse.jface.text.IInformationControlCreatorExtension#canReplace(org.eclipse.jface.text.IInformationControlCreator)
	 */
	public boolean canReplace(IInformationControlCreator creator) {
		return creator.getClass() == getClass();
	}
}
