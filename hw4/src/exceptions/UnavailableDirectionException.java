package exceptions;


public class UnavailableDirectionException extends Exception {

    public UnavailableDirectionException(String cannot_move_in_that_direction) {
        super(cannot_move_in_that_direction);
    }

}
