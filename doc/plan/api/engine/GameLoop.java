/*
Backend GameLoop which updates in game object states at every time step
 */

public interface GameLoop{
    /*
    When called, updates all in game objects by one time step
    * */
    public void step();
}