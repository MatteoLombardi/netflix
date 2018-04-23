/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.netflix.exception;

/**
 *
 * @author UTENTE
 */
public class NetflixException extends Exception {

    public NetflixException() {
    }

    public NetflixException(String message) {
        super(message);
    }

    public NetflixException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetflixException(Throwable cause) {
        super(cause);
    }

    public NetflixException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
