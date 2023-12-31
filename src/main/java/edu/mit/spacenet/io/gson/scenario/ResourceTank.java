package edu.mit.spacenet.io.gson.scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import edu.mit.spacenet.data.ElementPreview;
import edu.mit.spacenet.domain.ClassOfSupply;
import edu.mit.spacenet.domain.Environment;
import edu.mit.spacenet.domain.element.ElementIcon;
import edu.mit.spacenet.domain.element.ElementType;
import edu.mit.spacenet.domain.element.I_State;
import edu.mit.spacenet.domain.resource.I_Resource;

public class ResourceTank extends Element {
  protected UUID resource;
  protected Double maxAmount;
  protected Double amount;

  public static ResourceTank createFrom(edu.mit.spacenet.domain.element.ResourceTank element,
      Context context) {
    ResourceTank e = new ResourceTank();
    e.id = UUID.randomUUID();
    context.put(element, e.id, e);
    e.templateId = context.getElementTemplate(element.getTid());
    ResourceTank template = (ResourceTank) context.getJsonObject(e.templateId);
    if (template == null) {
      e.name = element.getName();
      e.description = element.getDescription();
      e.accommodationMass = element.getAccommodationMass();
      e.mass = element.getMass();
      e.volume = element.getVolume();
      e.classOfSupply = element.getClassOfSupply().getId();
      e.environment = element.getEnvironment().getName();
      if (element.getIconType() != element.getElementType().getIconType()) {
        e.icon = element.getIconType().getName();
      }
      e.states = State.createFrom(element.getStates(), context);
      if (element.getCurrentState() != null) {
        e.currentStateIndex =
            new ArrayList<I_State>(element.getStates()).indexOf(element.getCurrentState());
      }
      e.parts = Part.createFrom(element.getParts(), context);

      e.resource = context.getJsonIdFromJavaObject(element.getResource());
      e.maxAmount = element.getMaxAmount();
      e.amount = element.getAmount();
    } else {
      if (!template.name.equals(element.getName())) {
        e.name = element.getName();
      }
      if (!template.description.equals(element.getDescription())) {
        e.description = element.getDescription();
      }
      if (!template.accommodationMass.equals(element.getAccommodationMass())) {
        e.accommodationMass = element.getAccommodationMass();
      }
      if (!template.mass.equals(element.getMass())) {
        e.mass = element.getMass();
      }
      if (!template.volume.equals(element.getVolume())) {
        e.volume = element.getVolume();
      }
      if (!template.classOfSupply.equals(element.getClassOfSupply().getId())) {
        e.classOfSupply = element.getClassOfSupply().getId();
      }
      if (!template.environment.equals(element.getEnvironment().getName())) {
        e.environment = element.getEnvironment().getName();
      }
      if ((template.icon == null && element.getIconType() != element.getElementType().getIconType())
          || (template.icon != null && !template.icon.equals(element.getIconType().getName()))) {
        e.icon = element.getIconType().getName();
      }
      List<State> states = State.createFrom(element.getStates(), context);
      if (!template.states.equals(states)) {
        e.states = states;
      }
      if (e.states != null || element.getCurrentState() != null) {
        List<I_State> eStates = new ArrayList<I_State>(element.getStates());
        if (!template.currentStateIndex.equals(eStates.indexOf(element.getCurrentState()))) {
          e.currentStateIndex = eStates.indexOf(element.getCurrentState());
        }
      }
      List<Part> parts = Part.createFrom(element.getParts(), context);
      if (!template.parts.equals(parts)) {
        e.parts = parts;
      }
      if (!template.resource.equals(context.getJsonIdFromJavaObject(element.getResource()))) {
        e.resource = context.getJsonIdFromJavaObject(element.getResource());
      }
      if (!template.maxAmount.equals(element.getMaxAmount())) {
        e.maxAmount = element.getMaxAmount();
      }
      if (!template.amount.equals(element.getAmount())) {
        e.amount = element.getAmount();
      }
    }
    return e;
  }

  @Override
  public edu.mit.spacenet.domain.element.ResourceTank toSpaceNet(Context context) {
    edu.mit.spacenet.domain.element.ResourceTank e =
        new edu.mit.spacenet.domain.element.ResourceTank();
    context.put(e, id, this);
    e.setUid(context.getJavaId(id));
    e.setTid(templateId == null ? context.getJavaId(id) : context.getJavaId(templateId));
    ResourceTank template = (ResourceTank) context.getJsonObject(templateId);
    e.setName(name == null ? template.name : name);
    e.setDescription(description == null ? template.description : description);
    e.setAccommodationMass(
        accommodationMass == null ? template.accommodationMass : accommodationMass);
    e.setMass(mass == null ? template.mass : mass);
    e.setVolume(volume == null ? template.volume : volume);
    e.setClassOfSupply(
        ClassOfSupply.getInstance(classOfSupply == null ? template.classOfSupply : classOfSupply));
    e.setEnvironment(
        Environment.getInstance(environment == null ? template.environment : environment));
    e.setIconType(ElementIcon.getInstance(icon == null && template != null ? template.icon : icon));
    List<State> _states = states;
    if (states == null) {
      _states = State.clone(template.states);
    }
    e.setStates(State.toSpaceNet(e, states == null ? _states : states, context));
    if (currentStateIndex == null) {
      if (template != null && template.currentStateIndex != null) {
        e.setCurrentState(
            (I_State) context.getJavaObjectFromJsonId(_states.get(template.currentStateIndex).id));
      }
    } else {
      e.setCurrentState(
          (I_State) context.getJavaObjectFromJsonId(_states.get(currentStateIndex).id));
    }
    e.setParts(Part.toSpaceNet(parts == null ? template.parts : parts, context));

    e.setResource((I_Resource) context
        .getJavaObjectFromJsonId(resource == null ? template.resource : resource));
    e.setMaxAmount(maxAmount == null ? template.maxAmount : maxAmount);
    e.setAmount(amount == null ? template.amount : amount);
    return e;
  }

  @Override
  public ElementPreview getPreview(Context context) {
    return new ElementPreview(context.getJavaId(id), name, ElementType.RESOURCE_TANK,
        ElementIcon.getInstance(icon));
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ResourceTank)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    final ResourceTank other = (ResourceTank) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).append(resource, other.resource)
        .append(maxAmount, other.maxAmount).append(amount, other.amount).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 31).appendSuper(super.hashCode()).append(resource)
        .append(maxAmount).append(amount).toHashCode();
  }

  @Override
  public ResourceTank clone() {
    ResourceTank e = new ResourceTank();
    e.id = UUID.randomUUID();
    e.templateId = templateId;
    e.name = name;
    e.description = description;
    e.accommodationMass = accommodationMass;
    e.mass = mass;
    e.volume = volume;
    e.classOfSupply = classOfSupply;
    e.environment = environment;
    e.states = State.clone(states);
    e.currentStateIndex = currentStateIndex;
    e.parts = Part.clone(parts);
    e.icon = icon;
    e.resource = resource;
    e.maxAmount = maxAmount;
    e.amount = amount;
    return e;
  }
}
