package gadgetStore.validations;
import gadgetStore.validations.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {

    String message() default "Пароль должен содержать минимум 8 символов, хотя бы одну букву и одну цифру";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
