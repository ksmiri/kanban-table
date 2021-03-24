package fr.ks.kanban.exceptions;

public class TacheInconnueException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TacheInconnueException(String message) {
        super(message);
    }
}
