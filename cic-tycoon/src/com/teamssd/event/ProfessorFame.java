package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.Resource.Professor;


public class ProfessorFame implements Event {
    private long prev_mon;
	
    public ProfessorFame() {
        prev_mon = 0;
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
        Professor[] profs = GameContext.r.professors();
		
        for (Professor p : profs) {
            if (p.hired) {
                GameContext.r.fame += p.p.getRising_fame();		
            }
        }
    }

}
