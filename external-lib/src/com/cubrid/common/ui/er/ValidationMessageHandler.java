/*******************************************************************************
 * Copyright (c) 2014 Search Solution Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Yu Guojia
 *******************************************************************************/
package com.cubrid.common.ui.er;

/**
 * Represents interface for outputting validation error messages to some widget
 * 
 * @author Yu Guojia
 * @version 1.0 - 2013-7-10 created by Yu Guojia
 */
public interface ValidationMessageHandler {
	public void setMessageText(String text);

	/**
	 * Resets so that the validation message is no longer shown
	 */
	public void reset();
}