package com.teamssd;


import com.teamssd.Finance.FINANCE_TYPE;


public abstract class FinanceUnit {
    public final FINANCE_TYPE type;
    public final int count;
	
    public FinanceUnit(FINANCE_TYPE type, int count) {
        this.type = type;
        this.count = count;
    }
}
