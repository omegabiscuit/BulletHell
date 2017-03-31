package main.java.engine.events;

import main.java.engine.display.Sprite;

public interface IEventListener {
    void handleEvent(Event event);
    void handleEvent(Event event, Sprite sprite);
}

