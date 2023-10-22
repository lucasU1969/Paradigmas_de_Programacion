package nemo;

public abstract class DepthOperator {
    public abstract Depth moveUpwards();
    public abstract Depth moveDownwards();
    public abstract void launchCapsule(Nemo submarine);
    public abstract int getDepth();
}
