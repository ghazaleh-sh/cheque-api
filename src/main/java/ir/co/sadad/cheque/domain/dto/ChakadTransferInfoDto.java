package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ChakadTransferInfoDto {
    /**
     * شناسه صياد چك مورد نظر
     * require
     */
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    @NotNull(message = "chakad.error.sayad.id.is.mandatory")
    private String sayadId;

    /**
     * ليستي از دريافت كنندگان چك
     * شرح در جدول receivers
     * در صورتي كه انتقال جهت عودت چك باشد نبايد ارسال شود.
     */
    private List<ChakadReceiverDto> receivers;

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
    private List<ChakadSignerDto> signers;

//    /**
//     * نظر دارنده درباره انتقال در حالت ذينفع مشترك؛
//     * ٠ :انتقال را رد كرده است
//     * ١ :انتقال را تائيد كرده است .
//     * در حالتي كه ذينفع مشترك نباشد اين داده بايد مقدار ١ داشته
//     * باشد.
//     * در حالت ذينفع مشترك، چنانچه ارسال كننده درخواست انتقال را
//     * رد كرده باشد، به ليست دريافت كنندگان توجه نمي شود. در غير
//     * اين صورت ارسال كننده درخواست مي بايست ليست يكساني با
//     * ليست اعلامي توسط اولين ذينفع را ارسال كرده باشد .در حالتي
//     * كه ليست چند امضاء كننده ارسال مي شود، چنانچه همه تائيد
//     * كرده باشند تائيد و در غير اينصورت رد ارسال شود.
//     * require
//     */
//    @NotNull(message = "chakad.error.accept.transfer.is.mandatory")
//    private Integer acceptTransfer;

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
    private Integer giveBack;

    /**
     * اين فيلد متناسب با مبلغ چك تعيين مي شود.
     * در انقتال چک اجباری است
     *  در صورتي كه انتقال جهت عودت چك باشد نبايد ارسال شود
     */
    @NotNull(message = "chakad.error.reason.is.mandatory.in.transfer")
    private String reason;

//    /**
//     * كد شناسايي دارنده فعلي چك
//     * با توجه به identifierType اعتبار سنجي مي شود؛
//     * مشتري حقيقي: كد ملي (١٠رقم)
//     * مشتري حقوقي: شناسه ملي شركت (١١رقم)
//     * اتباع بيگانه حقيقي و حقوقي: شماره فراگير: (٨ الي١٥ رقم)
//     * require
//     */
//    @NotNull(message = "chakad.error.identifier.for.transfer.is.mandatory")
//    private String identifier;
//    /**
//     * نوع كد شناسايي دارنده فعلي چك
//     * ١ :مشتري حقيقي
//     * ٢ :مشتري حقوقي
//     * ٣ :اتباع بيگانه حقيقي
//     * ٤ :اتباع بيگانه حقوقي
//     * require
//     */
//    @NotNull(message = "chakad.error.identifier.type.for.transfer.is.mandatory")
//    private Integer identifierType;
//
//    /**
//     * براي اشخاص حقوقي مقداردهي به اين فيلد جهت كنترل
//     * امضاداران اجباري مي باشد.
//     */
//    private String account;
}
