package main.logic;

import main.model.Candy;

public interface ICandyDestroyListener {
    void onCandyDestroyed(Candy candy);
}
