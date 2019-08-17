package com.emploc.utils;

import javax.ws.rs.BadRequestException;
import java.util.function.Function;
import java.util.function.Supplier;

public class RsCheck {

    private RsCheck() {
    }

    public static void badRequest(final Boolean condition, final Supplier<CharSequence> msg) {
        check(condition, BadRequestException::new, msg);
    }

    public static <E extends RuntimeException> void check(final Boolean condition, final Function<String, E> ex, final Supplier<CharSequence> msg) {
        if (ex == null) {
            throw new NullPointerException("ex is null");
        }
        if (msg == null) {
            throw new NullPointerException("msg is null");
        }
        if (!condition) {
            throw ex.apply(msg.get().toString());
        }
    }

}
