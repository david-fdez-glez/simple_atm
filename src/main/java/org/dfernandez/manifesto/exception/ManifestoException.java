package org.dfernandez.manifesto.exception;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 15/12/15
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class ManifestoException extends  Exception {

    public ManifestoException() {

    }

    public ManifestoException(String message) {
        super(message);
    }

    public ManifestoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManifestoException(Throwable cause) {
        super(cause);
    }
}
