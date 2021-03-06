package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.Finance.FINANCE_TYPE;


public class FameToStudent implements Event {
    final static int StartStudent = 150;

    private long prev_mon;
    private int capacity;
	
    public FameToStudent() {
        prev_mon = 0;
        capacity = StartStudent;
    }

    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
		
        System.out.println(gm);
		
        if ((gm % 6) == 0) {
            if (prev_mon != gm) {
                prev_mon = gm;
                return true;
            }
        }
		
        return false;
    }

    @Override
    public double getProb() {
        return 1.0;
    }

    @Override
    public void run() {
        int student = GameContext.r.student;
		
        capacity = StartStudent + GameContext.b.count(FINANCE_TYPE.CLASS) * 40;
        student = StartStudent + GameContext.r.fame / 10;
        GameContext.r.student = (student > capacity) ? capacity : student;
    }

}
