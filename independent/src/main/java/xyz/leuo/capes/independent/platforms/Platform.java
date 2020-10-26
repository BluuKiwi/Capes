package xyz.leuo.capes.independent.platforms;

public abstract class Platform {

    protected String name;
    protected Runtime runtime;

    public abstract void addEntry();

    public abstract void removeEntry();
}
