package com.emploc.utils;

import com.emploc.validation.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.BadRequestException;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RsCheck {
    private RsCheck() {
    }

    public static void badRequest(final Boolean condition, final Supplier<CharSequence> msg) {
        check(condition, BadRequestException::new, msg);
    }

    public static void badRequest(final Boolean condition, final CharSequence msg, final Object... msgArgs) {
        badRequest(condition, () -> String.format(msg.toString(), msgArgs));
    }

    public static void notNullBadRequest(final Object value, final Supplier<CharSequence> msg) {
        badRequest(value != null, msg);
    }

    public static void notNullBadRequest(final Object value, final CharSequence msg, final Object... msgArgs) {
        badRequest(value != null, msg, msgArgs);
    }

    public static void throwBadRequest(final Supplier<CharSequence> msg) {
        badRequest(false, msg);
    }

    public static void throwBadRequest(final String msg, final Object... msgArgs) {
        throwBadRequest(() -> String.format(msg, msgArgs));
    }

    public static <E extends RuntimeException> void check(final Boolean condition, final Function<String, E> ex, final Supplier<CharSequence> msg) {
        System.out.println("check" + condition + ex.toString() + "message11"+ msg.get().toString());
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

    public static void validate(final Object bean, final Validator validator, final String path) {
        RsCheck.notNullBadRequest(bean, path + " is null");
        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean);
        if (!constraintViolations.isEmpty()) {
            final ValidationException validationReportException = new ValidationException();
            validationReportException.getViolations().addAll(constraintViolations.stream().map(violation ->
                    String.format("%s%s: %s", ".".equalsIgnoreCase(path) ? "" : path, violation.getPropertyPath(), violation.getMessage())
            ).collect(Collectors.toList()));
            throw validationReportException;
        }
    }
}
