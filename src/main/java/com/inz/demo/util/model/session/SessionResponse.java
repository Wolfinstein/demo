package com.inz.demo.util.model.session;

import com.inz.demo.util.model.response.OperationResponse;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class SessionResponse extends OperationResponse {

    private SessionItem item;

    public SessionResponse() {
    }

    public SessionResponse(SessionItem item) {
        this.item = item;
    }

    public SessionItem getItem() {
        return item;
    }

    public void setItem(SessionItem item) {
        this.item = item;
    }
}
