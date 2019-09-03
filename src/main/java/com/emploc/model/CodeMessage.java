package com.emploc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.StringJoiner;

public class CodeMessage implements Serializable {

    private static final long serialVersionUID = -696899611129612908L;

    @JsonProperty("code")
    private Integer code = null;

    @JsonProperty("message")
    private String message = null;

    public CodeMessage code(Integer code) {
        this.code = code;
        return this;
    }

    @JsonProperty("code")
    @ApiModelProperty(value = "Success / Error code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CodeMessage message(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("message")
    @ApiModelProperty(value = "Detailed error message.")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CodeMessage)) {
            return false;
        }

        final CodeMessage that = (CodeMessage) obj;

        return new EqualsBuilder()
                .append(code, that.code)
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(message)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CodeMessage.class.getCanonicalName() + "[", "]")
                .add("code=" + code)
                .add("message='" + message + "'")
                .toString();
    }
}
