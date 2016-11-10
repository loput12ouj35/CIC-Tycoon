package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.ui.TimedMessageBox;


public class SupportFund implements Event {
	
    private long prev_mon;
	    
    public SupportFund() {
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
        return 0.3;
    }
	
    @Override
    public void run() {
        int income = 2 * (GameContext.r.fame / 10 + 1);
	    	
        income = (income > 2000) ? 2000 : income;
        GameContext.f.add_Money(income);
	    	
        GameContext.newMessage(
                new TimedMessageBox(
                        "The government supports your college!\n" + "$ +"
                        + income));
    }
}
