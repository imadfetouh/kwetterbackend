package com.imadelfetouh.authservice.model.response;

public class ResponseModel<T> {

    private T data;
    private ResponseType responseType;

    public ResponseModel() {

    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

}
