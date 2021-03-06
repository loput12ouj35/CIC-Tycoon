package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class Embezzlement implements Event {
	
    private long prev_mon;
	    
    public Embezzlement() {
        prev_mon = 0;
    }
	    
    @Override
    public boolean check() {
        long gm = GameContext.t.getMonth() + 1;
			
        System.out.println(gm);
			
        if ((gm % 12) == 0) {
            if (prev_mon != gm) {
                prev_mon = gm;
                return true;
            }
        }
			
        return false;
    }
	
    @Override
    public double getProb() {
        double Prob = 0.1;
	    	
        Prob += (GameContext.r.student / 300) * 0.1;
        Prob = (Prob > 0.3) ? 0.3 : Prob;
        return Prob;
    }
	
    @Override
    public void run() {
        double random = Math.random();
        double percent = (random >= 0.5) ? ((random >= 0.8) ? 0.4 : 0.2) : 0.1;
	    	
        GameContext.f.spend_Money((int) (GameContext.f.get_Money() * percent));
        GameContext.r.fame -= GameContext.r.fame * percent;
        GameContext.newMessage(
                new TimedMessageBox(
                        "An insane employee embezzled the college funds!\n" 
                                + "$ and Fame -" + percent * 100 + "%"));
    }
}
