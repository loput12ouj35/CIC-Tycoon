package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Lab implements Buildable {

    @Override
    public Room build(int id) {
        Room ret = new Room(FINANCE_TYPE.LAB, 1, id, Animations.lab());
		
        return ret;
    }

}
