/*
 * Copyright 2010 MIT Strategic Engineering Research Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package edu.mit.spacenet.gui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import edu.mit.spacenet.domain.network.edge.Burn;
import edu.mit.spacenet.simulator.event.BurnStageItem;

/**
 * Renders the burn label as its burn type icon, time, delta-v and type.
 */
public class BurnListCellRenderer extends DefaultListCellRenderer {
  private static final long serialVersionUID = 2481135321054790586L;

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax.swing.JList,
   * java.lang.Object, int, boolean, boolean)
   */
  public Component getListCellRendererComponent(JList<?> list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    if (value instanceof Burn) {
      Burn burn = (Burn) value;
      setIcon(BurnStageItem.BURN_ICON);
      setText("(" + burn.getTime() + ") " + burn.getDeltaV() + " m/s " + burn.getBurnType());
    }
    return this;
  }
}
