package com.some.game1.Entities.Technical.Texts;

public class Event {
    private String name;
    private int id;
    private String[] answers;
    private String text;
    private String addingName = "";
    private int[] modClass;//0 - estates, 1 - army
    private int[] modId;

    public Event(String name, int id, String[] answers, String text, int[] modClass, int[] modId) {
        this.name = name;
        this.id = id;
        this.answers = answers;
        this.text = text;
        this.modClass = modClass;
        this.modId = modId;
    }

    public void setModif(int clas, int id, int choice){
        modClass[choice] = clas;
        modId[choice] = id;
    }

    public String getName() {
        String res = name + addingName;
        addingName = "";
        return res;
    }

    public int getId() {
        return id;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }

    public void setAddingName(String addingName) {
        this.addingName = addingName;
    }

    public Event clone(){
        return new Event(name, id, answers, text, modClass, modId);
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setText(String text) {
        this.text = text;
    }
}
