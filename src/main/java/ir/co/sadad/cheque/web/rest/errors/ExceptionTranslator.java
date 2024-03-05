package ir.co.sadad.cheque.web.rest.errors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabSubErrors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.violations.ConstraintViolationProblem;
import tech.jhipster.config.JHipsterConstants;
import tech.jhipster.web.util.HeaderUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807).
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    private final MessageSource messageSource;

    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String MESSAGE_KEY = "errorSummary";
    private static final String PATH_KEY = "path";
    private static final String VIOLATIONS_KEY = "violations";
    private static final String TIME_STAMP = "timestamp";
    private static final String EXTRA_DATA = "extraData";
    private static final String CODE = "errorCode";
    private static final String ERROR_CAUSES = "errorCauses";
    private static final String MESSAGE = "message";
    private static final String LOCALIZED_MESSAGE = "localizedMessage";
    private static final String CHAKAD_CODE = "code";
    private static final String SUB_ERRORS = "subErrors";
    private static final String[] VALIDATION_ERROR = new String[]{"VALIDATION_ERROR"};
    private static final Locale LOCALE_FA = new Locale("fa");
    private static final Locale LOCALE_EN = new Locale("en");

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Value("${spring.application.name}")
    private String applicationName;

    private final Environment env;

    //        public ExceptionTranslator(Environment env) {
    //            this.env = env;
    //        }

    /**
     * Post-process the Problem payload to add the message key for the front-end if needed.
     */
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return null;
        }
        Problem problem = entity.getBody();

        //        || problem instanceof DefaultProblem
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }

        HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
        String requestUri = nativeRequest != null ? nativeRequest.getRequestURI() : StringUtils.EMPTY;
        ProblemBuilder builder = Problem
            .builder();

        if (requestUri.contains("chakad") || requestUri.contains("sayad") || requestUri.contains("shahab")) {
            String timestamp;
//            if (problem.getParameters().get(TIME_STAMP) != null)
//                timestamp = problem.getParameters().get(TIME_STAMP).toString();
//            else
            timestamp = formatter.format(Instant.now().atZone(ZoneId.of("UTC")));
//TODO:error 500 should be separated
            builder
                .withStatus(problem.getStatus())
                .with(TIME_STAMP, timestamp)
                .with(CHAKAD_CODE, Optional.ofNullable(problem.getParameters().get(CHAKAD_CODE)).orElse("CHB" + Optional.ofNullable(problem.getStatus()).map(s -> String.valueOf(s.getStatusCode())).orElse("")))
                .with(MESSAGE, Optional.ofNullable(problem.getParameters().get(MESSAGE))
                    .orElseGet(() -> Objects.requireNonNull(problem.getTitle()).contains(INTERNAL_SERVER_ERROR) ? "INTERNAL_SERVER_ERROR" : problem.getTitle()))
                .with(LOCALIZED_MESSAGE, Optional.ofNullable(problem.getParameters().get(LOCALIZED_MESSAGE))
                    .orElseGet(() -> problem.getTitle().contains(INTERNAL_SERVER_ERROR) ? messageSource.getMessage("INTERNAL_SERVER_ERROR", null, LOCALE_FA) : problem.getTitle()))
                .with(SUB_ERRORS, problem.getParameters().get(SUB_ERRORS) == null ? new ArrayList<>() : problem.getParameters().get(SUB_ERRORS))
//                .withDetail(problem.getDetail())
                .with(EXTRA_DATA, problem.getParameters().get(EXTRA_DATA));
        }

        else {
                //            .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorConstants.DEFAULT_TYPE : problem.getType())
            builder
                .withStatus(problem.getStatus())
                .withTitle(problem.getTitle())
                .with(PATH_KEY, requestUri);

            if (problem instanceof ConstraintViolationProblem) {
                builder
                    .with(VIOLATIONS_KEY, ((ConstraintViolationProblem) problem).getViolations())
                    .with(MESSAGE_KEY, ErrorConstants.ERR_VALIDATION);
            } else {
                builder.withCause(((DefaultProblem) problem).getCause()).withDetail(problem.getDetail()).withInstance(problem.getInstance());
                problem.getParameters().forEach(builder::with);
                //            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                //                builder.with(MESSAGE_KEY, "error.http." + problem.getStatus().getStatusCode());
                //            }
            }
        }
        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();

        ProblemBuilder builder = Problem
            .builder();

        if (((ServletWebRequest) request).getRequest().getRequestURI().contains("chakad")) {
            List<DetailErrorDto> fieldErrors = result
                .getFieldErrors()
                .stream()
                .map(f -> DetailErrorDto.builder()
                    .code(f.getField())
                    .message(StringUtils.isNotBlank(f.getDefaultMessage())
                        ? messageSource.getMessage(f.getDefaultMessage(), null, LOCALE_FA)
                        : f.getCode())
                    .localizedMessage(StringUtils.isNotBlank(f.getDefaultMessage())
                        ? messageSource.getMessage(f.getDefaultMessage(), null, LOCALE_EN)
                        : f.getCode())
                    .build())
                .collect(Collectors.toList());

            builder
                .withStatus(defaultConstraintViolationStatus())
                .with(CHAKAD_CODE, "CHB400")
                .with(MESSAGE, messageSource.getMessage("CHB400", null, LOCALE_EN))
                .with(LOCALIZED_MESSAGE, messageSource.getMessage("CHB400", null, LOCALE_FA))
                .with(SUB_ERRORS, fieldErrors);
        } else {
            List<FieldErrorVM> fieldErrors = result
                .getFieldErrors()
                .stream()
                .map(f ->
                    new FieldErrorVM(
                        f.getObjectName().replaceFirst("DTO$", ""),
                        f.getField(),
                        StringUtils.isNotBlank(f.getDefaultMessage())
                            ? messageSource.getMessage(f.getDefaultMessage(), null, LOCALE_FA)
                            : f.getCode()
                    )
                )
                .collect(Collectors.toList());
            // these below fields synced with ChequeException
            builder
                .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with(MESSAGE_KEY, ErrorConstants.ERR_VALIDATION)
                .with(FIELD_ERRORS_KEY, fieldErrors)
                .with(CODE, "E0000499")
                .with(ERROR_CAUSES, VALIDATION_ERROR);
//            .build();
        }
        return create(ex, builder.build(), request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
        return create(
            ex,
            request,
            HeaderUtil.createFailureAlert(applicationName, true, ex.getEntityName(), ex.getErrorKey(), ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<Problem> generalFeignException(FeignGeneralException ex, NativeWebRequest request) {
        Problem problem = Problem
            .builder()
            .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
            .withTitle(ex.getTitle())
            .withStatus(ex.getStatus())
            .with(CODE, ex.getCode())
            .withDetail(ex.getDetail())
            .with(TIME_STAMP, ex.getTimestamp())
            .with(EXTRA_DATA, ex.getExtraData())
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> shahabException(ShahabException ex, NativeWebRequest request) {
        List<DetailErrorDto> fieldErrors = new ArrayList<>();
        if(ex.getExtraData() != null) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            Type listType = new TypeToken<List<ShahabSubErrors>>() {}.getType();
            List<ShahabSubErrors> subErrors = gson.fromJson(ex.getExtraData(), listType);

            subErrors.forEach(sub -> {
                fieldErrors.add(
                    DetailErrorDto.builder()
                        .code(sub.getName())
                        .message(sub.getDetail())
                        .build());
            });
        }

        Problem problem = Problem
            .builder()
            .withStatus(ex.getStatus())
            .with(TIME_STAMP, ex.getTimestamp())
            .with(CHAKAD_CODE, "CHB".concat(ex.getCode()))
            .with(MESSAGE, ex.getMessage())
            .with(LOCALIZED_MESSAGE, ex.getMessage())// != null ? messageSource.getMessage(ex.getMessage(), null, request.getLocale()) : null)
            .with(SUB_ERRORS, fieldErrors)
            .with(EXTRA_DATA, ex.getExtraData())
//            .withDetail(ex.getDetail())
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> generalException(GeneralException ex, NativeWebRequest request) {
        String localizedMessage;
        String message;
        try {
            localizedMessage = messageSource.getMessage(ex.getMessage(), null, LOCALE_FA);
        } catch (Exception e) {
            localizedMessage = messageSource.getMessage("server.external.exception.not.translate", null, LOCALE_FA);
        }

        try {
            message = messageSource.getMessage(ex.getMessage(), null, LOCALE_EN);
        } catch (Exception e) {
            message = messageSource.getMessage("server.external.exception.not.translate", null, LOCALE_EN);
        }

        List<DetailErrorDto> fieldErrors = new ArrayList<>();
        if (ex instanceof ChakadClientException) {
            if (((ChakadClientException) ex).extraData != null) {
                ((ChakadClientException) ex).extraData.forEach(sub -> {
                    fieldErrors.add(
                        DetailErrorDto.builder()
                            .code(sub.getCode())
                            .message(messageSource.getMessage("server.external.exception." + sub.getCode(), null, LOCALE_EN))
                            .localizedMessage(messageSource.getMessage("server.external.exception." + sub.getCode(), null, LOCALE_FA))
                            .build());
                });
            }
        } else if (ex instanceof BaambaseClientException) {
            if (((BaambaseClientException) ex).extraData != null) {
                ((BaambaseClientException) ex).extraData.forEach(sub -> {
                    fieldErrors.add(
                        DetailErrorDto.builder()
                            .code(sub.getCode())
                            .message("PARAM_INVALID")
                            .localizedMessage(sub.getMessage())
                            .build());
                });
            }
        }

        Problem problem = Problem
            .builder()
            .withStatus(ex.getStatusType())
            .with(TIME_STAMP, ex.getTimestamp())
            .with(CHAKAD_CODE, ex.getCode())
            .with(MESSAGE, message)
            .with(LOCALIZED_MESSAGE, localizedMessage)
            .with(SUB_ERRORS, fieldErrors)
//            .with(EXTRA_DATA, ex.getExtraData())
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> sayadInquiresHandler(SayadInquiresClientException ex, NativeWebRequest request) {

        List<DetailErrorDto> fieldErrors=new ArrayList<>();
            ex.getMessages().forEach((key, value)->{
                fieldErrors.add(
                DetailErrorDto.builder()
                    .code(key)
                    .message(messageSource.getMessage(value, null, LOCALE_EN))
                    .localizedMessage(messageSource.getMessage(value, null, LOCALE_FA))
                    .build());
            });

        Problem problem = Problem
            .builder()
            .with(CHAKAD_CODE, "CHB400")
            .with(MESSAGE, messageSource.getMessage("CHB400", null, LOCALE_EN))
            .with(LOCALIZED_MESSAGE, messageSource.getMessage("CHB400", null, LOCALE_FA))
            .withStatus(ex.getStatusType())
            .with(TIME_STAMP, ex.getTimestamp())
            .with(SUB_ERRORS, fieldErrors)
//            .with(EXTRA_DATA, ex.getExtraData())
            .build();
        return create(ex, problem, request);
    }

    @Override
    public ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            if (throwable instanceof HttpMessageConversionException) {
                return Problem
                    .builder()
                    .withType(type)
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status)
                    .withDetail("Unable to convert http message")
                    .withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
                    );
            }
            if (containsPackageName(throwable.getMessage())) {
                return Problem
                    .builder()
                    .withType(type)
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status)
                    .withDetail("Unexpected runtime exception")
                    .withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
                    );
            }
        }

        return Problem
            .builder()
            .withType(type)
            .withTitle(status.getReasonPhrase())
            .withStatus(status)
            .withDetail(throwable.getMessage())
            .withCause(
                Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
            );
    }

    private boolean containsPackageName(String message) {
        // This list is for sure not complete
        return StringUtils.containsAny(message, "org.", "java.", "net.", "javax.", "com.", "io.", "de.", "ir.co.sadad.cheque");
    }
}
