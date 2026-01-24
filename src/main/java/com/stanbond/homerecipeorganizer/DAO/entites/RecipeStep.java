package com.stanbond.homerecipeorganizer.DAO.entites;

public class RecipeStep {
    private long stepId;
    private long recId;
    private int stepNumber;
    private String stepText;

    public RecipeStep() {}

    public RecipeStep(long stepId, long recId, int stepNumber, String stepText) {
        this.stepId = stepId;
        this.recId = recId;
        this.stepNumber = stepNumber;
        this.stepText = stepText;
    }

    public long getStepId() { return stepId; }
    public void setStepId(long stepId) { this.stepId = stepId; }

    public long getRecId() { return recId; }
    public void setRecId(long recId) { this.recId = recId; }

    public int getStepNumber() { return stepNumber; }
    public void setStepNumber(int stepNumber) { this.stepNumber = stepNumber; }

    public String getStepText() { return stepText; }
    public void setStepText(String stepText) { this.stepText = stepText; }
}
