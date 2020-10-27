package xyz.leuo.capes.independent.platforms;

public abstract class Platform {

    protected String name;
    protected Runtime runtime;

    public abstract boolean addEntry();

    public abstract boolean removeEntry();
}
