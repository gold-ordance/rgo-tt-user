package rgo.tt.user.rest.api;

import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.ServiceRequestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rgo.tt.common.exceptions.UniqueViolationException;
import rgo.tt.common.rest.api.ErrorResponse;
import rgo.tt.common.validator.ValidateException;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.tt.common.rest.api.StatusCode.ERROR;
import static rgo.tt.common.rest.api.StatusCode.INVALID_ENTITY;
import static rgo.tt.common.rest.api.StatusCode.INVALID_RQ;
import static rgo.tt.common.rest.api.utils.RestUtils.fromJson;
import static rgo.tt.common.utils.RandomUtils.randomString;

@ExtendWith(MockitoExtension.class)
class ExceptionCommonHandlerTest {

    private final ExceptionCommonHandler handler = new ExceptionCommonHandler();

    @Mock private ServiceRequestContext ctx;
    @Mock private HttpRequest req;

    @Test
    void handleException_validateException() {
        String errorMsg = randomString();
        ValidateException e = new ValidateException(errorMsg);

        HttpResponse httpResponse = handler.handleException(ctx, req, e);
        String json = httpResponse.aggregate().join().content().toStringUtf8();
        ErrorResponse response = fromJson(json, ErrorResponse.class);

        assertThat(response.getStatus().getMessage()).isEqualTo(errorMsg);
        assertThat(response.getStatus().getStatusCode()).isEqualTo(INVALID_RQ);
    }

    @Test
    void handleException_uniqueViolationException() {
        String errorMsg = randomString();
        UniqueViolationException e = new UniqueViolationException(errorMsg);

        HttpResponse httpResponse = handler.handleException(ctx, req, e);
        String json = httpResponse.aggregate().join().content().toStringUtf8();
        ErrorResponse response = fromJson(json, ErrorResponse.class);

        assertThat(response.getStatus().getMessage()).isEqualTo(errorMsg);
        assertThat(response.getStatus().getStatusCode()).isEqualTo(INVALID_ENTITY);
    }

    @Test
    void handleException_undefinedException() {
        UndefinedException e = new UndefinedException();

        HttpResponse httpResponse = handler.handleException(ctx, req, e);
        String json = httpResponse.aggregate().join().content().toStringUtf8();
        ErrorResponse response = fromJson(json, ErrorResponse.class);

        assertThat(response.getStatus().getStatusCode()).isEqualTo(ERROR);
    }

    private static class UndefinedException extends RuntimeException {
    }
}