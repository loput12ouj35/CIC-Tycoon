package com.teamssd.event;


import java.util.ArrayList;


public class EventList {
    private ArrayList<Event> events;
    
    public EventList() {
        events = new ArrayList<Event>();
		
        events.add(new Tuition());
        events.add(new ContestWin());
        events.add(new Contribution());
        events.add(new Embezzlement());
        events.add(new NobelPrize());
        events.add(new SupportFund());
        events.add(new Research());
        events.add(new LoungeEvent());
        events.add(new FameToStudent());
        events.add(new Debt());
        events.add(new ProfessorFame());
        events.add(new ProfessorResearch());
        events.add(new Bankrupcy());
    }
    
    public void newEvent(Event e) {
        events.add(e);
    }
    
    private static void run(Event e) {
        if (e.getProb() > Math.random()) {
            e.run();
        }
    }
    
    public void run() {
        for (Event e : events) {
            if (e.check()) {
                run(e);
            }
        }
    }
}
