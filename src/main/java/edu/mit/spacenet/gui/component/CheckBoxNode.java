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
package edu.mit.spacenet.gui.component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.mit.spacenet.domain.I_Container;
import edu.mit.spacenet.domain.element.I_Element;

/**
 * Node for the associated CheckBoxTree that represents elements as a check box, so they can easily
 * be checked or unchecked for selection.
 * 
 * @author Paul Grogan
 */
public class CheckBoxNode extends DefaultMutableTreeNode {
  private static final long serialVersionUID = -2315502537628420250L;
  private CheckBoxNodeComponent component;

  /**
   * The constructor.
   * 
   * @param checkBox the underlying check box component
   */
  public CheckBoxNode(CheckBoxNodeComponent checkBox) {
    super(checkBox);
    this.component = checkBox;
  }

  /**
   * Gets the check box component for display.
   * 
   * @return the check box component
   */
  public CheckBoxNodeComponent getCheckBox() {
    return component;
  }

  /**
   * Gets the element packaged inside the node.
   * 
   * @return the element
   */
  public I_Element getElement() {
    return component.getElement();
  }

  /**
   * Gets whether the check box is checked.
   * 
   * @return whether the check box is checked
   */
  public boolean isChecked() {
    return component.isChecked();
  }

  /**
   * Sets whether the check box is checked.
   * 
   * @param isChecked whether the check box is checked
   */
  public void setChecked(boolean isChecked) {
    component.setChecked(isChecked);
  }

  /**
   * Static method to create a new tree node hierarchy for elements and nested elements.
   * 
   * @param object the root object
   * 
   * @return the tree node
   */
  public static DefaultMutableTreeNode createNode(Object object) {
    DefaultMutableTreeNode node = null;
    if (object instanceof I_Element) {
      node = new CheckBoxNode(new CheckBoxNodeComponent((I_Element) object));
    } else {
      node = new DefaultMutableTreeNode(object);
    }

    if (object instanceof I_Container) {
      int index = 0;
      for (I_Element e : ((I_Container) object).getContents()) {
        node.insert(createNode(e), index++);
      }
    }
    return node;
  }

  /**
   * The Class CheckBoxNodeComponent.
   * 
   * @author Paul Grogan The visual component of a check box node. Includes a reference to the
   *         underlying element to be represented along with a check box and a label (for display of
   *         the element icon).
   */
  public static class CheckBoxNodeComponent extends JPanel {
    private static final long serialVersionUID = -4070444077100724836L;
    private I_Element element;
    private JCheckBox checkBox;
    private JLabel label;

    /**
     * The constructor.
     * 
     * @param element the element to represent
     * @param isChecked whether the check box should initially be checked
     */
    public CheckBoxNodeComponent(I_Element element, boolean isChecked) {
      super();
      setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
      setOpaque(false);
      checkBox = new JCheckBox("", isChecked);
      checkBox.setOpaque(false);
      this.element = element;
      label = new JLabel(getElement().getName(), getElement().getIcon(), JLabel.LEFT);
      label.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
      label.setOpaque(true);
      add(checkBox);
      add(label);
    }

    /**
     * The constructor.
     * 
     * @param element the element to represent
     */
    public CheckBoxNodeComponent(I_Element element) {
      this(element, false);
    }

    /**
     * Gets the underlying element.
     * 
     * @return the element
     */
    public I_Element getElement() {
      return element;
    }

    /**
     * Gets the check box component.
     * 
     * @return the check box component
     */
    public JCheckBox getCheckBox() {
      return checkBox;
    }

    /**
     * Gets the label component.
     * 
     * @return the label component
     */
    public JLabel getLabel() {
      return label;
    }

    /**
     * Gets whether the check box is checked.
     * 
     * @return whether the check box is checked
     */
    public boolean isChecked() {
      return checkBox.isSelected();
    }

    /**
     * Sets whether the check box is checked.
     * 
     * @param isChecked whether the check box is checked
     */
    public void setChecked(boolean isChecked) {
      checkBox.setSelected(isChecked);
    }
  }

}
