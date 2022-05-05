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
package edu.mit.spacenet.gui.command;

import javax.swing.JOptionPane;

import edu.mit.spacenet.gui.SpaceNetFrame;

/**
 * The command to close a scenario.
 * 
 * @author Paul Grogan
 */
public class CloseScenarioCommand {
  private SpaceNetFrame spaceNetFrame;

  /**
   * The constructor.
   * 
   * @param spaceNetFrame the SpaceNet frame component.
   */
  public CloseScenarioCommand(SpaceNetFrame spaceNetFrame) {
    this.spaceNetFrame = spaceNetFrame;
  }

  /**
   * Execute.
   */
  public void execute() {
    int answer = JOptionPane.showOptionDialog(spaceNetFrame,
        "Save " + spaceNetFrame.getScenarioPanel().getScenario().getName() + " before closing?",
        "Close Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
        null);
    if (answer == JOptionPane.YES_OPTION) {
      SaveScenarioCommand command = new SaveScenarioCommand(spaceNetFrame);
      command.execute();
    }
    if (answer != JOptionPane.CANCEL_OPTION)
      spaceNetFrame.closeScenario();
  }
}
