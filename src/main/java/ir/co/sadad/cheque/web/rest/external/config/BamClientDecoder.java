package ir.co.sadad.cheque.web.rest.external.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

@Log4j2
public class BamClientDecoder extends SpringDecoder {

    public BamClientDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }
    //    @Override
    //    public List<ChequebookResponseDto> decode(Response response, Type type) throws IOException, FeignException {
    //        Gson gson = new GsonBuilder().serializeNulls().create();
    //        Type collectionType = new TypeToken<List<ChequebookResponseDto>>() {}.getType();
    //        List<ChequebookResponseDto> genericResponse = gson.fromJson(response.body().asReader(), collectionType);
    //        if (genericResponse == null) {
    //            throw new IOException("Can't convert response correctly!");
    //        }
    //        return genericResponse;
    //    }
}
