package com.example.quotecalc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class Quote {
    private static final double LOAN_AMOUNT_LIMIT = 1000000;

    @Positive
    @Max(72)
    private int term;

    @Min(100)
    @Max(100000000)
    private float loanAmount;   //TODO convert to BigDecimal

    @PositiveOrZero
    @Max(100)
    private float interestRate;

    @PositiveOrZero
    private float residualValue;
    private double approxQuote = 0;
    private double realQuote = 0;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public float getResidualValue() {
        return residualValue;
    }

    public void setResidualValue(float residualValue) {
        this.residualValue = residualValue;
    }

    public float getMonthlyRate() {
        return this.getInterestRate() / 12;
    }

    public double getApproxQuote() {
        return approxQuote;
    }

    public void setApproxQuote(double approxQuote) {
        this.approxQuote = approxQuote;
    }

    public double getRealQuote() {
        return realQuote;
    }

    public void setRealQuote(double realQuote) {
        this.realQuote = realQuote;
    }

    public double calculateApproxQuote() {
        return ((getLoanAmount() + getResidualValue()) / 2 * getMonthlyRate() * getTerm() + (getLoanAmount() - getResidualValue())) / getTerm();
    }

    public double calculateRealQuote() {
        return (getLoanAmount() - (getResidualValue() / Math.pow((1 + getMonthlyRate()), getTerm()))) * getMonthlyRate() / (1 - Math.pow(1 + getMonthlyRate(), (getTerm() * -1)));
    }

    public boolean isQuoteAmountExceeded() {
        return this.getLoanAmount() >= LOAN_AMOUNT_LIMIT;
    }
}
