package main.engine.events;

import main.engine.display.Sprite;

public interface IEventListener {
    void handleEvent(Event event);
    void handleEvent(Event event, Sprite sprite);
}

