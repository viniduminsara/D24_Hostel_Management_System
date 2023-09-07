package com.d24.bo.factory;

import com.d24.bo.SuperBO;
import com.d24.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBOFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case HOME:
                return new HomeBOImpl();
            case SIGNUP:
                return new SignupBOImpl();
            case ACCOUNT:
                return new AccountBOImpl();
            default:
                return null;
        }
    }
}
