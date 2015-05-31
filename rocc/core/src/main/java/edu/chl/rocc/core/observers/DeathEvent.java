package edu.chl.rocc.core.observers;

/**
 * An implementation of IDeathEvent.
 * Describes a death of a mortal object
 * Created by Joel on 2015-05-18.
 */
public class DeathEvent implements IDeathEvent {

    private final IMortal source;
    private final String message;
    private final Object killer;

    /**
     * Constructor
     * @param source which mortal died
     * @param message message describing
     */
    public DeathEvent(IMortal source, final String message){
        this(source, message, null);
    }

    /**
     * Constructor
     * @param source which mortal died
     * @param message message describing
     * @param killer which object killed the mortal
     */
    public DeathEvent(IMortal source, final String message, Object killer){
        this.source = source;
        this.message = message;
        this.killer = killer;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IMortal getSource() {
        return source;
    }

    @Override
    public Object getKiller() {
        return killer;
    }
}
