package com.teamssd.building.room;


import com.teamssd.Animations;
import com.teamssd.Finance.FINANCE_TYPE;


public class Class2x1 implements Buildable {

    @Override
    public Room build(int id) {
        Room ret = new Room(FINANCE_TYPE.CLASS, 2, id, Animations.class2x1());
		
        ret.occ.add(id + 1);
		
        return ret;
    }

}
