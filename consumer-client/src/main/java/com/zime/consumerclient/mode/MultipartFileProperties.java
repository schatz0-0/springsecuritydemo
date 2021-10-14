package com.zime.consumerclient.mode;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Component
@Data
@NoArgsConstructor
public class MultipartFileProperties {

    private boolean enabled;

    @DataSizeUnit(DataUnit.BYTES)
    private DataSize file_size_threshold;

    private String location;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize max_file_size;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize max_request_size;

    private boolean resolve_lazily;

}
