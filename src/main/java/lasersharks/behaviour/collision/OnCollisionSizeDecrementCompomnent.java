package lasersharks.behaviour.collision;

import lasersharks.interfaces.Displayable;

class OnCollisionSizeDecrementCompomnent implements CollisionComponent {
  private Displayable me;
  private CollisionComponent next;

  public OnCollisionSizeDecrementCompomnent(Displayable me, CollisionComponent next) {
    this.me = me;
    this.next = next;
  }

  @Override
  public void handleCollision(Displayable other) {
    me.onCollisionSizeDecrement(other.getOnCollisionSizeDecrement()); // ( eerste regel code.)
    next.handleCollision(other); // ga door naar het volgende stuk.
  }

}