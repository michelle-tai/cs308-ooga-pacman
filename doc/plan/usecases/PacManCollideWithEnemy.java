public class PacManCollideWithEnemy {

}

class PacMann implements Sprite, Visitable {
  private Visitor myVisitor = new pacManCollisions();

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public double getY() {
    return 0;
  }

  @Override
  public void setX(double newX) {

  }

  @Override
  public void setY(double newY) {

  }

  @Override
  public void update() {

  }

  @Override
  public void collidesWith(Visitor visitor) {
    visitor.collidedWith(this);
  }

  public Visitor getVisitor(){
    return myVisitor;
  }
}

class Coin implements Sprite, Visitable {
  private Visitor myVisitor = new coinCollisions();
  public Visitor getVisitor(){
    return myVisitor;
  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public double getY() {
    return 0;
  }

  @Override
  public void setX(double newX) {

  }

  @Override
  public void setY(double newY) {

  }

  @Override
  public void update() {

  }

  @Override
  public void collidesWith(Visitor visitor) {
    visitor.collidedWith(this);
  }
}

class Ghost implements Sprite, Visitable {
  private Visitor myVisitor = new ghostCollisions();
  public Visitor getVisitor(){
    return myVisitor;
  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public double getY() {
    return 0;
  }

  @Override
  public void setX(double newX) {

  }

  @Override
  public void setY(double newY) {

  }

  @Override
  public void update() {

  }


  public void collidesWith(Visitor other) {
    other.collidedWith(this);
  }

}

class pacManCollisions implements Visitor {

  @Override
  public void collidedWith(Sprite other) {

  }

  /**
   * pacman with ghost collision behavior
   * @param ghost
   */
  public void collidedWith(Ghost ghost) {

  }

  /**
   * pacman with pacman collision behavior
   * @param pm
   */
  public void collidedWith(PacMann pm) {

  }

  /**
   * pacman with coin collision behavior
   * @param coin
   */
  public void collidedWith(Coin coin) {

  }
}

class ghostCollisions implements Visitor {

  @Override
  public void collidedWith(Sprite other) {

  }

  /**
   * ghost with ghost collision behavior
   * @param ghost
   */
  public void collidedWith(Ghost ghost) {

  }

  /**
   * ghost with pacman collision behavior
   * @param pm
   */
  public void collidedWith(PacMann pm) {

  }

  /**
   * ghost with coin collision behavior
   * @param coin
   */
  public void collidedWith(Coin coin) {

  }
}

class coinCollisions implements Visitor {

  @Override
  public void collidedWith(Sprite other) {

  }

  /**
   * coin with ghost collision behavior
   * @param ghost
   */
  public void collidedWith(Ghost ghost) {

  }

  /**
   * coin with pacman collision behavior
   * @param pm
   */
  public void collidedWith(PacMann pm) {

  }

  /**
   * coin with coin collision behavior
   * @param coin
   */
  public void collidedWith(Coin coin) {

  }
}

// so if ghost collides with pacman, pacman calls ghost.getVisitable().collidedWith(this) to have the ghost do the pacman collision behavior