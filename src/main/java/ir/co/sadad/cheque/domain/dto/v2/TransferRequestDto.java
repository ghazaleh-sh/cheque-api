package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TransferRequestDto {
    /**
     * شناسه صياد چك مورد نظر
     * require
     */
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;

    /**
     * ليستي از دريافت كنندگان چك
     * در صورتي كه انتقال جهت عودت چك باشد نبايد ارسال شود.
     */
    private List<UserDto> receivers;

    /**
     * ليست امضاكنندگان چك
     * جزييات در جدول signers
     * در صورتيكه امضاء كننده نماينده نباشد(براي موارد امضاء حقوقي
     * يا وكالتي) بايد صاحب چك هم باشد.
     * در حالتي كه دارنده فعلي حقوقي باشد و به امضاء چند امضاء دار
     * نياز باشد، ليست اطلاعات امضاء كننده ها ارسال شود.
     * در حالتي كه ليست(بيش از يك امضاء كننده) ارسال شود لازماست
     * داده sign.cms شامل امضاء تمامي اشخاصي معرفي شده در
     * ليست باشد. در اين حالت بايد صاحب چك شخص حقوقي بوده و
     * امضاء هاي ارسال شده در داده sign.cms نيز با پروفايل نماينده
     * حقوقي امضاء شده باشند.
     */
    @NotNull(message = "chakad.error.signers.is.mandatory")
    private List<UserDto> signers;

    /**
     * شرح انتقال چك
     * require
     */
    @Size(max = 250, message = "chakad.error.description.length.invalid")
    @NotNull(message = "chakad.error.description.is.mandatory")
    private String description;

    /**
     * در صورتي كه انتقال جهت عودت چك باشد اين فيلد با مقدار ١
     * تعيين مي شود. در غير اين صورت ٠ ارسال شود يا ارسال نشود.
     */
    private Boolean giveBack;

    /**
     * اين فيلد متناسب با مبلغ چك تعيين مي شود.
     * در انقتال چک اجباری است
     * در صورتي كه انتقال جهت عودت چك باشد نبايد ارسال شود
     */
    private String reason;

    /**
     * براي اشخاص حقوقي مقداردهي به اين فيلد جهت كنترل
     * امضاداران اجباري مي باشد.
     */
    private String account;
}
