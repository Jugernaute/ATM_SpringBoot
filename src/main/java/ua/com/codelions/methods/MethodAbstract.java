package ua.com.codelions.methods;

import java.util.Map;

public abstract class MethodAbstract {

    abstract boolean multipleBills(int num);
    abstract Map plusMoneyIfTrueAndMinusIfFalse (int sum, boolean plus);
    abstract Map moneyFromOnePersonToAnother (int sum, int idCard);
}
