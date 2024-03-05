package ir.co.sadad.cheque.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CreateSignTestDisplay {

    CHAKAD_ACTIVATION("فعالسازی چک امن دیجیتال"),
    CHAKAD_DEACTIVATION("غیر فعال چک امن دیجیتال"),
    CHAKAD_UPDATE("بروز رسانی چک امن دیجیتال"),
    CHAKAD_ISSUE("صدور چک امن دیجیتال");

    private final String description;
}
