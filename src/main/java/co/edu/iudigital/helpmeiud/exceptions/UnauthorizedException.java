package co.edu.iudigital.helpmeiud.exceptions;

public class UnauthorizedException extends RestException { // 401

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(ErrorDto errorDto) {
        super(errorDto);
    }
}
