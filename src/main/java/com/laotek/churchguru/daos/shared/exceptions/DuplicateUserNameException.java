package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;

public class DuplicateUserNameException extends RuntimeException implements
	Serializable {

    private static final long serialVersionUID = 1L;

    public DuplicateUserNameException() {
    }
}
